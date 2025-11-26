package dev.meteordev;

import dev.meteordev.commands.handlers.InvalidCooldownHandler;
import dev.meteordev.commands.handlers.InvalidPermissionHandler;
import dev.meteordev.commands.handlers.InvalidUsageCmdHandler;
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


        // Implementation of core commands in lobby:

        LiteBukkitFactory.builder("meteordev")
                .commands(

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
