package dev.meteordev.commands.admin;

import dev.meteordev.config.MessageConfig;
import dev.meteordev.utils.ConsoleMessageUtil;
import dev.meteordev.utils.ConsoleUtil;
import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import eu.okaeri.injector.annotation.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

@Command(name = "speed")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class SpeedCommand {

    private final MessageConfig messageConfig;

    @Execute
    public void onUseSpeed(@Context CommandSender commandSender, @Arg("speed") int speed) {

        if (commandSender instanceof Player player) {

            Map<String, Object> placeholders = Map.of("SPEED", speed);

            player.setWalkSpeed(speed / 10f);

            this.messageConfig.onSpeedUse.send(player, placeholders);

            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);


        } else {
            ConsoleMessageUtil.logMessage(ConsoleUtil.RED, "Ta komenda jest do użycia tylko dla gracza!");
        }
    }

    @Execute
    public void onUseSpeedOthers(@Context CommandSender commandSender, @Arg("speed") int speed, @Arg("nick") Player target) {
        if (commandSender instanceof Player player) {

            Map<String, Object> placeholders = Map.of("SPEED", speed, "TARGET", target.getName());

            target.setWalkSpeed(speed / 10f);

            this.messageConfig.onSpeedUsePlayer.send(player, placeholders);

            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);

        } else {
            ConsoleMessageUtil.logMessage(ConsoleUtil.RED, "Ta komenda jest do użycia tylko dla gracza!");
        }
    }
}
