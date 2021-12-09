package frc.robot.intake ;

import org.xero1425.base.Subsystem ;
import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorController;
import org.xero1425.base.motors.MotorRequestFailedException;

public class IntakeSubsystem extends Subsystem {
    public static final String SubsystemName = "intake";

    MotorController lower_motor_;
    MotorController upper_motor_;
    double p_lower_ ;
    double p_upper_ ;
    boolean are_motors_running_ = false;

    public IntakeSubsystem(Subsystem parent) throws Exception {
        super(parent, SubsystemName);

        lower_motor_ = getRobot().getMotorFactory().createMotor("lowermotor", "subsystems:intake:hw:lowermotor") ;
        upper_motor_ = getRobot().getMotorFactory().createMotor("uppermotor", "subsystems:intake:hw:uppermotor") ;

    }

    public void setBothMotorPowers(double p_lower, double p_upper) throws BadMotorRequestException, MotorRequestFailedException {
        p_lower_ = p_lower ;
        p_upper_ = p_upper ;

        lower_motor_.set(p_lower) ;
        upper_motor_.set(p_upper) ;

    }

    public boolean isRunning() {
        //TODO: add a constant "epsilon" or smth to params file and get the "tiny number" (0.0001) from there
        if ((Math.abs(p_lower_) < 0.0001) || (Math.abs(p_upper_) < 0.0001)) {
            are_motors_running_ = false ;
        }
        else {
            are_motors_running_ = true ;
        }

        return are_motors_running_ ;
    }

}

