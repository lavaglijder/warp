package com.lavaglijder.warp.commands;

import com.lavaglijder.warp.utils.CooldownAPI;
import com.lavaglijder.warp.utils.PlayerWarp;
import com.lavaglijder.warp.utils.WarpAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileAPI fileAPI = com.lavaglijder.warp.Warp.getFileAPI();

        File configFile = fileAPI.getFile("config");
        File messagesFile = fileAPI.getFile("messages");
        WarpAPI warpAPI = com.lavaglijder.warp.Warp.getWarpAPI();
        CooldownAPI cooldownAPI = com.lavaglijder.warp.Warp.getCooldownAPI();

        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You're not a player!");
            return true;
        }

        Player p = (Player) sender;

        if(!p.hasPermission("warp.use")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().get("noperms") + "") + "");
            return true;
        }

        if(args.length == 0) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().get("warpUsage") + "") + "");
            return true;
        }

        if(warpAPI.warpExist(args[0])) {
            PlayerWarp selectedWarp = warpAPI.getWarp(args[0]);
            if(p.hasPermission("warp.cooldown") || configFile.getFile().getInt("cooldown") <= 0) {
                cooldownAPI.setCooldown(p, 0, selectedWarp);
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().getString("warpWait")).replace("(delay)", configFile.getFile().getInt("cooldown") + ""));
                cooldownAPI.setCooldown(p, configFile.getFile().getInt("cooldown"), selectedWarp);
            }
            return true;
        }
        p.sendMessage((ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().get("warpCommandWarpNotFound") + "") + "").replace("(warp)", args[0]));

        return true;
    }
}
