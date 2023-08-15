[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-8d59dc4de5201274e310e4c54b9627a8934c3b88527886e3b421487c677d23eb.svg)](https://classroom.github.com/a/nCSHVuVh)
# COMP 132: Advanced Programming 
# Spring2023 Lab-3 PreLab

# IMPORTANT

For each lab assignment, **you must include and sign (by writing your name and student id number) the following Pledge of Honor statement at the beginning of your main method source code file. After including this statement, make sure that you do the commit and push operation on GitHub. Otherwise, your lab solution will not be graded.**
```
/* *********** Pledge of Honor ************************************************ *

I hereby certify that I have completed this lab assignment on my own
without any help from anyone else. I understand that the only sources of authorized
information in this lab assignment are (1) the course textbook, (2) the
materials posted at the course website and (3) any study notes handwritten by myself.
I have not used, accessed or received any information from any other unauthorized
source in taking this lab assignment. The effort in the assignment thus belongs
completely to me.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Name Surname, Student id>
********************************************************************************/
```
## IMPORTANT NOTE 2: After you complete the tasks, do not forget to commit your changes and push them to your repository on Github.

## IMPORTANT NOTE 3: This README.md file only contains the instructions for PRELAB. You will also have INLAB questions.

## IMPORTANT NOTE 4: DO NOT CHANGE ANY METHODS OR VARIABLES THAT ARE PROVIDED TO YOU!!!

# Lab-3 PreLab : Ship Simulation Engine 

In this programming lab, you are given a scenario and a Java project. You are asked to write multiple class declarations, implement the methods described and test the correctness of your implementation.

**Scenario:** As an eminent developer of the Ministry of Defence, we need to shift you to a confidential project called “Ship Simulation Engine” that is in the early stages of development.

The project has an arena that represents a sea, and ships that we’ll place in. Since it’s in the early development stage what you need to do is to simply select a coordinate that comprises x and y values and attack that location to destroy ships. Simulation ends when you destroy all the ships that you placed in. 

You’ll receive a project structure as follows

![Screenshot 2023-03-22 at 5 46 53 PM](https://user-images.githubusercontent.com/22853419/226942564-619c404e-9a2b-4003-aa06-1e53f97939bb.png)


Before starting, please examine the project and try to understand what we’ve developed so far.

### Open the “models” package

* There’s a class called **Location**
	* declare x and y coordinates with proper variable type. 
	* Implement a constructor that takes x and y coordinates as inputs
	* Implement only getter methods for those coordinates since we don’t want to change the “status” of the object after creation (this is called immutability, like strings in java)
	* Write a toString method for that class with the format of “Location(x = {}, y = {})”  {} corresponds to the actual values.

* There’s a class called **Ship**
	* Each ship must have a name, health, Location and isAlive as its status along with xwidth and ywidth as sizes of the ship.
	* Write a constructor that takes a name and ship sizes (width and height of the ship)
	* Inside the constructor initialize all variables, set health to 100 and isAlive to true.
	* Write a method called reduceHealth that takes a damage score as a parameter and subtract it from the actual health. If the health of the ship is below 1 then set its isAlive status to false.
	* write a toString method for the class with the format of “Ship(name={}, isAlive={}, health={} … )
	* Write getters and setters when needed.

### Open the “simulationengine” package:

* There exists a class called **BattleshipSimulationEngine** that contains three important methods called “init, start and update” 

  * init: is called once and responsible for setting up the simulation environment
  * update: is called relentlessly unless the simulation is end
  * start: is called once to start the simulation.

In the start(), as you can see, we call the update() method as long as the “status” of the simulation is true. So to end the simulation you need to set the “status” variable to false at some point in the simulation.

* Implementation of the init method is what you should do first, do the following
  * Set status to true so that the simulation can begin.
  * initialize the arena variable with proper width on the x axis and height on the y axis.
  * add one ship to arena using addShip(..) method defined in arena
  * call the provided arena.drawArena(...) to see the set up environment.


* Implementation of update() method is as follows:
	* if there are alive ships in the arena you can call getAliveShipCount(..) defined in arena
  * get x location from user
  * get y location from user
  * call arena.attack(...) method to attack the specified location.
  * if there are no alive ships in the arena then you need to terminate the simulation.

### Open the “models” package again

#### Open “BattleshipArena” class.

The class has a variable named xySpace, which is a 2D char array and corresponds to the sea/simulation environment. When you add ships to the environment it should look like the following when you draw it. Here Ships are represented with the letter ‘S’ and when they’re hit ‘S’ becomes ‘X’ to represent the damage.

![Screenshot 2023-03-22 at 5 19 23 PM](https://user-images.githubusercontent.com/22853419/226933930-e9b80ba1-90fc-4938-9b07-4e8f1dc9f1ef.png)


The value of the top left is (0,0) corresponds to xySpace[0][0] and it goes by 1 to the left and the bottom
For example the coordinates of the left most ship are (5,0) and its sizes are (5,3)
The values of the biggest ship in the map as follows
Location -> (0,15) Size -> (7,6)

* Implement the constructor that takes width(“xlength”) and height(“ylength”) of the environment 
* initialize the “shipList” array to 10 ships max
* initialize the xySpace with given parameters
* to give the default value of the “xySpace” create a method named initXYSpace that takes the “xySpace” as its first parameter and a default pattern (in this image it is a dot ‘.’ ) and then assign that pattern to each elements of the array. 

* finally, call the initXYSpace() in the constructor to set the default pattern of the xySpace.

* implement a method called **getAliveShipCount()**
	* traverse the Ship array and call the **isAlive()** method to check the status of the ship and count them if it’s alive then return the final sum.

* implement a method called **showShipInfo**
	* traverse the shipList and print each ship with System.out.println(ship). This method automatically calls the toString method you implemented or you can do this as well System.out.println(ship.toString()) 

* implement a method called **isLocationValid** that takes a **Location** object. 
	*Check if this location exceeds the size of the xySpace. If so return false, if it doesn’t exceed than return true

* implement a method called **isLocationHitsTheShip** that takes a **Ship** and a **Location** as parameters
  * check if that location hits the ship or not and return a boolean result
  * remember each ship has a coordinate and size, if the given location drops into that “location range” that it’s a hit.
  * say a ship has the following
  	Location -> (5,0)
	Size ->  (5,3)
	* given location hits the ship if
		5 <= location.x < (5 + 5) and 0 <= location.y < (0 + 3)

* implement a method called **isHit** that takes a **location object** and returns the corresponding ship or null if no hit
	* if the xySpace value of the given location is ‘S’, meaning there’s a ship here, it it’s not return null.
	* traverse the shipList array ship by ship
	* call **isLocationHitsTheShip(..)** here, if so, return the corresponding ship as a result.
	* if no hit exists return null

* implement a method called **Attack** that takes a **location** object as a parameter
	* check if the location valid (call **isLocationValid()** method)
	* then check if it hits a ship, call **isHit(..)** method and get a result
	* if result is null meaning there’s no hit
	* else set ‘X’ to corresponding location in xySpace, reduce the health of the ship by calling calculateHitDamage(...) and call the provided drawArena method to draw the current status of environment


* implement a method called **addShip** that takes a **Ship** and **Location** objects
	* check if we have enough room for the ship by checking the fullness of the shipList array.
	* check if location is valid if it doesn’t return false
	* check if ship fits into the location if it doesn’t return false
	* check if the ship that we’ll add overlaps any other ship, you can simply check if xySpace has ‘S’ value or not. If it has, return false.
	* if any fails existed up until now please notify the user by printing the error like System.err.println(erorr_message) before you return false from the method..
	* if you haven't returned from the method so far then we can add the ship
	* set the given location in the parameters to shiplocation by calling ship.setLocation.
	* set ‘S’ to the corresponding ship_location + shipsize location range of xySpace
	* add ship to the shipList array and return true.

Now start the program and see if it works. 

This is the end of simulation of the given area above. 

![Screenshot 2023-03-22 at 5 19 42 PM](https://user-images.githubusercontent.com/22853419/226933835-a1e9961f-63ad-4bd0-8219-c5388a453848.png)

## Prelab Sample Execution
![image](https://user-images.githubusercontent.com/22853419/226930755-53ee59ad-e8f5-4be3-89f9-f2a5062c501b.png)
## IMPORTANT NOTE: DO NOT CHANGE ANY METHODS OR VARIABLES THAT ARE PROVIDED TO YOU!!!
