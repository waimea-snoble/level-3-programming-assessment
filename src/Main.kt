/**
 * =====================================================================
 * Programming Project for NCEA Level 3, Standard 91906
 * ---------------------------------------------------------------------
 * Project Name:   PROJECT NAME HERE
 * Project Author: Scott Noble
 * GitHub Repo:    https://github.com/waimea-snoble/level-3-programming-assessment
 * ---------------------------------------------------------------------
 * Notes:
 * This game involves navigating through various locations to collect items
 * needed to create an antidote for a poison, while the layers health slowly decreases.
 * =====================================================================
 */


import com.formdev.flatlaf.FlatDarkLaf
import java.awt.*
import java.awt.event.*
import javax.swing.*
import kotlin.system.exitProcess


/**
 * Launch the application
 */
fun main() {
    FlatDarkLaf.setup()     // Flat, dark look-and-feel
    val app = App()         // Create the app model
    MainWindow(app)         // Create and show the UI, using the app model
}


/**
 * The application class (model)
 * This is the place where any application data should be
 * stored, plus any application logic functions
 */

class App() {


    // the inventory is a mutable list because it means tht items can be added or removed
    val inventory = mutableListOf<String>() // players inventory to store collected items

    // data fields
    var currentLocation: Location  // Variable to track the player's current location

    // Player stats
    val maxHealth = 200 // maximum health value

    // Data fields
    var health = maxHealth // set player health to maximum

