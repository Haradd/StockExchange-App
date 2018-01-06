/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.model;

import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 *
 * @author blazej
 */
public class Investor implements Runnable {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty id;
    private DoubleProperty budget;
    
    private StockExchange memberOfStockExchange;
    
    private HashMap<Company, Integer> shares;
    
    
    public Investor(String firstName, String lastName, String id, Double budget, StockExchange memberOfStockExchange) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.id = new SimpleStringProperty(id);
        this.budget = new SimpleDoubleProperty(budget);
        this.shares = new HashMap<>();
        this.memberOfStockExchange = memberOfStockExchange;
    }
    
    public Investor() {
        this(null, null, null, 0.0, null);
    }
    
    private volatile boolean running = true;
    public void terminate() {
        running = false;
    }
    
    public void run(){
        while(running){
            int i =0;
            try {
                buyShare();
                //sellAllShares();
                i++;
                raiseBudget();
                
                if ( i == 3){
                    sellAllShares();
                }
                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Investor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println(this.getId() + " investor thread terminated");
    }
    
    public void raiseBudget() throws InterruptedException{
        int min = 5;
        int max = 7;
        int randomInt = ThreadLocalRandom.current().nextInt(min * 1000, max * 1000);
        
        sleep(randomInt);
        synchronized(this){
            this.setBudget(this.getBudget() + randomInt);
        }
        System.out.println(this.getBudget() + " -investorID: " + this.getId());
    }
    
    public void buyShare() throws InterruptedException{
        int min = 2;
        int max = 7;
        int randomInt = ThreadLocalRandom.current().nextInt(min * 1000, max * 1000);
        
        sleep(randomInt);  
        synchronized(this.getStockExchangeBelonging()){
            List<Company> companies = this.memberOfStockExchange.getCompanies();
            int randomIndex = ThreadLocalRandom.current().nextInt(0, companies.size());
            Company company = companies.get(randomIndex);

            if (company.getSharesForSale() > 0 && this.getBudget() > 0) {

                int sharesToBuy = (int) ((int) this.getBudget() / company.getCurrent());

                if (company.getSharesForSale() - sharesToBuy >= 0) {
                    double value = sharesToBuy * company.getCurrent() ;
                    this.getShares().put(company, sharesToBuy);                    
                    this.setBudget(this.getBudget() - value);
                    
                    company.getTransactionList().add(new Transaction(company.getCurrent(), sharesToBuy));
                    company.calculateCurrentPrice();
                    company.setSharesForSale( company.getSharesForSale() - sharesToBuy);
                    company.setVolume(company.getVolume() + sharesToBuy);
                    company.setTurnoverValue(company.getTurnoverValue() + value);

                    System.out.println(String.valueOf("investor:" + this.getId() + " buys " + sharesToBuy + " for: " + value + " from: " + company.getName()));
                } else{
                    // buy all available shares
                    int availableShares = company.getSharesForSale();
                    double value = availableShares * company.getCurrent();
                    this.getShares().put(company, availableShares);                                       
                    this.setBudget( this.getBudget() - value);

                    company.getTransactionList().add(new Transaction(company.getCurrent(), availableShares));
                    company.calculateCurrentPrice();
                    company.setSharesForSale(0);   
                    company.setVolume(company.getVolume() + availableShares);
                    company.setTurnoverValue(company.getTurnoverValue() + value);
                    
                    System.out.println(String.valueOf("investor:" + this.getId() + " buys " + availableShares + " for: " + value + " from: " + company.getName()));
                }
                
                company.getInvestorSet().add(this);
                
            }  else System.out.println("investor: " + this.getId() + "can't buy shares");    
        }
    }
    
    public void sellShares() throws InterruptedException {
        int min = 5;
        int max = 7;
        int randomInt = ThreadLocalRandom.current().nextInt(min * 1000, max * 1000);
        
        sleep(randomInt);  
        synchronized(this.getStockExchangeBelonging()){
            if (!this.getShares().isEmpty()){
                Map.Entry<Company, Integer> entry = this.getShares().entrySet().iterator().next();
                Company company = entry.getKey();

                int investorShares = this.shares.get(company);
                double value = investorShares * company.getCurrent();
                double income = value * (1 - this.getStockExchangeBelonging().getFee()/100);

                company.getTransactionList().add(new Transaction(company.getCurrent(), investorShares));
                company.getInvestorSet().remove(this);
                company.calculateCurrentPrice();
                company.setSharesForSale( company.getSharesForSale() + investorShares);
                company.setVolume(company.getVolume() + investorShares);
                company.setTurnoverValue(company.getTurnoverValue() + value);           

                this.setBudget(this.getBudget() + income);
                this.getShares().remove(company);
                
            }
        }
    }
    
    public void sellAllShares() {
        synchronized(this.getStockExchangeBelonging()){
            if (!this.getShares().isEmpty()){
                
                
                Iterator it = this.getShares().entrySet().iterator();
                while (it.hasNext()) {
                    HashMap.Entry pair = (HashMap.Entry)it.next();
                    
                    Company company = (Company) pair.getKey();
                    int investorShares = (int) pair.getValue();
                    
                    double value = investorShares * company.getCurrent();
                    double income = value * (1 - this.getStockExchangeBelonging().getFee()/100);

                    company.getTransactionList().add(new Transaction(company.getCurrent(), investorShares));
                    company.getInvestorSet().remove(this);
                    company.calculateCurrentPrice();
                    company.setSharesForSale( company.getSharesForSale() + investorShares);
                    company.setVolume(company.getVolume() + investorShares);
                    company.setTurnoverValue(company.getTurnoverValue() + value);     
                    
                    this.setBudget(this.getBudget() + income);
                    this.getShares().remove(company);
                }             
            }
        }       
    }
    
    @XmlElementWrapper(name = "shares")
    @XmlElement(name = "share")
    public HashMap<Company, Integer> getShares() {
        return shares;
    }
    
    public void setShares(HashMap<Company, Integer> shares) {
        this.shares = shares;
    }

    public final String getFirstName() {
        return firstName.get();
    }

    public final void setFirstName(String value) {
        firstName.set(value);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public final String getLastName() {
        return lastName.get();
    }

    public final void setLastName(String value) {
        lastName.set(value);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public final String getId() {
        return id.get();
    }

    public final void setId(String value) {
        id.set(value);
    }

    public StringProperty idProperty() {
        return id;
    }

    public final double getBudget() {
        return budget.get();
    }

    public final void setBudget(double value) {
        budget.set(value);
    }

    public DoubleProperty budgetProperty() {
        return budget;
    }    
    
    public final StockExchange getStockExchangeBelonging () {
        return memberOfStockExchange;
    }
    
    public final void setStockExchangeBelonging(StockExchange stockExchange) {
        memberOfStockExchange = stockExchange;
    }
    
    
}
