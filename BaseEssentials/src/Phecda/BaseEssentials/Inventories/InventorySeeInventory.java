package Phecda.BaseEssentials.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BasePlugin.Components.PluginInventory;

public class InventorySeeInventory extends PluginInventory<EssentialsPlugin> {

	private Player target;
	
	public InventorySeeInventory(EssentialsPlugin plugin, Player player) {
		super(plugin, player.getInventory(), false);
		this.target = player;
		
	}
	
	// Sadly having to overwrite the eventhandler
	@Override
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (player.getGameMode() == GameMode.SPECTATOR) {
			event.setCancelled(true);
			return;
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {

			@Override
			public void run() {
				player.updateInventory();
				target.updateInventory();
				
			}
			
		});
		
	}


	@Override
	protected void handleInventoryClickEvent(InventoryClickEvent event, Player player) { }
	
	
	
}
