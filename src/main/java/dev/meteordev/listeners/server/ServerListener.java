package dev.meteordev.listeners.server;

import dev.meteordev.config.MessageConfig;
import dev.meteordev.config.PluginConfig;
import org.bukkit.event.Listener;

public class ServerListener implements Listener {

    private final PluginConfig pluginConfig;
    private final MessageConfig messageConfig;

    public ServerListener(PluginConfig pluginConfig, MessageConfig messageConfig) {
        this.pluginConfig = pluginConfig;
        this.messageConfig = messageConfig;
    }
}
