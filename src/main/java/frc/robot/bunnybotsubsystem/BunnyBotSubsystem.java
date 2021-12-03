package frc.robot.bunnybotsubsystem; //directory path from "java"

import org.xero1425.base.RobotSubsystem;
import org.xero1425.base.XeroRobot;
import org.xero1425.base.tankdrive.TankDriveSubsystem;

import frc.robot.bunnybotoi.BunnyBotOISubsystem;
import frc.robot.conveyor.ConveyorSubsystem;
import frc.robot.intake.IntakeSubsystem;

public class BunnyBotSubsystem extends RobotSubsystem {
    public final static String SubsystemName = "bunnybot" ;
    public final static String TankdriveSubsystemName = "tankdrive" ;
    private TankDriveSubsystem db_ ;
    private ConveyorSubsystem conveyor_ ;
    private IntakeSubsystem intake_ ;
    private BunnyBotOISubsystem oi_ ;

    public BunnyBotSubsystem(XeroRobot robot) throws Exception {
        super(robot, SubsystemName) ;

        db_ = new TankDriveSubsystem(this, TankdriveSubsystemName, "tankdrive") ;
        addChild(db_) ;

        conveyor_ = new ConveyorSubsystem(this) ;
        addChild(conveyor_) ;

        intake_ = new IntakeSubsystem(this) ;
        addChild(intake_) ;

        oi_ = new BunnyBotOISubsystem(this, db_) ;
        addChild(oi_) ;
    }

    public TankDriveSubsystem getTankDrive() {
        return db_ ;
    }
    
    public ConveyorSubsystem getGamePieceManipulator() {
        return conveyor_ ;
    }

    public IntakeSubsystem getIntake() {
        return intake_ ;
    }

    public BunnyBotOISubsystem getOI() {
        return oi_ ;
    }

}