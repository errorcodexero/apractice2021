package frc.robot.conveyor;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.motorsubsystem.MotorSubsystem;


public class ConveyorSubsystem extends MotorSubsystem {

    public static final String SubsystemName = "conveyor";
   
    public ConveyorSubsystem(Subsystem parent) throws Exception {
        super(parent, SubsystemName) ;

    }
   
    @Override
    public void postHWInit() {
        setDefaultAction(new MotorPowerAction(this, 0.0));
    }

    public void setConveyorPower(double p) {
        setPower(p) ;
    }

}

