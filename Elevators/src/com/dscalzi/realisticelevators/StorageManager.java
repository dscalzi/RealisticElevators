package com.dscalzi.realisticelevators;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.dscalzi.realisticelevators.components.Elevator;

public final class StorageManager {
	
	private static boolean initialized;
	private static StorageManager instance;
	
	private Main plugin;
	private FileConfiguration storage;
	private File location;
	
	private StorageManager(Main plugin){
		this.plugin = plugin;
		this.location = new File(this.plugin.getDataFolder(), "elevators.yml");
		loadFileConfiguration();
	}
	
	private void loadFileConfiguration(){
    	verifyFile();
    	//Ensure that the Elevator class is loaded before we try to load the storage file.
    	//Annoying but throws errors otherwise..
    	@SuppressWarnings("unused")
		Elevator forceLoad = new Elevator("forceLoad");
		this.storage = YamlConfiguration.loadConfiguration(location); 
    }
	
	public void verifyFile(){
		if (!location.exists())
			try {
				location.getParentFile().mkdirs();
				location.createNewFile();
			} catch (IOException e) {
				plugin.getLogger().severe("Unable to create file " + location.getName());
			}
    }
	
	public static void initialize(Main plugin){
		if(!initialized){
			instance = new StorageManager(plugin);
			initialized = true;
		}
	}
	
	public static boolean reload(){
		if(!initialized) return false;
		getInstance().loadFileConfiguration();
		return true;
	}
	
	public static StorageManager getInstance(){
		return StorageManager.instance;
	}
	

	public boolean saveElevator(Elevator e){
		storage.set(e.getName(), e);
		
		try {
			storage.save(location);
		} catch (IOException e1) {
			this.plugin.getLogger().severe("Error occurred while saving data to " + location.getName());
			return false;
		}
		
		return true;
	}
	
	public Elevator getElevator(String name){
		int id = -1;
		List<Elevator> l = loadElevators();
		for(int i=0; i<l.size(); ++i){
			if(l.get(i).getName().equals(name)){
				id = i;
				break;
			}
		}
		return id > -1 ? l.get(id) : null;
	}
	
	public List<Elevator> loadElevators(){
		List<Elevator> l = new ArrayList<Elevator>();
		for(String s : storage.getKeys(false)){
			Elevator e = (Elevator)storage.get(s);
			l.add(e);
		}
		return l;
	}
	
}
