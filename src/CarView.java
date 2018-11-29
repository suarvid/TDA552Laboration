import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the full view of the MVC pattern of the car simulator.
 * The view is initialized in the center of the screen and references a car controller.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * @author Alle LV, Matte B, Arvid S
 * @since 2018-11-28
 */
public class CarView extends JFrame {

    //Width and height of frame.
    private static final int frameWidth = 800;
    private static final int frameHeight = 800;

    // The controller member
    private CarController carC;

    // This panel represent the animated part of the view with the car images.
    private DrawPanel drawPanel = new DrawPanel(frameWidth, frameHeight - 240);

    //This panel contains all the buttons and controls for the car simulation.
    private JPanel controlPanel = new JPanel();

    //controls etc.
    private JPanel gasPanel = new JPanel();
    private JSpinner gasSpinner = new JSpinner();
    private int gasAmount = 0;
    private JLabel gasLabel = new JLabel("Amount of gas");

    private JButton gasButton = new JButton("Gas");
    private JButton brakeButton = new JButton("Brake");
    private JButton turboOnButton = new JButton("Saab Turbo on");
    private JButton turboOffButton = new JButton("Saab Turbo off");
    private JButton liftBedButton = new JButton("Scania Raise FlatBed");
    private JButton lowerBedButton = new JButton("Scania Lower FlatBed");

    private JButton startButton = new JButton("Start all cars");
    private JButton stopButton = new JButton("Stop all cars");


    // Constructor
    public CarView(String framename, CarController cc) {
        this.carC = cc;
        initComponents(framename);
    }

    /**
     *
     * @return returns the frame width.
     */
    public int getFrameWidth() {
        return frameWidth;
    }

    /**
     *
     * @return returns the frame height.
     */
    public int getFrameHeight() {
        return frameHeight;
    }

    // Sets everything in place and fits everything

    /**
     * Initializes all the visual components.
     * Binds all the component actions to methods in the carController.
     * @param title the title of the frame.
     */
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);//The space in which cars live.


        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2, 4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((frameWidth / 2) + 4, 200));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);


        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(frameWidth / 5 - 15, 200));
        this.add(startButton);


        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(frameWidth / 5 - 15, 200));
        this.add(stopButton);

        // This actionListener is for the gas button only
        // TODO: Create more for each component as necessary
        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.raiseFlatBed();
            }
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.lowerFlatbed();
            }
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                carC.turboOn();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                carC.stopEngine();
            }
        });

        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                carC.brake(gasAmount);
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                carC.startEngine();
            }
        });

        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carC.gas(gasAmount);
            }
        });


        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}