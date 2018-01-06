/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author blazej
 */
public class CommodityMarket extends Market {
    
    private List<Commodity> commodities;
    
    public CommodityMarket(String name, Double fee){
        super(name, fee);
        
        this.commodities = new ArrayList<>();

    }
}
