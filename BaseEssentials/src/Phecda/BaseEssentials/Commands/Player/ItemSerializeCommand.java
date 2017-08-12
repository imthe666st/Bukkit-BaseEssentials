package Phecda.BaseEssentials.Commands.Player;

import java.util.Iterator;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

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
		PlayerInventory pinv = p.getInventory();
		
		FileConfiguration config = plugin.getFileConfiguration("itemserialization");
		
		if (args.isEmpty()) {
			int c = 0;
			Iterator<ItemStack> it = pinv.iterator();
			it.next();
			
			for (ItemStack is = null; it.hasNext(); is = it.next()) {
				config.set("inventory." + c , is);
				c++;
			}
			
			plugin.saveFileConfiguration("itemserialization");
			
		} else {
			ItemStack[] items = new ItemStack[41];
//			int c = 0;
			for (int i=0; i<items.length; i++) {
				items[i] = config.getItemStack("inventory." + i);
			}
			
			pinv.setContents(items);
		}
		
		
				
		return true;
	}
	
}
