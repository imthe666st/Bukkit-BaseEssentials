package Phecda.BaseEssentials.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import Phecda.BaseEssentials.EssentialsPlugin;

public class PlayerRespawnHandler implements Listener {
	
	private EssentialsPlugin plugin;
	
	public PlayerRespawnHandler(EssentialsPlugin plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (!event.isBedSpawn()) {
			event.setRespawnLocation(plugin.getSpawnLocation());
		}
	}
}
