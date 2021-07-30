package net.forsaken_borders.mino.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.forsaken_borders.mino.App;
import net.forsaken_borders.mino.commands.rin.PortalRoom;
import net.forsaken_borders.mino.commands.rin.PurchasePortalRoom;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandRin implements CommandExecutor {
    public static final TextComponent RinSubcommandHelpMessage = new TextComponent(ChatColor.GRAY + "Below is a list of all Rin subcommands:\n");
    public static final TextComponent RinPortalRoom = new TextComponent(ChatColor.GOLD + "/rin portal_room:" + ChatColor.WHITE + " Teleports you to Rin's Portal Room.\n");
    public static final TextComponent RinPurchasePortalRoom = new TextComponent(ChatColor.GOLD + "/rin purchase_portal_room:" + ChatColor.WHITE + " Allows access to the portal room.");
    public static final TextComponent MissingPortalRoomPermission = new TextComponent(ChatColor.RED + "Error: You're missing the " + ChatColor.GRAY + "mino.rin.portal_room" + ChatColor.RED + " permission.");
    public static final TextComponent MissingPurchasePortalRoomPermission = new TextComponent(ChatColor.RED + "Error: You're missing the " + ChatColor.GRAY + "mino.rin.purchase_portal_room" + ChatColor.RED + " permission.");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            TextComponent chatMessage = App.Prefix.duplicate();
            chatMessage.addExtra(RinSubcommandHelpMessage);
            chatMessage.addExtra(RinPortalRoom);
            chatMessage.addExtra(RinPurchasePortalRoom);

            sender.spigot().sendMessage(chatMessage);
        } else {
            switch (args[0].toLowerCase()) {
            case "portal_room":
                if (sender.hasPermission("mino.rin.portal_room")) {
                    new PortalRoom().onCommand(sender, command, label, args);
                } else {
                    TextComponent chatMessage = App.Prefix.duplicate();
                    chatMessage.addExtra(MissingPortalRoomPermission);
                    sender.spigot().sendMessage(chatMessage);
                }
                break;
            case "purchase_portal_room":
                if (sender.hasPermission("mino.rin.purchase_portal_room")) {
                    new PurchasePortalRoom().onCommand(sender, command, label, args);
                } else {
                    TextComponent chatMessage = App.Prefix.duplicate();
                    chatMessage.addExtra(MissingPurchasePortalRoomPermission);
                    sender.spigot().sendMessage(chatMessage);
                }
                break;
            case "help":
            default:
                TextComponent chatMessage = App.Prefix.duplicate();
                chatMessage.addExtra(RinSubcommandHelpMessage);
                chatMessage.addExtra(RinPortalRoom);
                chatMessage.addExtra(RinPurchasePortalRoom);

                sender.spigot().sendMessage(chatMessage);
                break;
            }
        }
        return true;
    }
}
