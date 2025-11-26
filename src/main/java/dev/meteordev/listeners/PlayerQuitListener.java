package dev.meteordev.listeners;

import dev.meteordev.config.MessageConfig;
import dev.meteordev.config.message.Message;
import dev.meteordev.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

public class PlayerQuitListener implements Listener {

    private final MessageConfig messageConfig;

    public PlayerQuitListener(MessageConfig messageConfig) {
        this.messageConfig = messageConfig;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        Map<String, Object> placeholders = Map.of("TARGET", player.getName());

        // Sends message when player quits server

        final Message quitPlayerMessage = this.messageConfig.onQuitPlayer;

        String message;

        message = ChatUtil.fixColor(quitPlayerMessage.getMessage(), placeholders);

        e.setQuitMessage(message);
    }
}