    // setup the app model
    init {
        // Initialize the list with some locations
        val woods = Location("The Woods", "You find yourself in a forest, still dazed from what had just happened.", Location.ACTION_OPEN, "Look around", "You dont recognise where you are")
        val house = Location("Abandoned House", "A decaying house, creaking with the wind. Its windows are shattered, and the door hangs open.", Location.ACTION_NONE)
        val attic = Location("Attic", "A dusty attic covered with cobwebs. A locked chest sits ominously in the corner, as if it’s been waiting for years.", Location.ACTION_COLLECT, "Open chest", "You found 5 gold bars inside the chest", "5 Gold Bars")
        val hallway = Location("Hallway", "A narrow hallway, its walls cracked and flaking. A small ladder leads upward, perhaps to an attic.", Location.ACTION_OPEN, "Open entrance to attic", "You opened the attic", "", "", "north", attic)
        val trail = Location("Trail", "A beaten path stretches into the distance. Birds chirping in the trees above, and distant mountains loom on the horizon.", Location.ACTION_NONE)
        val bedroom = Location("Bedroom", "An old, stale room with a broken bed. The only light seeps through a window that looks like it hasn’t been opened in years.", Location.ACTION_OPEN, "Open window","The window is now open", "", "", "north", trail)
        val cave = Location("Cave", "A cold, damp cave with echoes of dripping water. Light looming in the distance.", Location.ACTION_NONE)
        val cave2 = Location("Cave", "A cold, damp cave with echoes of dripping water. Light looming in the distance.", Location.ACTION_NONE)
        val village = Location("Village", "A quiet, almost lifeless village. Some doors hang ajar, and faint smoke curls from a chimney—but no one seems to be around.", Location.ACTION_NONE)
        val caveExit = Location("Cave", "You found a boulder blocking the exit.", Location.ACTION_OPEN, "Move Boulder", "You moved the boulder", "", "", "east", village)
        val caveEntrance = Location("Cave Entrance", "The cave is dark but you see a light in the distance. Be careful you might get lost.", Location.ACTION_NONE)
        val marketStall = Location("Market Stall", "A tattered stall stands in the village square. A mysterious merchant eyes you warily, pointing to a glowing vial said to be from the spring of life, costing 5 gold bars.", Location.ACTION_USE, "Buy vial", "You bought the water from the spring of life", "Water Vial", "5 Gold Bars")
        val forkInTheRoad = Location("Fork in The Road", "The path splits, one veering toward the mountains, the other disappearing towards a shadowy figure leaning against a wagon.", Location.ACTION_NONE)
        val pathToMountain = Location("Path to Frostfang Peak", "The temperature drops as you walk. The towering castle of Frostfang Peak stands like a frozen sentinel above.", Location.ACTION_NONE)
        val castleGate = Location("Castle Gate", "The massive gate is sealed shut, encrusted in layers of ice.", Location.ACTION_OPEN, "Shout", "There is no response")
        val castleWall = Location("Castle Wall", "Part of the wall has collapsed, leaving a gap just wide enough to squeeze through. You may have found another way in.", Location.ACTION_NONE)
        val throneRoom = Location("Throne Room", "A grand hall, icy and regal. A broken window lets in cold air, and hanging just within reach, Frostsnap Berries.", Location.ACTION_COLLECT, "Collect berries", "You picked up the Frostsnap berries", "Frostsnap Berries", "Pickaxe")
        val castleCourtyard = Location("Castle Courtyard", "A courtyard frozen in time. At its far end, a wall of solid ice hides something...important.", Location.ACTION_USE, "Break through ice", "You used the pickaxe to break through", "", "Pickaxe", "north", throneRoom)
        val mine = Location("Mine", "The mine shaft is quiet except for the occasional creak of timber. A lifeless body lies crumpled near the wall, with a pickaxe still in his hand.", Location.ACTION_COLLECT, "Pick Up", "You found a pickaxe", "Pickaxe")
        val topOfDarkfireDepths = Location("Top of Darkfire Depths", "Looking down from the top, a vast abyss stretches below. The heat rising is oppressive, like something ancient stirs beneath.", Location.ACTION_NONE)
        val wagon = Location("Man on a wagon", "A man leaning against a wagon, saying he can give you a ride to Darkfire Depths, but only if you give him a rare diamond only found in Frostfang Peak", Location.ACTION_USE, "Give diamond", "Thank you, you can now go to Darkfire Depths", "", "Diamond", "east", topOfDarkfireDepths)
        val deepInTheMine = Location("Deep in the Mine", "", Location.ACTION_NONE)
        val deadEnd = Location("Dead End", "Rocks block the path ahead, no sign of anything useful.", Location.ACTION_NONE)
        val minecart = Location("Minecart", "A rusted minecart rests on broken tracks. Inside, a bag, maybe a forgotten treasure?", Location.ACTION_COLLECT, "Search the bag", "You found a diamond in the bag", "Diamond")
        val coreOfEternalFlame = Location("Core of Eternal Flame", "At the heart of the abyss, molten light bathes the room. A dragon bone flask pulses with the flameheart essence.", Location.ACTION_COLLECT, "Pick up flask", "You picked up the flask", "Flameheart Essence")
        val door = Location("Door", "You climbed down into dark fire depths where a large glowing etched ith glowing runes blocks your path", Location.ACTION_USE, "Use Key", "You opened the door", "", "Ashen Key", "east", coreOfEternalFlame)
        val beachCave = Location("Beach Cave", "A steep climb leads up through the cave to a ledge. You could jump to the beach below, but there will be no way back", Location.ACTION_NONE)
        val starlitCavern = Location("Starlit cavern", "Crystals shimmer with light. A peculiar pattern on the wall suggests something can be revealed.", Location.ACTION_OPEN, "Align crystals", "You opened a secret path", "", "", "south", beachCave)
        val obsidianSpire = Location("Obsidian Spire", "A spiraling descent into darkness carved from black volcanic glass. The deeper you go, the hotter it becomes, each step echoing like a warning through the hollow, cursed stone.", Location.ACTION_NONE)
        val ashenForge = Location("Ashen Forge", "You find an ancient forge, flames roar beneath a stone anvil.", Location.ACTION_COLLECT, "Pull Lever", "Lava comes pouring down into a mold where the ashen key is made", "Ashen Key")
        val field = Location("Field", "A wide-open field, peaceful and calm.", Location.ACTION_NONE)
        val glowstoneChasm = Location("Glowstone Chasm", "Deep beneath the surface, glowing stones line the walls of a vast underwater chasm. Their light cuts through the darkness, revealing a seemingly endless drop into the unknown.", Location.ACTION_NONE)
        val beach = Location("Beach", "You arrive at a quiet beach where the remains of a shipwreck loom in the distance. Beneath the gentle waves, something glimmers under the water, hinting at secrets yet to be discovered.", Location.ACTION_USE, "Put on ring", "The sunken king has now granted you the ability to breath underwater", "", "King's Ring", "east", glowstoneChasm)
        val shipwreck = Location("Shipwreck", "Broken masts and torn sails lie scattered. Something valuable must still remain within the ruins.", Location.ACTION_NONE)
        val captainsQuarters = Location("Captains Quarters", "The room smells of salt and decay. A locked chest sits beneath the captain's bed", Location.ACTION_USE, "Use Key", "You opened the chest and found a mysterious ring", "King's Ring", "Captain's Key")
        val shipDeck = Location("Ship Deck", "Skeletons litter the deck, frozen in their last stand. The captain lies impaled, still gripping something in his cold hand.", Location.ACTION_COLLECT, "Pick Up Key", "You found the captain's key", "Captain's Key")
        val leviathanGraveyard = Location("Leviathan Graveyard", "Monstrous bones spike out of the ground like jagged cliffs. A single bone fragment glows faintly with power.", Location.ACTION_COLLECT, "Pick up bone fragment", "You picked up the bone of a leviathan", "Leviathan Bone")

        // Connect locations to create map
        woods.left = house
        house.up = bedroom
        bedroom.down = house
        house.left = hallway
        hallway.right = house
        attic.down = hallway
        trail.up = caveEntrance
        trail.down = bedroom
        house.right = woods
        caveEntrance.down =trail
        caveEntrance.up = cave
        cave.up = cave
        cave.left = cave
        cave.down = caveEntrance
        cave.right = cave2
        cave2.right = caveExit
        cave2.left = cave
        village.up = marketStall
        marketStall.down = village
        village.left = caveExit
        caveExit.left = cave2
        village.right = forkInTheRoad
        forkInTheRoad.up = pathToMountain
        forkInTheRoad.left = village
        pathToMountain.down = forkInTheRoad
        pathToMountain.up = castleGate
        castleGate.down = pathToMountain
        castleGate.left = castleWall
        castleWall.right = castleGate
        castleWall.up = castleCourtyard
        castleCourtyard.down = castleWall
        castleWall.left = mine
        mine.right = castleWall
        throneRoom.down = castleCourtyard
        forkInTheRoad.right = wagon
        wagon.left = forkInTheRoad
        mine.left = deepInTheMine
        deepInTheMine.down = minecart
        minecart.up = deepInTheMine
        deepInTheMine.up = deadEnd
        deadEnd.down = deepInTheMine
        deepInTheMine.right = mine
        topOfDarkfireDepths.left = wagon
        topOfDarkfireDepths.down = door
        door.up = topOfDarkfireDepths
        door.down = starlitCavern
        starlitCavern.up = door
        beachCave.up = starlitCavern
        beachCave.down = beach
        door.left = obsidianSpire
        obsidianSpire.right = door
        obsidianSpire.down = ashenForge
        ashenForge.up = obsidianSpire
        coreOfEternalFlame.left = door
        woods.up = field
        field.down = woods
        field.right = beach
        beach.left = field
        beach.down = shipwreck
        shipwreck.up = beach
        shipwreck.right = captainsQuarters
        captainsQuarters.left = shipwreck
        captainsQuarters.right = shipDeck
        shipDeck.left = captainsQuarters
        glowstoneChasm.left = beach
        glowstoneChasm.down = leviathanGraveyard
        leviathanGraveyard.up = glowstoneChasm

        currentLocation = woods // starting location
    }

