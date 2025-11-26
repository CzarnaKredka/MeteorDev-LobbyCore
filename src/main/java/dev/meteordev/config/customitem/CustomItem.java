package dev.meteordev.config.customitem;

import dev.meteordev.utils.ItemBuilderMix;
import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
@AllArgsConstructor
public class CustomItem extends OkaeriConfig {
    private ItemStack itemStack;
    private int customModelData;

    public ItemStack toItemStack() {
        return ItemBuilderMix.of(this.itemStack.clone()).setCmd(this.customModelData).toItemStack();
    }
}