package xyz.starrylandserver.thestarryguardforge.Event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.starrylandserver.thestarryguardforge.DataType.*;
import xyz.starrylandserver.thestarryguardforge.TgMain;
import xyz.starrylandserver.thestarryguardforge.Tool;

import java.util.HashMap;

public class AttEntityEvent {
    TgMain main;

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event)
    {
        Player mc_player = event.getEntity();
        BlockPos pos = event.getTarget().getOnPos();//获取位置
        ActionType action_type;//行为的类型
        TargetType target_type;//目标的类型
        HashMap<String,String>temp_target_data_slot = new HashMap<>();
        TgPlayer player = new TgPlayer(mc_player.getName().getString(),mc_player.getStringUUID());
        String dimension_name = event.getTarget().level().dimension().toString();

        if(event.getTarget() instanceof Player)//判断攻击的对象是否为玩家
        {
            System.out.println("attack player.");
            action_type = ActionType.ATTACK_PLAYER_ACTION;
            target_type = TargetType.PLAYER;
            temp_target_data_slot.put("name",event.getTarget().getType().getDescriptionId());
        }else{
            System.out.println("attack entity.");
            action_type = ActionType.ATTACK_ENTITY_ACTION;
            target_type = TargetType.ENTITY;
            temp_target_data_slot.put("name",mc_player.getName().getString());
            temp_target_data_slot.put("uuid",mc_player.getStringUUID());
        }

        Target target = new Target(target_type,temp_target_data_slot);
        Action action = new Action(action_type,player, pos.getX(), pos.getY(), pos.getZ(),
                dimension_name,target, Tool.GetCurrentTime());
        this.main.onStorage(action);
    }

    public AttEntityEvent(TgMain tg_main)
    {
        this.main = tg_main;
    }
}
