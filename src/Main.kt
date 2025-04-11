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
        // Initialize the list with some cats
        val woods = Location("The Woods", "A twilight-draped forest", Location.ACTION_NONE)
        val house = Location("Abandoned House", "", Location.ACTION_COLLECT, "open chest", "you found a key in the chest", "key")
        val trail = Location("Trail", "", Location.ACTION_NONE )
        val bedroom = Location("Bedroom", "", Location.ACTION_OPEN, "open window","the window is now open", "", "", "north", trail)


        // Connect locations
        woods.left = house
        house.up = bedroom
        bedroom.down = house
        trail.down = bedroom
        house.right = woods

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
    val maxHealth = 50

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

        if (app.inventory.contains("heart") && app.inventory.contains("feather") && app.inventory.contains("water") && app.inventory.contains("dragon scale")) {
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
                            inventoryBox.text = "<html>" + app.inventory.joinToString("<br>")
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
                            inventoryBox.text = "<html>" + app.inventory.joinToString("<br>")
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

