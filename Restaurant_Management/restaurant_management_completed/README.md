# Restaurant Management System (Java Console)

## Features
- View, occupy, and free restaurant tables.
- View a sample menu with item prices.
- Create an order for an occupied table.
- Add menu items and quantities to an order.
- View all orders, print a bill, and mark an order as paid.
- Basic validation for IDs, quantity, table status, and order status.

## Requirements
- JDK 11 or newer (the code uses `var` and switch expressions, so use JDK 14+; JDK 17 recommended).
- VS Code or another Java IDE (optional).

## Project structure
```text
src/com/rest/
  Main.java
  CLI/RestaurantCLI.java
  models/
    Table.java
    MenuItem.java
    OrderItem.java
    Order.java
  repository/
    TableRepository.java
    MenuItemRepository.java
    OrderRepository.java
  service/
    TableService.java
    MenuService.java
    OrderService.java
```

## Run in Windows PowerShell
From the project root:
```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object FullName)
java -cp out com.rest.Main
```
If using a JDK that doesn't support switch-arrow syntax, use JDK 17 or newer.

## Recommended workflow
1. View tables.
2. Occupy a free table.
3. View menu items.
4. Create an order for the occupied table.
5. Add menu items and quantities using their IDs.
6. Print the bill and verify the total.
7. Mark the order as paid.

Data is held in memory, so it resets when the program restarts. This is a learning project and does not yet connect to a database or process real payments.
