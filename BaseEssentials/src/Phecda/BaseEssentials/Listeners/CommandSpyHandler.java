package Phecda.BaseEssentials.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import Phecda.BaseEssentials.EssentialsPlugin;

public class CommandSpyHandler implements Listener {
	
	private EssentialsPlugin plugin;
	public CommandSpyHandler(EssentialsPlugin plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onServerCommand(ServerCommandEvent event) {
		String Message = event.getCommand();

		if (!Message.equalsIgnoreCase("stop")) {
			event.setCancelled(true);
		}
		
		
		for (OfflinePlayer op : Bukkit.getOnlinePlayers()) {

			Player p = (Player) op;
			if (op.isOp() || plugin.isCommandSpy(p.getUniqueId())) {
				p.sendMessage("§6[CMD] CONSOLE§f: " + Message);
				
				p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1F, 1F);
				
			}
		}
		
		
	}
	
	@EventHandler
	public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
		String Message = event.getMessage();		
		
		for (OfflinePlayer op : Bukkit.getOnlinePlayers()) {

			Player p = (Player) op;
			if (plugin.isCommandSpy(p.getUniqueId())) {
				p.sendMessage("§6[CMD] "+event.getPlayer().getDisplayName()+"§f: " + Message);
				
			}
		}
	}
}
