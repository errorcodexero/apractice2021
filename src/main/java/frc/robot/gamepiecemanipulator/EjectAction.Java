package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;
import org.xero1425.base.motorsubsystem.MotorPowerAction;

public class EjectAction extends Action {

    private GamePieceManipulatorSubsystem sub_ ;
    private MotorPowerAction conveyor_eject_action_ ;
    private MotorPowerAction chute_eject_action_ ;

    public EjectAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger());

        //conveyor eject action: applys power to run conveyor motors backwards
        conveyor_eject_action_ = new MotorPowerAction(sub_.getConveyor(), "conveyor:motor:eject:power");

        //chute eject action: applys power to run chute motors backwards
        chute_eject_action_ = new MotorPowerAction(sub_.getChute(), "conveyor:motor:eject:power");

    }

    @Override
    public void start() throws Exception {
        super.start() ;
    
        sub_.getConveyor().setAction(conveyor_eject_action_, true); 
        sub_.getChute().setAction(chute_eject_action_, true);

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
        return prefix(indent) + "EjectAction" ;
    }

}
