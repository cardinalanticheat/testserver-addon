package me.micartey.testserver.utilities;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemStackUtils {

    public static ItemStack createItemStack(Material material, int amount, String title, String... lore) {
        ItemStack itemStack = new ItemStack(material, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(title);

        if (lore != null && lore.length > 0 && lore[0] != null) {
            ArrayList<String> subtitle = new ArrayList<>();
            Arrays.stream(lore).filter(java.util.Objects::nonNull).forEach(raw -> {
                Arrays.stream(raw.split("\n")).filter(java.util.Objects::nonNull).forEach(string -> {
                    subtitle.add("§7" + string);
                });
            });

            itemMeta.setLore(subtitle);
        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
