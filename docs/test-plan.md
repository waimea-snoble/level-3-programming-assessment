# Plan for Testing the Program

The test plan lays out the actions and data I will use to test the functionality of my program.

Terminology:

- **VALID** data values are those that the program expects
- **BOUNDARY** data values are at the limits of the valid range e.g. Move to the limits of the map, top of the map, bottom, left, right. testing that i can navigate the map boundaries to its full extent
- **INVALID** data values are those that the program should reject e.g I cant click the button to leave the map, it is faded out



**VALID**
**BOUNDARY**
**INVALID**

The program has been tested and there is documented evidence that:
- [ ] The game is **fully functional**, showing testing of:
    - [ ] game setup
    - [ ] player movement
    - [ ] other player actions
    - [ ] scoring (if any)
    - [ ] player win / lose states
    - [ ] final feedback (if appropriate)
- [ ] **Valid (expected) data** has been used to test **all data inputs**
- [ ] Where tests have failed, **fixes** are discussed and **tests re-run**

---

## Game Setup

testing what happens when the player runs the game

### Test Data To Use

The player will click the run button to play the game

### Expected Test Result

Statement detailing what should happen.

---

## Player movement

**VALID** tests whether the player can move around on the map
**BOUNDARY** test to see if the player can explore the map boundaries to its full extent
**INVALID** test they player trying to move through a locked path

### Test Data To Use

- the player starts in the abandoned house and will try to move left, then they will try to move up into the attic
  I have chosen these because when the player moves left this is a valid move, 
  and when they try to move into the attic without opening it this is an invalid move.

### Expected Test Result

- when the player moves left this will ba a valid move and they will move from the abandoned house into teh hallway, 
  if the player tries to move through the closed attic or outside of the map nothing will happen as the button will be disabled


---

## Action button

**VALID** tests what happens when a player clicks the action button when an action is available
**INVALID** tests what happens when a player clicks the action button when they are in a location with no action or they dont have the item needed to make the action

### Test Data To Use

- the player will start at the path to Frostfang Peak and click the action button, 
  then move to the castle gate and click the action button, 
  then move to the castle courtyard and click the action button, 
  then move to the mine and click the action button, 
  then move to the castle courtyard and click the action button.
- I have chosen these locations because these are locations with no action, open action, collect action, and use action

### Expected Test Result

- when the player clicks the action button in the frast to Frostfang Peak nothing will happen, 
  when the  player clicks the action button at the castle gate there will be a message saying "There is no response", 
  when the player clicks the action button in the castle courtyard nothing will happen, 
  when the player clicks the action button in the mine there will be a message saying "You found a pickaxe" and the pickaxe will be added to the players inventory, 
  when the player clicks the action button in the castle courtyard after picking up the pickaxe there will be a message saying "You used the pickaxe to break through" resulting in a new path being unlocked

---

## Help button

testing what will happen when the player clicks on the help button

### Test Data To Use

Details of test data and reasons for selection.

### Expected Test Result

Statement detailing what should happen.

---

## Player Win

**VALID** Testing what happens when the player has collected all of the required ingredients for the potion
**INVALID** testing what happens when the player has not collected all of the ingredients

### Test Data To Use

- the player will move around the map and collect the water from the spring of life, 
  the frostsnap berries, the flameheart essence, amd the leviathan bone
-

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

**VALID** The player will move between two locations until the players health reaches 0
**INVALID** the player will not move

### Expected Test Result

- when the player dies a message will pop up saying "You Win! You found all of the ingredients and made a potion to cure the poison" and the game will end
- when the player's health reaches 0 there will be a message saying "You have died, you failed to gather all of the ingredients. GAME OVER" and the game will end
- when the player moves the players health will decrease by 1
- if the player does not move the health will not decrease
- if the players health is at least 1 the game will not end

---

## Example Test Name

Example test description.

### Test Data To Use

Details of test data and reasons for selection.

### Expected Test Result

Statement detailing what should happen.

---


- [*] Uses **iteration** (loops) to repeat things (for, while, etc.)

- [*] Explaining the **reason** (the '**why**') of key parts of the code
- [*] Defining function **parameters** and/or **return values**

- [ ] **Constants** being used to define key values, and used throughout the program
- [ ] Variable values being **derived at run-time** (from other variables / constants)
- [ ] Literal values only being used if they **make sense**, and they don't impact program flexibility




