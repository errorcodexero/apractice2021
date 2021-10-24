package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
// Butch: remove these since I removed the logging below to eliminate warnings
// import org.xero1425.misc.MessageLogger;
// import org.xero1425.misc.MessageType;
import org.xero1425.misc.MissingParameterException;

public class IntakeOffAction extends MotorPowerAction {
    // Butch: we don't need to hold onto this, the base class has it
    // private IntakeSubsystem sub_;

    public IntakeOffAction(IntakeSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        super(sub, 0.0) ;

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
