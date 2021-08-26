package net.forsaken_borders.mino.event_listeners;

import java.util.UUID;

import com.earth2me.essentials.commands.WarpNotFoundException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import net.ess3.api.InvalidWorldException;
import net.forsaken_borders.mino.App;
import net.md_5.bungee.api.ChatColor;

public class OnPlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        OnSprint.sprints.put(player.getUniqueId(), false);

        if (!player.hasPlayedBefore()) {
            try {
                player.teleport(App.Essentials.getWarps().getWarp("spawn"));
            } catch (WarpNotFoundException | InvalidWorldException e) {
                player.sendMessage(App.Prefix + "Uh oh! There was an error teleporting you to the spawn. Please inform Lunar#9860 of this issue on Discord!");
            }

            //player.performCommand("mino:continent info");
        } else if (player.getUniqueId() == UUID.fromString("ee6d1622-f764-44d2-bb31-f5331f194166")) {
            for (Player onlinePlayer : player.getServer().getOnlinePlayers()) {
                for (ItemStack itemStack : onlinePlayer.getInventory().getContents()) {
                    if (itemStack != null) {
                        ItemMeta meta = itemStack.getItemMeta();
                        if (meta instanceof Damageable damageable) {
                            damageable.setDamage(0);
                            itemStack.setItemMeta(meta);
                        }
                    }
                }

                onlinePlayer.sendMessage(App.Prefix + ChatColor.GOLD + "Lunar's passive skill, The Lord's Grace, has repaired all your items.");
            }
        }
    }
}
