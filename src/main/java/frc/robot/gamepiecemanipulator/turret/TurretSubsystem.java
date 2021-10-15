package frc.robot.gamepiecemanipulator.turret;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorController;
import org.xero1425.base.motors.MotorRequestFailedException;
import org.xero1425.base.motorsubsystem.MotorEncoderSubsystem;

public class TurretSubsystem extends MotorEncoderSubsystem {
    public static final String SubsystemName = "turret";
    private MotorController turret_;

    public TurretSubsystem(Subsystem parent) throws Exception {
        super(parent, "turret", false) ;

        turret_ = getRobot().getMotorFactory().createMotor("turret", "hw:turret:motor") ;

    }

    public void setTurretPower(double p) throws BadMotorRequestException, MotorRequestFailedException {
        turret_.set(p) ;
    }
    
}
