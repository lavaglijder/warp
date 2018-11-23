package com.lavaglijder.warp.commands;

import com.lavaglijder.warp.utils.WarpAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        FileAPI fileAPI = com.lavaglijder.warp.Warp.getFileAPI();

        File configFile = fileAPI.getFile("config");
        File messagesFile = fileAPI.getFile("messages");
        WarpAPI warpAPI = com.lavaglijder.warp.Warp.getWarpAPI();

        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You're not a player!");
            return true;
        }

        Player p = (Player) sender;

        if(!p.hasPermission("warp.delete")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().get("noperms") + "") + "");
            return true;
        }

        if(args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().get("delWarpUsage") + "") + "");
            return true;
        }

        if(!warpAPI.warpExist(args[0])) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().get("delWarpNotExist") + "").replace("(warp)", args[0]) + "");
            return true;
        }

        warpAPI.removeWarp(args[0]);
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().get("delWarpSuccess") + "").replace("(warp)", args[0]) + "");
        return true;
    }
}
