package me.micartey.testserver;

import me.clientastisch.cardinal.extension.Extension;
import me.clientastisch.cardinal.extension.impl.Addon;
import me.micartey.testserver.commands.AnticheatCommand;
import me.micartey.testserver.commands.GiveCommand;
import me.micartey.testserver.events.*;
import me.micartey.testserver.inventories.AnticheatSelectInventory;

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

        Extension.registerListener(this, new AnticheatSelectInventory());

        // Commands
        Extension.registerCommand(this, new GiveCommand());
        Extension.registerCommand(this, new AnticheatCommand());
    }

    @Override
    public void onDisable() {

    }
}
