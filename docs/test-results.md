# Results of Testing

The test results show the actual outcome of the testing, following the [Test Plan](test-plan.md)

---

## Game Setup and game close

testing what happens when the player runs the game and closes the game

### Test Data Used

The player will click the run button to play the game and then click on the X button on the top right corner to close the game

### Test Result

https://mywaimeaschool-my.sharepoint.com/:v:/g/personal/snoble_waimea_school_nz/Efpc5stUJuFHtMBjvpjyNhIBfCRlxYTK_Mk-5yg5C0rCnA?e=yZCDio

Comment on test result.

---

## Player movement

**VALID** tests whether the player can move around on the map and the current location name, description, and action type will change
**BOUNDARY** test to see if the player can explore the map boundaries to its full extent
**INVALID** test they player trying to move through a locked path

### Test Data Used

- I will be moving around the whole map which will show me moving through an open path e.g. the woods to teh abandoned house,
  as well as me trying to move through a blocked path e.g. moving through the closed attic, and also me testing all of the map boundaries.
  when i move between locations this will also change the current location name, description, and the action button name and type
- i have chosen to go to every location to show the valid and invalid moves and that the player can not leave the map

### Test Result

![player movement.gif](<screenshots/player movement.gif>)

This all works as i intended

---

## Action button and item being added to inventory

**VALID** tests what happens when a player clicks the action button when an action is available
**INVALID** tests what happens when a player clicks the action button when they are in a location with no action or they dont have the item needed to make the action

### Test Data Used
- the player will start at the bedroom and click the action button
- the player will start at the castle wall and click the action button,
  then move to the castle courtyard and click the action button,
  then move to the castle mine and click the action button,
  then move to the castle courtyard and click the action button,
- I have chosen these locations because these are locations with no action, open action, collect action, and use action

### Test Result

![Action button test.gif](<screenshots/Action button test.gif>)

Every button works as intended, but the item isnt removed from the inventory when i clicked on the use button
![Action button test fixed.gif](<screenshots/Action button test fixed.gif>)
---

## Help button

**VALID** testing what will happen when the player clicks on the help button

### Test Data Used

The player will click on the help button in multiple locations
I have chosen multiple locations to show that it doesn't matter where the player is, they can get help anywhere

### Test Result

![help button.gif](<screenshots/help button.gif>)

this works as i intended

---

## Player Win

**VALID** Testing what happens when the player has collected all of the required ingredients for the potion
**INVALID** testing what happens when the player has not collected all of the ingredients

### Test Data Used

- the player will move around the map and collect the water from the spring of life,
  the frostsnap berries, the flameheart essence, and the leviathan bone
- i have chosen this data because these are the key items the player needs in their inventory in order to win

### Test Result

https://mywaimeaschool-my.sharepoint.com/:v:/g/personal/snoble_waimea_school_nz/EWmzyhqHzldPpzd1xgzV0SgBl7A4aiSDjjTlFnKKzufCDg?e=oVBfXR

My test failed so i checked my code and I realised that the one of the items the player picks up is Frostsnap Berries (uppercase b)
but for the player to win they needed Frostsnap berries (lowercase b) i fixed this and did the test again

https://mywaimeaschool-my.sharepoint.com/:v:/g/personal/snoble_waimea_school_nz/Ec6fs4sKoERKuEfFEmAU51kB7huQ752_blSMddrAtZu6xg?e=8F6bpn
I fixed the issue and now it works

---

## Decreasing health and Player Lose

**VALID** testing what happens when the player dies
**VALID** testing what happens to the health when the player moves
**INVALID** testing what happens when the player as at least one health left
**INVALID** testing that happens when the player doesn't move

### Test Data Used

- The player will move between locations up, down, left and right until the players health reaches 0
- the player will not move
- I have chosen multple locations to show that it works for avery direction

### Test Result

![decrease health and death.gif](<screenshots/decrease health and death.gif>)

Comment on test result.

---

## clicking on the game buttons while a popup is visible

**INVALID** test clicking on the game buttons while a popup is visible

### Test Data Used

I am going to click on a button while the intro pop up, help pop up, action popup, lose pop up, and win popup are visible
i have chosen these to show that it works for every pop up

### Test Result

![popup button press.gif](<screenshots/popup button press.gif>)

this works as intended

---
