package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;

import frc.robot.gamepiecemanipulator.intake.IntakeCollectAction;
import frc.robot.gamepiecemanipulator.turret.TurretSubsystem;

public class StartIntakeAction extends Action {

    public StartIntakeAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger());
        sub_ = gpm;
        
        intake_on_act_ = new IntakeCollectAction(gpm.getIntake());
        turret_ = new TurretSubsystem(gpm);
 
    }

    @Override
    public void start() throws Exception {
        super.start() ;

        if (turret_.getTurretAngle() < 90 || turret_.getTurretAngle() > 90) {
            sub_.getIntake().setAction(intake_on_act_, true); 
        }  
    }

    @Override
    public void run() throws Exception {
        super.run() ;
    }

    @Override
    public void cancel() {
        super.cancel() ;

        if (sub_.getIntake().isBusy()) {
            sub_.getIntake().cancelAction();
        }
    }

    @Override
    public String toString(int indent) {
        return prefix(indent) + "StartIntakeAction" ;
    }

    private GamePieceManipulatorSubsystem sub_ ;
    private IntakeCollectAction intake_on_act_ ;
    private TurretSubsystem turret_;
 
}
