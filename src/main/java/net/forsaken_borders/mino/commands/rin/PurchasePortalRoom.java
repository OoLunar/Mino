package net.forsaken_borders.mino.commands.rin;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.chat.TextComponent;

public class PurchasePortalRoom {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        OfflinePlayer offlinePlayer = null;

        if (sender instanceof OfflinePlayer) {
            offlinePlayer = (OfflinePlayer) sender;
        } else {
            if (args.length == 0) {
                App.Prefix.addExtra(new TextComponent(ChatColor.RED + "Error: This command can only be used by players!"));
            } else {
                // TODO: Fix depreciated method
                offlinePlayer = sender.getServer().getOfflinePlayer(args[0]);
            }
        }

        // TODO: Grab required amount from config
        TextComponent prefix = App.Prefix.duplicate();
        if (App.Economy.has(offlinePlayer, 3000)) {
            App.Economy.withdrawPlayer(offlinePlayer, 3000);
            App.Permissions.playerAdd(null, offlinePlayer, "mino.rin.portal_room");
            if (offlinePlayer.isOnline()) {
                prefix.addExtra(new TextComponent(ChatColor.GREEN + "At the cost of 3000" + App.Economy.currencyNameSingular() + ", you now have access to Rin's Portal Room! Use " + ChatColor.GRAY + "/rin portal_room" + ChatColor.GREEN + " to enter it."));
                offlinePlayer.getPlayer().spigot().sendMessage(prefix);
            } else {
                prefix.addExtra(new TextComponent(ChatColor.GREEN + "At the cost of 3000" + App.Economy.currencyNameSingular() + " " + offlinePlayer.getName() + " now has access to Rin's Portal Room!"));
                sender.spigot().sendMessage(prefix);
            }
        } else {
            if (offlinePlayer.isOnline()) {
                prefix.addExtra(new TextComponent(ChatColor.RED + "Error: You're " + Math.round(3000 - App.Economy.getBalance(offlinePlayer)) + App.Economy.currencyNameSingular() + " short!"));
                offlinePlayer.getPlayer().spigot().sendMessage(prefix);
            } else {
                prefix.addExtra(new TextComponent(ChatColor.RED + "Error: Player " + offlinePlayer.getName() + " is " + Math.round(3000 - App.Economy.getBalance(offlinePlayer)) + App.Economy.currencyNameSingular() + " short!"));
                sender.spigot().sendMessage(prefix);
            }
        }

        return true;
    }
}
