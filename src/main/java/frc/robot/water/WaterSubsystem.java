package frc.robot.water;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.motorsubsystem.MotorSubsystem;

// TODO: add water subsystem into OI device + 2tote automode

public class WaterSubsystem extends MotorSubsystem {
    public static final String SubsystemName = "water";

    public WaterSubsystem(Subsystem parent) {
        super(parent, SubsystemName) ;
    }

    @Override
    public void postHWInit() {
        setDefaultAction(new MotorPowerAction(this, 0.0));
    }
    
}

