# Trade P&L 
Application consisting of modules to generate reports:
- read data from .csv files
- fetch data from api endpoints 
- fetch data from exchange endpoints
- store data into a db
- read data from db in order to generate reports
- read data from csv in order to generate reports

## phase 1 :: module to fetch data and write to csv
- coinmarketcap
- etherscan
- bscscan
- lunarcrush ??
- include testing
- additional resources tbd

## phase 2 :: module to persist data to db
- read from csv files - persist results to local db
- include testing

## phase 3 :: module to read from .csv and generate reports
- read from transaction history - generate report in order to determine buy / sell values
- read from transaction history - generate report in order to determine p & l
- include testing
- additional reports tbd

## phase 4 :: create spring or javaEE service layer api
- endpoint for fetching transactions
- endpoint for fetching performance metrics
- endpoint for fetching general crypto data
- endpoint for create accounts
- more tbd

## phase 5 :: create react or angular UI layer
- transaction dashboard
- historical dashboard
- tbd
