package xyz.starrylandserver.thestarryguardforge;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import xyz.starrylandserver.thestarryguardforge.DataType.TsPlayer;
import xyz.starrylandserver.thestarryguardforge.Event.BlockBreakEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheStarryGuardForge.MODID)
public class TheStarryGuardForge {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "thestarryguardforge";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();





    public TheStarryGuardForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(BlockBreakEvent.class);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
    }


    public void SendMsgToPlayer(TsPlayer player,String msg)
    {

    }
}