    /**
     * Moves player to a new location in the specified direction
     */
    fun move(direction: String) {

        // update the current location based on the direction input
        // I used when() because it easier to read than if else chains
        val newLocation = when (direction) {
            "north" -> currentLocation.up
            "south" -> currentLocation.down
            "east" -> currentLocation.right
            "west" -> currentLocation.left
            else -> null

        }

        // update the current location if move is valid
        if (newLocation != null) {
            currentLocation = newLocation
        }


    }

    /**
     * function to decrease the health
     */
    fun decreaseHealth() {
        health-- // this decreases the health by one every move due to the posion
    }






}


/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */
class MainWindow(val app: App) : JFrame(), ActionListener {

    // Fields to hold the UI elements
    private lateinit var helpButton: JButton
    private lateinit var helpPopUp: HelpPopUpDialog
    private lateinit var introPopUp: IntroPopUpDialog
    private lateinit var winPopUp: WinPopUpDialog
    private lateinit var healthBackPanel: JPanel
    private lateinit var healthLevelPanel: JPanel
    private lateinit var locationLabel: JLabel
    private lateinit var descriptionLabel: JLabel
    private lateinit var actionPopUp: ActionPopUpDialog
    private lateinit var deathPopUp: ActionPopUpDialog
    private lateinit var inventoryLabel: JLabel
    private lateinit var inventoryBox: JLabel
    private lateinit var actionButton: JButton
    private lateinit var upButton: JButton
    private lateinit var downButton: JButton
    private lateinit var leftButton: JButton
    private lateinit var rightButton: JButton


