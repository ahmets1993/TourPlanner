package com.example.tourplanner.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Tour {

    public IntegerProperty tour_idProperty;
    public StringProperty countryProperty;
    public StringProperty start_pointProperty;
    public StringProperty end_pointProperty;
    public StringProperty durationProperty;
    public StringProperty priceProperty;
    public StringProperty dateProperty;
    public StringProperty timeProperty;
    public StringProperty distanceProperty;



    public Tour(){
     this.tour_idProperty = new SimpleIntegerProperty();
     this.countryProperty = new SimpleStringProperty();
     this.start_pointProperty = new SimpleStringProperty();
     this.end_pointProperty = new SimpleStringProperty();
     this.durationProperty = new SimpleStringProperty();
     this.priceProperty = new SimpleStringProperty();
     this.dateProperty = new SimpleStringProperty();
     this.timeProperty = new SimpleStringProperty();
     this.distanceProperty = new SimpleStringProperty();
    }

    // This is for tour id
    public int getTour_id() {
        return tour_idProperty.get();
    }

    public void setTour_id(int id){
        this.tour_idProperty.set(id);
    }

    public IntegerProperty getTour_idProperty(){
        return tour_idProperty;
    }


    // This is for county
    public String getCountry() {
        return countryProperty.get();
    }

    public void setCountry(String country){
        this.countryProperty.set(country);
    }

    public StringProperty getCountryProperty(){
        return countryProperty;
    }



    // This is for tour start point
    public String getStartPoint() {
        return start_pointProperty.get();
    }

    public void setStartPoint(String id){
        this.start_pointProperty.set(id);
    }

    public StringProperty getStart_pointProperty(){
        return start_pointProperty;
    }


    // This is for end
    public String getEndPoint() {
        return end_pointProperty.get();
    }

    public void setEnd_Point(String id){
        this.end_pointProperty.set(id);
    }

    public StringProperty getEnd_pointProperty(){
        return end_pointProperty;
    }


    // This is for duration
    public String getDuration() {
        return durationProperty.get();
    }

    public void setDuration(String id){
        this.durationProperty.set(id);
    }

    public StringProperty getDurationProperty(){
        return durationProperty;
    }


    // This is for price
    public String getPrice() {
        return priceProperty.get();
    }

    public void setPrice(String id){
        this.priceProperty.set(id);
    }

    public StringProperty getPriceProperty(){
        return priceProperty;
    }


    // This is for date
    public String getDate() {
        return dateProperty.get();
    }

    public void setDate(String date){
        this.dateProperty.set(date);
    }

    public StringProperty getDateProperty(){
        return dateProperty;
    }

    // This is for Time
    public String getTime() {
        return timeProperty.get();
    }

    public void setTime(String time){
        this.timeProperty.set(time);
    }

    public StringProperty getTimeProperty(){
        return timeProperty;
    }


    // This is for distance
    public String getDistance() {return distanceProperty.get();}

    public void setDistance(String distance){
        this.distanceProperty.set(distance);
    }

    public StringProperty getDistanceProperty(){
        return distanceProperty;
    }

}
