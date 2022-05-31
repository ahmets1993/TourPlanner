package com.example.tourplanner.Model.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create {

    private final String url = "jdbc:postgresql://localhost/TourPlanner";
    private final String user = "postgres";
    private final String password = "root";
    private static final String INSERT_USERS_SQL = "INSERT INTO public.\"Tours\"(tour_id, country, start_point, end_point, duration, price, tour_date, tour_time, tour_name, distance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String INSERT_DESCRIPTION_SQL = "INSERT INTO public.\"Tour_Descriptions\"(tour_name, tour_description) VALUES (?, ?);";


    public boolean insertTour(Integer tourID, String country, String startPoint, String endPoint, String duration, String preise, String Date, String Time, String tourName, String distance) {

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = conn.prepareStatement(INSERT_USERS_SQL);) {
            statement.setInt(1, tourID);
            statement.setString(2, country);
            statement.setString(3, startPoint);
            statement.setString(4, endPoint);
            statement.setString(5, duration);
            statement.setString(6, preise);
            statement.setString(7, Date);
            statement.setString(8, Time);
            statement.setString(9, tourName);
            statement.setString(10, distance);
            statement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean insertDescription(  String tourName, String description) {

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = conn.prepareStatement(INSERT_DESCRIPTION_SQL);) {
            statement.setString(1, tourName);
            statement.setString(2, description);

            statement.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
