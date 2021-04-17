package net.tylers1066.flags.helper;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.FlagContext;
import com.sk89q.worldguard.protection.flags.InvalidFlagFormat;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

public class MaterialFlag extends Flag<Material> {

    public MaterialFlag(String name) {
        super(name);
    }

    @Override
    public Material parseInput(FlagContext flagContext) throws InvalidFlagFormat {
        Material material = Material.matchMaterial(flagContext.getUserInput());
        if (material != null)
            return material;
        else
            throw new InvalidFlagFormat("Unable to find the material! Please refer to https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html for valid ids");
    }

    @Override
    public Material unmarshal(@Nullable Object o) {
        if(o == null)
            return null;

        return Material.matchMaterial(o.toString());
    }

    @Override
    public Object marshal(Material material) {
        return material.name();
    }
}
