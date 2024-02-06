package me.micartey.testserver.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageHandler implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getDamage() <= 0)
            return;

        event.getEntity().sendMessage("§7You took §c" + event.getDamage() + " ♥ §damage!");
        event.setDamage(0);
    }
}