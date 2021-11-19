package frc.robot.gamepiecemanipulator.chute;

import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class ChuteOffAction extends MotorPowerAction {

    public ChuteOffAction(ChuteSubsystem sub) throws BadParameterTypeException, MissingParameterException {
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
