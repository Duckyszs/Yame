package org.yamemod.yamemod.world;

import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.yamemod.yamemod.YameMod;
import org.yamemod.yamemod.world.gen.ModOreGeneration;

@Mod.EventBusSubscriber(modid = YameMod.MOD_ID)
public class ModWorldEvents {

    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModOreGeneration.generateOres(event);
    }
}
