package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;

import frc.robot.gamepiecemanipulator.intake.IntakeOffAction;
import frc.robot.gamepiecemanipulator.turret.TurretTurnAction;

public class StopIntakeAction extends Action {

    public StopIntakeAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger());

        intake_off_act_ = new IntakeOffAction(gpm.getIntake());
        turret_act_ = new TurretTurnAction(gpm.getTurret(), 0.0);

    }

    @Override
    public void start() throws Exception {
        super.start() ;

        // Butch: The instructions say set the turret back to zero degrees, so we need to 
        //        assign the turret action.  We stop 

        sub_.getIntake().setAction(intake_off_act_, true); 
    }

    @Override
    public void run() throws Exception {
        super.run() ;
        
        // Butch: we do not set the action on the child every robot loop.  We set it once, and if necessary
        //        We can check the isDone() method of a child method to see if it is complete.
        //
        // sub_.getIntake().setAction(intake_off_act_); // turret won't move in this case...
        // sub_.getTurret().setAction(turret_act_);
        
        // Butch: does this action ever complete?  if so, what are the completion conditions?  When an action completes,
        //        we need to call setDone() from the run method.
    }

    @Override
    public void cancel() {
        super.cancel() ;

        if (sub_.getIntake().isBusy())
            sub_.getIntake().cancelAction();
        if (sub_.getTurret().isBusy()) 
            sub_.getTurret().cancelAction();

    }


    @Override
    public String toString(int indent) {
        return prefix(indent) + "StopIntakeAction" ;
    }

    private GamePieceManipulatorSubsystem sub_ ;
    private IntakeOffAction intake_off_act_ ;
    private TurretTurnAction turret_act_ ;

}
