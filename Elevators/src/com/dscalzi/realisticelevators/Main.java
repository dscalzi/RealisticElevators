package com.dscalzi.realisticelevators;

import org.bukkit.plugin.java.JavaPlugin;

import com.dscalzi.realisticelevators.command.MainCommandExecutor;
import com.dscalzi.realisticelevators.util.MessageManager;
import com.dscalzi.realisticelevators.util.StorageManager;

public class Main extends JavaPlugin{

	@Override
	public void onEnable(){
		MessageManager.initialize(this);
		StorageManager.initialize(this);
		this.getCommand("realisticelevators").setExecutor(new MainCommandExecutor());
	}
	
	@Override
	public void onDisable(){
		
	}
	
}
