package frc.robot.gamepiecemanipulator.turret;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motorsubsystem.MotorEncoderHoldAction;
import org.xero1425.base.motorsubsystem.MotorEncoderSubsystem;

public class TurretSubsystem extends MotorEncoderSubsystem {
    public static final String SubsystemName = "turret";
    private MotorEncoderSubsystem turret_; 

    public TurretSubsystem(Subsystem parent) throws Exception {
        super(parent, "turret", false) ;

        min_safe_angle_ = getRobot().getSettingsParser().get("turret:min").getDouble() ;
        max_safe_angle_ = getRobot().getSettingsParser().get("turret:max").getDouble() ;
    }
    
    @Override
    public void postHWInit() {
        setDefaultAction(new MotorEncoderHoldAction(this)) ;
    }
    
    public double getTurretVelocity() {
        return getVelocity();
    }
    // public void setTurretPower(double p) throws BadMotorRequestException, MotorRequestFailedException {
    //     if (p < 0 && getPosition() < getMinSafeAngle()) {
    //         p = 0 ;
    //     } else if (p > 0 && getPosition() > getMaxSafeAngle()) {
    //         p = 0 ;
    //     }
    //     turret_.set(p) ;
    // }
    
    public double getTurretAngle() {
        return getPosition();
    }
    
    public double getMinSafeAngle() {
        return min_safe_angle_ ;
    }

    public double getMaxSafeAngle() {
        return max_safe_angle_ ;
    }

    @Override 
    public void computeMyState() throws Exception {
        super.computeMyState();
    }

    private double min_safe_angle_ ;
    private double max_safe_angle_ ;

}
