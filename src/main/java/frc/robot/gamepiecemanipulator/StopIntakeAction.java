package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;
import org.xero1425.base.motorsubsystem.MotorEncoderGotoAction;

import frc.robot.gamepiecemanipulator.intake.IntakeOffAction;

public class StopIntakeAction extends Action {
    
    private GamePieceManipulatorSubsystem sub_ ;
    private IntakeOffAction intake_off_act_ ;
    private MotorEncoderGotoAction turret_turn_act_ ;

    public StopIntakeAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger());

        intake_off_act_ = new IntakeOffAction(gpm.getIntake());
        turret_turn_act_ = new MotorEncoderGotoAction(sub_.getTurret(), 0.0, true) ;

    }

    @Override
    public void start() throws Exception {
        super.start() ;

        sub_.getIntake().setAction(intake_off_act_, true); 
        sub_.getTurret().setAction(turret_turn_act_);
    }

    @Override
    public void run() throws Exception {
        super.run() ;

        // Butch: we do not set the action on the child every robot loop.  We set it once, and if necessary
        //        We can check the isDone() method of a child method to see if it is complete.
        if(sub_.getIntake().getAction().isDone() && sub_.getTurret().getAction().isDone()) {
            setDone() ;
        }

    }

    @Override
    public void cancel() {
        super.cancel() ;

        if (sub_.getIntake().isBusy()) {
            sub_.getIntake().cancelAction() ;
        }

        if (sub_.getTurret().isBusy()) {
            sub_.getTurret().cancelAction() ; 
        }

    }

    @Override
    public String toString(int indent) {
        return prefix(indent) + "StopIntakeAction" ;
    }

}
