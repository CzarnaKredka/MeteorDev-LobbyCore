package dev.meteordev.commands.args;

import dev.meteordev.managers.MaterialSpecManager;
import dev.rollczi.litecommands.argument.Argument;
import dev.rollczi.litecommands.argument.parser.ParseResult;
import dev.rollczi.litecommands.argument.resolver.ArgumentResolver;
import dev.rollczi.litecommands.invocation.Invocation;
import dev.rollczi.litecommands.suggestion.SuggestionContext;
import dev.rollczi.litecommands.suggestion.SuggestionResult;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaterialArgument extends ArgumentResolver<CommandSender, MaterialSpecManager> {

    @Override
    protected ParseResult<MaterialSpecManager> parse(
            Invocation<CommandSender> invocation,
            Argument<MaterialSpecManager> argument,
            String input
    ) {
        String[] parts = input.split(",");
        List<String> valid = new ArrayList<>();
        List<String> invalid = new ArrayList<>();

        for (String part : parts) {
            String mat = part.trim().toUpperCase();
            if (mat.isEmpty()) continue;
            if (Material.matchMaterial(mat) != null) {
                valid.add(mat);
            } else {
                invalid.add(mat);
            }
        }

        if (!invalid.isEmpty()) {
            return ParseResult.failure("Nieprawidłowe materiały: " + String.join(", ", invalid));
        }

        if (valid.isEmpty()) {
            return ParseResult.failure("Nie podano żadnego poprawnego materiału!");
        }

        return ParseResult.success(new MaterialSpecManager(valid));
    }

    @Override
    public SuggestionResult suggest(
            Invocation<CommandSender> invocation,
            Argument<MaterialSpecManager> argument,
            SuggestionContext context
    ) {
        String current = context.getCurrent().toString();

        String[] split = current.split(",");
        String last = split[split.length - 1].trim().toUpperCase();

        return Arrays.stream(Material.values())
                .map(Material::name)
                .filter(mat -> mat.startsWith(last))
                .map(mat -> {
                    if (split.length > 1) {
                        String prefix = String.join(",", Arrays.copyOf(split, split.length - 1));
                        return prefix + "," + mat;
                    } else {
                        return mat;
                    }
                })
                .collect(SuggestionResult.collector());
    }

}
