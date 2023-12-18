package me.micartey.testserver.events;

import me.clientastisch.cardinal.controller.PlayerController;
import me.clientastisch.cardinal.events.EventManager;
import me.clientastisch.cardinal.events.event.impl.player.others.PlayerJoinEvent;
import me.clientastisch.cardinal.extension.impl.event.EventListener;
import org.bukkit.entity.Player;

public class PlayerJoinHandler implements EventListener {

    @EventManager.Target
    public void onJoin(PlayerJoinEvent event)  {
        Player player = event.getPlayer();

        // We can access the player controller and be sure that it has been initialized
        PlayerController.of(player).setShowingFlags(true);
    }
}
