package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.motorsubsystem.MotorSubsystem;

public class IntakeSubsystem extends MotorSubsystem {
    public static final String SubsystemName = "intake";

    // Butch: I would not leave this around as a comment.  Just delete it.  I think
    //        at times you leave too much stuff around as comments after you realize
    //        you don't need it.  We have our source in git, so if we need to get back
    //        to older versions of things, we can.  Leaving code in comments confuses
    //        the next person working on the code.
    //private MotorSubsystem intake_;

    public IntakeSubsystem(Subsystem parent) throws Exception {
        super(parent, SubsystemName) ;

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
