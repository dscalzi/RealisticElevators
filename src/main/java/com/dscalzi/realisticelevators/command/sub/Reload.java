package com.dscalzi.realisticelevators.command.sub;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.dscalzi.realisticelevators.command.SubCommandExecutor;
import com.dscalzi.realisticelevators.util.MessageManager;
import com.dscalzi.realisticelevators.util.StorageManager;

public class Reload implements SubCommandExecutor {

	@Override
	public void handle(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(StorageManager.reload())
			MessageManager.getInstance().reloadSuccess(sender);
		else
			MessageManager.getInstance().reloadFailed(sender);

	}

}
