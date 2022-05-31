package com.example.tourplanner.Model.MapAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;

public class MapAPI {


    public Float getDistance(String startPoint, String endPoint) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.mapquestapi.com/directions/v2/route?key=1VZGQLYYGNeRDrGnYXRQTTNPKU8lG1TX&from="+startPoint+"&to="+endPoint+"&unit=k"))
                .build();
        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        JSONObject myresponse = new JSONObject(response.body());
        System.out.println(myresponse);
        Float distance = myresponse.getJSONObject("route").getFloat("distance");
        System.out.println(distance);
        return distance;
    }

    }






