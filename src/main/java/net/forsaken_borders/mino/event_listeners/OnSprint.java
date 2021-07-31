package net.forsaken_borders.mino.event_listeners;

import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class OnSprint implements Listener {
    public static Hashtable<UUID, Boolean> sprints = new Hashtable<UUID, Boolean>();

    @EventHandler
    public void onSprint(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("mino.sprint")) {
            return;
        }

        if (sprints.get(player.getUniqueId())) {
            if (event.isSprinting()) {
                player.setWalkSpeed(0.3F);
            } else {
                player.setWalkSpeed(0.2F);
            }
        }
    }
}
