package com.example.tourplanner.Model.MapAPI;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
public class MapAPITest {

    @Test
    public void getDistance() throws IOException, InterruptedException {
        MapAPI mapAPI = new MapAPI();
        Float distance =mapAPI.getDistance("Vienna", "Graz");
        Assert.assertEquals(193.1358,distance,0.0001);
    }
}