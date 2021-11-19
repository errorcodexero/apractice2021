package frc.robot.intake;

import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class IntakeOffAction extends MotorPowerAction {

    public IntakeOffAction(IntakeSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        super(sub, 0.0);
    }

    @Override
    public void start() throws Exception {
        super.start() ;

    }

    @Override
    public void run() {
        super.run() ;

    }

    @Override
    public void cancel() {
        super.cancel() ;

    }
}