package frc.robot.conveyor;

import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

import frc.robot.conveyor.ConveyorSubsystem.LeftGatePosition;
import frc.robot.conveyor.ConveyorSubsystem.RightGatePosition;

public class ConveyorDeployLeftAction extends MotorPowerAction {

    private ConveyorSubsystem sub_ ;
    private ConveyorSubsystem.LeftGatePosition l_pos_ ;
    private ConveyorSubsystem.RightGatePosition r_pos_ ;
    private boolean setready_ ;

    public ConveyorDeployLeftAction(ConveyorSubsystem conveyor, boolean setready)
            throws BadParameterTypeException, MissingParameterException, BadMotorRequestException {
        super(conveyor, "motor:left:power") ;
        
        sub_ = conveyor ;
        setready_ = setready ;
    }

    @Override
    public void start() throws Exception {
        super.start() ;

    }

    @Override
    public void run() {
        super.run() ;
        sub_.setLeftGate(LeftGatePosition.OPEN);
        sub_.setRightGate(RightGatePosition.CLOSED);

    }

    @Override
    public void cancel() {
        super.cancel() ;
    }

    @Override 
    public String toString(int indent) {
        String ret = prefix(indent) + "ConveyorDeployLeftAction" ;
    
        ret += " Left Gate is = " ;
        if (l_pos_ == ConveyorSubsystem.LeftGatePosition.OPEN)
            ret += " Open" ;
        else
            ret += " Closed" ;
        
        ret += " Right Gate is = " ;
        if (r_pos_ == ConveyorSubsystem.RightGatePosition.OPEN)
            ret += " Open" ;
        else
            ret += " Closed" ;

        if (setready_)
            ret += " true" ;
        else
            ret += " false" ;
            
        return ret ;
    }

}
