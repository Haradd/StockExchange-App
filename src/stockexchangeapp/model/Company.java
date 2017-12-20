/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author blazej
 */
public class Company {
    
    private StringProperty name;
    private StringProperty abbreviation;
    private StringProperty chairman;
    private StringProperty firstListingDate;
    private DoubleProperty min;
    private DoubleProperty max;
    private DoubleProperty open;
    private DoubleProperty close;
    private DoubleProperty current;
    private DoubleProperty bid;
    private DoubleProperty offer;
    private DoubleProperty value;
    private DoubleProperty marketValue; //shares_count * current
    private IntegerProperty sharesCount;
    private IntegerProperty volume; 
   
    
    public Company(String name, String abbreviation, String chairman, String firstListingDate, Double min, Double max, Double open, Double close,
                   Double current, Double bid, Double offer, Double value, Double marketValue, Integer sharesCount, Integer volume) {
        
        this.name = new SimpleStringProperty(name);
        this.abbreviation = new SimpleStringProperty(abbreviation);
        this.chairman = new SimpleStringProperty(chairman);
        this.firstListingDate = new SimpleStringProperty(firstListingDate);
        this.min = new SimpleDoubleProperty(min);
        this.max = new SimpleDoubleProperty(max);
        this.open = new SimpleDoubleProperty(open);
        this.close = new SimpleDoubleProperty(close);
        this.current = new SimpleDoubleProperty(current);
        this.bid = new SimpleDoubleProperty(bid);
        this.offer = new SimpleDoubleProperty(offer);
        this.volume = new SimpleIntegerProperty(volume);
        this.sharesCount = new SimpleIntegerProperty(sharesCount);
        
    }
    
}
