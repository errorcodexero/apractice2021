package frc.robot.intake;

import org.xero1425.base.actions.Action;
import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorRequestFailedException;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MissingParameterException;

public class IntakePowerAction extends Action {
    private IntakeSubsystem sub_;
    private double lower_power_;
    private double upper_power_;

    public IntakePowerAction(MessageLogger logger, IntakeSubsystem sub, double lower_power, double upper_power) {
        super(logger) ;
        
        sub_ = sub;
        lower_power_ = lower_power;
        upper_power_ = upper_power;
    }

    public IntakePowerAction(MessageLogger logger, IntakeSubsystem sub, String lower_power, String upper_power)
            throws BadParameterTypeException, MissingParameterException {
        super(logger) ;
        
        sub_ = sub;
        lower_power_ = sub_.getSettingsValue(lower_power).getDouble() ;
        upper_power_ = sub_.getSettingsValue(upper_power).getDouble() ;
    }

    @Override
    public void start() throws Exception {
        super.start();

        sub_.setBothMotorPowers(lower_power_, upper_power_) ;
        setDone() ;

    }

    @Override
    public void run() throws Exception {
        super.run();

    }

    @Override
    public void cancel() {
        super.cancel() ;

        try {
            sub_.setBothMotorPowers(0.0, 0.0) ; 
        } catch (BadMotorRequestException | MotorRequestFailedException e) {
        
        }

    }

    @Override
    public String toString(int indent) {

        return "IntakePowerAction " + Double.toString(lower_power_) + " " + Double.toString(upper_power_) ;
    }
}
