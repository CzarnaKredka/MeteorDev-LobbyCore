package dev.meteordev.listeners.pvp;

import dev.meteordev.MeteorPlugin;
import dev.meteordev.config.MessageConfig;
import dev.meteordev.config.PluginConfig;
import dev.meteordev.utils.ChatUtil;
import dev.meteordev.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlayerSwordInteract implements Listener {

    private final PluginConfig pluginConfig;
    private final MessageConfig messageConfig;
    private final Set<UUID> pvpQueue = new HashSet<>();
    private final Map<UUID, Integer> holdSeconds = new HashMap<>();

    public PlayerSwordInteract(PluginConfig pluginConfig, MessageConfig messageConfig) {
        this.pluginConfig = pluginConfig;
        this.messageConfig = messageConfig;

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    UUID uuid = player.getUniqueId();

                    if (pvpQueue.contains(uuid)) continue;

                    if (isHoldingPvPSword(player)) {
                        int seconds = holdSeconds.getOrDefault(uuid, 6);
                        Map<String, Object> placeholders = Map.of("TIME", seconds);

                        messageConfig.onStartJoiningPvP.send(player, placeholders);
                        player.playSound(player.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP,1,1);

                        seconds--;
                        holdSeconds.put(uuid, seconds);

                        if (seconds <= 0) {
                            holdSeconds.remove(uuid);
                            pvpQueue.add(uuid);
                            setupPvP(player);
                        }
                    } else {
                        holdSeconds.remove(uuid);
                    }
                }
            }
        }.runTaskTimer(MeteorPlugin.getPlugin(), 0L, 20L);
    }

    private boolean isHoldingPvPSword(Player player) {
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();
        String targetName = this.pluginConfig.swordItem.toItemStack().getItemMeta().getDisplayName();

        return (mainHand != null && hasSameName(mainHand, targetName)) ||
                (offHand != null && hasSameName(offHand, targetName));
    }

    private boolean hasSameName(ItemStack item, String name) {
        if (item == null || !item.hasItemMeta()) return false;
        ItemMeta meta = item.getItemMeta();
        return meta.hasDisplayName() && meta.getDisplayName().equals(name);
    }

    @EventHandler
    public void onExitClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item == null) return;

        if (item.getType() == Material.BARRIER && pvpQueue.contains(player.getUniqueId())) {
            leavePvP(player);
            event.setCancelled(true);
        }
    }

    private void setupPvP(Player player) {
        player.getInventory().clear();

        player.getInventory().setHelmet(ItemUtil.of(Material.IRON_HELMET).setName(ChatUtil.fixColor("&#FFAE42⚠ &8| &#ffae42Zestaw pvp &8| &#FFAE42⚠")).addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).toItemStack());
        player.getInventory().setChestplate(ItemUtil.of(Material.IRON_CHESTPLATE).setName(ChatUtil.fixColor("&#FFAE42⚠ &8| &#ffae42Zestaw pvp &8| &#FFAE42⚠")).addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).toItemStack());
        player.getInventory().setLeggings(ItemUtil.of(Material.IRON_LEGGINGS).setName(ChatUtil.fixColor("&#FFAE42⚠ &8| &#ffae42Zestaw pvp &8| &#FFAE42⚠")).addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).toItemStack());
        player.getInventory().setBoots(ItemUtil.of(Material.IRON_BOOTS).setName(ChatUtil.fixColor("&#FFAE42⚠ &8| &#ffae42Zestaw pvp &8| &#FFAE42⚠")).addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).toItemStack());

        player.getInventory().setItem(0, ItemUtil.of(Material.DIAMOND_SWORD).setName(ChatUtil.fixColor("&#FFAE42⚠ &8| &#ffae42Zestaw pvp &8| &#FFAE42⚠")).addFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS).toItemStack());
        player.getInventory().setItem(8, ItemUtil.of(Material.BARRIER).setName(ChatUtil.fixColor("&cWyjdź z pvp")).toItemStack());

        updateVisibility(player);
        this.messageConfig.onJoinPvP.send(player);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);
    }

    private void updateVisibility(Player player) {
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (pvpQueue.contains(online.getUniqueId())) {
                player.showPlayer(MeteorPlugin.getPlugin(), online);
            } else {
                player.hidePlayer(MeteorPlugin.getPlugin(), online);
            }
        }
    }

    public void leavePvP(Player player) {
        UUID uuid = player.getUniqueId();
        if (!pvpQueue.contains(uuid)) return;

        pvpQueue.remove(uuid);
        player.getInventory().clear();

        player.getInventory().setItem(this.pluginConfig.chooseMode.getSlot(), this.pluginConfig.chooseMode.toItemStack());
        player.getInventory().setItem(this.pluginConfig.playerProfile.getSlot(), this.pluginConfig.playerProfile.toItemStack());
        player.getInventory().setItem(this.pluginConfig.swordItem.getSlot(), this.pluginConfig.swordItem.toItemStack());

        this.messageConfig.onLeavePvP.send(player);
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES,1,1);

        for (Player online : Bukkit.getOnlinePlayers()) {
            player.showPlayer(MeteorPlugin.getPlugin(), online);
        }
    }
}
