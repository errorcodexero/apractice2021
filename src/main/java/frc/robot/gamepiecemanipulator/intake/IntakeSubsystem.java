package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorController;
import org.xero1425.base.motors.MotorRequestFailedException;
import org.xero1425.base.motorsubsystem.MotorEncoderHoldAction;
import org.xero1425.base.motorsubsystem.MotorEncoderSubsystem;
import org.xero1425.base.motorsubsystem.MotorSubsystem;

public class IntakeSubsystem extends MotorEncoderSubsystem {
    public static final String SubsystemName = "intake";
    private MotorController intake_;

    public IntakeSubsystem(Subsystem parent) throws Exception {
        super(parent, SubsystemName, false) ;

        intake_ = getRobot().getMotorFactory().createMotor("intake", "hw:intake:motor") ;
    }

    public void setIntakePower(double p) throws BadMotorRequestException, MotorRequestFailedException {
        intake_.set(p) ;
    }

    @Override
    public void postHWInit() {
        setDefaultAction(new MotorEncoderHoldAction(this)) ;
    }

}
