package org.yamemod.yamemod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup YAME_GROUP = new ItemGroup("yameModTab") {
                @Override
                public ItemStack makeIcon() {
                    return new ItemStack(ModItems.RUBY.get());
                }
    };

}
