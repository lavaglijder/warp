package com.lavaglijder.warp.utils;

import com.lavaglijder.warp.Warp;
import lavaglijder.com.github.lavautils.lavaapi.LavaAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;

public class PlayerWarp {

    private int x;
    private int y;
    private int z;
    private int yaw;
    private int pitch;
    private String name;

    private LavaAPI lavaAPI;
    private FileAPI fileAPI;
    private File warpData;

    public PlayerWarp(int x, int y, int z, int yaw, int pitch, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.name = name;

        this.fileAPI = Warp.getFileAPI();
        this.lavaAPI = Warp.getLavaAPI();
        this.warpData = fileAPI.getFile("warps");

        warpData.getFile().set(this.name + ".x", this.x);
        warpData.getFile().set(this.name + ".y", this.y);
        warpData.getFile().set(this.name + ".z", this.z);
        warpData.getFile().set(this.name + ".yaw", this.yaw);
        warpData.getFile().set(this.name + ".pitch", this.pitch);
    }

    public PlayerWarp(String name) {
        this.name = name;

        this.fileAPI = Warp.getFileAPI();
        this.lavaAPI = Warp.getLavaAPI();
        this.warpData = fileAPI.getFile("warps");

        this.x = warpData.getFile().getInt(this.name + ".x");
        this.y = warpData.getFile().getInt(this.name + ".y");
        this.z = warpData.getFile().getInt(this.name + ".z");
        this.pitch = warpData.getFile().getInt(this.name + ".pitch");
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;

        warpData.getFile().set(this.name + ".x", this.x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        warpData.getFile().set(this.name + ".y", this.y);
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
        warpData.getFile().set(this.name + ".z", this.z);
    }

    public int getYaw() {
        return yaw;
    }

    public void setYaw(int yaw) {
        this.yaw = yaw;
        warpData.getFile().set(this.name + ".yaw", this.yaw);
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
        warpData.getFile().set(this.name + ".pitch", this.pitch);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        for(String path : warpData.getFile().getKeys(true)) {
            if(path.startsWith(this.name + ".")) {
                warpData.getFile().set(path, null);
            }
        }

        warpData.getFile().set(this.name, null);

        this.name = name;

        warpData.getFile().set(this.name + ".x", this.x);
        warpData.getFile().set(this.name + ".y", this.y);
        warpData.getFile().set(this.name + ".z", this.z);
        warpData.getFile().set(this.name + ".yaw", this.yaw);
        warpData.getFile().set(this.name + ".pitch", this.pitch);
    }

    public void remove() {
        for(String path : warpData.getFile().getKeys(true)) {
            if(path.startsWith(this.name + ".")) {
                warpData.getFile().set(path, null);
            }
        }

        warpData.getFile().set(this.name, null);
    }
}
