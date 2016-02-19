package org.usfirst.frc.team1261.robot.triggers;

import org.usfirst.frc.team1261.robot.OI;
import org.usfirst.frc.team1261.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class ManipulatorLeftTriggerTrigger extends Trigger {
	
	public static final Joystick JOYSTICK = Robot.oi.getManipulatorJoystick();
	public static final int TRIGGER_AXIS = OI.AXIS_LEFT_TRIGGER;
	public static final double TRIGGER_THRESHOLD = 0.75;
    
    public boolean get() {
        return JOYSTICK.getRawAxis(TRIGGER_AXIS) >= TRIGGER_THRESHOLD;
    }
}
