package frc.robot.practbotsubsystem; //directory path from "java"

import org.xero1425.base.RobotSubsystem;
import org.xero1425.base.XeroRobot;
import org.xero1425.base.tankdrive.TankDriveSubsystem;

public class PractBotSubsystem extends RobotSubsystem {
    public final static String SubsystemName = "practbot" ;
    public final static String TankdriveSubsystemName = "tankdrive" ;
    private TankDriveSubsystem db_ ;

    public PractBotSubsystem(XeroRobot robot) throws Exception {
        super(robot, SubsystemName) ;

        db_ = new TankDriveSubsystem(this, TankdriveSubsystemName, "tankdrive") ;
        addChild(db_) ;
    }

    public TankDriveSubsystem getTankDrive() {
        return db_ ;
    }

    // public PractBotOISubsystem getOI() {
    //     return oi_ ;
    // }

}