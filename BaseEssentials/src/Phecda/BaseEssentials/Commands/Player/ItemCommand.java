package Phecda.BaseEssentials.Commands.Player;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BaseEssentials.PluginStrings;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class ItemCommand extends PluginCommand<EssentialsPlugin> {

	public ItemCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "item";
	}

	@Override
	public String getCommandDescription() {
		return "Gives you an item";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/item <name> [amount]";
	}

	@Override
	public List<String> getCommandAliases() {
		return Arrays.asList("i");
	}

	@Override
	public String getCommandPermission() {
		return "be.command.item";
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) {
		if (!(sender instanceof Player)) return false;
		
		if (args.isEmpty()) {
			sender.sendMessage(PluginStrings.COMMAND_ITEM_INVALID_ARGUMENTS);
			return true;
		}
		
		String[] matParts = args.pop().toUpperCase().split(":");

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
			sender.sendMessage(PluginStrings.COMMAND_ITEM_INVALID_MATERIAL);
			return true;
		}
		
		ItemStack is = new ItemStack(mat, 1, (short)data);
		
		int amount = is.getMaxStackSize();
		if (!args.isEmpty()) {
			try {
				amount = Integer.parseInt(args.pop());
			} catch (Exception ex) {}
		}
		is.setAmount(amount);

		Player target;
		if (args.isEmpty()) target = (Player) sender;
		else target = getPlayerByName(args.pop());
		
		if (target == null) {
			sender.sendMessage(PluginStrings.COMMAND_ITEM_INVALID_TARGET);
			return true;
		}
		
		
		target.getInventory().addItem(is);
		target.sendMessage(PluginStrings.COMMAND_ITEM_SUCCESS.replaceAll("%amount%", ""+amount).replaceAll("%item%", mat.name()));
		
		
		return true;
	}
	
	
}
