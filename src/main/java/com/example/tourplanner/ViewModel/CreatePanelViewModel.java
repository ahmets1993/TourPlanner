package com.example.tourplanner.ViewModel;

import com.example.tourplanner.Model.DAL.Create;
import com.example.tourplanner.Model.DAL.Read;
import com.example.tourplanner.Model.MapAPI.MapAPI;

import java.io.IOException;
import java.sql.SQLException;

public class CreatePanelViewModel {
    public static void main(String[] args) throws IOException, InterruptedException {
        CreatePanelViewModel createPanelViewModel = new CreatePanelViewModel();
        float distance = createPanelViewModel.getDistance("Vienna", "Graz");
        System.out.println(distance);
    }

    public float getDistance(String startPoint, String endPoint) throws IOException, InterruptedException {
        MapAPI mapAPI = new MapAPI();
        float distance = mapAPI.getDistance(startPoint, endPoint);
        return distance;
    }

    public boolean insertTour(String Country, String StartPoint, String EndPoint, String Duration, String Price, String Date, String Time,String tourName, String Distance, String description) throws SQLException, ClassNotFoundException {
        Create create = new Create();
        boolean operation = false;
        boolean operation2 = false;
        int id = getCountOfTourName(tourName);
        System.out.println("Tour name: "+tourName);
        System.out.println("Date: "+Date);
        if(id == 0){
            System.out.println("this tour doesnt exist.");
            operation = create.insertTour(id,Country,StartPoint,EndPoint,Duration,Price,Date,Time,tourName,Distance);
            operation2 = create.insertDescription(tourName,description);
            System.out.println(operation);
            System.out.println(operation2);
        }else{
            id++;
        operation = create.insertTour(id,Country,StartPoint,EndPoint,Duration,Price,Date,Time,tourName,Distance);
            System.out.println(operation);
        }

        return operation;

    }

    public int getCountOfTourName(String tourName) throws SQLException, ClassNotFoundException {
        Read read = new Read();
        int id = read.itemCounterByTourName(tourName);
        return id;
    }


}
