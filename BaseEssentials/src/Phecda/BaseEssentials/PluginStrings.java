package Phecda.BaseEssentials;

import net.md_5.bungee.api.ChatColor;

public final class PluginStrings {
	
	public static final String COMMAND_SPAWN_SUCCESS = ChatColor.GOLD + "You have been teleported to the spawn!";
	public static final String COMMAND_SPAWN_FAILURE = ChatColor.RED + "Unable to teleport you to the spawn!";
	
	public static final String COMMAND_SETSPAWN = ChatColor.GOLD + "The spawn has been set to your current location!";
	
	public static final String COMMAND_CONSOLE_INVALID_ARGUMENTS = ChatColor.RED + "Invalid arguments!";

	public static final String COMMAND_SUPPLY_NO_MATERIAL = ChatColor.RED + "Please enter a material!";
	public static final String COMMAND_SUPPLY_INVALID_MATERIAL = ChatColor.RED + "This material could not be found!";
	public static final String COMMAND_SUPPLY_NO_PLAYER = ChatColor.RED + "Please specify a target player!";
	public static final String COMMAND_SUPPLY_INVALID_PLAYER = ChatColor.RED + "One or more players could not be found!";

	public static final String COMMAND_GAMEMODE_NO_TARGET = ChatColor.RED + "Please specify a target!";
	public static final String COMMAND_GAMEMODE_INVALID_TARGET = ChatColor.RED + "This target could not be found!";
	
	public static final String COMMAND_ENDERCHEST_INVALID_TARGET = ChatColor.RED + "Unable to find this target!";
	
	public static final String COMMAND_ITEM_INVALID_ARGUMENTS = ChatColor.RED + "Invalid arguments!";
	public static final String COMMAND_ITEM_INVALID_MATERIAL = ChatColor.RED + "Unable to find this material!";
	public static final String COMMAND_ITEM_INVALID_TARGET = ChatColor.RED + "Unable to find this target!";
	public static final String COMMAND_ITEM_SUCCESS = ChatColor.GOLD + "Gave you " + ChatColor.RED + "%amount%x %item%" + ChatColor.GOLD + "!"; // %amount% and %item%
	
	public static final String COMMAND_INVENTORYSEE_INVALID_ARGUMENTS = ChatColor.RED + "Invalid target!";
}
