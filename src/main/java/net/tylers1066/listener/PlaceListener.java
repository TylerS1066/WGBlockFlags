package net.tylers1066.listener;

import com.sk89q.worldguard.bukkit.event.block.PlaceBlockEvent;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import net.tylers1066.flags.Flags;
import net.tylers1066.utils.WGUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Set;

public class PlaceListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(PlaceBlockEvent e) {
        Object rootCause = e.getCause().getRootCause();

        if(!(rootCause instanceof Player))
            return;

        Player cause = (Player) rootCause;
        for(Block b : e.getBlocks()) {
            if(!WGUtils.canBuild(cause, b)) {
                continue;
            }
            Material type = b.getType();
            if(type == Material.AIR)
                type = e.getEffectiveMaterial();

            ApplicableRegionSet regions = WGUtils.getApplicableRegions(b.getLocation());

            // Check allow-blocks
            Set<Material> materials = WGUtils.queryValue(cause, cause.getWorld(), regions.getRegions(), Flags.ALLOW_BLOCKS);
            if (materials != null && (materials.contains(type) || materials.contains(Material.AIR))) {
                if(e.getResult() == Event.Result.DEFAULT) {
                    e.setResult(Event.Result.ALLOW);
                    return;
                }
            }

            // Check deny-blocks
            materials = WGUtils.queryValue(cause, cause.getWorld(), regions.getRegions(), Flags.DENY_BLOCKS);
            if(materials != null && (materials.contains(type) || materials.contains(Material.AIR))) {
                e.setResult(Event.Result.DENY);
                return;
            }

            // Check allow-block-place
            materials = WGUtils.queryValue(cause, cause.getWorld(), regions.getRegions(), Flags.ALLOW_BLOCK_PLACE);
            if(materials != null && materials.contains(type)) {
                e.setResult(Event.Result.ALLOW);
                return;
            }

            // Check deny-block-place
            materials = WGUtils.queryValue(cause, cause.getWorld(), regions.getRegions(), Flags.DENY_BLOCK_PLACE);
            if(materials != null && (materials.contains(type) || materials.contains(Material.AIR))) {
                e.setResult(Event.Result.DENY);
                return;
            }
        }
    }
}
