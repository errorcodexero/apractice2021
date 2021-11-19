package frc.robot.gamepiecemanipulator.chute;

import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class ChuteEjectAction extends MotorPowerAction {

    public ChuteEjectAction(ChuteSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        super(sub, "chute:motor:eject:power");
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