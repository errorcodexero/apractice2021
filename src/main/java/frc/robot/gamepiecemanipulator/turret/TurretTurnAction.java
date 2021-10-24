package frc.robot.gamepiecemanipulator.turret;

// Butch: Remove these imports that are no longer needed so what when we see
//        warnings we know they are real and we should deal them.
// import org.xero1425.base.motors.BadMotorRequestException;
//import org.xero1425.base.motors.MotorRequestFailedException;

import org.xero1425.base.motorsubsystem.MotorEncoderGotoAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class TurretTurnAction extends MotorEncoderGotoAction {

    // Butch: you don't use this, so don't hold onto it.  The base class
    //        has the subsystem and you can get it at any time by calling
    //        getSubsystem()
    // private TurretSubsystem sub_;

    private double desired_ ;

    public TurretTurnAction(TurretSubsystem sub, double desired) throws BadParameterTypeException, MissingParameterException {
        super(sub, desired, true);
        // sub_ = sub;
    
        desired_ = desired;
    }

    @Override
    public void start() throws Exception {
        super.start();

        // Butch: don't add log messages here as part of the action.
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
        // Butch: If you are going to override the class to provide this method, lets also add the desired value
        // return prefix(indent) + "FollowTargetAction" ;
        return prefix(indent) + "FollowTargetAction " + Double.toString(desired_) ;
    }

    
    public double getDesired() {
        return desired_ ;
    }

}
