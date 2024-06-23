package xyz.starrylandserver.thestarryguardforge.Command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;

public class QueryNear {
    public int onCommandExec(CommandContext<CommandSourceStack> dispatcher) {
        System.out.println("test on query near");
        return 1;
    }
}
