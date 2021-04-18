package net.tylers1066;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.SetFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import net.tylers1066.flags.Flags;
import net.tylers1066.listener.BreakListener;
import net.tylers1066.listener.PlaceListener;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

public final class WGBlockFlags extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlaceListener(), this);
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
    }

    @Override
    public void onLoad() {
        FlagRegistry flagRegistry = WGBukkit.getPlugin().getFlagRegistry();
        registerFlag(flagRegistry, Flags.ALLOW_BLOCKS);
        registerFlag(flagRegistry, Flags.ALLOW_BLOCK_PLACE);
        registerFlag(flagRegistry, Flags.ALLOW_BLOCK_BREAK);
        registerFlag(flagRegistry, Flags.DENY_BLOCKS);
        registerFlag(flagRegistry, Flags.DENY_BLOCK_PLACE);
        registerFlag(flagRegistry, Flags.DENY_BLOCK_BREAK);
    }

    private void registerFlag(FlagRegistry registry, SetFlag<Material> flag) {
        try {
            registry.register(flag);
        }
        catch (FlagConflictException e) {
            getLogger().severe("Failed to register flag '" + flag.getName() + "', already registered as '" + registry.get(flag.getName()).getClass().getName() + "'");
        }
    }
}