    /**
     * Configure the UI and display it
     */
    init {


        configureWindow() // Configure the window
        addControls() // Build the UI
        setLocationRelativeTo(null) // Centre the window
        isVisible = true // Make it visible


        introPopUp.isVisible = true // show the popup
        updateView() // Initialise the UI

    }

    /**
     * Configure the main window
     */
    private fun configureWindow() {
        title = "Kotlin Swing GUI Demo"
        contentPane.preferredSize = Dimension(550, 600)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI with UI controls
     */
    private fun addControls() {

        // initialise popup windows
        helpPopUp = HelpPopUpDialog()
        introPopUp = IntroPopUpDialog()


        // set fonts
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)
        val descriptionFont = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        val inventoryFont = Font(Font.SANS_SERIF, Font.PLAIN, 25)
        val interactFont = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        val inventoryListFont = Font(Font.SANS_SERIF, Font.PLAIN, 18)

        // create help button
        helpButton = JButton("Help")
        helpButton.bounds = Rectangle(40,487, 150, 50)
        helpButton.font = baseFont
        helpButton.addActionListener(this)     // Handle any clicks
        add(helpButton)

        // health bar components
        healthBackPanel = JPanel()
        healthBackPanel.bounds = Rectangle(50, 25, 450, 10)
        healthBackPanel.background = Color.BLACK
        healthBackPanel.layout = null                // Want layout to be manual
        add(healthBackPanel)


        healthLevelPanel = JPanel()
        healthLevelPanel.bounds = Rectangle(0, 0, 450, 10)
        healthLevelPanel.background = Color.RED
        healthBackPanel.add(healthLevelPanel)

        // displays location name
        locationLabel = JLabel("<html>" + app.currentLocation.name)
        locationLabel.horizontalAlignment = SwingConstants.CENTER
        locationLabel.bounds = Rectangle(26, 34, 499, 51)
        locationLabel.font = baseFont
        add(locationLabel)

        // description for the current location
        descriptionLabel = JLabel("<html>" + app.currentLocation.description)
        descriptionLabel.horizontalAlignment = SwingConstants.CENTER
        descriptionLabel.bounds = Rectangle(50, 106, 450, 110)
        descriptionLabel.font = descriptionFont
        add(descriptionLabel)

        // shows the inventory text
        inventoryLabel = JLabel("Inventory")
        inventoryLabel.horizontalAlignment = SwingConstants.CENTER
        inventoryLabel.bounds = Rectangle(33, 225, 100, 100)
        inventoryLabel.font = inventoryFont
        add(inventoryLabel)

        // the box for the inventory items to go in
        inventoryBox = JLabel()
        inventoryBox.verticalAlignment = SwingConstants.TOP
        inventoryBox.bounds = Rectangle(30, 300, 200, 288)
        inventoryBox.font = inventoryListFont
        add(inventoryBox)

        // buttons
        actionButton = JButton(app.currentLocation.action)
        actionButton.bounds = Rectangle(256,282,188,55)
        actionButton.font = interactFont
        actionButton.addActionListener(this)     // Handle any clicks
        add(actionButton)

        upButton = JButton("\uD83E\uDC71")
        upButton.bounds = Rectangle(320,363,60,80)
        upButton.font = baseFont
        upButton.addActionListener(this)     // Handle any clicks
        add(upButton)

        downButton = JButton("\uD83E\uDC73")
        downButton.bounds = Rectangle(320,459,60,80)
        downButton.font = baseFont
        downButton.addActionListener(this)     // Handle any clicks
        add(downButton)

        leftButton = JButton("\uD83E\uDC70")
        leftButton.bounds = Rectangle(226,479,80,60)
        leftButton.font = baseFont
        leftButton.addActionListener(this)     // Handle any clicks
        add(leftButton)

        rightButton = JButton("\uD83E\uDC72")
        rightButton.bounds = Rectangle(394,479,80,60)
        rightButton.font = baseFont
        rightButton.addActionListener(this)     // Handle any clicks
        add(rightButton)
    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {
        //update location info
        locationLabel.text = "<html>" + app.currentLocation.name // new name
        descriptionLabel.text = "<html>" + app.currentLocation.description //  new description
        actionButton.text = "<html>" + app.currentLocation.action //  new action


        // enable action button based on if an action has been taken or not
        actionButton.isEnabled = !app.currentLocation.actionTaken


        // disable teh action button if there is no action in the location
        if (app.currentLocation.actionType == Location.ACTION_NONE) {
            actionButton.isEnabled = false
        }
        // Disable actionButton if the actionType is ACTION_USE and the itemNeeded is not in the inventory
        if (app.currentLocation.actionType == Location.ACTION_USE && !app.inventory.contains(app.currentLocation.itemNeeded)) {
            actionButton.isEnabled = false
        }

        // player death
        if (app.health == 0) {
            this.isVisible = false
            deathPopUp = ActionPopUpDialog(app) // Show message
            deathPopUp.isVisible = true
            exitProcess(0)
        }



        // Enable or disable buttons based on available paths
        upButton.isEnabled = app.currentLocation.up != null
        downButton.isEnabled = app.currentLocation.down != null
        leftButton.isEnabled = app.currentLocation.left != null
        rightButton.isEnabled = app.currentLocation.right != null








        // Sizes of the health bar
        val healthWidth = calcHealthPanelWidth()
        val healthHeight = healthBackPanel.size.height

        // Update the bar's size
        healthLevelPanel.bounds = Rectangle(0, 0, healthWidth, healthHeight)
    }

    /**
     * Work out the volume bar width based on the parent back panel's width
     */
    fun calcHealthPanelWidth(): Int {
        val healthFraction = app.health.toDouble() / app.maxHealth   // Volume from 0.0 to 1.0
        val maxWidth = healthBackPanel.bounds.width                // Size of background panel
        val volWidth = (maxWidth * healthFraction).toInt()         // Size in px
        return volWidth
    }

    /**
     * Check if the player has the ingredients in order to win
     */
    fun checkForWin() {
        if (app.inventory.contains("Water Vial") &&
            app.inventory.contains("Frostsnap Berries") &&
            app.inventory.contains("Flameheart Essence") &&
            app.inventory.contains("Leviathan Bone")) {

            winPopUp = WinPopUpDialog()
            winPopUp.isVisible = true
            exitProcess(0)
        }
    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {

            // button to show the help dialog
            helpButton -> {
                helpPopUp.isVisible = true
            }



            actionButton -> {

                when (app.currentLocation.actionType) {
                    Location.ACTION_COLLECT -> {
                        if (!app.currentLocation.actionTaken) {
                            app.inventory.add(app.currentLocation.item) // Add item to inventory
                            inventoryBox.text = "<html>" + app.inventory.joinToString("<br>") { "• $it" } // this puts a bullet point before each item in the inventory, this is because it will make it easier for the player to read each item
                            app.currentLocation.actionTaken = true
                            actionPopUp = ActionPopUpDialog(app) // Show pop-up with message
                            actionPopUp.isVisible = true
                            helpPopUp.isVisible = false
                            checkForWin()
                        }

                    }

                    Location.ACTION_OPEN -> {
                        if (!app.currentLocation.actionTaken) {
                            app.currentLocation.actionTaken = true
                            actionPopUp = ActionPopUpDialog(app) // Show message "The door is now open"
                            actionPopUp.isVisible = true
                            helpPopUp.isVisible = false

                            // Unlock the path based on the stored direction

                            when (app.currentLocation.direction) { // when the direction is N/S/E/W it opens the link to the new location
                                "north" -> app.currentLocation.up = app.currentLocation.link
                                "south" -> app.currentLocation.down = app.currentLocation.link
                                "east" -> app.currentLocation.right = app.currentLocation.link
                                "west" -> app.currentLocation.left = app.currentLocation.link
                            }
                        }
                    }

                    Location.ACTION_USE -> {
                        // Example: If using a key, check if it's in inventory
                        if (app.inventory.contains(app.currentLocation.itemNeeded)) {
                            // Remove the item from the inventory after use
                            app.inventory.remove(app.currentLocation.itemNeeded)
                            inventoryBox.text = "<html>" + app.inventory.joinToString("<br>") { "• $it" }
                            if (app.currentLocation.item == "") {

                            }
                            else {
                                app.inventory.add(app.currentLocation.item) // Add item to inventory
                                inventoryBox.text = "<html>" + app.inventory.joinToString("<br>") { "• $it" }
                            }
                            app.currentLocation.actionTaken = true
                            actionPopUp = ActionPopUpDialog(app) // Show message like "You used the key"
                            actionPopUp.isVisible = true
                            helpPopUp.isVisible = false
                            checkForWin()
                            // unlock path if direction is specified
                            when (app.currentLocation.direction) {
                                "north" -> app.currentLocation.up = app.currentLocation.link
                                "south" -> app.currentLocation.down = app.currentLocation.link
                                "east" -> app.currentLocation.right = app.currentLocation.link
                                "west" -> app.currentLocation.left = app.currentLocation.link
                            }
                        }

                    }
                }
            }


            // movement buttons
            upButton -> {
                app.move("north")
                app.decreaseHealth()
            }
            downButton -> {
                app.move("south")
                app.decreaseHealth()
            }
            leftButton -> {
                app.move("west")
                app.decreaseHealth()
            }
            rightButton -> {
                app.move("east")
                app.decreaseHealth()
            }


        }

        updateView()  // Refresh UI after moving
    }



}

/**
 * Displays the action dialog
 */
class ActionPopUpDialog(val app: App): JDialog() {

