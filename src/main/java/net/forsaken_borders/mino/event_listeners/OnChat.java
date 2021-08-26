package net.forsaken_borders.mino.event_listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.TextReplacementConfig;
import net.md_5.bungee.api.ChatColor;

public class OnChat implements Listener {
    @EventHandler
    public void onChat (AsyncChatEvent event) {
        TextReplacementConfig textReplacementConfig = TextReplacementConfig.builder()
            // Custom heart emoji
            .match("<3")
            .replacement(builder -> builder.content(ChatColor.RED + "❤" + ChatColor.RESET))
            // Discord shrug replacement
            .match("/shrug")
            .replacement("¯\\_(ツ)_/¯")
            // Custom economy replacement. TODO: Support other currency symbols
            .match("$")
            .replacement("ẓ")
            .build();
        event.message(event.message().replaceText(textReplacementConfig));
    }
}
