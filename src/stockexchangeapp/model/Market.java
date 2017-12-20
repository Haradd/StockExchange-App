/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author blazej
 */
public class Market {
    
    private final StringProperty name;
    private final DoubleProperty fee;
    
    public Market(String name, Double fee){
        this.name = new SimpleStringProperty(name);
        this.fee = new SimpleDoubleProperty(fee);
        
    }
   
}
