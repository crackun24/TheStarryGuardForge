package xyz.starrylandserver.thestarryguardforge;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import xyz.starrylandserver.thestarryguardforge.Adapter.ForgeAdapter;
import xyz.starrylandserver.thestarryguardforge.Command.CmdMain;
import xyz.starrylandserver.thestarryguardforge.Command.QueryNear;
import xyz.starrylandserver.thestarryguardforge.Event.AttEntityEvent;
import xyz.starrylandserver.thestarryguardforge.Event.BlockBreakEvent;
import xyz.starrylandserver.thestarryguardforge.Event.KillEntityEvent;
import xyz.starrylandserver.thestarryguardforge.Event.RightClickEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheStarryGuardForge.MODID)
public class TheStarryGuardForge {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "thestarryguardforge";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    private TgMain serviceMain;
    private ForgeAdapter adapter;//适配器

    private void regEvent()//注册事件
    {
        MinecraftForge.EVENT_BUS.register(new BlockBreakEvent(serviceMain));//注册方块破坏事件
        MinecraftForge.EVENT_BUS.register(new RightClickEvent(serviceMain));//注册右键方块的事件
        MinecraftForge.EVENT_BUS.register(new AttEntityEvent(serviceMain));//注册右键方块的事件
        MinecraftForge.EVENT_BUS.register(new KillEntityEvent(serviceMain));//注册右键方块的事件
        MinecraftForge.EVENT_BUS.register(new CmdMain(serviceMain));//注册右键方块的事件
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(FMLCommonSetupEvent event) {
        this.adapter = new ForgeAdapter();//创建一个适配器
        serviceMain = TgMain.getInstance(adapter);
        serviceMain.start();//启动主服务
        regEvent();//注册事件
    }

    public TheStarryGuardForge() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    @SubscribeEvent
    public void onServerClose(ServerStoppedEvent event)//服务器关闭的事件
    {
        System.out.println("closing");//FIXME
        this.serviceMain.dataStorage.CloseDataStorage();
        this.serviceMain.dataQuery.CloseDataQuery();
    }
}
