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

    // data fields
    val locations = mutableListOf<Location>() // List of locations
    var currentLocation: Location  // Variable to track the player's current location

    // setup the app model
    init {
        // Initialize the list with some cats
        val woods = Location("The Woods", "A twilight-draped forest where bioluminescent fungi cast an eerie glow, ancient trees murmur forgotten lore, and unseen creatures stir in the misty depths.")
        val house = Location("Abandoned House", "Black")
        val bedroom = Location("Bedroom", "White")

        // Connect locations
        woods.left = house
        house.up = bedroom
        bedroom.down = house
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


}


/**
 * Main UI window (view)
 * Defines the UI and responds to events
 * The app model should be passwd as an argument
 */
class MainWindow(val app: App) : JFrame(), ActionListener {

    // Fields to hold the UI elements
    private lateinit var locationLabel: JLabel
    private lateinit var descriptionLabel: JLabel
    private lateinit var inventoryLabel: JLabel
    private lateinit var inventoryBox: JLabel
    private lateinit var interactButton: JButton
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
        val baseFont = Font(Font.SANS_SERIF, Font.PLAIN, 36)
        val descriptionFont = Font(Font.SANS_SERIF, Font.PLAIN, 20)
        val inventoryFont = Font(Font.SANS_SERIF, Font.PLAIN, 25)

        locationLabel = JLabel(app.currentLocation.name)
        locationLabel.horizontalAlignment = SwingConstants.CENTER
        locationLabel.bounds = Rectangle(157, 34, 209, 51)
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

        inventoryBox = JLabel("Inventory")
        inventoryBox.horizontalAlignment = SwingConstants.CENTER
        inventoryBox.bounds = Rectangle(33, 282, 100, 288)
        inventoryBox.font = baseFont
        add(inventoryBox)

        interactButton = JButton("Interact")
        interactButton.bounds = Rectangle(231,282,188,44)
        interactButton.font = baseFont
        interactButton.addActionListener(this)     // Handle any clicks
        add(interactButton)

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
        locationLabel.text = app.currentLocation.name // new name
        descriptionLabel.text = "<html>" + app.currentLocation.description //  new description

    }

    /**
     * Handle any UI events (e.g. button clicks)
     * Usually this involves updating the application model
     * then refreshing the UI view
     */
    override fun actionPerformed(e: ActionEvent?) {
        when (e?.source) {
            upButton -> app.move("north")
            downButton -> app.move("south")
            leftButton -> app.move("west")
            rightButton -> app.move("east")
        }
        updateView()  // Refresh UI after moving
    }



}

class Location(val name: String, val description: String) {

    var up: Location? = null
    var down: Location? = null
    var right: Location? = null
    var left: Location? = null


}

