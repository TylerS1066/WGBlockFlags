package net.tylers1066.flags;

import com.sk89q.worldguard.protection.flags.SetFlag;
import net.tylers1066.flags.helper.BlockMaterialFlag;
import org.bukkit.Material;

public class Flags {
    public final static SetFlag<Material> ALLOW_BLOCKS = new SetFlag<>("allow-blocks", new BlockMaterialFlag(null));
    public final static SetFlag<Material> DENY_BLOCKS = new SetFlag<>("allow-blocks", new BlockMaterialFlag(null));

    public final static SetFlag<Material> ALLOW_BLOCK_PLACE = new SetFlag<>("allow-block-place", new BlockMaterialFlag(null));
    public final static SetFlag<Material> DENY_BLOCK_PLACE = new SetFlag<>("deny-block-place", new BlockMaterialFlag(null));

    public final static SetFlag<Material> ALLOW_BLOCK_BREAK = new SetFlag<>("allow-block-break", new BlockMaterialFlag(null));
    public final static SetFlag<Material> DENY_BLOCK_BREAK = new SetFlag<>("deny-block-break", new BlockMaterialFlag(null));
}
