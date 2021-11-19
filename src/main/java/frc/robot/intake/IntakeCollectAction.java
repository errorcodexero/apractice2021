package frc.robot.intake;

import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class IntakeCollectAction extends MotorPowerAction {

    public IntakeCollectAction(IntakeSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        super(sub, "intake:motor:collect:power");
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