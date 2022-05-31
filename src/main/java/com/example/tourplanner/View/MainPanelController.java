package com.example.tourplanner.View;
import com.example.tourplanner.App;
import com.example.tourplanner.ViewModel.MainPanelViewModel;
import com.example.tourplanner.ViewModel.TourDTO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainPanelController implements Initializable {

    @FXML
    private MenuItem detail;
    @FXML
    private MenuItem print;
    @FXML
    public Label titleTour;
    @FXML
    private Label welcomeText;
    @FXML
    private Button searchButton;
    @FXML
    private Button createButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField textField;
    @FXML
    private TableView<TourDTO> tableView;
    @FXML
    private TableColumn<TourDTO, Integer> tour_id;
    @FXML
    private TableColumn<TourDTO, String> country;
    @FXML
    private TableColumn<TourDTO, String> start_point;
    @FXML
    private TableColumn<TourDTO, String> end_point;
    @FXML
    private TableColumn<TourDTO, String> duration;
    @FXML
    private TableColumn<TourDTO, String> distance;
    @FXML
    private TableColumn<TourDTO, String> price;
    @FXML
    private TableColumn<TourDTO, String> date;
    @FXML
    private TableColumn<TourDTO, String> time;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField SearchBar;

    public MainPanelViewModel tourViewModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.tourViewModel = new MainPanelViewModel();
        try {
            listView.setItems(tourViewModel.getTourNames());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ListViewWorker();
    }


    @FXML
    protected void onSearchButtonClick() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
       String searchedText = SearchBar.getText();
       MainPanelViewModel mainPanelViewModel = new MainPanelViewModel();
       ArrayList<String> tourList =  mainPanelViewModel.getTourNamesAsArray();
        if(tourList.contains(searchedText)){
            ObservableList<String> tour = FXCollections.observableArrayList(searchedText);
           listView.setItems(tour);
       }else
       {
           System.out.println("this tour is not exist");
           ObservableList<String> tour = FXCollections.observableArrayList(mainPanelViewModel.getTourNames());
           listView.setItems(tour);
       }
    }

    @FXML
    public void openAddTour(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("createPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 870);
        stage = new Stage();
        stage.setTitle("TOUR PLANNER");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }



    public void ListViewWorker() {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                tourViewModel.select(newValue);
                updateMainPanel(newValue);
            }
        });
    }

    public void updateMainPanel(String selectedTour){

        tour_id.setCellValueFactory(new PropertyValueFactory<>("tour_id"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        start_point.setCellValueFactory(new PropertyValueFactory<>("startPoint"));
        end_point.setCellValueFactory(new PropertyValueFactory<>("endPoint"));
        duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        MainPanelViewModel mainPanelViewModel = new MainPanelViewModel();
        ObservableList<TourDTO> tourList = FXCollections.observableArrayList();
        try {
            tourList = mainPanelViewModel.getToursByName();
            System.out.println("\nTurlar geliyor");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tableView.setItems(tourList);
        //Set Image
        File file = new File("C:/Users/ahmet/IdeaProjects/TourPlanner/src/main/resources/Images/Maps/"+selectedTour+".jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        //Set Title Label
        titleTour.setText(selectedTour);
    }

    @FXML
    public void onDetailButtonClick(ActionEvent actionEvent) throws IOException {
        System.out.println("bana bastilar");
        System.out.println( detail);
        String tourName = listView.getSelectionModel().getSelectedItem();
        MainPanelViewModel mainPanelViewModel = new MainPanelViewModel();
        String tourDescription = mainPanelViewModel.getTourDescription(tourName);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("descriptionPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 870);
        Stage stage = new Stage();
        stage.setTitle("TOUR PLANNER");
        stage.setScene(scene);
        stage.setResizable(false);
        DescriptionPanelController descriptionPanelController = fxmlLoader.getController();
        descriptionPanelController.setTitle_Description(tourName,tourDescription);
        stage.show();

    }

    @FXML
    public void onPrintButtonClicked(ActionEvent actionEvent) throws IOException{
        String tourName = listView.getSelectionModel().getSelectedItem();
        System.out.println(tourName);
        MainPanelViewModel mainPanelViewModel = new MainPanelViewModel();

    }



    @FXML
    public void onUpdateButtonClicked(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("updatePanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 870);
        Stage stage = new Stage();
        stage.setTitle("TOUR PLANNER");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void onDeleteButtonClicked(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("deletePanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 870);
        Stage stage = new Stage();
        stage.setTitle("TOUR PLANNER");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }


}
