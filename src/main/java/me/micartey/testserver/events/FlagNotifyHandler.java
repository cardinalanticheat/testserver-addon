package me.micartey.testserver.events;

import me.clientastisch.cardinal.events.EventManager;
import me.clientastisch.cardinal.events.event.impl.server.others.NotifyFlagEvent;
import me.clientastisch.cardinal.extension.impl.event.EventListener;

public class FlagNotifyHandler implements EventListener {

    @EventManager.Target
    public void onFlag(NotifyFlagEvent event) {
        // Player has permission to see other flags
        if (event.getPlayer().hasPermission("cardinal.common.command"))
            return;

        // Player is suspect and sees his own flags
        if (event.getPlayer() == event.getSuspect())
            return;

        event.cancelled();
    }

}
