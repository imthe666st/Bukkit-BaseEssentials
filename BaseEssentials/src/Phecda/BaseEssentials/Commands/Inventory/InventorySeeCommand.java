package Phecda.BaseEssentials.Commands.Inventory;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BaseEssentials.PluginStrings;
import Phecda.BaseEssentials.Inventories.InventorySeeInventory;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class InventorySeeCommand extends PluginCommand<EssentialsPlugin> {

	public InventorySeeCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "inventorysee";
	}

	@Override
	public String getCommandDescription() {
		return "Allows you to peek into someones inventory";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/inventorysee <player>";
	}

	@Override
	public List<String> getCommandAliases() {
		return Arrays.asList("invsee");
	}

	@Override
	public String getCommandPermission() {
		return "be.command.inventorysee";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) {
		if (!(sender instanceof Player)) return false;
		if (args.isEmpty()) {
			sender.sendMessage(PluginStrings.COMMAND_INVENTORYSEE_INVALID_ARGUMENTS);
			return true;
		}
		
		Player target = getPlayerByName(args.pop());
		if (target == null) {
			sender.sendMessage(PluginStrings.COMMAND_INVENTORYSEE_INVALID_ARGUMENTS);
			return true;
		}
		Player me = (Player) sender;
		
		
		InventorySeeInventory isi = new InventorySeeInventory(plugin, target);
		isi.show(me);
		
		return false;
	}

	
}
