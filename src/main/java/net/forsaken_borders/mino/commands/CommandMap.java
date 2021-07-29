package net.forsaken_borders.mino.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandMap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        TextComponent textComponent = new TextComponent("https://mc.forsaken-borders.net/dynmap/");
        textComponent.setColor(ChatColor.GRAY);
        TextComponent chatMessage = App.Prefix.duplicate();
        chatMessage.addExtra(textComponent);

        sender.spigot().sendMessage(chatMessage);
        return true;
    }
}
