package Phecda.BaseEssentials.Inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BasePlugin.Components.PluginInventory;
import net.md_5.bungee.api.ChatColor;

public class SupplyInventory extends PluginInventory<EssentialsPlugin> {

	public SupplyInventory(EssentialsPlugin plugin, Material mat, int data) {
		super(plugin, null, 27, ChatColor.GOLD + "Supply Crate", false);
		
		ItemStack is = new ItemStack(mat,1,(short)data);
		int ss = is.getMaxStackSize();
		if (ss > 0) is.setAmount(ss);
		
		for (int i=0; i< this.inventory.getSize(); i++) {
			this.inventory.setItem(i, is.clone());
		}
	}

	@Override
	protected void handleInventoryClickEvent(InventoryClickEvent event, Player player) {}

}
