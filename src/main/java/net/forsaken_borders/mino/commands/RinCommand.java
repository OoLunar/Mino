package net.forsaken_borders.mino.commands;

import com.earth2me.essentials.commands.WarpNotFoundException;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import net.ess3.api.InvalidWorldException;
import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;

@CommandAlias("rin")
@Description("Every command that every citizen of Rin has access to.")
public class RinCommand extends BaseCommand {
    @Subcommand("portal_room")
    @CommandPermission("mino.rin.portal_room")
    @Description("Teleports the player to Rin's portal room.")
    public void portal_room(Player player) {
        try {
            Location location = App.Essentials.getWarps().getWarp("rin_portal_room");
            Entity vehicle = player.getVehicle();
            if (vehicle != null) {
                vehicle.teleport(location);
            } else {
                player.teleport(location);
            }
        } catch (WarpNotFoundException | InvalidWorldException e) {
            player.sendMessage(App.Prefix + ChatColor.RED + "Error: The warp to the portal room has been deleted.");
        }
    }

    @Subcommand("purchase_portal_room")
    @CommandPermission("mino.rin.purchase_portal_room")
    @Description("Buys access to Rin's portal room.")
    public void purchase_portal_room(Player player) {
        if (player.hasPermission("mino.rin.portal_room")) {
            player.sendMessage(App.Prefix + ChatColor.RED + "Error: You already have access to the portal room.");
            return;
        }

        // TODO: Get price from config
        if (App.Economy.has(player, player.getWorld().getName(), 3000)) {
            App.Economy.withdrawPlayer(player, 3000);
            App.Permissions.playerAdd(null, player, "mino.rin.portal_room");
            player.sendMessage(ChatColor.GREEN + "At the cost of 3000" + App.Economy.currencyNameSingular() + ", you now have access to Rin's Portal Room! Use " + ChatColor.GRAY + "/rin portal_room" + ChatColor.GREEN + " to enter it.");
        } else {
            player.sendMessage(App.Prefix + ChatColor.RED + "Error: You're " + Math.round(3000 - App.Economy.getBalance(player)) + App.Economy.currencyNameSingular() + " short!");
        }
    }
}
