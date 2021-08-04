package net.forsaken_borders.mino.commands;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;

@CommandAlias("shop")
@CommandPermission("mino.admin.shop")
public class ShopCommand extends BaseCommand {
    public class HomeCommand extends BaseCommand {
        @Subcommand("set")
        @CommandPermission("mino.admin.shop.home.set")
        public void setHome(Player player, int homeLimit) {
            if (homeLimit < -1) {
                player.sendMessage(App.Prefix + ChatColor.RED + "Error: " + homeLimit + " is an invalid number! Use -1 to remove the home limit, or any positive number between 1 and 10 to set the home limit.");
                return;
            }

            if (player.hasPermission("essentials.sethome.multiple.home_" + homeLimit)) {
                player.sendMessage(App.Prefix + ChatColor.RED + "Error: " + player.displayName() + " already has " + homeLimit + " homes!");
                return;
            }

            // This requires the Essentials home section of the plugin to be configured correctly
            Set<PermissionAttachmentInfo> permissions = player.getEffectivePermissions();
            permissions.removeIf(filter -> filter.getPermission().startsWith("essentials.sethome.multiple."));

            if (homeLimit == -1) {
                App.Permissions.playerAdd(player, "essentials.sethome.multiple.unlimited");
                player.sendMessage(App.Prefix + ChatColor.GREEN + "Your home limit has been removed! You can set unlimited homes now!");
            } else {
                App.Permissions.playerAdd(player, "essentials.sethome.multiple.home_" + homeLimit);
                player.sendMessage(App.Prefix + ChatColor.GREEN + "Your home limit has been set to " + homeLimit + "/10!");
            }
        }

        @Subcommand("add")
        @CommandPermission("mino.admin.shop.home.add")
        public void addHome(Player player) {
            if (player.hasPermission("essentials.sethome.multiple.unlimited")) {
                player.sendMessage(App.Prefix + ChatColor.RED + "Error: You have unlimited homes!");
                App.Economy.depositPlayer(player, 10000);
                return;
            }

            for (int i = 1; i <= 10; i++) {
                if (!player.hasPermission("essentials.sethome.multiple.home_" + i)) {
                    App.Permissions.playerAdd(player, "essentials.sethome.multiple.home_" + i);
                    player.sendMessage(App.Prefix + ChatColor.GREEN + "Your home limit has been set to " + i + "/10!");
                    return;
                }
            }

            player.sendMessage(App.Prefix + ChatColor.RED + "Error: You already have 10 homes!");
            App.Economy.depositPlayer(player, 10000);
        }

        @Subcommand("remove")
        @CommandPermission("mino.admin.shop.home.remove")
        public void removeHome(Player player) {
            if (player.hasPermission("essentials.sethome.multiple.unlimited")) {
                App.Permissions.playerRemove(player, "essentials.sethome.multiple.unlimited");
                player.sendMessage(App.Prefix + ChatColor.RED + "Your unlimited homes havve been reduced to 10!");
            }

            for (int i = 1; i <= 10; i++) {
                if (!player.hasPermission("essentials.sethome.multiple.home_" + i)) {
                    App.Permissions.playerRemove(player, "essentials.sethome.multiple.home_" + i);
                    player.sendMessage(App.Prefix + ChatColor.GREEN + "Your home limit has been reduced to " + (i - 1) + "/10!");
                    App.Economy.depositPlayer(player, 10000);
                    return;
                }
            }
        }
    }
}
