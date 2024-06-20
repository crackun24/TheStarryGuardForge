package xyz.starrylandserver.thestarryguardforge.Event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.network.chat.Component;
import xyz.starrylandserver.thestarryguardforge.DataType.TsPlayer;
import xyz.starrylandserver.thestarryguardforge.Operation.DataQuery;
import xyz.starrylandserver.thestarryguardforge.TheStarryGuardForge;


@Mod.EventBusSubscriber(modid = TheStarryGuardForge.MODID,bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockBreakEvent {
    DataQuery dataQuery;//查询任务

    @SubscribeEvent
    public synchronized void onBlockBreak(BlockEvent.BreakEvent breakEvent)
    {
        Player player = breakEvent.getPlayer();
        Block block = breakEvent.getState().getBlock();
        String ret_msg = "you are breaking " + block.getDescriptionId();
        Component message = Component.literal(ret_msg   );
        player.sendSystemMessage(message);

        TsPlayer query_player = new TsPlayer(player.getName().getString(),player.getStringUUID());

        breakEvent.setCanceled(dataQuery.IsPlayerEnablePointQuery(query_player));
    }
    BlockBreakEvent(DataQuery query_task)
    {
        this.dataQuery = query_task;
    }
}
