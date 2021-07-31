package net.forsaken_borders.mino.commands;

import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.forsaken_borders.mino.App;
import net.forsaken_borders.mino.event_listeners.OnSprint;
import net.md_5.bungee.api.ChatColor;

@CommandAlias("sprint")
@Description("Toggles your extra speed!")
public class SprintCommand extends BaseCommand {
    @Default
    @CommandPermission("mino.sprint")
    public void onDefault(Player player) {
        boolean wasEnabled = OnSprint.sprints.replace(player.getUniqueId(), !OnSprint.sprints.get(player.getUniqueId()));
        player.sendMessage(App.Prefix + (wasEnabled ? ChatColor.RED + "Sprint disabled." : ChatColor.GREEN + "Sprint enabled."));
    }
}
