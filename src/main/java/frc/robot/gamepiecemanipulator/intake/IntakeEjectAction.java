package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class IntakeEjectAction {
    private IntakeSubsystem sub_;
    private double intake_power_;

    public IntakeEjectAction(IntakeSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        
        sub_ = sub ;
        intake_power_ = sub.getRobot().getSettingsParser().get("intake:motor:eject:power").getDouble() ;

    }
    
    // @Override
    // public void start() throws Exception {
    //     super.start() ;

    //     if (!isDone())
    //         sub_.setCollectorPower(collect_power_);
    //     else
    //         sub_.setCollectorPower(0.0);
    // }



}