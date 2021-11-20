package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;
import org.xero1425.base.motorsubsystem.MotorPowerAction;

public class StopAction extends Action {

    private GamePieceManipulatorSubsystem sub_ ;
    private MotorPowerAction conveyor_off_action_ ;
    private MotorPowerAction chute_off_action_ ;

    public StopAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger()) ;

        sub_ = gpm;

        //conveyor off action: applys 0 power to conveyor motors
        conveyor_off_action_ = new MotorPowerAction(sub_.getConveyor(), 0.0) ;

        //chute off action: applys 0 power to chute motors
        chute_off_action_ = new MotorPowerAction(sub_.getChute(), 0.0) ;

    }

    @Override
    public void start() throws Exception {
        super.start() ;
    
        sub_.getConveyor().setAction(conveyor_off_action_, true); 
        sub_.getChute().setAction(chute_off_action_, true);

    }

    @Override
    public void run() throws Exception {
        super.run() ;

        if(sub_.getConveyor().getAction().isDone() && sub_.getChute().getAction().isDone()) {
            setDone() ;
        }

    }

    @Override
    public void cancel() {
        super.cancel() ;

        if (sub_.getConveyor().isBusy()) {
            sub_.getConveyor().cancelAction() ;
        }

        if (sub_.getChute().isBusy()) {
            sub_.getChute().cancelAction() ;
        }

    }

    @Override
    public String toString(int indent) {
        return prefix(indent) + "StopAction" ;
    }

}
