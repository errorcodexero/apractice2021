package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.motorsubsystem.MotorSubsystem;

public class IntakeSubsystem extends MotorSubsystem {
    public static final String SubsystemName = "intake";
    //private MotorSubsystem intake_;

    public IntakeSubsystem(Subsystem parent) throws Exception {
        super(parent, SubsystemName) ;
        //intake_ = getRobot().getMotorFactory().createMotor("intake", "hw:intake:motor") ;
    }

    // public void setIntakePower(double p) throws BadMotorRequestException, MotorRequestFailedException {
    //     intake_.set(p) ;
    // }

    @Override
    public void postHWInit() {
        // setDefaultAction(new MotorEncoderHoldAction(this)) ;
        setDefaultAction(new MotorPowerAction(this, 0.0));
    }

    @Override 
    public void computeMyState() throws Exception {
        super.computeMyState();
    }

}
