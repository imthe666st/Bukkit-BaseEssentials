package Phecda.BaseEssentials.Commands.Warp;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BaseEssentials.PluginStrings;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class SpawnCommand extends PluginCommand<EssentialsPlugin> {

	public SpawnCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "spawn";
	}

	@Override
	public String getCommandDescription() {
		return "Allows you to warp to the spawn";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/spawn";
	}

	@Override
	public List<String> getCommandAliases() {
		return null;
	}

	@Override
	public String getCommandPermission() {
		return "be.command.spawn";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) {
		if (!(sender instanceof Player)) return false;
		if (((Player) sender).teleport(plugin.getSpawnLocation())) sender.sendMessage(PluginStrings.COMMAND_SPAWN_SUCCESS);
		else sender.sendMessage(PluginStrings.COMMAND_SPAWN_FAILURE);
		
		return true;
	}

}
