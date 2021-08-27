package net.forsaken_borders.mino.event_listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.TextReplacementConfig.Builder;

public class OnDeath implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();
        Builder textReplacementBuilder = TextReplacementConfig.builder()
            .match(player.getName())
            .replacement(player.displayName());

        if (killer != null) {
            textReplacementBuilder = textReplacementBuilder
                .match(killer.getName())
                .replacement(killer.displayName());
        }

        event.deathMessage(event.deathMessage().replaceText(textReplacementBuilder.build()));
    }
}
