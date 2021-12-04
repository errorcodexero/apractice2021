package frc.robot.water;

import org.xero1425.base.Subsystem;

import edu.wpi.first.wpilibj.Relay;

public class WaterSubsystem extends Relay {
    public static final String SubsystemName = "water";

    public WaterSubsystem(Subsystem parent) throws Exception {
        super(1, Direction.kForward) ;
        
    }
}

