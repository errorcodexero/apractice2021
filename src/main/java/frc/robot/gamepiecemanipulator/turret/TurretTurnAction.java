package frc.robot.gamepiecemanipulator.turret;

import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorRequestFailedException;
import org.xero1425.base.motorsubsystem.MotorEncoderGotoAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class TurretTurnAction extends MotorEncoderGotoAction {

    private TurretSubsystem sub_;

    private double desired_ ;

    public TurretTurnAction(TurretSubsystem sub, double desired) throws BadParameterTypeException, MissingParameterException {
        super(sub, desired, true);
        sub_ = sub;
    
        desired_ = desired;
    }

    @Override
    public void start() throws Exception {
        super.start();
     
        //tbd add log messages
    }

    @Override
    public void run() throws Exception {
        super.run();
        
        //tbd add log messages
    }

    @Override
    public void cancel() {
        super.cancel() ;

        //tbd add log messages
       
    }

    @Override
    public String toString(int indent) {
        return prefix(indent) + "FollowTargetAction" ;
    }

    
    public double getDesired() {
        return desired_ ;
    }

}
