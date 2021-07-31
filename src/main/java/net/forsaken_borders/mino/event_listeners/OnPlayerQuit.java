package net.forsaken_borders.mino.event_listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        OnSprint.sprints.remove(event.getPlayer().getUniqueId());
    }
}
