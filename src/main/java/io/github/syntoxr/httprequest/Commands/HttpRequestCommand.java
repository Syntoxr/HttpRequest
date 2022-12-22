package io.github.syntoxr.httprequest.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestCommand implements CommandExecutor {
    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {


        if(args.length == 0) { // if no args send usage
            return false;
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("""
                    Send a HTTP request with the following arguments:
                    1. endpoint
                    2. body (optional)""");
            return true;
        }

        URI uri;

        HttpClient client = HttpClient.newHttpClient();


        try {
            //parse provided URI
            uri = new URI(args[0]);

            String body = "";
            if (args.length > 1) body = args[1];


            sender.sendMessage(String.format("""
                Uri: %s
                Body: %s
                """, uri, body));

            //build request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());


        } catch (URISyntaxException e) {
            sender.sendMessage("Could not parse URI");
            return false;

        } catch (IOException | InterruptedException e) {
            sender.sendMessage("Error sending request:\n" + e);
            return false;

        }

        return  true;

    }




}
