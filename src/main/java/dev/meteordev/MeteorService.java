package dev.meteordev;

import dev.meteordev.commands.admin.*;
import dev.meteordev.commands.handlers.InvalidCooldownHandler;
import dev.meteordev.commands.handlers.InvalidPermissionHandler;
import dev.meteordev.commands.handlers.InvalidUsageCmdHandler;
import dev.meteordev.commands.user.FeedCommand;
import dev.meteordev.commands.user.GammaCommand;
import dev.meteordev.commands.user.HealCommand;
import dev.meteordev.commands.user.WorkBenchCommand;
import dev.meteordev.config.MenusConfig;
import dev.meteordev.config.MessageConfig;
import dev.meteordev.config.PluginConfig;
import dev.meteordev.listeners.PlayerInteractListener;
import dev.meteordev.listeners.PlayerJoinListener;
import dev.meteordev.listeners.PlayerQuitListener;
import dev.meteordev.listeners.server.ServerListener;
import dev.meteordev.managers.ChatManager;
import dev.meteordev.utils.ChatUtil;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.LiteBukkitMessages;
import dev.rollczi.litecommands.message.LiteMessages;

public class MeteorService {

    private final MessageConfig messageConfig;
    private final PluginConfig pluginConfig;
    private final MenusConfig menusConfig;
    private final ChatManager chatManager;

    public MeteorService(MessageConfig messageConfig, PluginConfig pluginConfig, MenusConfig menusConfig, ChatManager chatManager) {
        this.messageConfig = messageConfig;
        this.pluginConfig = pluginConfig;
        this.menusConfig = menusConfig;
        this.chatManager = chatManager;

        // Implementation of core events in lobby:

        MeteorPlugin.getMeteorPlugin().getServer().getPluginManager().registerEvents(
                new ServerListener(
                        this.pluginConfig,
                        this.messageConfig
                ),
                MeteorPlugin.getMeteorPlugin()
        );

        MeteorPlugin.getMeteorPlugin().getServer().getPluginManager().registerEvents(
                new PlayerInteractListener(
                        this.pluginConfig,
                        this.menusConfig,
                        this.messageConfig
                ),
                MeteorPlugin.getMeteorPlugin()
        );

        MeteorPlugin.getMeteorPlugin().getServer().getPluginManager().registerEvents(
                new PlayerJoinListener(
                        this.pluginConfig,
                        this.messageConfig
                ),
                MeteorPlugin.getMeteorPlugin()
        );

        MeteorPlugin.getMeteorPlugin().getServer().getPluginManager().registerEvents(
                new PlayerQuitListener(
                        this.messageConfig
                ),
                MeteorPlugin.getMeteorPlugin()
        );


        // Implementation of core commands in lobby:

        LiteBukkitFactory.builder("meteordev")
                .commands(
                        new AdminChatCommand(this.messageConfig),
                        new AlertCommand(this.messageConfig),
                        new ChatCommand(this.messageConfig, this.chatManager),
                        new ClearCommand(this.messageConfig),
                        new FlyCommand(this.messageConfig),
                        new FlySpeedCommand(this.messageConfig),
                        new GamemodeCommand(this.messageConfig),
                        new SpeedCommand(this.messageConfig),
                        new SummonCommand(this.messageConfig),
                        new FeedCommand(this.messageConfig),
                        new GammaCommand(this.messageConfig),
                        new HealCommand(this.messageConfig),
                        new WorkBenchCommand()
                )
                .missingPermission(new InvalidPermissionHandler())
                .invalidUsage(new InvalidUsageCmdHandler())
                .message(LiteMessages.COMMAND_COOLDOWN, (invocation, cooldownState) ->
                        InvalidCooldownHandler.handleCooldown(invocation.sender(), cooldownState))
                .message(LiteBukkitMessages.PLAYER_NOT_FOUND, player ->
                        ChatUtil.fixColor("&#FF0000❌ &8| &cGracz &#FF0000" + player + " &cnie został odnaleziony!"))
                .build();
    }
}
