package Phecda.BaseEssentials.Commands.Player;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

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
			config.set("inv", ItemStackToBase64(pinv.getItemInMainHand()));
			
			plugin.saveFileConfiguration("itemserialization");
		} else {
			try {
				pinv.setItemInMainHand(ItemStackFromBase64(config.getString("inv")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return true;
		
	}
	
	
	public static String ItemStackToBase64(ItemStack item) {

		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			BukkitObjectOutputStream boos = new BukkitObjectOutputStream(os);
		
			boos.writeObject(item);
			
			boos.close();
			return Base64Coder.encodeLines(os.toByteArray()).replaceAll(System.lineSeparator(), new String());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static ItemStack ItemStackFromBase64(String data) {
		try {
            ByteArrayInputStream is = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream bois = new BukkitObjectInputStream(is);

			ItemStack item = (ItemStack) bois.readObject();
            
            bois.close();
            
            return item;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}