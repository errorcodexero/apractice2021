package frc.robot.gamepiecemanipulator.turret;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motorsubsystem.MotorEncoderHoldAction;
import org.xero1425.base.motorsubsystem.MotorEncoderSubsystem;

public class TurretSubsystem extends MotorEncoderSubsystem {
    public static final String SubsystemName = "turret";

    private double min_safe_angle_ ;
    private double max_safe_angle_ ;

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

    // Butch: in my physical descriptor of the turret, I said it turns from 165 degrees
    //        to -165 degrees.  This method is a method in the base class that is called
    //        just before power is applied to the motors.  In a subsystem we can check
    //        to be sure we don't let bad things happen.
    @Override
    protected double limitPower(double p) {
        double ret = p ;

        if (p < 0 && getTurretAngle() <= getMinSafeAngle())
        {
            // The power is negative, so we are trying to move the turret more
            // negative, but we are at the safe limit, so shut down the turret.
            ret = 0 ;
        }
        else if (p > 0 && getTurretAngle() >= getMaxSafeAngle())
        {
            // The power is positive, so we are trying to move the turret more
            // positive, but we are at the safe limit, so shut down the turret.
            ret = 0 ;
        }
        return ret ;
    }
}
