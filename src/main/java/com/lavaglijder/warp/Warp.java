package com.lavaglijder.warp;

import com.lavaglijder.warp.commands.Completer;
import com.lavaglijder.warp.commands.CreateWarp;
import com.lavaglijder.warp.commands.DelWarp;
import com.lavaglijder.warp.commands.Warps;
import com.lavaglijder.warp.utils.WarpAPI;
import lavaglijder.com.github.lavautils.lavaapi.LavaAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.File;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileAPI;
import lavaglijder.com.github.lavautils.lavaapi.fileapi.FileType;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public class Warp extends JavaPlugin {

    private static LavaAPI lavaAPI;
    private static FileAPI fileAPI;
    private static WarpAPI warpAPI;


    @Override
    public void onEnable() {
        fileAPI = new FileAPI(this);
        fileAPI.addFile("config", FileType.CONFIG);
        fileAPI.addFile("warps");
        fileAPI.addFile("messages", FileType.CONFIG);
        File configFile = fileAPI.getFile("config");
        File messagesFile = fileAPI.getFile("messages");

        lavaAPI = new LavaAPI("Warp");


        configFile.addDefault("cooldown",5);

        messagesFile.addDefault("noperms", "&cNot enough permissions!");
        messagesFile.addDefault("warpUsage", "&cUsage: /warp (warp)");
        messagesFile.addDefault("createWarpUsage", "&cUsage: /createwarp (name)");
        messagesFile.addDefault("warpsUsage", "&cUsage: /warps (name)");
        messagesFile.addDefault("warpCommandSuccess", "&aYou have been warped to: &e(warp)&a!");
        messagesFile.addDefault("warpCommandWarpNotFound", "&cWarp &e(warp) &cdoes not exist!");//
        messagesFile.addDefault("createWarpExist", "&cThat warp does already exist!");
        messagesFile.addDefault("createWarpSuccess", "&aYou created a warp named: &e(warp)&a!");
        messagesFile.addDefault("delWarpUsage", "&cUsage: /delwarp (name)");
        messagesFile.addDefault("delWarpNotExist", "&cWarp &e(warp) &cdoes not exist!");
        messagesFile.addDefault("delWarpSuccess", "&aYou deleted a warp named: &e(warp)&a!");
        messagesFile.addDefault("warpsWarp", "&e(warp)");
        messagesFile.addDefault("warpsHeader", "&7-------------------\n&6Warps (currentPage)/(maxPage)");
        messagesFile.addDefault("warpsFeader", "&7-------------------");

        getLogger().info("Warp has been enabled");

        for(File file : fileAPI.getFiles()) {
            file.setup();
        }

        registerCommand("warp", new com.lavaglijder.warp.commands.Warp());
        registerCommand("createwarp", new CreateWarp());
        registerCommand("delwarp", new DelWarp());
        registerCommand("setwarp", new CreateWarp());
        registerCommand("warps", new Warps());
        registerCommand("addwarp", new CreateWarp());
        warpAPI = new WarpAPI();
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

    private void registerCommand(String name, CommandExecutor executor) {
        getCommand(name).setExecutor(executor);
        getCommand(name).setTabCompleter(new Completer());
    }
}
