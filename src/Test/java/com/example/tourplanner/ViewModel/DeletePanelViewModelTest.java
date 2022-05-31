package com.example.tourplanner.ViewModel;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeletePanelViewModelTest {

    @Test
    public void deleteTourByID() {
        DeletePanelViewModel deletePanelViewModel = new DeletePanelViewModel();
        boolean op = deletePanelViewModel.deleteTourByID(0);//you need to add an existed tour id
        Assert.assertEquals(true,op);
    }
}