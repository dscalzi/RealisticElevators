package com.dscalzi.realisticelevators.components;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

public class Floor implements ConfigurationSerializable{

	//Settings
	private Integer number;
	private Integer yLevel;
	private Location moveButton;
	private Location summonButton;
	private List<Location> activeFloorLights;
	
	public Floor(Integer number){
		this.number = number;
		this.activeFloorLights = new ArrayList<Location>();
	}
	
	public Integer setYLevel(Integer y){
		return this.yLevel = y;
	}
	
	public Location setMoveButton(Location loc){
		return this.moveButton = loc;
	}
	
	public Location setSummonButton(Location loc){
		return this.summonButton = loc;
	}
	
	public boolean addActiveFloorLight(Location loc){
		return this.activeFloorLights.add(loc);
	}
	
	public boolean removeActiveFloorLight(Location loc){
		return this.activeFloorLights.remove(loc);
	}
	
	public Integer getNumber(){
		return this.number;
	}
	
	public Integer getYLevel(){
		return this.yLevel;
	}
	
	public Location getMoveButton(){
		return this.moveButton;
	}
	
	public Location getSummonButton(){
		return this.summonButton;
	}
	
	public List<Location> getCurrentFloorLights(){
		return this.activeFloorLights;
	}
	
	@Override
	public String toString(){
		final String spacing = "  ";
		
		String s = number + ":" + System.lineSeparator();
		//Increase spacing
		s += spacing + "y: " + yLevel + System.lineSeparator();
		s += spacing + "move_button:" + System.lineSeparator();
		//Increase spacing
		s += spacing + spacing + "x: " + moveButton.getX() + System.lineSeparator();
		s += spacing + spacing + "y: " + moveButton.getY() + System.lineSeparator();
		s += spacing + spacing + "z: " + moveButton.getZ() + System.lineSeparator();
		//Decrease spacing
		s += spacing + "summon_button:" + System.lineSeparator();
		//Increase spacing
		s += spacing + spacing + "x: " + summonButton.getX() + System.lineSeparator();
		s += spacing + spacing + "y: " + summonButton.getY() + System.lineSeparator();
		s += spacing + spacing + "z: " + summonButton.getZ() + System.lineSeparator();
		//Decrease spacing
		s += spacing + "active_floor_lights:" + System.lineSeparator();
		//Increase spacing
		for(int i=0; i<activeFloorLights.size(); ++i){
			s += spacing + spacing + i + ":" + System.lineSeparator();
			//Increase spacing
			s += spacing + spacing + spacing + "x: " + activeFloorLights.get(i).getX() + System.lineSeparator();
			s += spacing + spacing + spacing + "y: " + activeFloorLights.get(i).getY() + System.lineSeparator();
			s += spacing + spacing + spacing + "z: " + activeFloorLights.get(i).getZ() + System.lineSeparator();
		}
		
		return s;
	}

	@Override
	public Map<String, Object> serialize(){
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
		data.put("number", number);
		data.put("y", yLevel);
		data.put("move_button", moveButton.serialize());
		data.put("summon_button", summonButton.serialize());
		
		Map<String, Object> activeFloorLightsData = new LinkedHashMap<String, Object>();
		for(int i=0; i<activeFloorLights.size(); ++i)
			activeFloorLightsData.put(String.valueOf(i), activeFloorLights.get(i).serialize());
		
		data.put("active_floor_lights", activeFloorLightsData);
		
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public static Floor deserialize(Map<String, Object> args){
		Floor floor = new Floor((Integer)args.get("number"));
		
		floor.setYLevel((Integer)args.get("y"));
		floor.setMoveButton(Location.deserialize((Map<String, Object>)args.get("move_button")));
		floor.setSummonButton(Location.deserialize((Map<String, Object>)args.get("summon_button")));
		
		Map<String, Object> activeFloorLightsData = (Map<String, Object>)args.get("active_floor_lights");
		for(Map.Entry<String, Object> entry : activeFloorLightsData.entrySet()){
			floor.addActiveFloorLight(Location.deserialize((Map<String, Object>)entry.getValue()));
		}
		
		return floor;
	}
	
}
