/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stockexchangeapp.MainApp;
import stockexchangeapp.model.Company;

/**
 * FXML Controller class
 *
 * @author blazej
 */
public class PricesPanelController implements Initializable {
   
    private MainApp app;
    
    @FXML
    private TableView<Company> companyTable;
    @FXML
    private TableColumn<Company, String> nameColumn;
    @FXML
    private TableColumn<Company, String> abbreviationColumn;
    @FXML
    private TableColumn<Company, Double> currentPriceColumn;
    @FXML
    private TableColumn<Company, Double> changColumn;
    

    
    public void setApp(MainApp app){
        this.app = app;
        companyTable.setItems(app.getCompanyData());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        abbreviationColumn.setCellValueFactory(cellData -> cellData.getValue().abbreviationProperty());
        currentPriceColumn.setCellValueFactory(cellData -> cellData.getValue().currentProperty().asObject());
        changColumn.setCellValueFactory(cellData -> cellData.getValue().changeProperty().asObject());

        
        
        companyTable.setOnMousePressed(new EventHandler<MouseEvent> () {
            @Override
            public void handle(MouseEvent event) {
                try {
                    // Load the fxml file and create a new stage for the popup dialog.
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainApp.class.getResource("view/PriceDetailsDialog.fxml"));
                    AnchorPane page = (AnchorPane) loader.load();

                    // Create the dialog Stage.
                    Stage dialogStage = new Stage();
                    dialogStage.setTitle("Company details");
                    dialogStage.initModality(Modality.WINDOW_MODAL);
                    dialogStage.initOwner(app.getPrimaryStage());
                    Scene scene = new Scene(page);
                    dialogStage.setScene(scene);

                    // Set the currency into the controller.
                    PriceDetailsDialogController controller = loader.getController();
                    controller.setDialogStage(dialogStage);

                    // Set the dialog icon.
                    //dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

                    // Show the dialog and wait until the user closes it
                    dialogStage.showAndWait();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Listen for selection changes and show the person details when changed.
    //    personTable.getSelectionModel().selectedItemProperty().addListener(
     //           (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }


    public void handleDetailsButton(){
        
    }
    
}
