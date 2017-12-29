/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.view;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import stockexchangeapp.MainApp;
import stockexchangeapp.model.Company;
import stockexchangeapp.model.StockExchange;

/**
 * FXML Controller class
 *
 * @author blazej
 */
public class CompanyFormDialogController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField abbreviationField;
    @FXML
    private TextField chairmanField;
    @FXML
    private ComboBox<StockExchange> stockComboBox;
                 
    
    private Stage dialogStage;
    private Company company;
    private boolean okClicked = false;
    
    private MainApp app;
    
    public void setApp(MainApp app){
        this.app = app;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        
        // Set the dialog icon.
       // this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }
    
        public void setCompanyFields(Company company) {
            this.company = company;
            
            nameField.setText(company.getName());
            abbreviationField.setText(company.getAbbreviation());
            chairmanField.setText(company.getChairman());
    
            stockComboBox.setItems(app.getStockExchangeData());
            stockComboBox.getSelectionModel().selectFirst();
     }
        
    public boolean isOkClicked() {
        return okClicked;
    }
    
    @FXML
    private void handleOk() {
        if(isInputValid()){
            company.setName(nameField.getText());
            company.setAbbreviation(abbreviationField.getText());
            company.setChairman(chairmanField.getText());
            company.setStockExchangeBelonging(stockComboBox.getSelectionModel().getSelectedItem());
            
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDate = new SimpleDateFormat("MM.yyyy");
            company.setFirstListingDate(simpleDate.format(date));
            
            int min = 1;
            int max = 50; 
            double random = ThreadLocalRandom.current().nextDouble(min, max);
            int randomInt = ThreadLocalRandom.current().nextInt(min, max);

            
            company.setMin(random - 0.5);
            company.setMax(random + 0.5);
            company.setOpen(random);
            company.setClose(random);
            company.setCurrent(random);
            company.setBid(random - 0.1);
            company.setOffer(random + 0.1);
            company.setVolume(randomInt);
            company.setSharesCount(randomInt);
            company.setTurnoverValue(company.getCurrent() * company.getVolume());
            company.setMarketValue(company.getCurrent() * company.getVolume());            
            company.setChange(1.0);
            
            
            
            okClicked = true;
            dialogStage.close();  
        }


    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "Company name can't be empty\n"; 
        }
        
        if (abbreviationField.getText() == null || abbreviationField.getText().length() == 0  ){
            errorMessage += "Company abbreviation can't be empty\n";
        }
        
        if (chairmanField.getText() == null || chairmanField.getText().length() == 0  ){
            errorMessage += "Company chairman can't be empty\n";
        }
        
        if (app.getAbbreviationsSet().contains(abbreviationField.getText())){
            errorMessage += "This abbreviation already exists!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }    
}
