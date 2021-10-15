package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class IntakeCollectAction {
    private IntakeSubsystem sub_;
    private double intake_power_;

    public IntakeCollectAction(IntakeSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        
        sub_ = sub ;
        intake_power_ = sub.getRobot().getSettingsParser().get("intake:motor:collect:power").getDouble() ;
    }

    // @Override
    // public void start() throws Exception {
    //     super.start() ;

    //     sub_.setIntakePower(intake_power_);
    // }
    
    // @Override
    // public void run() throws Exception {
    //     super.run() ;
    // }

    // @Override
    // public void cancel() {
    //     super.cancel();

    //     try {
    //         sub_.setIntakePower(0.0);
    //     } 
    //     catch(Exception ex) {
    //     }
    // }
}