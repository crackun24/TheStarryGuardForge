package xyz.starrylandserver.thestarryguardforge.Event;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.starrylandserver.thestarryguardforge.DataType.*;
import xyz.starrylandserver.thestarryguardforge.TgMain;
import xyz.starrylandserver.thestarryguardforge.Tool;

import java.util.HashMap;


public class BlockBreakEvent {
    TgMain main;//服务的主类

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent break_event)
    {
        Player mc_player = break_event.getPlayer();//获取玩家实例
        TgPlayer player = new TgPlayer(mc_player.getName().getString(),mc_player.getStringUUID());

        BlockState block_state = break_event.getState();//获取方块状态
        BlockPos pos = break_event.getPos();//获取事件发生的位置
        Level world = mc_player.level();//获取发生的世界

        ResourceKey<Level>dimension_key = world.dimension();
        String dimension_str = dimension_key.location().toString();//获取维度的名字

        HashMap<String,String>temp_data_slot = new HashMap<>();//临时的数据插槽
        temp_data_slot.put("name",block_state.getBlock().getDescriptionId());
        Target target = new Target(TargetType.BLOCK,temp_data_slot);//构造一个目标

        Action action = new Action(ActionType.BLOCK_BREAK_ACTION,player,
                pos.getX(),pos.getY(),pos.getZ(),dimension_str,target, Tool.GetCurrentTime());
        this.main.onStorage(action);
    }
    public BlockBreakEvent(TgMain tg_main)
    {
        this.main = tg_main;
    }
}
