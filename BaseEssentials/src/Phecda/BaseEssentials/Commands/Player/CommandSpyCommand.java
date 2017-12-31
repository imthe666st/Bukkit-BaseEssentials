package Phecda.BaseEssentials.Commands.Player;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class CommandSpyCommand extends PluginCommand<EssentialsPlugin> {

	public CommandSpyCommand(EssentialsPlugin plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getCommandName() {
		return "commandspy";
	}

	@Override
	public String getCommandDescription() {
		return "Allows you to see other peoples commands!";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/commandspy";
	}

	@Override
	public List<String> getCommandAliases() {
		return null;
	}

	@Override
	public String getCommandPermission() {
		return "be.commandspy";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) throws Exception {
		if (!(sender instanceof Player)) return false;
		
		if (plugin.toggleCommandSpy(((Player) sender).getUniqueId())) {
			((Player) sender).sendMessage("§6You are now a commandspy!");
		} else {

			((Player) sender).sendMessage("§6You are no longer a commandspy!");
		}
		
		
		return true;
	}

}
