package xyz.starrylandserver.thestarryguardforge.Event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.starrylandserver.thestarryguardforge.DataType.*;
import xyz.starrylandserver.thestarryguardforge.TgMain;
import xyz.starrylandserver.thestarryguardforge.Tool;
import java.util.HashMap;

public class KillEntityEvent {
    TgMain main;

    @SubscribeEvent
    public void onKillEntity(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player) {

            Entity be_killed_entity = event.getEntity();
            Player mc_player = (Player) event.getSource().getEntity();//转换为玩家对象
            TgPlayer player = new TgPlayer(mc_player.getName().getString(), mc_player.getStringUUID());

            BlockPos pos = be_killed_entity.getOnPos();
            ActionType type;//行为的类型
            TargetType target_type;//目标的类型

            String dimension_str = mc_player.level().dimension().location().toString();//获取维度的名字

            HashMap<String, String> temp_data_slot = new HashMap<>();//临时的数据插槽

            if (be_killed_entity instanceof Player)//击杀玩家
            {
                type = ActionType.KILL_PLAYER_ACTION;
                target_type = TargetType.PLAYER;
                Player be_killed_player = (Player) be_killed_entity;

                temp_data_slot.put("name", be_killed_player.getName().getString());
                temp_data_slot.put("uuid", be_killed_player.getStringUUID());
            } else {//击杀实体
                type = ActionType.KILL_ENTITY_ACTION;
                target_type = TargetType.ENTITY;

                temp_data_slot.put("name", be_killed_entity.getType().getDescriptionId());
            }

            Target target = new Target(target_type, temp_data_slot);
            Action action = new Action(type, player, pos.getX(), pos.getY(), pos.getZ(), dimension_str, target, Tool.GetCurrentTime());

            this.main.onStorage(action);
        }
    }

    public KillEntityEvent(TgMain main) {
        this.main = main;
    }
}
