package com.example.tourplanner.ViewModel;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CreatePanelViewModelTest {

    @Test
    public void getDistance() throws IOException, InterruptedException {

        CreatePanelViewModel createPanelViewModel = new CreatePanelViewModel();
        Float distance = createPanelViewModel.getDistance("Vienna","Graz");
        Assert.assertEquals(193.1358,distance,0.00002);
    }
    @Test
    public void insertTour() throws SQLException, ClassNotFoundException {
        CreatePanelViewModel createPanelViewModel = new CreatePanelViewModel();
        boolean result = createPanelViewModel.insertTour("Country","Country",
                "Country","Country","Country","Country","Country",
                "Country","Country","Country");
       Assert.assertEquals(true,result);

    }

    @Test
    public void getCountOfTourName() throws SQLException, ClassNotFoundException {
        CreatePanelViewModel createPanelViewModel = new CreatePanelViewModel();
        Integer no = createPanelViewModel.getCountOfTourName("Black Tomato");
        Assert.assertEquals(8,no,0);
    }

}