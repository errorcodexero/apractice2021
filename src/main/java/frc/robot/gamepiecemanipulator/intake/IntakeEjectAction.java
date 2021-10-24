package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MessageType;
import org.xero1425.misc.MissingParameterException;

public class IntakeEjectAction extends MotorPowerAction {
    private IntakeSubsystem sub_;
    //private MotorPowerAction intake_power_act_;

    public IntakeEjectAction(IntakeSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        super(sub, "intake:motor:eject:power");
        sub_ = sub ; 
    }
    
    @Override
    public void start() throws Exception {
        super.start() ;

        MessageLogger logger = sub_.getRobot().getMessageLogger() ;
        logger.startMessage(MessageType.Debug, sub_.getLoggerID()) ;
        logger.add("start") ;
        logger.add("isdone", isDone()) ;
        //logger.add("power", intake_power_) ;
        logger.endMessage() ;

        // if (!isDone())
        //     sub_.setAction(intake_power_act_) ;
        // else
        //     sub_.setAction(new MotorPowerAction(sub_, 0.0)) ;
    }

    @Override
    public void run() {
        super.run() ;

        MessageLogger logger = sub_.getRobot().getMessageLogger() ;
        logger.startMessage(MessageType.Debug, sub_.getLoggerID()) ;
        logger.add("run") ;
        logger.add("isdone", isDone()) ;
        //logger.add("power", intake_power_) ;
        logger.endMessage() ;

        // if (isDone())
        //     sub_.setAction(new MotorPowerAction(sub_, 0.0)) ;
    }

    @Override
    public void cancel() {
        super.cancel() ;

        MessageLogger logger = sub_.getRobot().getMessageLogger() ;
        logger.startMessage(MessageType.Debug, sub_.getLoggerID()) ;
        logger.add("cancel") ;
        logger.add("isdone", isDone()) ;
        //logger.add("power", intake_power_) ;
        logger.endMessage() ;

        // try {
        //     sub_.setAction(new MotorPowerAction(sub_, 0.0)) ;
        // }
        // catch(Exception ex) {
        // }
    }
}