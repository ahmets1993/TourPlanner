package com.example.tourplanner.ViewModel;

import com.example.tourplanner.Model.DAL.Delete;
import com.example.tourplanner.Model.DAL.Update;

import java.sql.SQLException;

public class DeletePanelViewModel {

    public boolean deleteTourByID(Integer id){
        Delete delete = new Delete();
        try {
            delete.deleteTourByID(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
