package frc.robot.conveyor;

import org.xero1425.base.Subsystem;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.motorsubsystem.MotorSubsystem;

import edu.wpi.first.wpilibj.Servo;

public class ConveyorSubsystem extends MotorSubsystem {

    // TODO: complete deploy right action
    //   ->  should open  right gate
    // TODO: reflect changes in rest of robot, including automodes + oi

    public static final String SubsystemName = "conveyor";

    private Servo left_gate_servo_ ;
    private Servo right_gate_servo_ ;
  
    private double left_gate_open_pos_ ;
    private double left_gate_closed_pos_ ;
    private double right_gate_open_pos_ ;
    private double right_gate_closed_pos_ ;

    private LeftGatePosition l_desired_ ;
    private LeftGatePosition l_actual_ ;
    private RightGatePosition r_desired_ ;
    private RightGatePosition r_actual_ ;

    public enum LeftGatePosition {
        CLOSED,
        OPEN,
        UNKNOWN
    } ;

    public enum RightGatePosition {
        CLOSED,
        OPEN,
        UNKNOWN
    } ;

    public enum State {
        LEFT,
        RIGHT,
        OFF
    } ;

    public ConveyorSubsystem(Subsystem parent) throws Exception {
        super(parent, SubsystemName);
        
        int index = getRobot().getSettingsParser().get("hw:gate:l").getInteger() ;
        left_gate_servo_ = new Servo(index) ;
        index = getRobot().getSettingsParser().get("hw:gate:r").getInteger() ;
        right_gate_servo_ = new Servo(index) ;

        left_gate_open_pos_ = getRobot().getSettingsParser().get("gate:left:open").getDouble() ;
        left_gate_closed_pos_ = getRobot().getSettingsParser().get("gate:left:closed").getDouble() ;

        left_gate_open_pos_ = getRobot().getSettingsParser().get("gate:right:open").getDouble() ;
        left_gate_closed_pos_ = getRobot().getSettingsParser().get("gate:right:closed").getDouble() ;

        
        l_actual_ = LeftGatePosition.UNKNOWN ;
        l_desired_ = LeftGatePosition.CLOSED ;

    }

    public boolean setLeftGate(LeftGatePosition pos) {
        l_desired_ = pos ;
        return true;
    }

    public boolean setRightGate(RightGatePosition pos) {
        r_desired_ = pos ;
        return true;
    }

    public LeftGatePosition getLeftGate() {
        return l_actual_ ;
    }
    public RightGatePosition getRightGate() {
        return r_actual_ ;
    }

    @Override
    public void postHWInit() {
        setDefaultAction(new MotorPowerAction(this, 0.0));
    }

    @Override 
    public void computeMyState() throws Exception {
        super.computeMyState();
        putDashboard("leftgate", DisplayType.Always, left_gate_servo_.get());
        putDashboard("rightgate", DisplayType.Always, right_gate_servo_.get());
    }

    @Override
    public void run() throws Exception {
        super.run() ;
        updatePhysicalLeftGate();
        updatePhysicalRightGate() ;
    }

    private void updatePhysicalLeftGate() {
        if (l_desired_ == LeftGatePosition.OPEN)
        {
            left_gate_servo_.set(left_gate_open_pos_) ;
            l_actual_ = l_desired_  ;
        }
        else if (l_desired_ == LeftGatePosition.CLOSED)
        {
            left_gate_servo_.set(left_gate_closed_pos_) ;  
            l_actual_ = l_desired_  ;              
        }
    }

    private void updatePhysicalRightGate() {
        if (r_desired_ == RightGatePosition.OPEN)
        {
            right_gate_servo_.set(right_gate_open_pos_) ;
            r_actual_ = r_desired_  ;
        }
        else if (r_desired_ == RightGatePosition.CLOSED)
        {
            right_gate_servo_.set(right_gate_closed_pos_) ;  
            r_actual_ = r_desired_  ;              
        }
    }

}

