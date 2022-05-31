package com.example.tourplanner.Model;

public interface EventListener  {

    public enum EventType {TOUR_SELECTED}
    void onEvent(EventType eventType);

}
