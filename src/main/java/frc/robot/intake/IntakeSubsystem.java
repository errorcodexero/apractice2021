package frc.robot.intake;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.motorsubsystem.MotorSubsystem;

public class IntakeSubsystem extends MotorSubsystem {
    public static final String SubsystemName = "intake";

    public IntakeSubsystem(Subsystem parent) throws Exception{
        super(parent, SubsystemName);
        
    }
    
    @Override
    public void postHWInit() {
        setDefaultAction(new MotorPowerAction(this, 0.0));
    }

    @Override 
    public void computeMyState() throws Exception {
        super.computeMyState();
    }
}


//Note: we can use "motorpoweractions"; don't need actual actions for the subsystem.