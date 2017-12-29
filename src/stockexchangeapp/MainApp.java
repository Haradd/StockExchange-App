package stockexchangeapp;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import stockexchangeapp.model.Company;
import stockexchangeapp.model.Currency;
import stockexchangeapp.model.StockExchange;
import stockexchangeapp.view.ControlPanelController;
import stockexchangeapp.view.PricesPanelController;
import stockexchangeapp.view.RootLayoutController;

/**
 *
 * @author blazej
 */

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    private ObservableList<Currency> currencyData = FXCollections.observableArrayList();
    private ObservableList<StockExchange> stockExchangeData  = FXCollections.observableArrayList();
    private ObservableList<Company> companyData  = FXCollections.observableArrayList();
    
    private Set<String> abbreviationsSet = new HashSet<String>();

    
    public MainApp(){
        this.currencyData.add(new Currency("Zloty", "PLN"));
        this.stockExchangeData.add(new StockExchange("Warsaw Stock Exchange", 0.05, "WSE", this.currencyData.get(0), "Poland", "Warsaw", "Książęca 4" ));
        this.companyData.add(new Company("TAURON Polska Energia S.A.", "TPE", "Filip Grzegorczyk", "06.2010", 3.0, 3.5, 3.1, 3.4, 3.3, 3.1, 3.5,
                30000.0, 60000.0, 10000, 20000, 5.1, stockExchangeData.get(0)));
        abbreviationsSet.add("TPE");
  

    }
    
    public ObservableList<Currency> getCurrencyData(){
        return currencyData;
    }
    
    public ObservableList<StockExchange> getStockExchangeData(){
        return stockExchangeData;
    }
    
    public ObservableList<Company> getCompanyData(){
        return companyData;
    }
    
    public Set<String> getAbbreviationsSet(){
        return abbreviationsSet;
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("StockExchangeApp");

        initRootLayout();

        showControlPanel();
        showPricesPanel();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();            

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            //Give the controller access to main app
            RootLayoutController controller = loader.getController();
            controller.setApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showControlPanel() {
        try {
            // Load control panel.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ControlPanel.fxml"));
            AnchorPane controlPanel= (AnchorPane) loader.load();
            
            // Set control panel into the TabPane of root layout.       
            Tab tab = new Tab();
            tab.setText("Control Panel");
            tab.setContent(controlPanel);
            
            VBox vbox = (VBox) rootLayout.getTop();
            TabPane tabPane = (TabPane) vbox.getChildren().get(1);
            tabPane.getTabs().add(tab);
            
            // Give the controller access to the main app.
            ControlPanelController controller = loader.getController();
            controller.setApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showPricesPanel() {
        try {
            // Load control panel.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PricesPanel.fxml"));
            AnchorPane pricesPanel= (AnchorPane) loader.load();
            
            // Set prices panel into the TabPane of root layout.                   
            Tab tab = new Tab();
            tab.setText("Prices & Markets");
            tab.setContent(pricesPanel);
      
            VBox vbox = (VBox) rootLayout.getTop();
            TabPane tabPane = (TabPane) vbox.getChildren().get(1);
            tabPane.getTabs().add(tab);            
            
            // Give the controller access to the main app.
            PricesPanelController controller = loader.getController();
            controller.setApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
