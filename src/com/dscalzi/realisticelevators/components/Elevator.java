package com.dscalzi.realisticelevators.components;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class Elevator implements ConfigurationSerializable{

	//Settings
	private String name;
	private Map<Integer, Floor> levels;
	private Material liftMaterial;
	
	public Elevator(String name){
		this.levels = new LinkedHashMap<Integer, Floor>();
		this.name = name;
	}
	
	public String setName(String name){
		return this.name = name;
	}
	
	/**
	 * Overrides any existing level with the same id.
	 * 
	 * @param floor Floor object to be added to the elevator.
	 * @return Same as {@link java.util.Map#put(K,V) Map.put(K,V)}
	 */
	public Floor addLevel(Floor floor){
		return levels.put(floor.getNumber(), floor);
	}
	
	public Material setLiftMaterial(Material liftMaterial){
		return this.liftMaterial = liftMaterial;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Map<Integer, Floor> getLevels(){
		return this.levels;
	}
	
	public Material getLiftMaterial(){
		return this.liftMaterial;
	}
	
	@Override
	public String toString(){
		final String spacing = "  ";
		
		String s = name + ":" + System.lineSeparator();
		//Increase spacing
		s += spacing + "lift_material: " + liftMaterial.toString() + System.lineSeparator();
		s += spacing + "floors:" + System.lineSeparator();
		//Increase spacing
		for(Map.Entry<Integer, Floor> e : levels.entrySet()){
			String f = e.getValue().toString().replace(System.lineSeparator(), System.lineSeparator() + spacing + spacing);
			s += spacing + spacing + f;
		}
		
		return s;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
		data.put("name", name);
		data.put("lift_material", liftMaterial.toString());
		
		Map<String, Object> floorData = new LinkedHashMap<String, Object>();
		for(Map.Entry<Integer, Floor> entry : levels.entrySet()){
			floorData.put(String.valueOf(entry.getValue().getNumber()), entry.getValue().serialize());
		}
		
		data.put("floors", floorData);
		
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public static Elevator deserialize(Map<String, Object> args){
		Elevator e = new Elevator((String)args.get("name"));
		
		e.setLiftMaterial(Material.matchMaterial((String)args.get("lift_material")));
		
		Map<String, Object> floorData = (Map<String, Object>)args.get("floors");
		for(Map.Entry<String, Object> entry : floorData.entrySet()){
			e.addLevel(Floor.deserialize((Map<String, Object>)entry.getValue()));
		}
		
		return e;
	}
	
}
