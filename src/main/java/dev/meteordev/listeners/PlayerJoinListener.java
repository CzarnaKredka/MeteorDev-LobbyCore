package dev.meteordev.listeners;

import dev.meteordev.config.MessageConfig;
import dev.meteordev.config.PluginConfig;
import dev.meteordev.config.message.Message;
import dev.meteordev.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Map;

public class PlayerJoinListener implements Listener {

    private final PluginConfig pluginConfig;
    private final MessageConfig messageConfig;

    public PlayerJoinListener(PluginConfig pluginConfig, MessageConfig messageConfig) {
        this.pluginConfig = pluginConfig;
        this.messageConfig = messageConfig;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        ItemMeta meta = this.pluginConfig.playerProfile.toItemStack().getItemMeta();

        Map<String, Object> placeholders = Map.of("TARGET", player.getName());

        // Checks actually meta of item and adding texture player head

        if (meta instanceof SkullMeta skullMeta) {
            skullMeta.setOwningPlayer(player);
            this.pluginConfig.playerProfile.toItemStack().setItemMeta(skullMeta);
        }

        // Adds items to player when joins server

        player.getInventory().setItem(this.pluginConfig.chooseMode.getSlot(), this.pluginConfig.chooseMode.toItemStack());
        player.getInventory().setItem(this.pluginConfig.playerProfile.getSlot(), this.pluginConfig.playerProfile.toItemStack());
        player.getInventory().setItem(this.pluginConfig.swordItem.getSlot(), this.pluginConfig.swordItem.toItemStack());

        // Sends message when player joins to server

        final Message joinNewPlayerMessage = this.messageConfig.onJoinNewPlayer;
        final Message joinPlayerMessage = this.messageConfig.onJoinPlayer;

        String message;

        if (player.hasPlayedBefore()) {
            message = ChatUtil.fixColor(joinPlayerMessage.getMessage(), placeholders);
        } else {
            message = ChatUtil.fixColor(joinNewPlayerMessage.getMessage(), placeholders);
        }

        e.setJoinMessage(message);
    }
}
