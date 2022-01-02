package xyz.raitaki.DnDCraftFishing;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class FishingMenu implements Listener {

    private Inventory inventory;
    private Integer   state = 0;
    private Integer   level = 1;
    private Player    p;
    private ItemStack redbar        = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    private ItemStack orangebar     = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
    private ItemStack yellowbar     = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
    private ItemStack greenbar      = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
    private ItemStack graybar       = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    private int redbari      = 40; // 40
    private int orangebari   = 31; // 31
    private int yellowbari   = 22; // 22
    private int greenbari    = 13; // 13
    private ItemStack greenbutton      = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
    private ItemStack redbutton        = new ItemStack(Material.RED_STAINED_GLASS_PANE);
    private int greenbuttoni  = 25;
    private int redbuttoni    = 19;
    private boolean stopped = false;
    private String inventoryname = "§6Fishing";

    private void setItemNames(){
        changeItemName(greenbutton, "§aCatch a fish");
        changeItemName(redbutton, "§4Stop fishing");
    }

    private void changeItemName(ItemStack item, String name){
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Component.text(name));
        item.setItemMeta(itemMeta);
    }

    public void setPlayer(Player p){
        this.p = p;
    }

    public void start(){
        inventory = Bukkit.createInventory(null, 54, Component.text(inventoryname));
        createInventoryItems();
        setItemNames();
        p.openInventory(inventory);
        inventory.setItem(greenbuttoni, greenbutton);
        inventory.setItem(redbuttoni, redbutton);
        new BukkitRunnable(){
            boolean shouldup = true;
            public void run() {
                if(shouldup) level++;
                if (!shouldup) level--;
                if(level == 41) shouldup = false;
                if(level == -1) shouldup = true;
                setState();
                setStateItems();
                if(stopped){
                    Events.fishinglist.remove(p);
                    cancel();
                }
            }
        }.runTaskTimer(Main.getInstance(), 1,1);
    }

    private void createInventoryItems(){
        for(int i = 0; i < inventory.getSize(); i ++){
            inventory.setItem(i, graybar);
        }
    }

    private void setStateItems(){
        switch (state){
            case 1:
                setInventoryItem(redbar, redbari);
                setInventoryItem(graybar, orangebari);
                setInventoryItem(graybar, yellowbari);
                setInventoryItem(graybar, greenbari);
                break;
            case 2:
                setInventoryItem(redbar, redbari);
                setInventoryItem(orangebar, orangebari);
                setInventoryItem(graybar, yellowbari);
                setInventoryItem(graybar, greenbari);
                break;
            case 3:
                setInventoryItem(redbar, redbari);
                setInventoryItem(orangebar, orangebari);
                setInventoryItem(yellowbar, yellowbari);
                setInventoryItem(graybar, greenbari);
                break;
            case 4:
                setInventoryItem(redbar, redbari);
                setInventoryItem(orangebar, orangebari);
                setInventoryItem(yellowbar, yellowbari);
                setInventoryItem(greenbar, greenbari);
                break;
        }
    }

    private void setState(){
        switch (level) {
            case 10:
                state = 1;
                break;
            case 25:
                state = 2;
                break;
            case 35:
                state = 3;
                break;
            case 40:
                state = 4;
                break;
        }
    }

    @EventHandler
    private void inventoryCloseEvent(InventoryCloseEvent event){
        if(event.getInventory() == inventory) stopped = true;
    }

    //checks event for fishing
    public void eventchecker(InventoryClickEvent event) {
        if(event.getView().title() == p.getOpenInventory().title() && event.getWhoClicked() == p){
            if(state < 3){
                event.setCancelled(true);
                event.getWhoClicked().closeInventory();
                return;
            }
            if(event.getSlot() == greenbuttoni && state >= 3){
                ItemStack fish = getFish();
                HumanEntity p = event.getWhoClicked();
                if(p.getInventory().isEmpty()){
                    p.getInventory().addItem(fish);
                }else{
                    p.getWorld().dropItem(p.getLocation(), fish);
                }
                p.closeInventory();
                stopped = true;
            }
            if(event.getSlot() == redbuttoni){
                p.closeInventory();
                stopped = true;
            }
            event.setCancelled(true);
        }
    }

    private boolean hasFishingRod(){
        return Methods.isRod(p.getInventory().getItemInMainHand());
    }

    private ItemStack getFish(){
        ItemStack item = null;
        int commonchance     = 0;
        int rarechance       = 0;
        int legendarychance  = 0;

        if(hasFishingRod()){
            CustomRod rod = Methods.getRodType(p.getInventory().getItemInMainHand());
            commonchance     = rod.getCommonchance();
            rarechance       = rod.getRarechance();
            legendarychance  = rod.getLegendarychance();
        }
        switch (state){
            case 3:
                item = Main.rc.nextEdited(5+commonchance,5+rarechance,1+legendarychance);
                break;
            case 4:
                item = Main.rc.nextEdited(10+commonchance,10+rarechance,3+legendarychance);
                break;
        }
        return item;
    }

    private void setInventoryItem(ItemStack item, Integer slot){
        inventory.setItem(slot, item);
    }
}
