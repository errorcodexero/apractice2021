package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;
import org.xero1425.misc.MessageLogger;

import frc.robot.gamepiecemanipulator.intake.IntakeCollectAction;

public class StopIntakeAction extends Action {

    public StopIntakeAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger());

        collect_ = new IntakeCollectAction(gpm.getIntake());

        // sub_ = gp ;

    }


    @Override
    public void start() throws Exception {
        super.start() ;

        //if turret is <90 and >-90...
        //sub_.getIntake().set power to 0 ;
       
    }

    @Override
    public void run() throws Exception {
        super.run() ;
        
        // if (!sub_.getIntake().isBusy() && !sub_.getConveyor().isBusy()) { 
        //     setDone() ;
        // }
    }

    @Override
    public void cancel() {
        super.cancel() ;

        // if (sub_.getIntake().isBusy())
        //     sub_.getIntake().cancelAction();

    }


    @Override
    public String toString(int indent) {
        return prefix(indent) + "StopIntakeAction" ;
    }

    private GamePieceManipulatorSubsystem sub_ ;
    private IntakeCollectAction collect_ ;
    //something about the turret

}
