package com.dscalzi.realisticelevators;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.dscalzi.realisticelevators.components.Elevator;
import com.dscalzi.realisticelevators.components.Floor;

public class MainCommandExecutor implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		/**
		 * DEBUGGING CODE - TEMPORARY
		 */
		
		Floor f = new Floor(1);
		f.setSummonButton(new Location(Bukkit.getWorld("world"), 100, 70, -100));
		f.setYLevel(68);
		f.setMoveButton(new Location(Bukkit.getWorld("world"), 100, 70, -100));
		f.addActiveFloorLight(new Location(Bukkit.getWorld("world"), 100, 70, -100));
		f.addActiveFloorLight(new Location(Bukkit.getWorld("world"), 150, 70, -150));
		
		Elevator e = new Elevator("TestElevator");
		e.setLiftMaterial(Material.QUARTZ_BLOCK);
		e.addLevel(f);
		
		System.out.println(e.toString());
		StorageManager.getInstance().saveElevator(e);
		StorageManager.getInstance().loadElevators();
		
		Elevator sec = StorageManager.getInstance().getElevator("TestElevator");
		System.out.println(sec.toString());
		
		
		return true;
	}

	
	
}
