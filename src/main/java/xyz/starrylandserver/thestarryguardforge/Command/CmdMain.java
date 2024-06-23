package xyz.starrylandserver.thestarryguardforge.Command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import xyz.starrylandserver.thestarryguardforge.TgMain;

public class CmdMain {
    private QueryNear queryNear;
    private QueryPoint queryPoint;
    private TgMain main;

    public void onCommandReg(CommandDispatcher<CommandSourceStack> dispatcher, boolean check) {
        dispatcher.register(
                Commands.literal("tg").executes((commandContext) -> {
                    return 1;
                }).then(Commands.literal("near").executes(queryNear::onCommandExec)));

        dispatcher.register(Commands.literal("tg").//注册查询点的指令
                then(Commands.literal("point").executes(queryPoint::onCommandExec)));
    }

    @SubscribeEvent
    public void onRegCommand(RegisterCommandsEvent event) {
        this.queryNear = new QueryNear();
        this.queryPoint = new QueryPoint(main);
        this.onCommandReg(event.getDispatcher(), true);
    }

    public CmdMain(TgMain main)
    {
        this.main = main;
    }
}
