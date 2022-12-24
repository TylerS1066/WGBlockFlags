package net.tylers1066.utils;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.FlagValueCalculator;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import com.sk89q.worldguard.protection.util.NormativeOrders;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WGUtils {
    public static ApplicableRegionSet getApplicableRegions(Location loc) {
        return WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery().getApplicableRegions(BukkitAdapter.adapt(loc));
    }

    public static <T> T queryValue(Player player, World world, Set<ProtectedRegion> regions, Flag<T> flag) {
        return createFlagValueCalculator(player, world, regions, flag).queryValue(wrapPlayer(player), flag);
    }

    public static boolean canBuild(Player player, Block block) {
        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        LocalPlayer localPlayer = null;
        if (player != null) {
            localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        }
        StateFlag.State state = query.queryState(BukkitAdapter.adapt(block.getLocation()), localPlayer, Flags.BUILD);
        if (state == null)
            return true;
        switch (state) {
            case DENY:
                return false;
            case ALLOW:
            default:
                return true;
        }
    }

    private static LocalPlayer wrapPlayer(Player p) {
        return WorldGuardPlugin.inst().wrapPlayer(p);
    }

    private static <T> FlagValueCalculator createFlagValueCalculator(Player player, World world, @NotNull Set<ProtectedRegion> regions, Flag<T> flag) {
        List<ProtectedRegion> checkForRegions = new ArrayList<>();
        for(ProtectedRegion region : regions) {
            if (!hasBypass(player, world, region, flag))
                checkForRegions.add(region);
        }

        NormativeOrders.sort(checkForRegions);

        ProtectedRegion global = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world)).getRegion(ProtectedRegion.GLOBAL_REGION);
        if (global != null) {
            if (hasBypass(player, world, global, flag)) //Lets do like this for now to reduce dublicated code
                global = null;
        }

        return new FlagValueCalculator(checkForRegions, global);
    }

    private static boolean hasBypass(@NotNull Player player, @NotNull World world, @NotNull ProtectedRegion region, @NotNull Flag<?> flag) {
        return player.hasPermission("worldguard.region.bypass." + world.getName() + "." + region.getId() + "." + flag.getName());
    }
}
