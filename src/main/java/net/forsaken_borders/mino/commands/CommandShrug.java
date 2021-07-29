package net.forsaken_borders.mino.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandShrug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String message = String.join(" ", args).trim().concat(" ¯\\_(ツ)_/¯");
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.chat(message);
            return true;
        } else {
            TextComponent chatMessage = App.Prefix.duplicate();
            chatMessage.addExtra("Error: Only players can use this command!");
            sender.spigot().sendMessage(chatMessage);
            return true;
        }
    }
}
