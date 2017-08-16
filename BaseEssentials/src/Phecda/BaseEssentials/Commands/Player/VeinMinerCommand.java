package Phecda.BaseEssentials.Commands.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Phecda.BaseEssentials.EssentialsPlugin;
import Phecda.BasePlugin.Components.PluginCommand;
import Phecda.Utility.Queue;

public class VeinMinerCommand extends PluginCommand<EssentialsPlugin> {

	public VeinMinerCommand(EssentialsPlugin plugin) {
		super(plugin);
	}

	@Override
	public String getCommandName() {
		return "veinminer";
	}

	@Override
	public String getCommandDescription() {
		return "Destroys a vein";
	}

	@Override
	public String getCommandUsageMessage() {
		return "/veinminer";
	}

	@Override
	public List<String> getCommandAliases() {
		return Arrays.asList("vein", "vm");
	}

	@Override
	public String getCommandPermission() {
		return "be.command.veinminer";
	}

	protected int taskId = -1;
	private int total = 0;
	private int cycle = 0;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, Queue<String> args) throws Exception {
		if (!(sender instanceof Player)) return false;
		
		
		Player player = (Player) sender;
		
		if (taskId != -1) {
			player.sendMessage("Task #" + taskId + " is already running!");
			return true;
		}
		
		Block block = player.getTargetBlock(null, 5);
		if (block == null) {
			player.sendMessage("null or air?");
			return true;
		}
		
		Material m = block.getType();
		
		Queue<Block> q = new Queue<Block>();
		q.push(block);
		
		total = 0;
		
		
		taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

			@Override
			public void run() {
				addCycle();
				int c = 0;
				ArrayList<Block> b = new ArrayList<Block>();
				while (c++ < 0x100 && !q.isEmpty()) {
					Block a = q.pop();
					if (a.getType() == Material.AIR) continue;
					
					player.sendMessage("Popping loc: " + a.getX() + " " + a.getY() + " " + a.getZ());

					addTotal();
					
					a.breakNaturally(player.getInventory().getItemInMainHand());
					
					for (int x = -1; x <= 1; x++) {
						for (int y = -1; y <= 1; y++) {
							for (int z = -1; z <= 1; z++) {						
								Block newBlock = block.getWorld().getBlockAt(a.getLocation().add(x,y,z));
								if (newBlock.getType() == m) {
									
									if (b.contains(newBlock)) continue;
									
									player.sendMessage("Pushing loc: " + newBlock.getX() + " " + newBlock.getY() + " " + newBlock.getZ());
									
									b.add(newBlock);
									q.push(newBlock);
								}
								
								
							}
						}
					}
					
				}
				
				if (q.isEmpty() || getTotal() > 0x10000) {
					cancelTask();
					player.sendMessage("VeinMiner finsihed in " + getCycle() + " cycles");
				}
			}
			
		}, 1, 1);
		
		return true;
	}
	
	private void cancelTask() {
		
		if (taskId != -1) {
			Bukkit.getScheduler().cancelTask(taskId);
			
			taskId = -1;
		}
	}
	
	private void addTotal() { total++; }
	private int getTotal() { return total; }
	
	private void addCycle() { cycle++; }
	private int getCycle() { return cycle; }

}
