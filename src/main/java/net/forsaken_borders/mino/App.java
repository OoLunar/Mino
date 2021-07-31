package net.forsaken_borders.mino;

import java.util.logging.Logger;

import com.earth2me.essentials.Essentials;

import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import co.aikar.commands.PaperCommandManager;
import net.forsaken_borders.mino.commands.MapCommand;
import net.forsaken_borders.mino.commands.RinCommand;
import net.forsaken_borders.mino.commands.ShrugCommand;
import net.forsaken_borders.mino.commands.SprintCommand;
import net.forsaken_borders.mino.commands.ThrowCommand;
import net.forsaken_borders.mino.event_listeners.OnPlayerJoin;
import net.forsaken_borders.mino.event_listeners.OnPlayerQuit;
import net.forsaken_borders.mino.event_listeners.OnSprint;
import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class App extends JavaPlugin {
    public static String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.of("#7b84d1") + "Mino" + ChatColor.DARK_GRAY + "]" + ChatColor.RESET + " ";
    public static Logger Logger = null;
    public static Economy Economy = null;
    public static Permission Permissions = null;
    public static Essentials Essentials = null;

    @Override
    public void onEnable() {
        OnSprint.sprints.clear();
        for (Player p : getServer().getOnlinePlayers()) {
            OnSprint.sprints.put(p.getUniqueId(), false);
        }

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new MapCommand());
        manager.registerCommand(new ShrugCommand());
        manager.registerCommand(new SprintCommand());
        manager.registerCommand(new ThrowCommand());
        manager.registerCommand(new RinCommand());

        Logger = getLogger();
        this.getServer().getPluginManager().registerEvents(new OnSprint(), this);
        this.getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new OnPlayerQuit(), this);
        if (!vaultEnabled()) {
            Logger.warning("Vault is not enabled! Commands that require permissions or economy will be disabled.");
        }

        setupEconomy();
        setupPermissions();

        if (Economy == null) {
            Logger.warning("Economy is not enabled! Commands that require economy will be disabled.");
        }

        if (Permissions == null) {
            Logger.warning("Permissions are not enabled! Commands that require permissions will be disabled.");
        }

        Essentials = (Essentials) getServer().getPluginManager().getPlugin("Essentials");
        if (Essentials == null) {
            Logger.warning("Essentials is not enabled! Commands that require essentials will be disabled.");
        }

        if (Economy != null && Permissions != null && Essentials != null) {
            //manager.registerCommand(new CommandRin());
        }
    }

    private boolean vaultEnabled() {
        return getServer().getPluginManager().getPlugin("Vault") != null;
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> economyServiceProvider = getServer().getServicesManager().getRegistration(Economy.class);
        Economy = economyServiceProvider.getProvider();
    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        Permissions = rsp.getProvider();
    }
}