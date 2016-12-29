package com.dscalzi.realisticelevators.util;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.dscalzi.realisticelevators.Main;

public class MessageManager {

	private static boolean initialized;
	private static MessageManager instance;
	
	private Main plugin;
	private Logger logger;

	private ChatColor cGeneric;
	private ChatColor cSuccess;
	private ChatColor cError;
	private ChatColor cBase;
	private ChatColor cTrim;
	
	private String prefix;
	
	private MessageManager(Main plugin){
		this.plugin = plugin;
		this.logger = this.plugin.getLogger();
		
		this.cGeneric = ChatColor.YELLOW;
		this.cSuccess = ChatColor.GREEN;
		this.cError = ChatColor.RED;
		this.cBase = ChatColor.YELLOW;
		this.cTrim = ChatColor.GRAY;
		
		this.prefix = cTrim + "| " + cBase + plugin.getName() + cTrim + " |";
	}
	
	public static void initialize(Main plugin){
		if(!initialized){
			instance = new MessageManager(plugin);
			initialized = true;
		}
	}
	
	public static boolean reload(){
		if(!initialized) return false;
		//getInstance().assignVars();
		return true;
	}
	
	public static MessageManager getInstance(){
		return MessageManager.instance;
	}
	
	/* Message Distribution */
	
	public void sendRawMessage(CommandSender sender, String message){
		sender.sendMessage(message);
	}
	
	public void sendMessage(CommandSender sender, String message){
		sendRawMessage(sender, this.prefix + " " + cGeneric + message);
	}
	
	public void sendSuccess(CommandSender sender, String message){
		sendRawMessage(sender, this.prefix + " " + cSuccess + message);
	}
	
	public void sendError(CommandSender sender, String message){
		sendRawMessage(sender, this.prefix + " " + cError + message);
	}
	
	public Logger getLogger() {
		return logger;
	}

	public ChatColor getColorGeneric() {
		return cGeneric;
	}

	public ChatColor getColorSuccess() {
		return cSuccess;
	}

	public ChatColor getColorError() {
		return cError;
	}

	public ChatColor getColorBase() {
		return cBase;
	}

	public ChatColor getColorTrim() {
		return cTrim;
	}

	public String getPrefix() {
		return prefix;
	}
	
	/* Preset Strings */
	
	public void reloadSuccess(CommandSender sender){
		sendSuccess(sender, "Successfully reloaded the plugin.");
	}
	
	public void reloadFailed(CommandSender sender){
		sendError(sender, "Failed to reload the plugin.");
	}
	
}
