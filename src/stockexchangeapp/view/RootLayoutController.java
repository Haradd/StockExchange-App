/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.view;

import javafx.fxml.FXML;
import stockexchangeapp.MainApp;

/**
 *
 * @author blazej
 */
public class RootLayoutController {
    
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
