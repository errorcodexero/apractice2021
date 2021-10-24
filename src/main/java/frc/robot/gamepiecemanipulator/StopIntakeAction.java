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

        sub_.getIntake().setAction(intake_off_act_, true); 
    }

    @Override
    public void run() throws Exception {
        super.run() ;
        
        sub_.getIntake().setAction(intake_off_act_); // turret won't move in this case...
        sub_.getTurret().setAction(turret_act_);
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
