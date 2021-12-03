package frc.robot.automodes;

import org.xero1425.base.controllers.TestAutoMode;
import org.xero1425.base.tankdrive.TankDrivePathFollowerAction;
import org.xero1425.base.tankdrive.TankDrivePowerAction;
// import org.xero1425.base.tankdrive.TankDriveRamseteAction;
import org.xero1425.base.tankdrive.TankDriveSubsystem;
import frc.robot.bunnybotsubsystem.BunnyBotSubsystem;

public class BunnyBotTestAutoMode extends TestAutoMode {
    public BunnyBotTestAutoMode(BunnyBotAutoController ctrl) throws Exception {
        super(ctrl, "Bunny2021-Test-Mode");

        BunnyBotSubsystem bunnybot = (BunnyBotSubsystem) ctrl.getRobot().getRobotSubsystem();
        TankDriveSubsystem db = bunnybot.getTankDrive();

        switch (getTestNumber()) {
            //
            // Numbers 0 - 9 are for the driverbase
            //
            case 0:     // Drive straight, used to test and get Kv number
                addSubActionPair(db, new TankDrivePowerAction(db, getPower(), getPower(), getDuration()), true);
                break;

            case 1:     // Test the path follower
                addSubActionPair(db, new TankDrivePathFollowerAction(db, getNameParam(), false), true) ;
                break ;          
                
            case 2:
                // addSubActionPair(db, new TankDriveRamseteAction(db, getNameParam(), false), true);
                break ;

            case 3:     // Drive left db motors
                addSubActionPair(db, new TankDrivePowerAction(db, getPower(), 0.0, getDuration()), true);
                break;

            case 4:     // Drive right db motors
                addSubActionPair(db, new TankDrivePowerAction(db, 0.0, getPower(), getDuration()), true);
                break;
        }
    }
}