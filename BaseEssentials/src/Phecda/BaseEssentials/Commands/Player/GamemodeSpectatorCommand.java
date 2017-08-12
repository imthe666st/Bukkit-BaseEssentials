package Phecda.BaseEssentials.Commands.Player;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BaseEssentials.PluginStrings;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class GamemodeSpectatorCommand extends PluginCommand<EssentialsPlugin> {

	public GamemodeSpectatorCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "gmsp";
	}

	@Override
	public String getCommandDescription() {
		return "Sets your gamemode to spectator";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/gmsp [player]";
	}

	@Override
	public List<String> getCommandAliases() {
		return Arrays.asList("spectator");
	}

	@Override
	public String getCommandPermission() {
		return "be.command.gamemode.spectator";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) {
		Player target;
		if (!(sender instanceof Player) && args.isEmpty()) {
			sender.sendMessage(PluginStrings.COMMAND_GAMEMODE_NO_TARGET);
			return true;
		}
		
		if (args.isEmpty()) target = (Player) sender;
		else target = getPlayerByName(args.pop());

		if (target == null) {
			sender.sendMessage(PluginStrings.COMMAND_GAMEMODE_INVALID_TARGET);
			return true;
		}
		
		target.setGameMode(GameMode.SPECTATOR);
		
		return true;
	}

	
}
