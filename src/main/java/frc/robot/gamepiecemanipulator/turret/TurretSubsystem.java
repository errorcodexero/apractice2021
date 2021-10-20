package frc.robot.gamepiecemanipulator.turret;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorController;
import org.xero1425.base.motors.MotorRequestFailedException;
import org.xero1425.base.motorsubsystem.MotorEncoderHoldAction;
import org.xero1425.base.motorsubsystem.MotorEncoderSubsystem;

public class TurretSubsystem extends MotorEncoderSubsystem {
    public static final String SubsystemName = "turret";
    private MotorController turret_; //MotorEncoderSubsystem turret_;

    public TurretSubsystem(Subsystem parent) throws Exception {
        super(parent, "turret", false) ;

        turret_ = getRobot().getMotorFactory().createMotor("turret", "hw:turret:motor") ;
        //turr_encoders_ = getRobot().getSettingsParser.get("hw:turret:motor:encoder").get
        
        //TBD check in motor factory... need to declare an encoder here? or does factory create the neo encoders default?
    }
    
    @Override
    public void postHWInit() {
        setDefaultAction(new MotorEncoderHoldAction(this)) ;
    }
    
    public void setTurretPower(double p) throws BadMotorRequestException, MotorRequestFailedException {
        //TBD constraints on "p"
        turret_.set(p) ;
    }
    
    @Override 
    public void computeMyState() throws Exception {
        super.computeMyState();
        //anything else?
    }
}
