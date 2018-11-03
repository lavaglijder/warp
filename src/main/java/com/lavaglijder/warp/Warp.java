package com.lavaglijder.warp;

import com.lavaglijder.warp.utils.WarpAPI;
import lavaglijder.com.github.lavautils.lavaapi.LavaAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileType;
import org.bukkit.plugin.java.JavaPlugin;

public final class Warp extends JavaPlugin {

    private static LavaAPI lavaAPI;
    private static FileAPI fileAPI;
    private static WarpAPI warpAPI;


    @Override
    public void onEnable() {
        lavaAPI = new LavaAPI("Warp");
        fileAPI = new FileAPI(this);
        warpAPI = new WarpAPI();

        fileAPI.addFile("config", FileType.CONFIG);
        fileAPI.addFile("warps");
        fileAPI.addFile("messages", FileType.CONFIG);
        File configFile = fileAPI.getFile("config");

        configFile.addDefault("cooldown",5);

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

    public static WarpAPI getWarpAPI() {
        return warpAPI;
    }
}
