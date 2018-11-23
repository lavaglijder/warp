package com.lavaglijder.warp.utils;

import com.lavaglijder.warp.Warp;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CooldownAPI {

    private List<Player> playerList;
    private WarpAPI warpAPI;
    private File messagesFile;
    private FileAPI fileAPI;
    private HashMap<Player, Integer> playerIntegerList;
    private int current;

    public CooldownAPI() {
        playerList = new ArrayList<>();
        warpAPI = Warp.getWarpAPI();
        fileAPI = Warp.getFileAPI();
        messagesFile = fileAPI.getFile("messages");
        playerIntegerList = new HashMap<>();
        current = 0;
    }

    public List<Player> getCooldownPlayerList() {
        return this.playerList;
    }

    public boolean onCooldown(Player p) {
        return this.playerList.contains(p);
    }

    public void setCooldown(Player p, int delayInSec, PlayerWarp selectedWarp) {
        this.playerList.add(p);
        int i = current;
        this.playerIntegerList.put(p, i);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Warp.getPlugin(Warp.class), () -> {
            if(onCooldown(p)) {
                if(this.playerIntegerList.get(p) == i) {
                    this.playerList.remove(p);
                    p.teleport(selectedWarp.getLocation());
                    p.sendMessage((ChatColor.translateAlternateColorCodes('&', messagesFile.getFile().get("warpCommandSuccess") + "") + "").replace("(warp)", selectedWarp.getName()));
                }
            }
        },delayInSec * 20);

        current = current + 1;
    }

    public void removeCooldown(Player p) {
        if(onCooldown(p)) {
            this.playerList.remove(p);
            this.playerIntegerList.remove(p);
        }
    }

}
