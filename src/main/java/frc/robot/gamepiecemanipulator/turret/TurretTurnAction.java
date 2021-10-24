package frc.robot.gamepiecemanipulator.turret;

import org.xero1425.base.motorsubsystem.MotorEncoderGotoAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class TurretTurnAction extends MotorEncoderGotoAction {

    private double desired_ ;

    public TurretTurnAction(TurretSubsystem sub, double desired) throws BadParameterTypeException, MissingParameterException {
        super(sub, desired, true);
    
        desired_ = desired;
    }

    @Override
    public void start() throws Exception {
        super.start();

    }

    @Override
    public void run() throws Exception {
        super.run();
        
    }

    @Override
    public void cancel() {
        super.cancel() ;
       
    }

    @Override
    public String toString(int indent) {
        return prefix(indent) + "FollowTargetAction " + Double.toString(desired_) ;
    }

    
    public double getDesired() {
        return desired_ ;
    }

}
