package org.yamemod.yamemod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.yamemod.yamemod.YameMod;
import org.yamemod.yamemod.item.custom.Firestar;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, YameMod.MOD_ID);

    public static final RegistryObject<Item> RUBY = ITEMS.register("ruby",
                    () -> new Item(new Item.Properties().tab(ModItemGroup.YAME_GROUP)));

    public static final RegistryObject<Item> FIRESTAR = ITEMS.register("firestar",
            () -> new Firestar(new Item.Properties().tab(ModItemGroup.YAME_GROUP).durability(8)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
