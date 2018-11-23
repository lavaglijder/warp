package com.lavaglijder.warp.utils;

import com.lavaglijder.warp.Warp;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class WarpAPI {

    private List<PlayerWarp> warpList;

    public List<PlayerWarp> getWarpList() {
        return warpList;
    }

    public WarpAPI() {
        warpList = new ArrayList<>();
        File warpFile = Warp.getFileAPI().getFile("warps");
        for(String name : warpFile.getFile().getKeys(false)) {
            warpList.add(new PlayerWarp(name));
        }
    }

    public void createWarp(String name, int x, int y, int z, World world) {
        if(warpExist(name)) {
            return;
        }
        warpList.add(new PlayerWarp(x,y,z,(float)0,(float)0, world, name));
    }
    public void createWarp(String name, int x, int y, int z, float yaw, float pitch, World world) {
        if(warpExist(name)) {
            return;
        }
        warpList.add(new PlayerWarp(x,y,z,yaw,pitch, world, name));
    }

    public boolean warpExist(String name) {
        if(getWarpList().isEmpty()) {
            return false;
        }
        for(PlayerWarp warp : getWarpList()) {
            if(warp.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    public void removeWarp(String name) {
        if(!warpExist(name)) {
            return;
        }
        getWarp(name).remove();
        warpList.remove(getWarp(name));
    }

    public PlayerWarp getWarp(String name) {
        for(PlayerWarp warp : getWarpList()) {
            if(warp.getName().equalsIgnoreCase(name)) {
                return warp;
            }
        }
        return null;
    }

    public List<PlayerWarp> getWarpPer10(int page) {
        List<PlayerWarp> warps = new ArrayList<>();

        for(int i = -10 + (page * 10); i < page * 10 && i < warpList.size(); i++) {
            warps.add(warpList.get(i));
        }

        return warps;
    }
    public int getWarpSizePer10() {
        return (warpList.size() / 10) + 1;
    }
}
