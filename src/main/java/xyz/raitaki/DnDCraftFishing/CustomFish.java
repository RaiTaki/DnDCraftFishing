package xyz.raitaki.DnDCraftFishing;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CustomFish {

    private ItemStack fish              = new ItemStack(Material.SALMON);
    private ItemMeta fishmeta           = fish.getItemMeta();
    private String name                 = "Default fish name";
    private ArrayList<String> lore      = new ArrayList<>();
    private Integer chance              = 100;
    private Integer maxweight           = 1;
    private Integer minweight           = 0;
    private FishRarity rarity           = FishRarity.Legendary;

    public void setFish(ItemStack fish){
        this.fish     = fish;
        this.fishmeta = fish.getItemMeta();
    }

    public void setName(String name){
        this.name = name;
        this.fishmeta.displayName(Component.text(name));
    }

    public void setLore(ArrayList<String> lores){
        this.fishmeta.lore(Methods.stringToComp(lores));
    }

    public void setChance(Integer chance){
        this.chance = chance;
    }

    public void setWeights(Integer maxweight, Integer minweight){
        this.maxweight = maxweight;
        this.minweight = minweight;
    }

    public void setCustommodeldata(Integer data){
        this.fishmeta.setCustomModelData(data);
    }

    public void setRarity(FishRarity rarity){
        this.rarity = rarity;
    }

    public void createItem(){
        this.fish.setItemMeta(fishmeta);
    }

    public ItemStack getFish(){
        return this.fish;
    }

    public Integer getMaxWeight(){
        return this.maxweight;
    }

    public Integer getMinWeight(){
        return this.minweight;
    }

    public Integer getChance(){
        return this.chance;
    }

    public FishRarity getRarity(){
        return this.rarity;
    }

    public String getName(){
        return this.name;
    }

    public enum FishRarity{Common, Rare, Legendary}
}

