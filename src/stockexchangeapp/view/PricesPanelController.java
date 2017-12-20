/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    

    
    public void setApp(MainApp app){
        this.app = app;
        companyTable.setItems(app.getCompanyData());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        abbreviationColumn.setCellValueFactory(cellData -> cellData.getValue().abbreviationProperty());
        

        // Listen for selection changes and show the person details when changed.
    //    personTable.getSelectionModel().selectedItemProperty().addListener(
     //           (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }    
    
}
