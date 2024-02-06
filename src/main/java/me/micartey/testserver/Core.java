package me.micartey.testserver;

import me.clientastisch.cardinal.extension.Extension;
import me.clientastisch.cardinal.extension.impl.Addon;
import me.micartey.testserver.commands.GiveCommand;
import me.micartey.testserver.events.*;

public class Core implements Addon {

    public static Core INSTANCE;

    public Core() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        // Cardional Events
        Extension.registerListener(this, new FlagNotifyHandler());
        Extension.registerListener(this, new PlayerJoinHandler());

        // Bukkit Events
        Extension.registerListener(this, new JoinLeaveMessageHandler());
        Extension.registerListener(this, new InteractionHandler());
        Extension.registerListener(this, new PlayerDeathHandler());
        Extension.registerListener(this, new InventoryHandler());
        Extension.registerListener(this, new JumpadHandler());
        Extension.registerListener(this, new DamageHandler());

        // Commands
        Extension.registerCommand(this, new GiveCommand());
    }

    @Override
    public void onDisable() {

    }
}
