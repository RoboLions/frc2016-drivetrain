package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousCommand extends Command {
	
	static final double TOLERANCE = 10.0;
	static final double SETPOINT = 500.0;

    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.driveTrain.driveToRelative(SETPOINT);
    	Robot.driveTrain.disablePIDController();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.getFrontRightMotor().set(1.0);
    	Robot.driveTrain.getRearRightMotor().set(1.0);
    	Robot.driveTrain.getFrontLeftMotor().set(1.0);
    	Robot.driveTrain.getRearLeftMotor().set(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//        return Robot.driveTrain.onTarget();
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
