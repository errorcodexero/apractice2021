package frc.robot.gamepiecemanipulator.turret;

import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorRequestFailedException;
import org.xero1425.base.motorsubsystem.MotorEncoderGotoAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class TurretTurnAction extends MotorEncoderGotoAction {

    private TurretSubsystem sub_;
    //private double turret_power_;

    private double desired_ ;
   // private double error_ ;

    //private PIDCtrl pid_ ;

    //private double threshold_ ;

    public TurretTurnAction(TurretSubsystem sub, double desired) throws BadParameterTypeException, MissingParameterException {
        super(sub, desired, true);
        sub_ = sub;
        
        // turr_encoders_m_ = sub_.getRobot().getSettingsParser().get("hw:turret:encoder:quad:m").getDouble() ;
        // turr_encoders_b_ = sub_.getRobot().getSettingsParser().get("hw:turret:encoder:quad:b").getDouble() ;

        desired_ = desired;
        // threshold_ = sub.getRobot().getSettingsParser().get("turret:fire_threshold").getDouble() ;
    }

    @Override
    public void start() throws Exception {
        super.start();
        
        // if (turret_power_ > sub_.getMinSafeAngle() && turret_power_ < sub_.getMaxSafeAngle()) {
        //    sub_.addAction() motor encoder go to action ...
        // }
    
    }

    @Override
    public void run() throws BadMotorRequestException, MotorRequestFailedException {
        
        // double out = pid_.getOutput(desired_, sub_.getPosition(), sub_.getRobot().getDeltaTime()) ;
        // sub_.setTurretPower(out) ;

        // if (isDone()) {
        //     try {
        //         sub_.addAction(new MotorEncoderHoldAction(sub_));
        //     } catch (BadMotorRequestException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     } catch (MotorRequestFailedException e) {
        //         // TODO Auto-generated catch block
        //         e.printStackTrace();
        //     }
        // }
    }

    @Override
    public void cancel() {
        super.cancel() ;

        //
        // Set the turret power to zero
        //
        // try {
        //     sub_.setVelocity(0.0);
        // } catch (BadMotorRequestException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (MotorRequestFailedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }

    @Override
    public String toString(int indent) {
        return prefix(indent) + "FollowTargetAction" ;
    }

    
    public double getDesired() {
        return desired_ ;
    }

    // public double getError() {
    //     return error_ ;
    // }

    // private double turr_encoders_m_ ;
    // private double turr_encoders_b_ ;

}