    /**
     * Configure the UI
     */
    init {
        configureWindow()
        addControls()
        setLocationRelativeTo(null)     // Centre the window
    }

    /**
     * Setup the dialog window
     */
    private fun configureWindow() {
        title = "Pop-Up"
        contentPane.preferredSize = Dimension(400, 200)
        isResizable = false
        isModal = true
        layout = null
        pack()
    }

    /**
     * Add content to the dialog
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 16)

        // Adding <html> to the label text allows it to wrap
        // action result message
        val message = JLabel("<html> ${app.currentLocation.actionDialog}")
        message.bounds = Rectangle(25, 25, 350, 150)
        message.verticalAlignment = SwingConstants.TOP
        message.font = baseFont
        add(message)
        if (app.health == 0) {
            message.isVisible = false
        }

        // death message shown if health is 0
        val deathMessage = JLabel("<html> You have died, you failed to gather all of the ingredients. GAME OVER")
        deathMessage.bounds = Rectangle(25, 25, 350, 150)
        deathMessage.verticalAlignment = SwingConstants.TOP
        deathMessage.font = baseFont
        add(deathMessage)
        deathMessage.isVisible = false
        if (app.health == 0) {
            deathMessage.isVisible = true
        }
    }
}

/**
 * Displays the win dialog
 */
class WinPopUpDialog(): JDialog() {

