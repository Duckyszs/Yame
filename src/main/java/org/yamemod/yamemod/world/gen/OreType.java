package org.yamemod.yamemod.world.gen;

import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;
import org.yamemod.yamemod.block.ModBlocks;

public enum OreType {

    RUBY(Lazy.of(ModBlocks.RUBY_ORE), 8, 25, 50);

    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHight;
    private final int maxHight;

    OreType(Lazy<Block> block, int maxVeinSize, int minHight, int maxHight) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHight = minHight;
        this.maxHight = maxHight;
    }

    public int getMaxHight() {
        return maxHight;
    }

    public int getMinHight() {
        return minHight;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public static OreType get(Block block) {
        for (OreType ore : values()) {
            if(block == ore.block) {
                return ore;
            }
        }
        return null;
    }
}
