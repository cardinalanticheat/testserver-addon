package me.micartey.testserver;

import me.clientastisch.cardinal.extension.Extension;
import me.clientastisch.cardinal.extension.impl.Addon;
import me.micartey.testserver.events.FlagNotifyHandler;

public class Core implements Addon {

    @Override
    public void onEnable() {
        Extension.registerListener(this, new FlagNotifyHandler());
    }

    @Override
    public void onDisable() {

    }
}
