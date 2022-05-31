package com.example.tourplanner.Model.DAL;

import com.example.tourplanner.Model.Tour;

import java.sql.*;
import java.util.ArrayList;

public class Read {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Read read = new Read();
        read.itemCounterByTourName("Black Tomato");
    }

    private final String url = "jdbc:postgresql://localhost/TourPlanner";
    private final String user = "postgres";
    private final String password = "root";
    private static final String INSERT_USERS_SQL = "SELECT * FROM public.\"Tours\";";
    private static final String SELECT_TOUR_TABLE = "SELECT * FROM public.\"Tours\" WHERE tour_name = ?;";
    private static final String SELECT_TOUR_NAMES = "SELECT tour_name FROM public.\"Tours\" GROUP BY tour_name;";
    private static final String SELECT_TOUR_DESCRIPTION = "SELECT tour_description FROM public.\"Tour_Descriptions\" WHERE tour_name = ?;";

    public ArrayList<Tour> getAllRecords(String value) throws ClassNotFoundException, SQLException {
        Connection dbConnection;
        dbConnection = DriverManager.getConnection(url, user, password);
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(SELECT_TOUR_TABLE);
            preparedStatement.setString(1, value);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Tour> tourList = getTourObject(resultSet);
            System.out.printf(tourList.toString());
            System.out.println("The size of the ArrayList is: " + tourList.size());
            return tourList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    private ArrayList<Tour> getTourObject(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        ArrayList<Tour> tourlist = new ArrayList<>();
        try {

            while (resultSet.next()){
                Tour tourModel = new Tour();
                tourModel.setTour_id(resultSet.getInt("tour_id"));
                tourModel.setCountry(resultSet.getString("country"));
                tourModel.setDate(resultSet.getString("tour_date"));
                tourModel.setTime(resultSet.getString("tour_time"));
                tourModel.setStartPoint(resultSet.getString("start_point"));
                tourModel.setEnd_Point(resultSet.getString("end_point"));
                tourModel.setDistance(resultSet.getString("distance"));
                tourModel.setDuration(resultSet.getString("duration"));
                tourModel.setPrice(resultSet.getString("price"));
                tourlist.add(tourModel);
            }
        }catch (SQLException e){
            System.out.println("Error: "+e);
            e.printStackTrace();
            throw e;
        }
        return tourlist;
    }



    public ArrayList<String> getList() throws ClassNotFoundException, SQLException {
        Connection dbConnection;
        dbConnection = DriverManager.getConnection(url, user, password);
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(SELECT_TOUR_NAMES);
            resultSet = preparedStatement.executeQuery();
            ArrayList<String> arr = getListObject(resultSet);

            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public ArrayList<String> getListObject(ResultSet resultSet) throws ClassNotFoundException, SQLException {
        try {
            ArrayList<String> arr = new ArrayList<String>();
            while (resultSet.next()) {

                arr.add(resultSet.getString("tour_name"));

            }
            return arr;
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            throw e;
        }
    }

    public int itemCounterByTourName(String value) throws SQLException, ClassNotFoundException {
        Connection dbConnection;
        int numberOfElement = 0;
        dbConnection = DriverManager.getConnection(url, user, password);
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(SELECT_TOUR_TABLE);
            preparedStatement.setString(1, value);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Tour> tourList = getTourObject(resultSet);
            numberOfElement = tourList.size();
            System.out.println("The size of the ArrayList is: " + tourList.size());
            return numberOfElement;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }


    public String getDescription(String value) throws SQLException, ClassNotFoundException {

        Connection dbConnection;
        String des = null;
        dbConnection = DriverManager.getConnection(url, user, password);
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(SELECT_TOUR_DESCRIPTION);
            preparedStatement.setString(1, value);
            resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    des= resultSet.getString("tour_description");
                    System.out.println(des);
                }

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return des;
    }
}


