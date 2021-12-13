package frc.robot.bunnybotsubsystem; //directory path from "java"

import org.xero1425.base.RobotSubsystem;
import org.xero1425.base.XeroRobot;
import org.xero1425.base.tankdrive.TankDriveSubsystem;

import frc.robot.bunnybotoi.BunnyBotOISubsystem;
import frc.robot.conveyor.ConveyorSubsystem;
import frc.robot.intake.IntakeSubsystem;
import frc.robot.water.WaterSubsystem;

public class BunnyBotSubsystem extends RobotSubsystem {
    public final static String SubsystemName = "bunnybot" ;
    public final static String TankdriveSubsystemName = "tankdrive" ;
    private TankDriveSubsystem db_ ;
    private ConveyorSubsystem conveyor_ ;
    private IntakeSubsystem intake_ ;
    private WaterSubsystem water_ ;
    private BunnyBotOISubsystem oi_ ;

    public BunnyBotSubsystem(XeroRobot robot) throws Exception {
        super(robot, SubsystemName) ;

        db_ = new TankDriveSubsystem(this, TankdriveSubsystemName, "tankdrive") ;
        addChild(db_) ;

        conveyor_ = new ConveyorSubsystem(this) ;
        addChild(conveyor_) ;

        intake_ = new IntakeSubsystem(this) ;
        addChild(intake_) ;

        water_ = new WaterSubsystem(this) ;
        addChild(water_) ;

        oi_ = new BunnyBotOISubsystem(this, db_) ;
        addChild(oi_) ;
    }

    public TankDriveSubsystem getTankDrive() {
        return db_ ;
    }
    
    public ConveyorSubsystem getConveyorSubsystem() {
        return conveyor_ ;
    }

    public IntakeSubsystem getIntake() {
        return intake_ ;
    }

    public WaterSubsystem getWater() {
        return water_ ;
    }

    public BunnyBotOISubsystem getOI() {
        return oi_ ;
    }

}