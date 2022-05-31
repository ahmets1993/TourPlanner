package com.example.tourplanner.ViewModel;

import com.example.tourplanner.Model.DAL.Read;
import com.example.tourplanner.Model.EventListener;
import com.example.tourplanner.Model.Tour;
import com.example.tourplanner.Model.TourManager;
import com.itextpdf.layout.Document;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.print.Doc;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainPanelViewModel implements EventListener {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MainPanelViewModel mainPanelViewModel = new MainPanelViewModel();
        mainPanelViewModel.getTourNamesAsArray();
    }
 TourManager tourManager;

    public ObservableList<String> getTourNames() throws SQLException, ClassNotFoundException {
        ArrayList<String> tourNames = tourManager.getTourNames();
        ObservableList<String> listItems = FXCollections.observableArrayList(tourNames);
        return listItems;

    }

    public ArrayList<String> getTourNamesAsArray() throws SQLException, ClassNotFoundException {
        ArrayList<String> tourNames = tourManager.getTourNames();
        return tourNames;

    }


    public MainPanelViewModel() {
        this.tourManager = TourManager.getInstance();
        tourManager.addListener(this);
    }

    public void onEvent(EventType eventType){
      System.out.println(eventType);
      System.out.println(tourManager.getSelectTour());
    }



    public void select(String newValue) {
        tourManager.selectTour(newValue);
    }



    public ObservableList<TourDTO> getToursByName() throws SQLException, ClassNotFoundException {
        Read read = new Read();
        String selectedTour = tourManager.getSelectTour();
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


    public String  getTourDescription(String tourName){
    Read read = new Read();
        String tourDescription = null;
        try {
           tourDescription =  read.getDescription(tourName);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tourDescription;
    }


        public void yedek(String tourName){


            Read read = new Read();
            try {
                ArrayList<Tour> tableModel =  read.getAllRecords(tourName);
                ObservableList<TourDTO> tourVM =FXCollections.observableArrayList();
                TourDTO tourDTO = new TourDTO();
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
                    System.out.println(obj.getDate());
                    tourVM.add(obj);
                };

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


}
