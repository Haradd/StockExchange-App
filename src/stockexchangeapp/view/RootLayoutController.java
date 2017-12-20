/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import stockexchangeapp.MainApp;

/**
 *
 * @author blazej
 */
public class RootLayoutController {
    
    @FXML private TabPane tabPane;
    
    private MainApp app;
    
    public void setApp(MainApp app){
        this.app = app;
    }
    
    @FXML
    private void handleGoToPrices(){
        app.showPricesPanel();
        
    }
    
    @FXML
    private void handleGoToControlPanel(){
        app.showControlPanel();
    }
    
}
