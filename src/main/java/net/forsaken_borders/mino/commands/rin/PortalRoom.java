package net.forsaken_borders.mino.commands.rin;

import com.earth2me.essentials.commands.WarpNotFoundException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.ess3.api.InvalidWorldException;
import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class PortalRoom {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                Location location = App.Essentials.getWarps().getWarp("rin_portal_room");
                Entity vehicle = player.getVehicle();
                if (vehicle != null) {
                    vehicle.teleport(location);
                } else {
                    player.teleport(location);
                }
            } catch (WarpNotFoundException | InvalidWorldException e) {
                TextComponent prefix = App.Prefix.duplicate();
                prefix.addExtra(new TextComponent(ChatColor.RED + "Error: The warp to the portal room has been deleted."));
                sender.spigot().sendMessage(prefix);
            }
        } else {
            TextComponent prefix = App.Prefix.duplicate();
            prefix.addExtra(new TextComponent(ChatColor.RED + "Error: You must be a player to warp to the portal room!"));
            sender.spigot().sendMessage(prefix);
        }
        return true;
    }
}
