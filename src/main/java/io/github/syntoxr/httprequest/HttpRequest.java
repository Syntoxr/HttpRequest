package io.github.syntoxr.httprequest;

import io.github.syntoxr.httprequest.Commands.HttpRequestCommand;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class HttpRequest extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(this.getCommand("HttpRequest")).setExecutor(new HttpRequestCommand());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage(Component.text("Hello, " + event.getPlayer().getName() + "!"));
    }

}
