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
import javafx.scene.control.Button;
import stockexchangeapp.MainApp;
import stockexchangeapp.model.Currency;

/**
 * FXML Controller class
 *
 * @author blazej
 */
public class ControlPanelController implements Initializable {
    
    private MainApp app;
    
    public void setApp(MainApp app){
        this.app = app;
    }
    
    @FXML
    Button newCurrency;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    

    @FXML
    private void handleNewCurrency() {
        Currency tempCurrency = new Currency();
        boolean okClicked = app.showCurrencyFormDialog(tempCurrency);
        if (okClicked) {
            System.out.println("click");
            app.getCurrencyData().add(tempCurrency);
            System.out.println(app.getCurrencyData());
        }
    }
    
}
