package net.forsaken_borders.mino.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandThrow implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            TextComponent chatMessage = App.Prefix.duplicate();
            chatMessage.addExtra("Usage: /throw <player>");
            sender.spigot().sendMessage(chatMessage);
            return true;
        }

        Player player = Bukkit.getPlayerExact(args[0]);
        if (player == null) {
            TextComponent chatMessage = App.Prefix.duplicate();
            chatMessage.addExtra("Error: Player not found");
            sender.spigot().sendMessage(chatMessage);
            return true;
        }

        Location location = player.getLocation();
        float playerPitch = location.getPitch();

        location.setPitch(-90);
        player.setVelocity(location.getDirection().multiply(new Vector(0, 7, 0)));
        location.setPitch(playerPitch);

        player.getWorld().strikeLightningEffect(location);
        return true;
    }
}
