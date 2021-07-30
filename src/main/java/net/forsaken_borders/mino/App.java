package net.forsaken_borders.mino;

import java.util.logging.Logger;

import com.earth2me.essentials.Essentials;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.forsaken_borders.mino.commands.CommandMap;
import net.forsaken_borders.mino.commands.CommandRin;
import net.forsaken_borders.mino.commands.CommandShrug;
import net.forsaken_borders.mino.commands.CommandThrow;
import net.forsaken_borders.mino.tab_completers.TabCompleterRin;
import net.forsaken_borders.mino.tab_completers.TabCompleterThrow;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class App extends JavaPlugin {
    public static TextComponent Prefix = new TextComponent(ChatColor.RED + "[Mino] ");
    public static Logger Logger = null;
    public static Economy Economy = null;
    public static Permission Permissions = null;
    public static Essentials Essentials = null;

    @Override
    public void onEnable() {
        Logger = getLogger();
        this.getCommand("map").setExecutor(new CommandMap());
        this.getCommand("shrug").setExecutor(new CommandShrug());
        this.getCommand("throw").setExecutor(new CommandThrow());
        this.getCommand("throw").setTabCompleter(new TabCompleterThrow());
        if (vaultEnabled()) {
            setupEconomy();
            setupPermissions();

            if (Economy == null) {
                Logger.warning("Economy is not enabled! Commands that require economy will be disabled.");
            }

            if (Permissions == null) {
                Logger.warning("Permissions are not enabled! Commands that require permissions will be disabled.");
            }

            boolean essentialsEnabled = essentialsEnabled();

            if (!essentialsEnabled) {
                Logger.warning("Essentials is not enabled! Commands that require essentials will be disabled.");
            } else {
                Essentials = (Essentials) getServer().getPluginManager().getPlugin("Essentials");
            }

            if (Economy != null && Permissions != null && Essentials != null) {
                this.getCommand("rin").setExecutor(new CommandRin());
                this.getCommand("rin").setTabCompleter(new TabCompleterRin());
            }
        } else {
            Logger.warning("Vault is not enabled! Commands that require permissions or economy will be disabled.");
        }
    }

    private boolean vaultEnabled() {
        return getServer().getPluginManager().getPlugin("Vault") != null;
    }

    private boolean essentialsEnabled() {
        return getServer().getPluginManager().getPlugin("Essentials") != null;
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