    /**
     * Configure the UI
     */
    init {
        configureWindow()
        addControls()
        setLocationRelativeTo(null)     // Centre the window
    }

    /**
     * adds win message to dialog
     */
    private fun configureWindow() {
        title = "Pop-Up"
        contentPane.preferredSize = Dimension(400, 200)
        isResizable = false
        isModal = true
        layout = null
        pack()
    }

    /**
     * Populate the window with controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 16)


        // Adding <html> to the label text allows it to wrap
        val winMessage = JLabel("<html> You Win! You found all of the ingredients and made a potion to cure the poison")
        winMessage.bounds = Rectangle(25, 25, 350, 150)
        winMessage.verticalAlignment = SwingConstants.TOP
        winMessage.font = baseFont
        add(winMessage)

    }


}

/**
 * Displays the help dialog
 */
class HelpPopUpDialog(): JDialog() {

    /**
     * Configure the UI
     */
    init {
        configureWindow()
        addControls()
        setLocationRelativeTo(null)     // Centre the window
    }

    /**
     * Setup the dialog window
     */
    private fun configureWindow() {
        title = "Pop-Up"
        contentPane.preferredSize = Dimension(400, 200)
        isResizable = false
        isModal = true
        layout = null
        pack()
    }

    /**
     * adds help text to the dialog
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 16)


        // Adding <html> to the label text allows it to wrap
        val helpMessage = JLabel("<html> The aim of the game is to find the four ingredients Water from the spring of life, Frostsnap Berries, Flameheart Essence, and a bone of a leviathan. You can move around the map by using the arrow buttons on the screen and there is an action button above the arrows to interact with the location you are currently at.")
        helpMessage.bounds = Rectangle(25, 25, 350, 150)
        helpMessage.verticalAlignment = SwingConstants.TOP
        helpMessage.font = baseFont
        add(helpMessage)
        helpMessage.isVisible = true

    }


}

/**
 * Displays the intro dialog
 */
class IntroPopUpDialog(): JDialog() {

