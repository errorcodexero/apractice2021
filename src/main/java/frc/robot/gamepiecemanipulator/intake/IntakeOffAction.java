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
        // Butch: off is pretty simple, zero motor power.  I would not look this up in the settings file.
        //        It does not hurt except that making this zero makes it clear to some future reader of your
        //        code that off means off means no motor power.
        super(sub, 0.0) ;
        // super(sub, "intake:motor:off:power");

        
        // sub_ = sub ;    
    }

    @Override
    public void start() throws Exception {
        super.start() ;

        // Butch: you probably got this from some code on droid.  I would not have these
        //        logging messages.  These do not add value and provide new messages per
        //        robot loop (every 20 ms).
        // MessageLogger logger = sub_.getRobot().getMessageLogger() ;
        // logger.startMessage(MessageType.Debug, sub_.getLoggerID()) ;
        // logger.add("start") ;
        // logger.add("isdone", isDone()) ;
        // logger.add("power", intake_power_) ;
        // logger.endMessage() ;
    }

    @Override
    public void run() {
        super.run() ;

        // MessageLogger logger = sub_.getRobot().getMessageLogger() ;
        // logger.startMessage(MessageType.Debug, sub_.getLoggerID()) ;
        // logger.add("run") ;
        // logger.add("isdone", isDone()) ;
        // logger.add("power", intake_power_) ;
        // logger.endMessage() ;

    }

    @Override
    public void cancel() {
        super.cancel() ;

        // MessageLogger logger = sub_.getRobot().getMessageLogger() ;
        // logger.startMessage(MessageType.Debug, sub_.getLoggerID()) ;
        // logger.add("cancel") ;
        // logger.add("isdone", isDone()) ;
        // logger.add("power", intake_power_) ;
        // logger.endMessage() ;
    }
   
}
