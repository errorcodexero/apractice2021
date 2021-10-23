package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;
import org.xero1425.misc.MessageLogger;

import frc.robot.gamepiecemanipulator.intake.IntakeOffAction;
import frc.robot.gamepiecemanipulator.turret.TurretTurnAction;

public class StopIntakeAction extends Action {

    public StopIntakeAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger());

        intake_off_ = new IntakeOffAction(gpm.getIntake());
        turret_ = new TurretTurnAction(gpm.getTurret(), 0.0);

    }


    @Override
    public void start() throws Exception {
        super.start() ;

        sub_.getIntake().setAction(intake_off_, true); 
    }

    @Override
    public void run() throws Exception {
        super.run() ;
        
        sub_.getTurret().setTurretPower(0.0); // turret won't move in this case...
        //use while

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
    private IntakeOffAction intake_off_ ;
    private TurretTurnAction turret_ ;


}
