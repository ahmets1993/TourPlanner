package com.example.tourplanner.ViewModel;

import com.example.tourplanner.Model.DAL.Read;
import com.example.tourplanner.Model.DAL.Update;
import com.example.tourplanner.Model.EventListener;
import com.example.tourplanner.Model.Tour;
import com.example.tourplanner.Model.TourManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.ArrayList;





public class UpdatePanelViewModel implements EventListener {
    TourManager tourManager;


    public ObservableList<String> getTourNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> tourNames = tourManager.getTourNames();
        ObservableList<String> listItems = FXCollections.observableArrayList(tourNames);
        return listItems;

    }

    public ObservableList<TourDTO> getToursByName(String selectedTour) throws SQLException, ClassNotFoundException {
        Read read = new Read();
        ArrayList<Tour> tableModel = read.getAllRecords(selectedTour);
        ObservableList<TourDTO> tourVM =FXCollections.observableArrayList();
        for (var item : tableModel) {
            TourDTO obj = new TourDTO();
            obj.tour_idProperty=item.tour_idProperty;
            obj.countryProperty=item.countryProperty;
            obj.dateProperty=item.dateProperty;
            obj.timeProperty=item.timeProperty;
            obj.start_pointProperty=item.start_pointProperty;
            obj.end_pointProperty=item.end_pointProperty;
            obj.distanceProperty=item.distanceProperty;
            obj.durationProperty=item.durationProperty;
            obj.priceProperty=item.priceProperty;
            tourVM.add(obj);
        }

        return tourVM;
    }

    public UpdatePanelViewModel() {
        this.tourManager = TourManager.getInstance();
        tourManager.addListener(this);
    }

    public void updateTourByID(String date, String time, String duration, String price, Integer id){
        Update update = new Update();
        try {
            update.updateTourByID(date, time, duration,price, id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onEvent(EventType eventType) {
    }
}
