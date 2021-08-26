package net.forsaken_borders.mino;

import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.earth2me.essentials.Essentials;
import com.onarandombox.MultiverseCore.MultiverseCore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import co.aikar.commands.PaperCommandManager;
import net.forsaken_borders.mino.commands.MapCommand;
import net.forsaken_borders.mino.commands.RinCommand;
import net.forsaken_borders.mino.commands.ShrugCommand;
import net.forsaken_borders.mino.commands.SprintCommand;
import net.forsaken_borders.mino.commands.ThrowCommand;
import net.forsaken_borders.mino.event_listeners.OnDamage;
import net.forsaken_borders.mino.event_listeners.OnPlayerJoin;
import net.forsaken_borders.mino.event_listeners.OnPlayerQuit;
import net.forsaken_borders.mino.event_listeners.OnServerListPing;
import net.forsaken_borders.mino.event_listeners.OnSprint;
import net.forsaken_borders.mino.timers.MiningWorldReset;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class App extends JavaPlugin {
    public static String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.of("#7b84d1") + "Mino" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + " ";
    public static Logger Logger;
    public static Economy Economy;
    public static Permission Permissions;
    public static Essentials Essentials;
    public static MultiverseCore MultiverseCore;
    public static Plugin Vault;

    @Override
    public void onEnable() {
        PaperCommandManager manager = new PaperCommandManager(this);
        ServicesManager servicesManager = Bukkit.getServicesManager();
        PluginManager pluginManager = Bukkit.getPluginManager();

        Logger = getLogger();
        Economy = servicesManager.getRegistration(Economy.class).getProvider();
        Permissions = servicesManager.getRegistration(Permission.class).getProvider();
        Essentials = (Essentials) pluginManager.getPlugin("Essentials");
        MultiverseCore = (MultiverseCore) pluginManager.getPlugin("Multiverse-Core");
        Vault = pluginManager.getPlugin("Vault");

        if(Vault == null) {
            Logger.severe("Vault is not installed! Vault is required for permissions and (optionally) economy.");
            Logger.severe("Disabling Mino...");
            pluginManager.disablePlugin(this);
            return;
        }

        manager.registerCommand(new MapCommand());
        manager.registerCommand(new ShrugCommand());
        manager.registerCommand(new SprintCommand());
        manager.registerCommand(new ThrowCommand());

        if(Essentials != null && Permissions != null && Economy != null) {
            manager.registerCommand(new RinCommand());
        } else {
            Logger.warning("Essentials, Vault, and Vault-Economy are required for Rin to work. Rin will not be able to use their commands!");
        }

        pluginManager.registerEvents(new OnSprint(), this);
        pluginManager.registerEvents(new OnPlayerJoin(), this);
        pluginManager.registerEvents(new OnPlayerQuit(), this);
        pluginManager.registerEvents(new OnDamage(), this);
        pluginManager.registerEvents(new OnServerListPing(), this);

        Timer timer = new Timer();
        timer.schedule(new MiningWorldReset(getServer()), TimeUnit.HOURS.toMillis(6));
    }
}