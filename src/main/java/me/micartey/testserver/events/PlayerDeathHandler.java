package me.micartey.testserver.events;

import me.clientastisch.cardinal.core.Core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerDeathHandler implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setKeepInventory(true);
        event.setDeathMessage(null);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();

        // Teleport player to world spawn
        Bukkit.getScheduler().runTask(Core.INSTANCE.plugin, () -> {
            player.teleport(
                    player.getWorld().getSpawnLocation().add(.5, 0, .5)
            );
        });
    }

}