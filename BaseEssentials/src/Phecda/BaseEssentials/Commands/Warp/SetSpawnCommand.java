package Phecda.BaseEssentials.Commands.Warp;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BaseEssentials.PluginStrings;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class SetSpawnCommand extends PluginCommand<EssentialsPlugin> {

	public SetSpawnCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "setspawn";
	}

	@Override
	public String getCommandDescription() {
		return "Allows you to set the spawn";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/setspawn";
	}

	@Override
	public List<String> getCommandAliases() {
		return null;
	}

	@Override
	public String getCommandPermission() {
		return "be.command.setspawn";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) {
		if (!(sender instanceof Player)) return false;
		
		Location loc = ((Player) sender).getLocation();
		plugin.setSpawnLocation(loc);
		
		return true;
	}

}
