package frc.robot.gamepiecemanipulator.intake;

import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class IntakeOffAction {
    private IntakeSubsystem sub_;
    private double intake_power_;

    public IntakeOffAction(IntakeSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        
        sub_ = sub ;
        intake_power_ = sub.getRobot().getSettingsParser().get("intake:motor:off:power").getDouble() ;

    }

   
}
