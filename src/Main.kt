/**
 * =====================================================================
 * Programming Project for NCEA Level 3, Standard 91906
 * ---------------------------------------------------------------------
 * Project Name:   PROJECT NAME HERE
 * Project Author: Scott Noble
 * GitHub Repo:    https://github.com/waimea-snoble/level-3-programming-assessment
 * ---------------------------------------------------------------------
 * Notes:
 * This game involves...
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


    val inventory = mutableListOf<String>()

    // data fields
    val locations = mutableListOf<Location>() // List of locations
    var currentLocation: Location  // Variable to track the player's current location

    // setup the app model
    init {
        // Initialize the list with some locations
        val woods = Location("The Woods", "You find yourself in a forest", Location.ACTION_OPEN, "Look around", "You dont recognise where you are")
        val house = Location("Abandoned House", "", Location.ACTION_NONE)
        val attic = Location("Attic", "", Location.ACTION_COLLECT, "Open chest", "You found 5 gold bars inside the chest", "5 Gold Bars")
        val hallway = Location("Hallway", "", Location.ACTION_OPEN, "Open entrance to attic", "You opened the attic", "", "", "north", attic)
        val trail = Location("Trail", "", Location.ACTION_NONE)
        val bedroom = Location("Bedroom", "", Location.ACTION_OPEN, "Open window","The window is now open", "", "", "north", trail)
        val cave = Location("Cave", "", Location.ACTION_NONE)
        val cave2 = Location("Cave", "", Location.ACTION_NONE)
        val village = Location("Village", "", Location.ACTION_NONE)
        val caveExit = Location("Cave", "You found a boulder blocking the exit", Location.ACTION_OPEN, "Move Boulder", "You moved the boulder", "", "", "east", village)
        val caveEntrance = Location("Cave Entrance", "The cave is dark but you see a light in the distance. Be careful you might get lost", Location.ACTION_NONE)
        val marketStall = Location("Market Stall", "A vendor selling various items, including a vial from the spring of life costing 5 gold bars", Location.ACTION_USE, "Buy vial", "You bought the water from the spring of life", "Water Vial", "5 Gold Bars")
        val forkInTheRoad = Location("Fork in The Road", "", Location.ACTION_NONE)
        val pathToMountain = Location("Path to Frostfang Peak", "A path leading to a castle in Frostfang Peak", Location.ACTION_NONE)
        val castleGate = Location("Castle Gate", "A gate to the castle, frozen shut with icicles dripping from atop the walls", Location.ACTION_OPEN, "Shout", "There is no response")
        val castleWall = Location("Castle Wall", "There is a hole in the castle wall", Location.ACTION_NONE)
        val throneRoom = Location("Throne Room", "In the throne room you see some rare frostsnap berries dangling through a window", Location.ACTION_COLLECT, "Collect berries", "You picked up the Frostsnap berries", "Frostsnap Berries", "Pickaxe")
        val castleCourtyard = Location("Castle Courtyard", "The courtyard is empty, a wall of ice is blocking a room", Location.ACTION_USE, "Break through ice", "You used the pickaxe to break through", "", "Pickaxe", "north", throneRoom)
        val mine = Location("Mine", "You go inside a mine and see a dead miner", Location.ACTION_COLLECT, "Search Body", "You found a pickaxe", "Pickaxe")
        val topOfDarkfireDepths = Location("Top of Darkfire Depths", "", Location.ACTION_NONE)
        val wagon = Location("Man on a wagon", "A man saying he can give you a ride to Darkfire Depths, but only if you give him a rare diamond only found in Frostfang Peak", Location.ACTION_USE, "Give diamond", "Thank you, you can now go to Darkfire Depths", "", "Diamond", "east", topOfDarkfireDepths)
        val deepInTheMine = Location("Deep in the Mine", "", Location.ACTION_NONE)
        val deadEnd = Location("Dead End", "", Location.ACTION_NONE)
        val minecart = Location("Minecart", "You see a bag inside a minecart", Location.ACTION_COLLECT, "Search the bag", "You found a diamond in the bag", "Diamond")
        val coreOfEternalFlame = Location("Core of Eternal Flame", "In the core you see the flameheart essence being held in a dragon bone flask", Location.ACTION_COLLECT, "Pick up flask", "You picked up the flask", "Flameheart Essence")
        val door = Location("Door", "You climbed down into dark fire depths where a large glowing door blocks your path", Location.ACTION_USE, "Use Key", "You opened the door", "", "Ashen Key", "east", coreOfEternalFlame)
        val beachCave = Location("Beach Cave", "You make your way up through the cave and can jump down to a beach but you wont be able to make it back up", Location.ACTION_NONE)
        val starlitCavern = Location("Starlit cavern", "A cavern with glowing crystals that illuminate the dark, however you notice a pettern on the wall", Location.ACTION_OPEN, "Align crystals", "You opened a secret path", "", "", "south", beachCave)
        val obsidianSpire = Location("Obsidian Spire", "", Location.ACTION_NONE)
        val ashenForge = Location("Ashen Forge", "You find a forge deep in the Darkfire Depths", Location.ACTION_COLLECT, "Pull Lever", "Lava comes pouring down into a mold where the ashen key is made", "Ashen Key")
        val field = Location("Field", "", Location.ACTION_NONE)
        val glowstoneChasm = Location("Glowstone Chasm", "", Location.ACTION_NONE)
        val beach = Location("Beach", "You arrive at a beach and notice a ship wreck in the distance, as well as something under the water", Location.ACTION_USE, "Put on ring", "The sunken king has now granted you the ability to breath underwater", "", "King's Ring", "east", glowstoneChasm)
        val shipwreck = Location("Shipwreck", "", Location.ACTION_NONE)
        val captainsQuarters = Location("Captains Quarters", "You make your way through the hole into the captains quarters and see a chest with a lock", Location.ACTION_USE, "Use Key", "You opened the chest and found a mysterious ring", "King's Ring", "Captain's Key")
        val shipDeck = Location("Ship Deck", "You see the crew's skeletons on the ground, including the captains, with a sword through his chest", Location.ACTION_COLLECT, "Search Captain", "You found the captain's key", "Captain's Key")
        val leviathanGraveyard = Location("Leviathan Graveyard", "You see hundreds of bones on the ground of this battlefield, however there is fragment which is the perfect size", Location.ACTION_COLLECT, "Pick up bone fragment", "You picked up the bone of a leviathan", "Leviathan Bone")

        // Connect locations
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








            currentLocation = woods
    }

    fun move(direction: String) {

        // update the current location based on the direction input
        val newLocation = when (direction) {
            "north" -> currentLocation.up
            "south" -> currentLocation.down
            "east" -> currentLocation.right
            "west" -> currentLocation.left
            else -> null

        }
        if (newLocation != null) {
            currentLocation = newLocation
        }


    }

    // Constants
    val maxHealth = 100000

    // Data fields
    var health = maxHealth

    // Application logic functions

    fun decreaseHealth() {
        health--
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


        configureWindow()               // Configure the window
        addControls()                   // Build the UI


        setLocationRelativeTo(null)     // Centre the window
        isVisible = true                // Make it visible



        introPopUp.isVisible = true
        updateView()                    // Initialise the UI

    }

    /**
     * Configure the main window
     */
    private fun configureWindow() {
        title = "Kotlin Swing GUI Demo"
        contentPane.preferredSize = Dimension(500, 600)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        isResizable = false
        layout = null

        pack()
    }

    /**
     * Populate the UI with UI controls
     */
    private fun addControls() {

        helpPopUp = HelpPopUpDialog()
        introPopUp = IntroPopUpDialog()


        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)
        val descriptionFont = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        val inventoryFont = Font(Font.SANS_SERIF, Font.PLAIN, 25)
        val interactFont = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        val inventoryListFont = Font(Font.SANS_SERIF, Font.PLAIN, 15)

        helpButton = JButton("Open The Pop-Up")
        helpButton.bounds = Rectangle(25, 100, 350, 50)
        helpButton.font = baseFont
        helpButton.addActionListener(this)     // Handle any clicks
        add(helpButton)

        // This panel acts as the 'back' of the level meter
        healthBackPanel = JPanel()
        healthBackPanel.bounds = Rectangle(25, 25, 450, 10)
        healthBackPanel.background = Color.BLACK
        healthBackPanel.layout = null                // Want layout to be manual
        add(healthBackPanel)

        // And this one sits inside the one above to make resizing it easier
        healthLevelPanel = JPanel()
        healthLevelPanel.bounds = Rectangle(0, 0, 450, 10)
        healthLevelPanel.background = Color.RED
        healthBackPanel.add(healthLevelPanel)

        locationLabel = JLabel("<html>" + app.currentLocation.name)
        locationLabel.horizontalAlignment = SwingConstants.CENTER
        locationLabel.bounds = Rectangle(1, 34, 499, 51)
        locationLabel.font = baseFont
        add(locationLabel)

        descriptionLabel = JLabel("<html>" + app.currentLocation.description)
        descriptionLabel.horizontalAlignment = SwingConstants.CENTER
        descriptionLabel.bounds = Rectangle(50, 106, 450, 139)
        descriptionLabel.font = descriptionFont
        add(descriptionLabel)

        inventoryLabel = JLabel("Inventory")
        inventoryLabel.horizontalAlignment = SwingConstants.CENTER
        inventoryLabel.bounds = Rectangle(33, 225, 100, 100)
        inventoryLabel.font = inventoryFont
        add(inventoryLabel)

        inventoryBox = JLabel()
        inventoryBox.horizontalAlignment = SwingConstants.CENTER
        inventoryBox.bounds = Rectangle(33, 282, 100, 288)
        inventoryBox.font = inventoryListFont
        add(inventoryBox)

        actionButton = JButton(app.currentLocation.action)
        actionButton.bounds = Rectangle(231,282,188,44)
        actionButton.font = interactFont
        actionButton.addActionListener(this)     // Handle any clicks
        add(actionButton)

        upButton = JButton("\uD83E\uDC71")
        upButton.bounds = Rectangle(295,363,60,80)
        upButton.font = baseFont
        upButton.addActionListener(this)     // Handle any clicks
        add(upButton)

        downButton = JButton("\uD83E\uDC73")
        downButton.bounds = Rectangle(295,459,60,80)
        downButton.font = baseFont
        downButton.addActionListener(this)     // Handle any clicks
        add(downButton)

        leftButton = JButton("\uD83E\uDC70")
        leftButton.bounds = Rectangle(201,479,80,60)
        leftButton.font = baseFont
        leftButton.addActionListener(this)     // Handle any clicks
        add(leftButton)

        rightButton = JButton("\uD83E\uDC72")
        rightButton.bounds = Rectangle(369,479,80,60)
        rightButton.font = baseFont
        rightButton.addActionListener(this)     // Handle any clicks
        add(rightButton)
    }


    /**
     * Update the UI controls based on the current state
     * of the application model
     */
    fun updateView() {
        locationLabel.text = "<html>" + app.currentLocation.name // new name
        descriptionLabel.text = "<html>" + app.currentLocation.description //  new description
        actionButton.text = "<html>" + app.currentLocation.action //  new action


        actionButton.isEnabled = !app.currentLocation.actionTaken


        if (app.currentLocation.actionType == Location.ACTION_NONE) {
            actionButton.isEnabled = false
        }
        // Disable actionButton if the actionType is ACTION_USE and the itemNeeded is not in the inventory
        if (app.currentLocation.actionType == Location.ACTION_USE && !app.inventory.contains(app.currentLocation.itemNeeded)) {
            actionButton.isEnabled = false
        }

        if (app.health == 0) {
            this.isVisible = false
            deathPopUp = ActionPopUpDialog(app) // Show message
            deathPopUp.isVisible = true
            exitProcess(0)
        }

        if (app.inventory.contains("Water Vial") && app.inventory.contains("Frostsnap Berries") && app.inventory.contains("Flameheart Essence") && app.inventory.contains("Leviathan Bone")) {
            this.isVisible = false
            winPopUp = WinPopUpDialog() // Show message
            winPopUp.isVisible = true
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
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {

            helpButton -> {
                helpPopUp.isVisible = true   // And show it
            }


            actionButton -> {

                when (app.currentLocation.actionType) {
                    Location.ACTION_COLLECT -> {
                        if (!app.currentLocation.actionTaken) {
                            app.inventory.add(app.currentLocation.item) // Add item to inventory
                            inventoryBox.text = "<html>" + app.inventory.joinToString("<br>") { "• $it" }
                            app.currentLocation.actionTaken = true
                            actionPopUp = ActionPopUpDialog(app) // Show pop-up with message
                            actionPopUp.isVisible = true
                            helpPopUp.isVisible = false
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
                            when (app.currentLocation.direction) { // when the direction is N/S/E/W it opens the link to the new location if the key is in the inventory
                                "north" -> app.currentLocation.up = app.currentLocation.link
                                "south" -> app.currentLocation.down = app.currentLocation.link
                                "east" -> app.currentLocation.right = app.currentLocation.link
                                "west" -> app.currentLocation.left = app.currentLocation.link
                            }
                        }

                    }
                }
            }


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
 * Displays a modal dialog
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
     * Populate the window with controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 16)

        // Adding <html> to the label text allows it to wrap
        val message = JLabel("<html> ${app.currentLocation.actionDialog}")
        message.bounds = Rectangle(25, 25, 350, 150)
        message.verticalAlignment = SwingConstants.TOP
        message.font = baseFont
        add(message)
        if (app.health == 0) {
            message.isVisible = false
        }

        val deathMessage = JLabel("<html> You have died. GAME OVER")
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
 * Displays a modal dialog
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
     * Populate the window with controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 16)


        // Adding <html> to the label text allows it to wrap
        val winMessage = JLabel("<html> You Win!")
        winMessage.bounds = Rectangle(25, 25, 350, 150)
        winMessage.verticalAlignment = SwingConstants.TOP
        winMessage.font = baseFont
        add(winMessage)

    }


}

/**
 * Displays a modal dialog
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
     * Populate the window with controls
     */
    private fun addControls() {
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 16)


        // Adding <html> to the label text allows it to wrap
        val helpMessage = JLabel("<html>This is an example pop-up dialog window. Like any window it can have controls, respond to events, etc. <br><br>It is a <em>modal</em> window, so it grabs the focus, and the main window can't be interacted with until this pop-up is closed.")
        helpMessage.bounds = Rectangle(25, 25, 350, 150)
        helpMessage.verticalAlignment = SwingConstants.TOP
        helpMessage.font = baseFont
        add(helpMessage)
        helpMessage.isVisible = true

    }


}

/**
 * Displays a modal dialog
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
        val introMessage = JLabel("<html> hello")
        introMessage.bounds = Rectangle(25, 25, 350, 150)
        introMessage.verticalAlignment = SwingConstants.TOP
        introMessage.font = baseFont
        add(introMessage)
        introMessage.isVisible = true

    }


}

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
        const val ACTION_NONE = 0
        const val ACTION_COLLECT = 1
        const val ACTION_USE = 2
        const val ACTION_OPEN = 3
    }

    var up: Location? = null
    var down: Location? = null
    var right: Location? = null
    var left: Location? = null


}

