package frc.robot.automodes;

import org.xero1425.base.actions.Action;
import org.xero1425.base.actions.DelayAction;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.tankdrive.TankDrivePathFollowerAction;
import org.xero1425.base.tankdrive.TankDriveSubsystem;

import frc.robot.conveyor.ConveyorSubsystem;
import frc.robot.intake.IntakeSubsystem;

public class TwoToteAuto extends BunnyBotAutoMode {

    private Action conveyor_deposit_action_ ;
    private Action conveyor_stop_action_ ; 


    public TwoToteAuto(BunnyBotAutoController ctrl, String name, String path1, String path2, String delay) 
            throws Exception {
        super(ctrl, "TwoToteAuto") ;

        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getGamePieceManipulator() ; 
        TankDriveSubsystem db = getBunnyBotSubsystem().getTankDrive() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;
        MotorPowerAction intake_on_act_ = new MotorPowerAction(intake, "motor:on:power") ;

        conveyor_deposit_action_ = new MotorPowerAction(conveyor, "motor:on:power") ;
        conveyor_stop_action_ = new MotorPowerAction(conveyor, 0.0) ;

        //turn on intake
        addSubActionPair(intake, intake_on_act_, false) ;
        //drive 1st path
        addSubActionPair(db, new TankDrivePathFollowerAction(db, path1, false), true) ;
        //deposit in 1st tote
        addSubActionPair(conveyor, conveyor_deposit_action_, false) ;
        addAction(new DelayAction(getAutoController().getRobot(), delay)) ;
        addSubActionPair(conveyor, conveyor_stop_action_, false) ;
        //drive 2nd path
        addSubActionPair(db, new TankDrivePathFollowerAction(db, path2, false), true) ;
        //deposit in 2nd tote
        addSubActionPair(conveyor, conveyor_deposit_action_, false) ;
        addAction(new DelayAction(getAutoController().getRobot(), delay)) ;
        addSubActionPair(conveyor, conveyor_stop_action_, false) ;
    }
}
