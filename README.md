StockExchangeApp is an multi-threaded JavaFX application that let's you simulate brokers world.


## How does it work?

When you run the app, the game starts.
Initially there are present one stock exchange, some investors, companies and one commodity market with assets. 
Except the markets they are separate threads.
Investors buy and sell assets with some random time intervals. They can also randomly increase their budget, so that they won't get blocked. After transaction new asset price is established randomly.</br>
It's possible to save the state and restore it later.</br>
To resolve mutex problem I used default built-in monitor.




## Control Panel

![Control Panel](https://raw.githubusercontent.com/Haradd/StockExchange-App/master/screenshots/control-panel.png)

Here you can manage the app.




## Stocks and details

![Details](https://github.com/Haradd/StockExchange-App/blob/master/screenshots/stock-details.png?raw=true)
![Stocks](https://github.com/Haradd/StockExchange-App/blob/master/screenshots/stocks.png?raw=true)





## Statistics

![Statistics](https://github.com/Haradd/StockExchange-App/blob/master/screenshots/statistics.png?raw=true)

You're able to compare many assets on the one chart




## News

![News](https://github.com/Haradd/StockExchange-App/blob/master/screenshots/news.png?raw=true)

There are downloaded some latest news from bloomberg json api 

