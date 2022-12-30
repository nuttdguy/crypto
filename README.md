# Trade P&L 
Application consisting of modules to generate reports:
- read data from .csv files
- fetch data from api endpoints 
- fetch data from exchange endpoints
- store data into a db
- read data from db in order to generate reports
- read data from csv in order to generate reports

## phase 1.0 :: module to fetch data and write to csv
- coinmarketcap {1.0}
- etherscan {1.0}
- bscscan {1.0}
- lunarcrush ??
- include testing
- additional resources tbd

## phase 2.0 :: module to persist data to db
- read from csv files - persist results to local db {1.0}
- include testing

## phase 3.0 :: module to read from .csv and / or db and generate reports
- read from transaction history + generate report => in order to determine buy / sell values {1.0}
- read from transaction history + generate report => in order to determine p & l {1.0}
- include testing
- additional reports tbd

## phase 4.0 :: create spring or javaEE service layer api
- endpoint for fetching transactions
- endpoint for fetching performance metrics
- endpoint for fetching general crypto data
- endpoint for create accounts
- more tbd

## phase 5.0 :: create react or angular UI layer
- transaction dashboard
- historical dashboard
- tbd

### notes:
    | files and fields and to extract data from

    | common: timestamp,from,to
    | for reports:

    | tokentx_bsc_account.csv
    => value, from-address, to-address,blockhash, contractAddress, confirmations, timestamp, gasUsed, gas, gasPrice,
     tokenName,tokenSymbol,tokenDecimal

    | internal_bsc_account.csv
    => timeStamp, blockNumber, from, to, value, hash

    | txlist_bsc_account.csv
    => blockhash, functionName, confirmations, timeStamp, blockNumber, gasUsed,  gas, gasPrice, from, to, value, hash

    | bnb_price_history.csv
    => Date,open,high,low,close

    * calc: purchasePrice,sellPrice,tokensAvailable,totalSold,totalAcquired,tokenValue,totalPurchases,
    * avgPurchasePrice,
    *
    * hSet unique key: 
    *   - from + timeStamp + hash
    *   - to + timeStamp + hash
    *   - hash 
    *
    * fields to include in db model
    * bas_account_tokentx.csv
    *   - timeStamp = 2022-12-26T15:59:05.760399700
    *   - gasUsed = 51183
    *   - from = 0x8894e0a0c962cb723c1976a4421c95949be2d4e3
    *   - to = 0x1c93ba02fcf68fd9bee9e1a15a21495beaf36ad5
    *   - value = 40239.1833999999
    *   - hash = 0xedf20087c99be3e8755b5b1da91cd210470dc410c79a97978e9391c842b6f3b9
    *   - gasPrice = 0.00000001
    *   - tokenName = Contentos
    *   - tokenSymbol = COS
    *   - tokenDecimal = 18
    *   - contractAddress = 0x96dd399f9c3afda1f194182f71600f1b65946501
    * bsc_account.csv
    *   - functionName = swapExactEthForTokens
    *   - timeStamp = 2022-12-26T10:53:47.849990200
    *   - gasUsed = 21000
    *   - from = 0xcd5f3c15120a1021155174719ec5fcf2c75adf5b
    *   - to = 0x1c93ba02fcf68fd9bee9e1a15a21495beaf36ad5
    *   - value = 5.80685741
    *   - gasPrice =  16,000,000,000.00 / wei
     * bnb_price_history.csv
     *  - Date = 19-Dec-22
     *  - Open = 251.24
     *  - High = 252.93
     *  - Low = 238.65
     *  - Close = 240.66
     *
     *
    * */
