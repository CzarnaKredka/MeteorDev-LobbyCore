package dev.meteordev.menus;

import dev.meteordev.config.MenusConfig;
import dev.meteordev.config.MessageConfig;
import dev.meteordev.config.customitem.MenuDecoration;
import dev.meteordev.utils.ChatUtil;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerProfileMenu {

    private final MenusConfig menusConfig;
    private final MessageConfig messageConfig;

    private final Map<UUID, Integer> clickMap = new ConcurrentHashMap<>();

    public PlayerProfileMenu(MenusConfig menusConfig, MessageConfig messageConfig) {
        this.menusConfig = menusConfig;
        this.messageConfig = messageConfig;
    }

    public void openMainMenu(Player player) {
        Gui gui = Gui.gui()
                .title(Component.text(ChatUtil.fixColor(this.menusConfig.warpsMenu.getTitle())))
                .rows(this.menusConfig.warpsMenu.getRows())
                .create();

        for (MenuDecoration decoration : this.menusConfig.warpsMenu.getDecorations()) {
            GuiItem item = ItemBuilder.from(decoration.getMaterial())
                    .setName(ChatUtil.fixColor(decoration.getName()))
                    .asGuiItem();

            for (int slot : decoration.getSlots()) {
                gui.setItem(slot, item);
            }
        }


        GuiItem close = ItemBuilder.from(Material.BARRIER)
                .setName(ChatUtil.fixColor("&cZamknij!"))
                .asGuiItem(event -> {
                    player.closeInventory();

                    player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK,1,1);
                });

        gui.setItem(49, close);


        gui.setDefaultClickAction(e -> {
            e.setCancelled(true);

            UUID uuid = player.getUniqueId();
            int clicks = this.clickMap.getOrDefault(uuid, 0) + 1;

            if (clicks >= 4) {
                player.sendActionBar(ChatUtil.fixColor("&cGdzie ci się tak &4śpieszy?"));
                this.clickMap.put(uuid, 0);
            } else {
                this.clickMap.put(uuid, clicks);
            }
        });

        gui.open(player);
    }
}
