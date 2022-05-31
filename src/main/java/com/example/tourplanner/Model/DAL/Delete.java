package com.example.tourplanner.Model.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {


    private final String url = "jdbc:postgresql://localhost/TourPlanner";
    private final String user = "postgres";
    private final String password = "root";
    String sql = "UPDATE book SET author = ?, title = ?,subject = ?, publisher = ?, language = ? WHERE bookID = ?";
    private static final String DELETE_SELECTED_TOUR = "DELETE FROM public.\"Tours\" WHERE tour_id = ?;";

    public void deleteTourByID(Integer id) throws ClassNotFoundException, SQLException {
        Connection dbConnection;
        dbConnection = DriverManager.getConnection(url, user, password);
        PreparedStatement statement = (PreparedStatement) dbConnection.prepareStatement(DELETE_SELECTED_TOUR);
        statement.setInt(1, id);
        statement.execute();

    }


}
