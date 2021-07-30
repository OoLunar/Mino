package net.forsaken_borders.mino.tab_completers;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabCompleterThrow implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> playerNames = new java.util.ArrayList<String>();
        for (Player p : sender.getServer().getOnlinePlayers()) {
            playerNames.add(p.getName());
        }
        return playerNames;
    }
}
