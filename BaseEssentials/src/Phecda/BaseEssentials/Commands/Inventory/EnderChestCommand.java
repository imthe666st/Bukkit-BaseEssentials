package Phecda.BaseEssentials.Commands.Inventory;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BaseEssentials.PluginStrings;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class EnderChestCommand extends PluginCommand<EssentialsPlugin> {

	public EnderChestCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "enderchest";
	}

	@Override
	public String getCommandDescription() {
		return "Opens your enderchest";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/enderchest [user]";
	}

	@Override
	public List<String> getCommandAliases() {
		return Arrays.asList("ec");
	}

	@Override
	public String getCommandPermission() {
		return "be.command.enderchest";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) {
		if (!(sender instanceof Player)) return false;
		
		Player op;
		if (args.isEmpty()) op = (Player) sender;
		else {
			if (!sender.hasPermission("be.command.enderchest.other")) return false;
			op = getPlayerByName(args.pop());
		}
		
		if (op == null) {
			sender.sendMessage(PluginStrings.COMMAND_ENDERCHEST_INVALID_TARGET);
			return true;
		}
		
		((Player) sender).openInventory(op.getPlayer().getEnderChest());
		
		return true;
	}

}
