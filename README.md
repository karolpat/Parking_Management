# Parking_Management

### Starting the application

First fork and clone the repository. Then in the project directory Parking_Management open the terminal and key in

```
./start.sh
```

To check functionalities use Postman preferably on localhost:5555/
Ther is a text file address.txt containing all addresses.

The project uses embedded H2 database.

### REST API for car parking management. 

Implements short user stories as follows:

* As a driver, I want to start the parking meter, so I don’t have to pay the fine for the invalid parking.
* As a parking operator, I want to check if the vehicle has started the parking meter.
* As a parking operator, I want to set user vip status.
* As a parking operator, I want to be shown a list of all users of vip status.
* As a driver, I want to stop the parking meter, so that I pay only for the actual parking time.
* As a driver, I want to know how much I have to pay for parking.
* As a parking owner, I want to know how much money was earned during a given day.

### There are two types of Users

* VIP - first hour of parking is free, second 2 PLN, every next is 1,5 times more than the previous price per hour.
* Regular - first hour of parking is 1 PLN, second 2 PLN, every next is 2 times more than the previous price per hour.

### Authors

* **Karol Patecki** as **karolpat** 

