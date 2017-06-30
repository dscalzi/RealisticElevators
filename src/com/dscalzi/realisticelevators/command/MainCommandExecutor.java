package com.dscalzi.realisticelevators.command;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.dscalzi.realisticelevators.command.sub.Create;
import com.dscalzi.realisticelevators.command.sub.Reload;
import com.dscalzi.realisticelevators.util.MessageManager;

public class MainCommandExecutor implements CommandExecutor{

	private Map<String, SubCommandExecutor> subCommandRegistry;
	
	public MainCommandExecutor(){
		subCommandRegistry = new HashMap<String, SubCommandExecutor>();
		subCommandRegistry.put("create", new Create());
		subCommandRegistry.put("reload", new Reload());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length > 0){
			try{
				this.cmdList(sender, Integer.parseInt(args[0]));
				return true;
			} catch (NumberFormatException e){
				this.reroute(sender, cmd, label, args);
				return true;
			}
		}
		
		this.cmdList(sender, 1);
		return true;
	}

	private void cmdList(CommandSender sender, int page){
		MessageManager.getInstance().sendMessage(sender, "Command list coming soon!");
	}
	
	private void reroute(CommandSender sender, Command cmd, String label, String[] args){
		for(Map.Entry<String, SubCommandExecutor> entry : subCommandRegistry.entrySet()){
			if(args[0].equalsIgnoreCase(entry.getKey())){
				entry.getValue().handle(sender, cmd, label, args);
				return;
			}
		}
		MessageManager.getInstance().sendError(sender, "Unknown subcommand: " + args[0].toLowerCase());
	}
	
}
