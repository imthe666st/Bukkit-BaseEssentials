package Phecda.BaseEssentials.Commands.Player;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class ItemSerializeCommand extends PluginCommand<EssentialsPlugin> {

	public ItemSerializeCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "itemserialize";
	}

	@Override
	public String getCommandDescription() {
		return "Debug Command";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/itemserialize";
	}

	@Override
	public List<String> getCommandAliases() {
		return null;
	}

	@Override
	public String getCommandPermission() {
		return "be.command.itemserialize";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) {
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		
		
		return true;
	}
	
}
