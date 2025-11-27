package dev.meteordev.listeners;

import dev.meteordev.config.MenusConfig;
import dev.meteordev.config.MessageConfig;
import dev.meteordev.config.PluginConfig;
import dev.meteordev.menus.PlayerProfileMenu;
import dev.meteordev.utils.ChatUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerInteractListener implements Listener {

    private final PluginConfig pluginConfig;
    private final MenusConfig menusConfig;
    private final MessageConfig messageConfig;

    public PlayerInteractListener(PluginConfig pluginConfig, MenusConfig menusConfig, MessageConfig messageConfig) {
        this.pluginConfig = pluginConfig;
        this.menusConfig = menusConfig;
        this.messageConfig = messageConfig;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = e.getItem();

        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;
        if (item == null || item.getType() == Material.AIR || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        String itemName = ChatUtil.fixColor(meta.getDisplayName());

        // Uses values

        if (itemName.equalsIgnoreCase(this.pluginConfig.chooseMode.toItemStack().getItemMeta().getDisplayName())) {
            e.setCancelled(true);
            player.sendMessage("§aOtwieram menu trybów...");
        } else if (itemName.equalsIgnoreCase(this.pluginConfig.playerProfile.toItemStack().getItemMeta().getDisplayName())) {

            new PlayerProfileMenu(this.menusConfig, this.messageConfig).openMainMenu(player);

            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrag(InventoryDragEvent e) {
        Player player = (Player) e.getWhoClicked();

        // Checks player is op

        if (player.isOp()) { return; }

        // Cancelled drags items inventory

        e.setCancelled(true);

        player.sendTitle(
                ChatUtil.fixColor("&4&lx"),
                ChatUtil.fixColor("&cNie możesz przeciągać przedmiotów")
        );

        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        // Checks player is op

        if (player.isOp()) { return; }

        // Cancelled clicks items inventory

        e.setCancelled(true);

        player.sendTitle(
                ChatUtil.fixColor("&4&lx"),
                ChatUtil.fixColor("&cNie możesz przeciągać przedmiotów")
        );

        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();


        // Cancelled drops items inventory

        e.setCancelled(true);

        player.sendTitle(
                ChatUtil.fixColor("&4&lx"),
                ChatUtil.fixColor("&cNie możesz wyrzucać przedmiotów")
        );

        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
    }



    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();

        // Cancelled place items inventory

        e.setCancelled(true);

        player.sendTitle(
                ChatUtil.fixColor("&4&lx"),
                ChatUtil.fixColor("&cNie możesz stawiać przedmiotów")
        );

        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        ItemMeta meta = this.pluginConfig.playerProfile.toItemStack().getItemMeta();

        // Checks actually meta of item and adding texture player head

        if (meta instanceof SkullMeta skullMeta) {
            skullMeta.setOwningPlayer(player);
            this.pluginConfig.playerProfile.toItemStack().setItemMeta(skullMeta);
        }

        // Adds items to player when joins server

        player.getInventory().setItem(this.pluginConfig.chooseMode.getSlot(), this.pluginConfig.chooseMode.toItemStack());
        player.getInventory().setItem(this.pluginConfig.playerProfile.getSlot(), this.pluginConfig.playerProfile.toItemStack());
        player.getInventory().setItem(this.pluginConfig.swordItem.getSlot(), this.pluginConfig.swordItem.toItemStack());
    }
}
