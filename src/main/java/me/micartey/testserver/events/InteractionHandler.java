package me.micartey.testserver.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InteractionHandler implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Ignore players in gamemode (probably building)
        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        // Prevent players from extinglishing fire
        if (event.getAction() != null && event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getLocation().add(0, 1, 0).getBlock().getType().equals(Material.FIRE)) {
                event.setCancelled(true);
                return;
            }
        }

        // Prevent players from interacting with dragon eggs
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.DRAGON_EGG)) {
            event.setCancelled(true);
            return;
        }

        // TODO...
    }

    @EventHandler
    public void onEmptyBukket(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();

        // Ignore players in gamemode (probably building new stuff)
        if (player.getGameMode().equals(GameMode.CREATIVE))
            return;

        player.setItemInHand(new ItemStack(Material.COOKIE, 1));
        event.setCancelled(true);
    }

}
