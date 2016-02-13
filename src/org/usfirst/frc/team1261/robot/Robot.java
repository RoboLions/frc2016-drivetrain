
package org.usfirst.frc.team1261.robot;

import org.usfirst.frc.team1261.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1261.robot.subsystems.Flywheel;
import org.usfirst.frc.team1261.robot.subsystems.Intake;
import org.usfirst.frc.team1261.robot.subsystems.ShooterArm;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Intake intake = new Intake();
	public static final Flywheel flywheel = new Flywheel();
	public static final ShooterArm shooterArm = new ShooterArm();
	
	public static OI oi;

    Command autonomousCommand;
    SendableChooser chooser;
    
    public static boolean isAlignedVert = true;
    
    SendableChooser startingPositionChooser = new SendableChooser();
    SendableChooser endingPositionChooser = new SendableChooser();
    SendableChooser defenseTypeChooser = new SendableChooser();
    SendableChooser shotLocationChooser = new SendableChooser();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
//        chooser = new SendableChooser();
//        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
//        SmartDashboard.putData("Auto mode", chooser);
		
		startingPositionChooser.addDefault("Left", "Left");
        startingPositionChooser.addObject("Left-Center", "Left-Center");
        startingPositionChooser.addObject("Center", "Center");
        startingPositionChooser.addObject("Right-Center", "Right-Center");
        startingPositionChooser.addObject("Right", "Right");
        
        endingPositionChooser.addDefault("Left", "Left");
        endingPositionChooser.addObject("Left-Center", "Left-Center");
        endingPositionChooser.addObject("Center", "Center");
        endingPositionChooser.addObject("Right-Center", "Right-Center");
        endingPositionChooser.addObject("Right", "Right");
        
        defenseTypeChooser.addDefault("Low Bar", "Low Bar");
        defenseTypeChooser.addObject("Portcullis", "Portcullis");
        defenseTypeChooser.addObject("Cheval-de-Frise", "Cheval-de-Frise");
        defenseTypeChooser.addObject("Moat", "Moat");
        defenseTypeChooser.addObject("Ramparts", "Ramparts");
        defenseTypeChooser.addObject("Rock Wall", "Rock Wall");
        defenseTypeChooser.addObject("Rough Terrain", "Rough Terrain");
        
        shotLocationChooser.addDefault("Middle-Long", "Middle-Long");
        shotLocationChooser.addObject("Middle-Short", "Middle-Short");
        shotLocationChooser.addObject("Side-Long", "Side-Long");
        
        SmartDashboard.putData("Starting Position Chooser", startingPositionChooser);
        SmartDashboard.putData("Shot Location Chooser", shotLocationChooser);
        SmartDashboard.putData("Ending Position Chooser", endingPositionChooser);
        SmartDashboard.putData("Defense Type Chooser", defenseTypeChooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		allPeriodic();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	
    	System.out.println("Starting Position: " + startingPositionChooser.getSelected() + "     Defense Type: " + defenseTypeChooser.getSelected()
    	+ "     Shot Location: " + shotLocationChooser.getSelected() + "     End Position: " + endingPositionChooser.getSelected());
    
    	if((String)startingPositionChooser.getSelected() == "Left"){
    		isAlignedVert = false;
    	}
    	else{
    		isAlignedVert = true;
    	}
    	SmartDashboard.putBoolean("Aligned Vertically", isAlignedVert);
    	
        //autonomousCommand = (Command) chooser.getSelected();
    	autonomousCommand = new org.usfirst.frc.team1261.robot.commands.AutonomousCommand();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        allPeriodic();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        allPeriodic();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        allPeriodic();
    }
    
    /**
     * This function is called periodically during all modes
     */
    public void allPeriodic() {
    	SmartDashboard.putNumber("Left distance", Robot.driveTrain.leftDistanceTraveled());
    	SmartDashboard.putNumber("Right distance", Robot.driveTrain.rightDistanceTraveled());
    	SmartDashboard.putNumber("Range finder voltage", RobotMap.rangeFinder.getAverageVoltage());
    }
}
