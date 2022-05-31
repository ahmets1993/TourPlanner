package com.example.tourplanner.Model;

import com.example.tourplanner.Model.DAL.Read;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourManager {
    private  static TourManager instance;
    private String selectTour;

    public String getSelectTour() {
        return selectTour;
    }

    private TourManager() {
        this.eventListeners = new ArrayList<>();
    }

    public void addListener(EventListener listener){
        this.eventListeners.add(listener);
    }

    public void fireEvent(EventListener.EventType eventType){
        for(EventListener eventListener : eventListeners){
            eventListener.onEvent(eventType);
        };
    }
    public static TourManager getInstance(){
        if(instance == null){
            instance = new TourManager();
        }
        return instance;
    }
    List<EventListener> eventListeners;

    public void selectTour(String newValue) {
        this.selectTour = newValue;
        this.fireEvent(EventListener.EventType.TOUR_SELECTED);
    }

    public ArrayList<String> getTourNames() throws SQLException, ClassNotFoundException {
        Read read = new Read();
        ArrayList<String> tour = read.getList();
        System.out.println(String.valueOf(tour));
        return tour;

    }


}
