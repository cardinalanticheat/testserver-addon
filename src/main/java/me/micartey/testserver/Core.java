package me.micartey.testserver;

import me.clientastisch.cardinal.extension.Extension;
import me.clientastisch.cardinal.extension.impl.Addon;
import me.micartey.testserver.commands.GiveCommand;
import me.micartey.testserver.events.FlagNotifyHandler;
import me.micartey.testserver.events.InteractionHandler;
import me.micartey.testserver.events.JoinLeaveMessageHandler;
import me.micartey.testserver.events.PlayerJoinHandler;

public class Core implements Addon {

    @Override
    public void onEnable() {
        // Cardional Events
        Extension.registerListener(this, new FlagNotifyHandler());
        Extension.registerListener(this, new PlayerJoinHandler());

        // Bukkit Events
        Extension.registerListener(this, new JoinLeaveMessageHandler());
        Extension.registerListener(this, new InteractionHandler());

        // Commands
        Extension.registerCommand(this, new GiveCommand());
    }

    @Override
    public void onDisable() {

    }
}
