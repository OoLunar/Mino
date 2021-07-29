package net.forsaken_borders.mino;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import net.forsaken_borders.mino.commands.CommandMap;
import net.forsaken_borders.mino.commands.CommandShrug;
import net.forsaken_borders.mino.commands.CommandThrow;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public class App extends JavaPlugin {
    public static TextComponent Prefix = null;
    public static Logger Logger = null;

    @Override
    public void onEnable() {
        Prefix = new TextComponent("[Mino] ");
        Prefix.setColor(ChatColor.RED);

        Logger = getLogger();
        Logger.info("Mino enabled!");
        this.getCommand("map").setExecutor(new CommandMap());
        this.getCommand("shrug").setExecutor(new CommandShrug());
        this.getCommand("throw").setExecutor(new CommandThrow());
    }

    @Override
    public void onDisable() {
        getLogger().info("Mino disabled!");
    }
}