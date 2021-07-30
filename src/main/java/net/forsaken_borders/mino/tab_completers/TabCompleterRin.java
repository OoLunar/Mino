package net.forsaken_borders.mino.tab_completers;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompleterRin implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> subCommands = new java.util.ArrayList<String>();
            subCommands.add("portal_room");
            subCommands.add("purchase_portal_room");
            return subCommands;
        } else {
            return null;
        }
    }
}
