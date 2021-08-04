package net.forsaken_borders.mino.event_listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;

public class OnDamage implements Listener {
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (player.hasPermission("mino.skills.bat_wings")) {
                    event.setDamage(event.getDamage() / 2);
                    player.sendActionBar(Component.text(ChatColor.GRAY + "Bat Wings: Halved your fall damage!"));
                }
            }
        }
    }
}
