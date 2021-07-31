package net.forsaken_borders.mino.commands;

import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Optional;

@CommandAlias("shrug")
@Description("Shrug your worries away!")
public class ShrugCommand extends BaseCommand {
    @Default
    public void onDefault(Player player, @Optional String arg) {
        player.chat(arg == null ? "¯\\_(ツ)_/¯" : arg + " ¯\\_(ツ)_/¯");
    }
}
