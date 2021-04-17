package net.tylers1066.flags.helper;

import com.sk89q.worldguard.protection.flags.FlagContext;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import org.bukkit.Material;

public class BlockMaterialFlag extends MaterialFlag {

    public BlockMaterialFlag(String name) {
        super(name);
    }

    @Override
    public Material parseInput(FlagContext flagContext) throws InvalidFlagFormat {
        Material material = super.parseInput(flagContext);
        if(!material.isBlock())
            throw new InvalidFlagFormat("This material is not a placeable block!");

        return material;
    }
}
