package com.dscalzi.realisticelevators.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommandExecutor {

	public void handle(CommandSender sender, Command cmd, String label, String[] args);
	
}
