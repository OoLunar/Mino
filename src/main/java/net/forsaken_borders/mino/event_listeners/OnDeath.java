package net.forsaken_borders.mino.event_listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        // TODO: Remove warnings
        if (killer != null) {
            event.deathMessage().replaceText(killer.getName(), killer.displayName());
        }

        Player player = event.getEntity();
        event.deathMessage().replaceText(player.getName(), player.displayName());
    }
}
