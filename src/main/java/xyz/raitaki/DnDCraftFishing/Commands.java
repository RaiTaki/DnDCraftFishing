package xyz.raitaki.DnDCraftFishing;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (label.equalsIgnoreCase("qualitycheck")) {
                ItemStack item = p.getInventory().getItemInMainHand();
                if(Methods.isFish(item)){
                    Methods.addRandomQuality(item);
                }
            }
        }
        return false;
    }
}
