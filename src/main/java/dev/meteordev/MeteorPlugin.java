package dev.meteordev;

import dev.meteordev.config.MenusConfig;
import dev.meteordev.config.MessageConfig;
import dev.meteordev.config.PluginConfig;
import dev.meteordev.managers.ChatManager;
import eu.okaeri.platform.bukkit.OkaeriBukkitPlugin;
import eu.okaeri.platform.core.annotation.Register;
import eu.okaeri.platform.core.plan.ExecutionPhase;
import eu.okaeri.platform.core.plan.Planned;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

// Configuration

@Register(MenusConfig.class)
@Register(MessageConfig.class)
@Register(PluginConfig.class)

// Managers
@Register(ChatManager.class)
public final class MeteorPlugin extends OkaeriBukkitPlugin {

    @Getter private static MeteorPlugin meteorPlugin;

    @Inject private MenusConfig menusConfig;
    @Inject private MessageConfig messageConfig;
    @Inject private PluginConfig pluginConfig;
    @Inject private ChatManager chatManager;

    @Planned(ExecutionPhase.PRE_SETUP)
    public void preSetup() {
        meteorPlugin = this;
    }

    @Planned(ExecutionPhase.STARTUP)
    public void startUp() {

        final List<String> authors = Arrays.asList("CzarnaKredka", "Senbitek");
        final List<String> actualAuthors = meteorPlugin.getDescription().getAuthors();

        // Checks authors main plugin [ CzarnaKredka, Senbitek ]

        if (!actualAuthors.containsAll(authors) || actualAuthors.size() != authors.size()) {
            getLogger().warning("");
            getLogger().warning("-----------------------");
            getLogger().warning("ZMIENIONO AUTHORA PLUGINU!");
            getLogger().warning("");
            getLogger().warning("WYŁĄCZAM PLUGIN (...)");
            getLogger().warning("-----------------------");
            getLogger().warning("");
            meteorPlugin.getServer().getPluginManager().disablePlugin(meteorPlugin);
        }

        // Checks actually plugin name [ MeteorDev-LobbyCore ] :

        if (!meteorPlugin.getDescription().getName().contains("MeteorDev-LobbyCore")) {
            getLogger().log(Level.WARNING, "");
            getLogger().log(Level.WARNING, "-----------------------");
            getLogger().log(Level.WARNING, "ZMIENIONO NAZWĘ PLUGINU!");
            getLogger().log(Level.WARNING, "");
            getLogger().log(Level.WARNING, "WYŁĄCZAM PLUGIN (...)");
            getLogger().log(Level.WARNING, "-----------------------");
            getLogger().log(Level.WARNING, "");
            meteorPlugin.getServer().getPluginManager().disablePlugin(meteorPlugin);
        }

        // Implementation of service managment plugin:

        new MeteorService(
                this.messageConfig,
                this.pluginConfig,
                this.menusConfig,
                this.chatManager
        );
    }
}
