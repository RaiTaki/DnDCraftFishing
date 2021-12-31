package xyz.raitaki.DnDCraftFishing;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class CustomRod {

    private ItemStack rod               = new ItemStack(Material.FISHING_ROD);
    private String rodname              = "Default rod name";
    private ArrayList<String> lore      = new ArrayList<>();
    private Integer commonchance        = 0;
    private Integer rarechance          = 0;
    private Integer legendarychance     = 0;
    private Integer custommodeldata     = 0;

    public void setRodname(String name){
        this.rodname = name;
    }

    public void setChances(Integer commonchance, Integer rarechance, Integer legendarychance){
        this.commonchance       = commonchance;
        this.rarechance         = rarechance;
        this.legendarychance    = legendarychance;
    }

    public void setCustommodeldata(Integer data){
        this.custommodeldata = custommodeldata;
    }

    public void setLore(ArrayList<String> lorelist){
        this.lore = lorelist;
    }

    public void createRod(){
        ItemMeta meta = this.rod.getItemMeta();

        meta.displayName(Component.text(rodname));
        meta.setCustomModelData(custommodeldata);
        meta.lore(Methods.stringToComp(lore));

        this.rod.setItemMeta(meta);
    }

    public ItemStack getRod(){
        return this.rod;
    }

    public Integer getCommonchance(){
        return this.commonchance;
    }

    public Integer getRarechance(){
        return this.rarechance;
    }

    public Integer getLegendarychance(){
        return this.legendarychance;
    }

    public ArrayList<Integer> getChances(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(commonchance);
        list.add(rarechance);
        list.add(legendarychance);
        return list;
    }

    public Integer getCustommodeldata(){
        return custommodeldata;
    }

    public String getRodname(){
        return rodname;
    }

}
