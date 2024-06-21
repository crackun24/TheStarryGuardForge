package xyz.starrylandserver.thestarryguardforge.Adapter;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.server.ServerLifecycleHooks;
import xyz.starrylandserver.thestarryguardforge.DataType.TgPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.UUID;

public class FrogeAdapter implements TgAdapter{
    private final Logger LOGGER = LogManager.getLogger();
    @Override
    public String GetConfigFilePath() {
        return FMLPaths.CONFIGDIR.get().toAbsolutePath().toString();
    }

    @Override
    public String GetLangFilePath() {
        String config_path = GetConfigFilePath();
        return config_path + "/TheStarryGuard/lang/";
    }

    @Override
    public void LOGGER_INFO(String msg) {
        this.LOGGER.info(msg);
    }

    @Override
    public void LOGGER_WARN(String msg) {
        this.LOGGER.warn(msg);
    }

    @Override
    public void LOGGER_ERROR(String msg) {
        this.LOGGER.error(msg);
    }

    @Override
    public void LOGGER_DEBUG(String msg) {
        this.LOGGER.debug(msg);
    }

    @Override
    public void SendMsgToPlayer(TgPlayer player, String msg) {
        Component message = Component.literal(msg);
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();//获取服务器对象
        var mcPlayer = server.getPlayerList().getPlayer(UUID.fromString(player.UUID));
        mcPlayer.sendSystemMessage(message);//发送消息给玩家
    }

    @Override
    public void ShutDownService() {
        MinecraftServer serve = ServerLifecycleHooks.getCurrentServer();
        serve.close();//关闭服务
    }

    public FrogeAdapter()
    {

    }
}
