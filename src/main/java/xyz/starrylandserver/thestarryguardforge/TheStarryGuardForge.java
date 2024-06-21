package xyz.starrylandserver.thestarryguardforge;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import xyz.starrylandserver.thestarryguardforge.Adapter.FrogeAdapter;
import xyz.starrylandserver.thestarryguardforge.DataType.TgPlayer;
import xyz.starrylandserver.thestarryguardforge.Event.BlockBreakEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheStarryGuardForge.MODID)
public class TheStarryGuardForge {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "thestarryguardforge";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    private final TgMain serviceMain;
    private final FrogeAdapter adapter;//适配器
    private final BlockBreakEvent blockBreakEvent;

    private void regEvent()//注册事件
    {

        MinecraftForge.EVENT_BUS.register(blockBreakEvent);
    }

    public TheStarryGuardForge() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        this.adapter = new FrogeAdapter();
        serviceMain = TgMain.getInstance(adapter);
        serviceMain.start();//启动主服务
        blockBreakEvent = new BlockBreakEvent(this.serviceMain.getDataQuery());

        regEvent();//注册事件
    }
}
