package dev.meteordev.commands.args;

import dev.meteordev.config.PluginConfig;
import dev.meteordev.managers.GeneratorSpecManager;
import dev.rollczi.litecommands.argument.Argument;
import dev.rollczi.litecommands.argument.parser.ParseResult;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolver;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.suggestion.SuggestionContext;
import dev.rollczi.litecommands.suggestion.SuggestionResult;
import org.bukkit.command.CommandSender;

public class GeneratorArgument extends ArgumentResolver<CommandSender, GeneratorSpecManager> {

    private final PluginConfig config;

    public GeneratorArgument(PluginConfig config) {
        this.config = config;
    }

    @Override
    protected ParseResult<GeneratorSpecManager> parse(
            Invocation<CommandSender> invocation,
            Argument<GeneratorSpecManager> argument,
            String input
    ) {
        if (!config.generators.containsKey(input)) {
            return ParseResult.failure("Generator '" + input + "' nie istnieje!");
        }

        return ParseResult.success(new GeneratorSpecManager(input));
    }

    @Override
    public SuggestionResult suggest(
            Invocation<CommandSender> invocation,
            Argument<GeneratorSpecManager> argument,
            SuggestionContext context
    ) {
        return config.generators.keySet()
                .stream()
                .collect(SuggestionResult.collector());
    }
}
