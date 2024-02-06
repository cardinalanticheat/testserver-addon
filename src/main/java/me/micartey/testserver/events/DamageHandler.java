package me.micartey.testserver.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageHandler implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        event.setDamage(0);
    }
}