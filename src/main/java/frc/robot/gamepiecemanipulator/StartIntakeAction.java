package frc.robot.gamepiecemanipulator;

import org.xero1425.base.actions.Action;
import org.xero1425.misc.MessageLogger;

import frc.robot.gamepiecemanipulator.intake.IntakeCollectAction;
import frc.robot.gamepiecemanipulator.turret.TurretSubsystem;

public class StartIntakeAction extends Action {

    public StartIntakeAction(GamePieceManipulatorSubsystem gpm) throws Exception {
        super(gpm.getRobot().getMessageLogger());
        
        intake_on_ = new IntakeCollectAction(gpm.getIntake());
        turret_angle_ = sub_.getTurret().getTurretAngle();
    }

    @Override
    public void start() throws Exception {
        super.start() ;

        if (turret_angle_ < 90 || turret_angle_ > 90) {
            sub_.getIntake().setAction(intake_on_, true); 
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
    private IntakeCollectAction intake_on_ ;
    private double turret_angle_;
 
}
