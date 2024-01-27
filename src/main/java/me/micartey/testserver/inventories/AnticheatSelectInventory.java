package me.micartey.testserver.inventories;

import me.clientastisch.cardinal.extension.Extension;
import me.micartey.testserver.Core;
import me.micartey.testserver.commands.AnticheatCommand;
import me.micartey.testserver.utilities.ItemStackUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class AnticheatSelectInventory implements Listener {

    public static final Inventory INVENTORY = Bukkit.createInventory(null, 9, "§c§lAnticheats");

    public AnticheatSelectInventory() {
        INVENTORY.setItem(0, ItemStackUtils.createItemStack(Material.REDSTONE, 1, "§c§lCardinal", "by micartey"));
        INVENTORY.setItem(1, ItemStackUtils.createItemStack(Material.NETHER_STAR, 1, "§aNeutron", "by jonesdevelopment"));
    }

    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player))
            return;

        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null
                || event.getView() == null
                || event.getView().getTitle() == null
                || event.getCurrentItem().getItemMeta() == null
                || event.getCurrentItem().getItemMeta().getDisplayName() == null)
            return;

        if (!INVENTORY.getViewers().contains(player))
            return;

        event.setCancelled(true);

        String anticheat = event.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§.", "");
        String command = "/ac " + anticheat;

        player.chat(command);
    }
}