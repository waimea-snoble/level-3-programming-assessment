# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range e.g. Move to the limits of the map, top of the map, bottom, left, right. testing that i can navigate the map boundaries to its full extent
- **INVALID** data values are those that the program should reject e.g I cant click the button to leave the map, it is faded out


brief Test plan before coding

## Player movement

this will test all of the players movement and changing the location name, description, and item being collected will change

### Test Data To Use

- I will be moving around the map to test the movement in all directions and the location name, description, and collect text will change


### Expected Test Result

- when the player clicks on an arrow where there is no obstacle they will move to another location, however if they
  try to move while there is an obstacle in the way e.g locked door the button will be disabled and if they click it
  nothing will happen.


---

## Collect button

- tests what will happen when the user clicks on the collect button when there is an item to be collected and when there
  is no item to be collected.

### Test Data To Use

The player will click on the collect button in different locations

### Expected Test Result

- when the player clicks on the collect button the item they picked up will be added to the inventory, this item will be different
  for each location
- if there is no option to collect an item the collect button will be blank, disabled, and nothing will happen when clicked

---


Test plan after coding

---

## Game Setup and game close

**VALID** testing what happens when the player runs the game and closes the game

### Test Data To Use

The player will click the run button to play the game and then click on the X button on the top right corner to close the game
i have chosen this to 

### Expected Test Result

When the player runs the game there will be a pop up explaining the back story to the game and what the player needs to do to complete it
when the player clicks on the X button on the top right the game window will close and the game will end

---

## Player movement

**VALID** tests whether the player can move around on the map and the current location name, description, and action type will change
**BOUNDARY** test to see if the player can explore the map boundaries to its full extent
**INVALID** test they player trying to move through a locked path

### Test Data To Use

- I will be moving around the whole map which will show me moving through an open path e.g. the woods to teh abandoned house,
  as well as me trying to move through a blocked path e.g. moving through the closed attic, and also me testing all of the map boundaries.
  when i move between locations this will also change the current location name, description, and the action button name and type
- i have chosen to go to every location to show the valid and invalid moves and that the player can not leave the map



### Expected Test Result

- when the player moves left this will ba a valid move and they will move from the abandoned house into the hallway, 
  resulting in the current location name, description, and action type changing,
  if the player tries to move through the closed attic or outside of the map nothing will happen as the button will be disabled
- i have chosen this data because the player can freely move, as well as there being a blocked path


---

- NOTE: I changed the collect button to an action button because i wanted to have different actions for each location e.g. collect, use, open, or none

## Action button and item being added to inventory

**VALID** tests what happens when a player clicks the action button when an action is available
**INVALID** tests what happens when a player clicks the action button when they are in a location with no action or they dont have the item needed to make the action

### Test Data To Use
- the player will start at the bedroom and click the action button
- the player will start at the castle wall and click the action button, 
  then move to the castle courtyard and click the action button, 
  then move to the castle mine and click the action button, 
  then move to the castle courtyard and click the action button,
- I have chosen these locations because these are locations with no action, open action, collect action, and use action

### Expected Test Result

- when the player clicks the action button in the frast to Frostfang Peak nothing will happen, 
  when the  player clicks the action button at the castle gate there will be a message saying "There is no response", 
  when the player clicks the action button in the castle courtyard nothing will happen, 
  when the player clicks the action button in the mine there will be a message saying "You found a pickaxe" and the pickaxe will be added to the players inventory, 
  when the player clicks the action button in the castle courtyard after picking up the pickaxe there will be a message saying "You used the pickaxe to break through", removing the pickaxe from the inventory resulting in a new path being unlocked

---

## Help button

**VALID** testing what will happen when the player clicks on the help button

### Test Data To Use

The player will clcik on the help button in multiple locations
I have chosen multiple locations to show that it doesn't matter where the player is, they can get help anywhere

### Expected Test Result

When the player clicks on the help button in any location, a message will pop up showing what the aim of the game is and what each button
does/the controls, 

---

## Player Win

**VALID** Testing what happens when the player has collected all of the required ingredients for the potion
**INVALID** testing what happens when the player has not collected all of the ingredients

### Test Data To Use

- the player will move around the map and collect the water from the spring of life, 
  the frostsnap berries, the flameheart essence, and the leviathan bone
- i have chosen this data because these are the key items the player needs in their inventory in order to win


### Expected Test Result

When the player collects all of these ingredients a message will 
pop up saying "You Win! You found all of the ingredients and made a potion to cure the poison"
and the game will close.
If the playing has not collected all of the ingredients there will be no popup saying that they have
won and they will continue to play the game

---

## Decreasing health and Player Lose

**VALID** testing what happens when the player dies
**VALID** testing what happens to the health when the player moves
**INVALID** testing what happens when the player as at least one health left
**INVALID** testing that happens when the player doesn't move

### Test Data To Use

- The player will move between locations up, down, left and right until the players health reaches 0
- the player will not move
- I have chosen multple locations to show that it works for avery direction

### Expected Test Result

- when the player dies a message will pop up saying "You Win! You found all of the ingredients and made a potion to cure the poison" and the game will end
- when the player's health reaches 0 there will be a message saying "You have died, you failed to gather all of the ingredients. GAME OVER" and the game will end
- when the player moves the players health will decrease by 1
- if the player does not move the health will not decrease
- if the players health is at least 1 the game will not end

---

## clicking on the game while on a popup

**INVALID** test clicking on the game buttons while a popup is visible

### Test Data To Use

I am going to click on a button while the intro pop up, help pop up, action popup, lose pop up, and win popup are visible
i have chosen these to show that it works for every pop up

### Expected Test Result

When i click on a button while a popup is visible nothing will happen and the outline of the popup will flash
indicating it is still there

---
