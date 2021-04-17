package net.tylers1066.flags;

import com.sk89q.worldguard.protection.flags.SetFlag;
import net.tylers1066.flags.helper.BlockMaterialFlag;
import org.bukkit.Material;

public class Flags {
    public final static SetFlag<Material> ALLOW_BLOCKS = new SetFlag<Material>("allow-blocks", new BlockMaterialFlag(null));
    public final static SetFlag<Material> DENY_BLOCKS = new SetFlag<Material>("allow-blocks", new BlockMaterialFlag(null));

    public final static SetFlag<Material> ALLOW_BLOCK_PLACE = new SetFlag<Material>("allow-block-place", new BlockMaterialFlag(null));
    public final static SetFlag<Material> DENY_BLOCK_PLACE = new SetFlag<Material>("deny-block-place", new BlockMaterialFlag(null));

    public final static SetFlag<Material> ALLOW_BLOCK_BREAK = new SetFlag<Material>("allow-block-break", new BlockMaterialFlag(null));
    public final static SetFlag<Material> DENY_BLOCK_BREAK = new SetFlag<Material>("deny-block-break", new BlockMaterialFlag(null));
}
