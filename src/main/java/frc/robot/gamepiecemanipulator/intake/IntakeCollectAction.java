package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
// import org.xero1425.misc.MessageLogger;
// import org.xero1425.misc.MessageType;
import org.xero1425.misc.MissingParameterException;

public class IntakeCollectAction extends MotorPowerAction {
    // private IntakeSubsystem sub_;

    public IntakeCollectAction(IntakeSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        super(sub, "intake:motor:collect:power");
        // sub_ = sub ;      
    }

    @Override
    public void start() throws Exception {
        super.start() ;

        // MessageLogger logger = sub_.getRobot().getMessageLogger() ;
        // logger.startMessage(MessageType.Debug, sub_.getLoggerID()) ;
        // logger.add("start") ;
        // logger.add("isdone", isDone()) ;
        // //logger.add("power", intake_power_) ;
        // logger.endMessage() ;

    }

    @Override
    public void run() {
        super.run() ;

        // MessageLogger logger = sub_.getRobot().getMessageLogger() ;
        // logger.startMessage(MessageType.Debug, sub_.getLoggerID()) ;
        // logger.add("run") ;
        // logger.add("isdone", isDone()) ;
        // //logger.add("power", intake_power_) ;
        // logger.endMessage() ;

    }

    @Override
    public void cancel() {
        super.cancel() ;

        // MessageLogger logger = sub_.getRobot().getMessageLogger() ;
        // logger.startMessage(MessageType.Debug, sub_.getLoggerID()) ;
        // logger.add("cancel") ;
        // logger.add("isdone", isDone()) ;
        // //logger.add("power", intake_power_) ;
        // logger.endMessage() ;

    }
}