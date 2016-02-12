package org.usfirst.frc.team1261.robot.commands;

import org.usfirst.frc.team1261.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeOut extends Command {
	
	public static final double POWER = -1.0;

    public IntakeOut() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.setIntakeMotorPower(0.0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.setIntakeMotorPower(POWER);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.setIntakeMotorPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
