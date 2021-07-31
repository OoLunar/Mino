package net.forsaken_borders.mino.commands;

import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;

@CommandAlias("map")
@Description("Sends the Dynmap link.")
public class MapCommand extends BaseCommand {
    @Default
    public void onDefault(Player player) {
        player.sendMessage(App.Prefix + ChatColor.GRAY + "https://mc.forsaken-borders.net/dynmap/");
    }
}
