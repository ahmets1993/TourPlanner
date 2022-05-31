package com.example.tourplanner.ViewModel;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class MainPanelViewModelTest {

    @Test
    public void getTourNamesAsArray() throws SQLException, ClassNotFoundException {
        MainPanelViewModel mainPanelViewModel = new MainPanelViewModel();
        ArrayList<String> result = mainPanelViewModel.getTourNamesAsArray();
        ArrayList<String> myArray = new ArrayList<>();
        myArray.add("Greaves");
        myArray.add("Adventure Co.");
        myArray.add("Geographic Expeditions");
        myArray.add("Anatolia");
        myArray.add("Mole de France");
        myArray.add("DuVine Cycling");
        myArray.add("Mountain Lodges of Peru");
        myArray.add("TCS World Travel");
        myArray.add("VBT Bicycling Vacations");
        myArray.add("Wilderness Travel");
        myArray.add("Mozart's Life");
        myArray.add("Classic Journeys");
        myArray.add("Spanish Language Day Trip to Washington DC");
        myArray.add("Paris deL Europa");
        myArray.add("Tauck");
        myArray.add("Quasar Expeditions");
        myArray.add("Mao Politan");
        myArray.add("Lopa Describia");
        myArray.add("Black Tomato");
        myArray.add("Mao Tai");
        myArray.add("Inside Japan Tours");
        myArray.add("Trek Travel");
        myArray.add("Thomson Family Adventures");
        assertArrayEquals(myArray.toArray(), result.toArray());
    }

    @Test
    public void getTourDescription() {
        MainPanelViewModel mainPanelViewModel = new MainPanelViewModel();
        String actual = mainPanelViewModel.getTourDescription("Black Tomato");
        String expected = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Assert.assertEquals(actual,expected);
    }

}