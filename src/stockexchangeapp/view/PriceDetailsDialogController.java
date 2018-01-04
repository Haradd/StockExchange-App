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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import stockexchangeapp.model.Company;

/**
 * FXML Controller class
 *
 * @author blazej
 */
public class PriceDetailsDialogController implements Initializable {

    
    private Stage dialogStage;
    private Company company;
    private boolean okClicked = false;
    
    @FXML
    private Label companyName;
    @FXML
    private Label currentPrice;
    @FXML
    private Label changeFXML;
    @FXML
    private Label min;
    @FXML
    private Label max;
    @FXML
    private Label volume;
    @FXML
    private Label turnover;
    @FXML
    private Label sharesNumber;
    @FXML
    private Label marketValue;
    @FXML
    private Label chairman;
    @FXML
    private Label abbreviation;
    @FXML
    private Label firstListingDate;
    
    @FXML
    private LineChart<?,?> lineChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        
        // Set the dialog icon.
       // this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }
    
    public void setCompanyFields(Company company) {
        this.company = company;
            
        companyName.setText(company.getName());
        currentPrice.setText(String.valueOf(company.getCurrent()));
        
        double change = company.getChange();
        if (change > 0) {
            String changeText = "+" + String.valueOf(company.getChange()) + "%";
            changeFXML.setText(changeText);
            changeFXML.setStyle("-fx-text-fill: green;");
        } else {
            String changeText = "-" + String.valueOf(company.getChange()) + "%";
            changeFXML.setText(changeText);
            changeFXML.setStyle("-fx-text-fill: red;");
        }
        
        min.setText(String.valueOf(company.getMin()));
        max.setText(String.valueOf(company.getMax()));
        turnover.setText(String.valueOf(company.getTurnoverValue()));
        volume.setText(String.valueOf(company.getVolume()));
        sharesNumber.setText(String.valueOf(company.getSharesCount()));
        marketValue.setText(String.valueOf(company.getMarketValue()));
        
        chairman.setText(company.getChairman());
        abbreviation.setText(company.getAbbreviation());
        firstListingDate.setText(company.getFirstListingDate());
        
        XYChart.Series series = new XYChart.Series();
        
        for(int i = 5; i<100; i+=3){
            series.getData().add(new XYChart.Data(String.valueOf(i), i+5));
            lineChart.getData().addAll(series);
        }
        
   
        
    }
       
    @FXML
    private void handleOk() {
        dialogStage.close();


    }
   
    
}