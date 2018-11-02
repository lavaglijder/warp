package com.lavaglijder.warp;

import lavaglijder.com.github.lavautils.lavaapi.LavaAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class Warp extends JavaPlugin {

    private static LavaAPI lavaAPI;
    private static FileAPI fileAPI;

    @Override
    public void onEnable() {
        lavaAPI = new LavaAPI("Warp");
        fileAPI = new FileAPI(this);

        fileAPI.addFile("config");
        fileAPI.addFile("warps");
        File configFile = fileAPI.getFile("config");

        getLogger().info("Warp has been enabled");
    }

    @Override
    public void onDisable() {

        getLogger().info("Warp has been enabled");
    }

    public static LavaAPI getLavaAPI() {
        return lavaAPI;
    }

    public static FileAPI getFileAPI() {
        return fileAPI;
    }
}
