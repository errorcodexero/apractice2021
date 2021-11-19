package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;

public class DepositAction extends Action {

    private GamePieceManipulatorSubsystem sub_ ;
    

    public DepositAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger());

        //actions_name_ = new SomeTypeOfAction(gpm.getSubsystemNameHere());

    }

    @Override
    public void start() throws Exception {
        super.start() ;
    
        //TODO: add stuff here
    }

    @Override
    public void run() throws Exception {
        super.run() ;

       //TODO: add stuff here

    }

    @Override
    public void cancel() {
        super.cancel() ;

        //TODO: add stuff here

    }

    @Override
    public String toString(int indent) {
        return prefix(indent) + "StopAction" ;
    }

}
