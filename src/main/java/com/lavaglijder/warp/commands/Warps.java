package com.lavaglijder.warp.commands;

import com.lavaglijder.warp.utils.PlayerWarp;
import com.lavaglijder.warp.utils.WarpAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Warps implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileAPI fileAPI = com.lavaglijder.warp.Warp.getFileAPI();

        File configFile = fileAPI.getFile("config");
        File messagesFile = fileAPI.getFile("messages");
        WarpAPI warpAPI = com.lavaglijder.warp.Warp.getWarpAPI();
        try{
            if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You're not a player!");
            return true;
            }

            Player p = (Player) sender;

            if(!p.hasPermission("warp.list")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().get("noperms") + "") + "");
                return true;
            }
            if(args.length == 0) {
                List<PlayerWarp> warps = warpAPI.getWarpPer10(1);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().getString("warpsHeader")).replace("(currentPage)", 1 + "")
                        .replace("(maxPage)", warpAPI.getWarpSizePer10() + ""));
                for(PlayerWarp warp : warps) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',messagesFile.getFile().getString("warpsWarp").replace("(warp)", warp.getName())));
                }
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',messagesFile.getFile().getString("warpsFeader")));
            } else {
                List<PlayerWarp> warps = warpAPI.getWarpPer10(Integer.parseInt(args[0]));


                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().getString("warpsHeader")).replace("(currentPage)", Integer.parseInt(args[0]) + "")
                        .replace("(maxPage)", warpAPI.getWarpSizePer10() + ""));
                for(PlayerWarp warp : warps) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',messagesFile.getFile().getString("warpsWarp").replace("(warp)", warp.getName())));
                }
                p.sendMessage(ChatColor.translateAlternateColorCodes('&',messagesFile.getFile().getString("warpsFeader")));
            }

            return true;
        } catch(NumberFormatException e) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().getString("warpsUsage")));
        }
        return true;
    }
}
