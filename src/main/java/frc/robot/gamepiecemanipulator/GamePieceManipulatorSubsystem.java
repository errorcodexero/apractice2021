package frc.robot.gamepiecemanipulator;

import org.xero1425.base.Subsystem;

import frc.robot.gamepiecemanipulator.intake.IntakeSubsystem;
import frc.robot.gamepiecemanipulator.turret.TurretSubsystem;


public class GamePieceManipulatorSubsystem extends Subsystem {
    
    public static final String SubsystemName = "gamepiecemanipulator" ;
    public GamePieceManipulatorSubsystem(Subsystem parent) throws Exception {
        super(parent, SubsystemName) ;
        
        intake_ = new IntakeSubsystem(this) ;
        addChild(intake_) ;

        turret_ = new TurretSubsystem(this) ;
        addChild(turret_) ;

    }

    
    public IntakeSubsystem getIntake() {
        return intake_ ;
    }

    
    public TurretSubsystem getTurret() {
        return turret_ ;
    }

    @Override
    public void run() throws Exception {
        super.run() ;
    }

    private IntakeSubsystem intake_ ;
    private TurretSubsystem turret_ ;
}
