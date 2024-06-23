package xyz.starrylandserver.thestarryguardforge.Command;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Player;
import xyz.starrylandserver.thestarryguardforge.Adapter.TgAdapter;
import xyz.starrylandserver.thestarryguardforge.DataType.TgPlayer;
import xyz.starrylandserver.thestarryguardforge.Lang;
import xyz.starrylandserver.thestarryguardforge.Operation.DataQuery;
import xyz.starrylandserver.thestarryguardforge.TgMain;

public class QueryPoint {
    DataQuery query;
    TgAdapter adapter;
    Lang lang;

    public int onCommandExec(CommandContext<CommandSourceStack> dispatcher) {
        Player mc_player = dispatcher.getSource().getPlayer();
        if (mc_player == null) {
            return 1;
        }
        TgPlayer player = new TgPlayer(mc_player.getName().getString(), mc_player.getStringUUID());
        if (query.IsPlayerEnablePointQuery(player))//如果玩家启用了点查询
        {
            query.DisablePlayerPointQuery(player);//关闭点查询
            this.adapter.SendMsgToPlayer(player, this.lang.getVal("point_query_disable"));
        } else {
            query.EnablePlayerPointQuery(player);//启用点查询
            this.adapter.SendMsgToPlayer(player, this.lang.getVal("point_query_enable"));
        }
        return 1;
    }

    public QueryPoint(TgMain main) {
      this.query = main.getDataQuery();
      this.adapter=  main.getAdapter();
      this.lang = main.getLang();
    }
}
