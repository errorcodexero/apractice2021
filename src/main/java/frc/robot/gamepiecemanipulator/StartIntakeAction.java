package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;
import org.xero1425.base.motorsubsystem.MotorEncoderGotoAction;

import frc.robot.gamepiecemanipulator.intake.IntakeCollectAction;

public class StartIntakeAction extends Action {

    // Butch: this is a case where you do want to hold onto the subsystem
    //        since you are derived from the Action class and it does not
    //        know about the subsystem.
    private GamePieceManipulatorSubsystem sub_ ;
    private IntakeCollectAction intake_on_act_ ;
    private MotorEncoderGotoAction turret_turn_act_ ;

    // Butch: From the email, "the StartIntake action for the GamePieceManipulatorthat takes an angle between -90 and +90 as an angle for the turret"
    //        This is the desired angle for the turret during the intake action, so we need to make it an argument
    public StartIntakeAction(GamePieceManipulatorSubsystem gpm, double desiredturret) throws Exception {
        super(gpm.getRobot().getMessageLogger());
        sub_ = gpm;

        if (desiredturret < -90.0 || desiredturret > 90.0)
        {
            // Butch: what we decide to do here depends on the robot.  We could limit the value, so if the angle was
            //        less than -90 we set it to -90 and if it was greater than 90 we set it to 90.
            //        But for this example, I am going to assume that this is a major problem and throw an exception
            throw new Exception("Invalid value, " + Double.toString(desiredturret) + " for StartIntakeAction turrent angle") ;
        }
        
        intake_on_act_ = new IntakeCollectAction(gpm.getIntake()) ;

        // Butch: need to create a turret action here that is a TurretTurnAction ...
        turret_turn_act_ = new MotorEncoderGotoAction(sub_.getTurret(), desiredturret, true) ;
    }

    @Override
    public void start() throws Exception {
        super.start() ;

        sub_.getTurret().setAction(turret_turn_act_);
        // Butch: so you need to think about the sequencing here.  The description here in my email was not
        //        100 percent precise, but lets assume that you want the turret to get to the desired angle before
        //        you start the rollers on the intake.  What would you do in this method???
        // 

    }

    @Override
    public void run() throws Exception {
        super.run() ;

        // Butch: since we are assuming we want the turret to get to the right angle before we start the intake
        //        rollers, we need to monitor the turret action and only start the intake action after the turret
        //        action is done.

        if (sub_.getTurret().getAction().isDone()) {
            sub_.getIntake().setAction(intake_on_act_) ;
            setDone() ;
        }

    }

    @Override
    public void cancel() {
        super.cancel() ;

        if (sub_.getIntake().isBusy()) {
            sub_.getIntake().cancelAction();
        }

        if (sub_.getTurret().isBusy()) {
            sub_.getTurret().cancelAction();
        }

    }

    @Override
    public String toString(int indent) {
        // Butch: since we added a desired
        return prefix(indent) + "StartIntakeAction" ;
    }

}
