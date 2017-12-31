package Phecda.BaseEssentials.Commands.Bypass;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BaseEssentials.PluginStrings;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class ConsoleCommand extends PluginCommand<EssentialsPlugin> {

	public ConsoleCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "console";
	}

	@Override
	public String getCommandDescription() {
		return "Executes a command via console";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/console <command> [parameters.. ]";
	}

	@Override
	public List<String> getCommandAliases() {
		return null;
	}

	@Override
	public String getCommandPermission() {
		return "be.command.console";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) {
		if (args.isEmpty()) {
			sender.sendMessage(PluginStrings.COMMAND_CONSOLE_INVALID_ARGUMENTS);
			return true;
		}
		
		String s = args.pop();
		while (!args.isEmpty()) s += " " + args.pop();
		
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), s);
		
		return true;
	}


}
