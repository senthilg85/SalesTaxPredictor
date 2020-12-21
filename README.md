# SalesTax_CodeChallenge
Build was sucessful

Test coverage:  More than 85%

## PROBLEM: SALES TAXES


## Solution

- Solved using Decorator design pattern for add dynamic pricing with tax calculation
- ShoppingBasket class has a LinkedHashMap<Item, Integer> to store the items added to the basket and its amount.
- Read data from "data" folder files.
- An item is imported if contains "imported" keyword in its name.
- Exempt items are defined in Utilities class as LinkedHashMap<ItemType, Set<String>> ITEM_TYPES_BY_KEYWORDS



## Getting Started

### Prerequisites

- Java 8 (JDK)
- Maven 3


### Compile and build
```
mvn clean package
```

### Run it
to run the program once packaged. From the project folder:

- Do not pass arguments, and the program scans and executes all cases stored in "data" folder
    ```
    java -jar target/SalesTaxPredictor-1.0.jar


## Running the tests

From the project folder:
```
mvn clean test
```
