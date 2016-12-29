package com.dscalzi.realisticelevators;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	@Override
	public void onEnable(){
		StorageManager.initialize(this);
		this.getCommand("realisticelevators").setExecutor(new MainCommandExecutor());
	}
	
	@Override
	public void onDisable(){
		
	}
	
}
