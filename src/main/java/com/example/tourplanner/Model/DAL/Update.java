package com.example.tourplanner.Model.DAL;

import com.example.tourplanner.Model.Tour;

import java.sql.*;
import java.util.ArrayList;

public class Update {



    private final String url = "jdbc:postgresql://localhost/TourPlanner";
    private final String user = "postgres";
    private final String password = "root";
    String sql = "UPDATE book SET author = ?, title = ?,subject = ?, publisher = ?, language = ? WHERE bookID = ?";
    private static final String UPDATE_SELECTED_TOUR = "UPDATE public.\"Tours\" SET tour_date = ?, tour_time = ?, duration = ?, price = ? WHERE tour_id = ?;";

        public void updateTourByID(String date, String time, String duration, String price, Integer id) throws ClassNotFoundException, SQLException {
        Connection dbConnection;
        dbConnection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = (PreparedStatement) dbConnection.prepareStatement(UPDATE_SELECTED_TOUR);
        statement.setString(1,date);
        statement.setString(2, time);
        statement.setString(3, price);
        statement.setString(4, duration);
        statement.setInt(5, id);
        statement.execute();

    }
}