    /**
     * Configure the UI
     */
    init {
        configureWindow()
        addControls()
        setLocationRelativeTo(null)     // Centre the window
    }

    /**
     * Setup the dialog window
     */
    private fun configureWindow() {
        title = "Pop-Up"
        contentPane.preferredSize = Dimension(550, 600)
        isResizable = false
        isModal = true
        layout = null
        pack()
    }

    /**
     * adds intro story text to dialog
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 16)


        // Adding <html> to the label text allows it to wrap
        val introMessage = JLabel("<html> You awaken in a cold sweat, heart pounding, a bitter taste clinging to your tongue. A shadowy figure’s last words echo in your mind. The poison is already inside you. If you want to live you’ll have to find the cure yourself. Your body weakens with every passing moment. Somewhere out there, hidden in dark forests, crumbling ruins, and forgotten places, are the rare ingredients you need to create the antidote. Time is against you. Trust no one. Every decision matters. Find the ingredients, brew the cure, or succumb to the poison.")
        introMessage.bounds = Rectangle(50, 50, 450, 600)
        introMessage.verticalAlignment = SwingConstants.TOP
        introMessage.font = baseFont
        add(introMessage)
        introMessage.isVisible = true

    }


}

/**
 * represents a location in the world
 */
class Location(
    val name: String,
    val description: String,
    val actionType: Int,
    val action: String = "",
    val actionDialog: String = "",
    val item: String = "",
    val itemNeeded: String = "",
    val direction: String = "",
    val link: Location? = null,
    var actionTaken: Boolean = false
) {

    companion object {
        const val ACTION_NONE = 0 // No action at location
        const val ACTION_COLLECT = 1 // Action to collect an item
        const val ACTION_USE = 2 // Action to use an item
        const val ACTION_OPEN = 3 // action to open a path
    }


    // using vars instead of vals means that i can change the world such as openign doors
    var up: Location? = null // north connection
    var down: Location? = null // south connection
    var right: Location? = null // east connection
    var left: Location? = null // west connection


}

