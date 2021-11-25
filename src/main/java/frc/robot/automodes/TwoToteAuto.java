package frc.robot.automodes;

import org.xero1425.base.actions.DelayAction;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.tankdrive.TankDrivePathFollowerAction;
import org.xero1425.base.tankdrive.TankDriveSubsystem;

import frc.robot.gamepiecemanipulator.DepositAction;
import frc.robot.gamepiecemanipulator.GamePieceManipulatorSubsystem;
import frc.robot.gamepiecemanipulator.StopAction;
import frc.robot.intake.IntakeSubsystem;

public class TwoToteAuto extends BunnyBotAutoMode {
    public TwoToteAuto(BunnyBotAutoController ctrl, String name, String path1, String path2, String delay) 
            throws Exception {
        super(ctrl, "TwoToteAuto") ;

        GamePieceManipulatorSubsystem gpm = getBunnyBotSubsystem().getGamePieceManipulator() ; 
        TankDriveSubsystem db = getBunnyBotSubsystem().getTankDrive() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;
        MotorPowerAction intake_on_act_ = new MotorPowerAction(intake, "motor:on:power") ;

        addSubActionPair(intake, intake_on_act_, false) ;
        addSubActionPair(db, new TankDrivePathFollowerAction(db, path1, false), true) ;
        addSubActionPair(gpm, new DepositAction(gpm), false) ;
        addAction(new DelayAction(getAutoController().getRobot(), delay)) ;
        addSubActionPair(gpm, new StopAction(gpm), false) ;
        addSubActionPair(db, new TankDrivePathFollowerAction(db, path2, false), true) ;
    }
}
