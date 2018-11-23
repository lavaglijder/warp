package com.lavaglijder.warp.events;

import com.lavaglijder.warp.Warp;
import com.lavaglijder.warp.utils.CooldownAPI;
import com.lavaglijder.warp.utils.WarpAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Move implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        FileAPI fileAPI = com.lavaglijder.warp.Warp.getFileAPI();

        File configFile = fileAPI.getFile("config");
        File messagesFile = fileAPI.getFile("messages");
        WarpAPI warpAPI = com.lavaglijder.warp.Warp.getWarpAPI();
        CooldownAPI cooldownAPI = com.lavaglijder.warp.Warp.getCooldownAPI();
        if((int) e.getFrom().getX() != (int) e.getTo().getX() || (int) e.getFrom().getY() != (int) e.getTo().getY() || (int) e.getFrom().getZ() != (int) e.getTo().getZ()) {
            if(cooldownAPI.onCooldown(e.getPlayer())) {
                Warp.getCooldownAPI().removeCooldown(e.getPlayer());
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().getString("warpMoved")));
            }
        }
    }

}
