package com.lavaglijder.warp.utils;

import com.lavaglijder.warp.Warp;
import lavaglijder.com.github.lavautils.lavaapi.LavaAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class PlayerWarp {

    private int x;
    private int y;
    private int z;
    private float yaw;
    private float pitch;
    private String name;
    private World world;

    private LavaAPI lavaAPI;
    private FileAPI fileAPI;
    private File warpData;

    public PlayerWarp(int x, int y, int z, float yaw, float pitch, World world, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.name = name;
        this.world = world;

        this.fileAPI = Warp.getFileAPI();
        this.lavaAPI = Warp.getLavaAPI();
        this.warpData = fileAPI.getFile("warps");

        warpData.getFile().set(this.name + ".x", this.x);
        warpData.getFile().set(this.name + ".y", this.y);
        warpData.getFile().set(this.name + ".z", this.z);
        warpData.getFile().set(this.name + ".yaw", this.yaw);
        warpData.getFile().set(this.name + ".pitch", this.pitch);
        warpData.getFile().set(this.name + ".world", this.world.getName());
    }

    public PlayerWarp(String name) {
        this.name = name;

        this.fileAPI = Warp.getFileAPI();
        this.lavaAPI = Warp.getLavaAPI();
        this.warpData = fileAPI.getFile("warps");

        this.x = warpData.getFile().getInt(this.name + ".x");
        this.y = warpData.getFile().getInt(this.name + ".y");
        this.z = warpData.getFile().getInt(this.name + ".z");
        this.pitch = (float) warpData.getFile().getDouble(this.name + ".pitch");
        this.yaw = (float) warpData.getFile().getDouble(this.name + ".yaw");
        this.world = Bukkit.getServer().getWorld(warpData.getFile().getString(this.name + ".world"));
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

    public float getYaw() {
        return yaw;
    }

    public void setYaw(int yaw) {
        this.yaw = yaw;
        warpData.getFile().set(this.name + ".yaw", this.yaw);
    }

    public float getPitch() {
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

    public Location getLocation() {
        Location location = new Location(getWorld(), getX() + .5,getY(),getZ() + .5,getYaw(),getPitch());
        return location;
    }

    public World getWorld() {
        return this.world;
    }
    public void setWorld(World world) {
        this.world = world;
        warpData.getFile().set(this.name + ".world", this.world);

    }
}
