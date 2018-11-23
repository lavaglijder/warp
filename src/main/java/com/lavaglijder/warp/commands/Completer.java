package com.lavaglijder.warp.commands;

import com.lavaglijder.warp.utils.PlayerWarp;
import com.lavaglijder.warp.utils.WarpAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Completer implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabComplete = new ArrayList<>();
        String cmd = command.getName();
        WarpAPI warpAPI = com.lavaglijder.warp.Warp.getWarpAPI();


        if(cmd.equalsIgnoreCase("warp") || cmd.equalsIgnoreCase("delwarp")) {
            if(args.length > 0) {
                for(PlayerWarp warp : warpAPI.getWarpList()) {
                    tabComplete.add(warp.getName());
                }
                if(tabComplete.isEmpty()) {
                    tabComplete.add("");
                }
            }
        }

        if(cmd.equalsIgnoreCase("createwarp") || cmd.equalsIgnoreCase("setwarp") || cmd.equalsIgnoreCase("addwarp") || cmd.equalsIgnoreCase("warps")) {
            tabComplete.add("");
        }

        if(tabComplete.isEmpty()) {
            return null;
        }
        return tabComplete;
    }
}
