package Phecda.BaseEssentials.Commands.Inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BaseEssentials.PluginStrings;
import Phecda.BaseEssentials.Inventories.SupplyInventory;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class SupplyCommand extends PluginCommand<EssentialsPlugin> {

	public SupplyCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "supply";
	}

	@Override
	public String getCommandDescription() {
		return "Gives you an unlimited supply of items";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/supply <materials> [player]";
	}

	@Override
	public List<String> getCommandAliases() {
		return null;
	}

	@Override
	public String getCommandPermission() {
		return "be.command.supply";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) {
		
		if (args.isEmpty()) {
			sender.sendMessage(PluginStrings.COMMAND_SUPPLY_NO_MATERIAL);
			return true;
		}
				
		String mat_string = args.pop();
		String[] matParts = mat_string.toUpperCase().split(":");
		
		Material mat = null;
		int data = 0;
		if (matParts.length > 1) {
			try {
				data = Integer.parseInt(matParts[1]);
				
				mat = Material.getMaterial(matParts[0]);
			} catch (Exception ex) {}
		} else {
			mat = Material.getMaterial(matParts[0]);
		}
		
		if (mat == null) {
			sender.sendMessage(PluginStrings.COMMAND_SUPPLY_INVALID_MATERIAL);
			return true;
		}
		
		List<Player> targets = new ArrayList<Player>();
		
		if (args.isEmpty() && sender instanceof Player) targets.add((Player)sender);
		
		while (!args.isEmpty()) targets.add(getPlayerByName(args.pop()));
		
		SupplyInventory sinv = new SupplyInventory(this.plugin, mat, data);
		
		for (Player p : targets) {
			sinv.show(p);
		}
		
		return true;
	} 
	

}
