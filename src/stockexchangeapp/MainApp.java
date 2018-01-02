package stockexchangeapp;


import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import stockexchangeapp.model.Company;
import stockexchangeapp.model.XmlDataWrapper;
import stockexchangeapp.model.Currency;
import stockexchangeapp.model.Investor;
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
    private ObservableList<Investor> investorData  = FXCollections.observableArrayList();
    
    private Set<String> abbreviationsSet = new HashSet<String>();

    
    public MainApp(){
        this.currencyData.add(new Currency("Zloty", "PLN"));
        this.stockExchangeData.add(new StockExchange("Warsaw Stock Exchange", 0.05, "WSE", this.currencyData.get(0), "Poland", "Warsaw", "Książęca 4" ));
        this.companyData.add(new Company("TAURON Polska Energia S.A.", "TPE", "Filip Grzegorczyk", "06.2010", 3.0, 3.5, 3.1, 3.4, 3.3, 3.1, 3.5,
                30000.0, 60000.0, 10000, 20000, 5.1, stockExchangeData.get(0)));
        abbreviationsSet.add("TPE");
        
        this.investorData.add(new Investor("Błażej", "Piaskowski", "1", 10000.0, stockExchangeData.get(0)));
        
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
    
    public ObservableList<Investor> getInvestorData(){
        return investorData;
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
    
        /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
            primaryStage.setTitle("StockExchangeApp - " + file.getName());
        } else {
            prefs.remove("filePath");
            primaryStage.setTitle("StockExchangeApp");
        }
    }
    
   
    public void loadDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(XmlDataWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            XmlDataWrapper wrapper = (XmlDataWrapper) um.unmarshal(file);
            
            currencyData.clear();
            currencyData.addAll(wrapper.getCurrencies());

            companyData.clear();
            companyData.addAll(wrapper.getCompanies());
            companyData.forEach((company) -> {
                abbreviationsSet.add(company.getAbbreviation());
                //odpal wątek
            });
            
            stockExchangeData.clear();
            stockExchangeData.addAll(wrapper.getStockExchanges());
            
           investorData.clear();
           investorData.addAll(wrapper.getInvestors());
           investorData.forEach((investor) -> {
               //odpal wątek
           });

            // Save the file path to the registry.
            setFilePath(file);

        } catch (Exception e) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not load data");
        	alert.setContentText("Could not load data from file:\n" + file.getPath());
        	
        	alert.showAndWait();
        }
    }

    public void saveDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(XmlDataWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping data.
            XmlDataWrapper wrapper = new XmlDataWrapper();
            wrapper.setCurrencies(currencyData);
            wrapper.setCompanies(companyData);
            wrapper.setStockExchanges(stockExchangeData);
            wrapper.setInvestors(investorData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setFilePath(file);
        } catch (Exception e) { // catches ANY exception
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not save data");
        	alert.setContentText("Could not save data to file:\n" + file.getPath());
        	
        	alert.showAndWait();
        }
    }

    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
