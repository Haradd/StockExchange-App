/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import stockexchangeapp.MainApp;

/**
 * FXML Controller class
 *
 * @author blazej
 */
public class PricesPanelController implements Initializable {
   
    private MainApp app;
    
    public void setApp(MainApp app){
        this.app = app;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
