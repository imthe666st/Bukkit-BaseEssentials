package Phecda.BaseEssentials;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import Phecda.BaseEssentials.Commands.Bypass.ConsoleCommand;
import Phecda.BaseEssentials.Commands.Inventory.EnderChestCommand;
import Phecda.BaseEssentials.Commands.Inventory.InventorySeeCommand;
import Phecda.BaseEssentials.Commands.Inventory.SupplyCommand;
import Phecda.BaseEssentials.Commands.Player.CommandSpyCommand;
import Phecda.BaseEssentials.Commands.Player.GamemodeAdventureCommand;
import Phecda.BaseEssentials.Commands.Player.GamemodeCreativeCommand;
import Phecda.BaseEssentials.Commands.Player.GamemodeSpectatorCommand;
import Phecda.BaseEssentials.Commands.Player.GamemodeSurvivalCommand;
import Phecda.BaseEssentials.Commands.Player.ItemCommand;
import Phecda.BaseEssentials.Commands.Player.ItemSerializeCommand;
import Phecda.BaseEssentials.Commands.Warp.SetSpawnCommand;
import Phecda.BaseEssentials.Commands.Warp.SpawnCommand;
import Phecda.BaseEssentials.Listeners.CommandSpyHandler;
import Phecda.BaseEssentials.Listeners.PlayerRespawnHandler;
import Phecda.BasePlugin.BasePlugin;

public class EssentialsPlugin extends BasePlugin<EssentialsPlugin> {

	private Location spawnLocation;
	private List<String> commandSpy;
	
	@Override
	public void enablePlugin() {
		FileConfiguration config = getFileConfiguration();
		
		// default values
		
		Location spawnLocation = Bukkit.getWorlds().get(0).getSpawnLocation();
		config.addDefault(FileConfigurationPaths.SPAWN_LOCATION_WORLD, spawnLocation.getWorld().getUID().toString());
		config.addDefault(FileConfigurationPaths.SPAWN_LOCATION_X, spawnLocation.getBlockX());
		config.addDefault(FileConfigurationPaths.SPAWN_LOCATION_Y, spawnLocation.getBlockY());
		config.addDefault(FileConfigurationPaths.SPAWN_LOCATION_Z, spawnLocation.getBlockZ());
		config.addDefault(FileConfigurationPaths.SPAWN_LOCATION_PITCH, spawnLocation.getPitch());
		config.addDefault(FileConfigurationPaths.SPAWN_LOCATION_YAW, spawnLocation.getYaw());
		
		// saving all defaults
		saveFileConfiguration();
		
		// loading values.
		this.spawnLocation = new Location(
				Bukkit.getWorld(UUID.fromString(config.getString(FileConfigurationPaths.SPAWN_LOCATION_WORLD))), 
				config.getInt(FileConfigurationPaths.SPAWN_LOCATION_X), 
				config.getInt(FileConfigurationPaths.SPAWN_LOCATION_Y), 
				config.getInt(FileConfigurationPaths.SPAWN_LOCATION_Z)
		);
		this.spawnLocation.setPitch((float)config.getDouble(FileConfigurationPaths.SPAWN_LOCATION_PITCH));
		this.spawnLocation.setYaw((float)config.getDouble(FileConfigurationPaths.SPAWN_LOCATION_YAW));
		
		commandSpy = config.getStringList("commandspy");
		
		// listeners
		new PlayerRespawnHandler(this);
		new CommandSpyHandler(this); // Blocks console commands. DON'T USE THIS IN PROD
		
		// commands
		
		registerCommand(new ConsoleCommand(this));
		
		registerCommand(new SpawnCommand(this));
		registerCommand(new SetSpawnCommand(this));

		registerCommand(new SupplyCommand(this));
		registerCommand(new EnderChestCommand(this));
		registerCommand(new InventorySeeCommand(this));

		registerCommand(new GamemodeSurvivalCommand(this));
		registerCommand(new GamemodeCreativeCommand(this));
		registerCommand(new GamemodeAdventureCommand(this));
		registerCommand(new GamemodeSpectatorCommand(this));
		registerCommand(new ItemCommand(this));
		registerCommand(new ItemSerializeCommand(this));
		registerCommand(new CommandSpyCommand(this));
	}

	@Override
	public void disablePlugin() {
		
		
		
		saveFileConfigurations();

	}
	
	public Location getSpawnLocation() {
		return this.spawnLocation;
	}
	
	//
	
	public void setSpawnLocation(Location loc) {
		FileConfiguration config = getFileConfiguration();
		
		config.set(FileConfigurationPaths.SPAWN_LOCATION_WORLD, loc.getWorld().getUID().toString());
		config.set(FileConfigurationPaths.SPAWN_LOCATION_X, loc.getBlockX());
		config.set(FileConfigurationPaths.SPAWN_LOCATION_Y, loc.getBlockY());
		config.set(FileConfigurationPaths.SPAWN_LOCATION_Z, loc.getBlockZ());
		config.set(FileConfigurationPaths.SPAWN_LOCATION_PITCH, loc.getPitch());
		config.set(FileConfigurationPaths.SPAWN_LOCATION_YAW, loc.getYaw());
		
		this.spawnLocation = loc;
		
		saveFileConfiguration();
		
	}
	
	public boolean toggleCommandSpy(UUID id) {
		String s = id.toString();
		if (!commandSpy.remove(s)) {
			commandSpy.add(s);
			
			getFileConfiguration().set("commandspy", commandSpy);
			return true;
		} 

		getFileConfiguration().set("commandspy", commandSpy);
		return false;
	}
	
	public boolean isCommandSpy(UUID id) {
		return commandSpy.contains(id.toString());
	}

}
