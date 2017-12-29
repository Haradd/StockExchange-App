/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.model;

import java.util.HashMap;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author blazej
 */
public class Investor {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty id;
    private DoubleProperty budget;
    
    private HashMap<Company, Integer> shares;
    
    public Investor(String firstName, String lastName, String id, Double budget) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.id.set(id);
        this.budget.set(budget);
    }
    
    public Investor() {
        this(null, null, null, 0.0);
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
    
    
}
