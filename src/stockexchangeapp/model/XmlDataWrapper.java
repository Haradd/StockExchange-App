/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockexchangeapp.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author blazej
 */

@XmlRootElement(name = "Economy")
public class XmlDataWrapper {

    private List<Currency> currencies;
    private List<StockExchange> stockExchanges;
    private List<Company> companies;
    private List<Investor> investors;

    @XmlElement(name = "currency")
    public List<Currency> getCurrencies() {
        return currencies;
    }
    
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    @XmlElement(name = "stockExchange")
    public List<StockExchange> getStockExchanges() {
        return stockExchanges;
    }

    public void setStockExchanges(List<StockExchange> stockExchanges) {
        this.stockExchanges = stockExchanges;
    }
    
    @XmlElement(name = "company")
    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }
    
    @XmlElement(name = "investor")
    public List<Investor> getInvestors() {
        return investors;
    }

    public void setInvestors(List<Investor> investors) {
        this.investors = investors;
    }

}
