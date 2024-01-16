package me.micartey.testserver.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathHandler implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setKeepInventory(true);
        event.setDeathMessage(null);
    }

}