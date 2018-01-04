/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.view;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import stockexchangeapp.MainApp;
import stockexchangeapp.model.Company;
import stockexchangeapp.model.Currency;
import stockexchangeapp.model.Investor;
import stockexchangeapp.model.StockExchange;

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
    @FXML
    Button newCompany;
    @FXML
    Button newStockExchange;
    @FXML
    Button newInvestor;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleNewCurrency() {
        Currency tempCurrency = new Currency();
        boolean okClicked = showCurrencyFormDialog(tempCurrency);
        if (okClicked) {
            app.getCurrencyData().add(tempCurrency);
            System.out.println(app.getCurrencyData());
        }
    }
    
    @FXML
    private void  handleNewCompany() {
        Company tempCompany = new Company();
        boolean okClicked = showCompanyFormDialog(tempCompany);
        if (okClicked) {
            app.getCompanyData().add(tempCompany);
            app.getAbbreviationsSet().add(tempCompany.getAbbreviation());
            System.out.println(app.getCompanyData());
        }
        
    }

    @FXML
    private void  handleNewStockExchange() {
        StockExchange tempStockExchange = new StockExchange();
        boolean okClicked = showStockExchangeFormDialog(tempStockExchange);
        if (okClicked) {
            app.getStockExchangeData().add(tempStockExchange);
            System.out.println(app.getStockExchangeData());
        }
        
    }
    
    @FXML
    private void  handleNewInvestor() {
        Investor tempInvestor = new Investor();
        boolean okClicked = showInvestorFormDialog(tempInvestor);
        if (okClicked) {
            app.getInvestorData().add(tempInvestor);
            new Thread(tempInvestor).start();
            System.out.println(app.getInvestorData());
        }
        
    }
    
    
    public boolean showCurrencyFormDialog(Currency currency) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CurrencyFormDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Currency");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(app.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the currency into the controller.
            CurrencyFormDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCurrency(currency);
            
            // Set the dialog icon.
            //dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean showCompanyFormDialog(Company company) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CompanyFormDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Company");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(app.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the currency into the controller.
            CompanyFormDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(app);

            controller.setCompanyFields(company);
            
            // Set the dialog icon.
            //dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean showStockExchangeFormDialog(StockExchange stockExchange) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/StockExchangeFormDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New StockExchange");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(app.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the currency into the controller.
            StockExchangeFormDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(app);
            controller.setStockExchangeFields(stockExchange);
            
            // Set the dialog icon.
            //dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean showInvestorFormDialog(Investor investor) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/InvestorFormDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Investor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(app.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the currency into the controller.
            InvestorFormDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(app);

            controller.setInvestorFields(investor);
            
            // Set the dialog icon.
            //dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
