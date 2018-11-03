package com.lavaglijder.warp.utils;

import com.lavaglijder.warp.Warp;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;

import java.util.List;

public class WarpAPI {

    private List<PlayerWarp> warpList;

    public List<PlayerWarp> getWarpList() {
        return warpList;
    }

    public WarpAPI() {
        File warpFile = Warp.getFileAPI().getFile("warps");
        for(String name : warpFile.getFile().getKeys(false)) {
            warpList.add(new PlayerWarp(name));
        }
    }

    public void createWarp(String name, int x, int y, int z) {
        if(warpExist(name)) {
            return;
        }
        warpList.add(new PlayerWarp(x,y,z,0,0,name));
    }

    public boolean warpExist(String name) {
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
}
