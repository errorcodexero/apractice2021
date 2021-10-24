package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
// import org.xero1425.misc.MessageLogger;
// import org.xero1425.misc.MessageType;
import org.xero1425.misc.MissingParameterException;

public class IntakeEjectAction extends MotorPowerAction {
    // Butch: again, the base class has the subsystem and you can get it via the getSubsystem() method.  Don't store it here.
    // private IntakeSubsystem sub_;

    public IntakeEjectAction(IntakeSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        super(sub, "intake:motor:eject:power");
        // sub_ = sub ; 
    }
    
    @Override
    public void start() throws Exception {
        super.start() ;

        // Butch: again, don't log things unless you really need them, the base class as all of the per robot loop stuff
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