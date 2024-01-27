package me.micartey.testserver.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class JumpadHandler implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (!event.getTo().getBlock().getType().name().contains("STONE_"))
            return;

        if (!event.getTo().clone().subtract(0, 1, 0).getBlock().getType().equals(Material.REDSTONE_BLOCK))
            return;

        Vector velocity = new Vector(
                event.getTo().getX() - event.getFrom().getX(),
                .2,
                event.getTo().getZ() - event.getFrom().getZ()
        );

        player.setVelocity(velocity.multiply(6));
    }
}