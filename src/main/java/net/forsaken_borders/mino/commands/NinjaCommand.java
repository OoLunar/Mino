package net.forsaken_borders.mino.commands;

import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandIssuer;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;

@CommandAlias("ninja")
@CommandPermission("mino.commands.build")
@Description("Removes all traces of your location frmo Dynmap for 5 minutes!")
public class NinjaCommand extends BaseCommand {
    @Default
    public void onDefault() {
        CommandIssuer commandIssuer = getCurrentCommandIssuer();
        if(!(commandIssuer instanceof Player player)) {
            commandIssuer.sendMessage(App.Prefix + ChatColor.RED + "Error: Only players can use this command!");
            return;
        }

        if(App.DynmapAPI.getPlayerVisbility(player)) {
            commandIssuer.sendMessage(App.Prefix + ChatColor.RED + "Error: You are already in ninja mode!");
        } else {
            App.DynmapAPI.setPlayerVisiblity(player, false);
            commandIssuer.sendMessage(App.Prefix + "You are now in ninja mode for the next five minutes!");
            try {
                TimeUnit.MINUTES.sleep(5);
            } catch(final InterruptedException exception)
            {
                Thread.currentThread().interrupt();
            }
            App.DynmapAPI.setPlayerVisiblity(player, true);
            commandIssuer.sendMessage(App.Prefix + "You are no longer in ninja mode!");
        }
    }
}
