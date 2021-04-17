package net.tylers1066;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import net.tylers1066.flags.Flags;
import net.tylers1066.listener.BreakListener;
import net.tylers1066.listener.PlaceListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class WGBlockFlags extends JavaPlugin {
    @Override
    public void onEnable() {
        FlagRegistry flagRegistry = WorldGuard.getInstance().getFlagRegistry();
        flagRegistry.register(Flags.ALLOW_BLOCKS);
        flagRegistry.register(Flags.ALLOW_BLOCK_PLACE);
        flagRegistry.register(Flags.ALLOW_BLOCK_BREAK);
        flagRegistry.register(Flags.DENY_BLOCKS);
        flagRegistry.register(Flags.DENY_BLOCK_PLACE);
        flagRegistry.register(Flags.DENY_BLOCK_BREAK);

        getServer().getPluginManager().registerEvents(new PlaceListener(), this);
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
    }
}
