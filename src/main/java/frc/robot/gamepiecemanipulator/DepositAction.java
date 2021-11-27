package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;
import org.xero1425.base.motorsubsystem.MotorPowerAction;

public class DepositAction extends Action {

    private GamePieceManipulatorSubsystem sub_ ;
    private MotorPowerAction conveyor_on_action_ ; 
    private MotorPowerAction chute_on_action_ ;

    public DepositAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger()) ;
        
        sub_ = gpm;

        //conveyor on action: applys an "on" power to conveyor motors in order to eject the wiffle balls into chute
        conveyor_on_action_ = new MotorPowerAction(sub_.getConveyor(), "motor:on:power") ;
        
        //chute on action: applys an "on" power to chute motors in order to eject the wiffle balls into tote
        chute_on_action_ = new MotorPowerAction(sub_.getChute(), "motor:on:power") ;
    
    }

    @Override
    public void start() throws Exception {
        super.start() ;

        sub_.getConveyor().setAction(conveyor_on_action_, true); 
        sub_.getChute().setAction(chute_on_action_, true);

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
        return prefix(indent) + "DepositAction" ;
    }

}
