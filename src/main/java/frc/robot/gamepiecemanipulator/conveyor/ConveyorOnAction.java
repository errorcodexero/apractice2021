package frc.robot.gamepiecemanipulator.conveyor;

import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class ConveyorOnAction extends MotorPowerAction {

    public ConveyorOnAction(ConveyorSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        super(sub, "conveyor:motor:on:power");
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