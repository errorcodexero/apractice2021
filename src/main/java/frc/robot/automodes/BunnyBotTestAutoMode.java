package frc.robot.automodes;

import org.xero1425.base.actions.DelayAction;
import org.xero1425.base.controllers.TestAutoMode;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.tankdrive.TankDrivePathFollowerAction;
import org.xero1425.base.tankdrive.TankDrivePowerAction;
// import org.xero1425.base.tankdrive.TankDriveRamseteAction;
import org.xero1425.base.tankdrive.TankDriveSubsystem;
import org.xero1425.misc.MessageLogger;

import frc.robot.bunnybotsubsystem.BunnyBotSubsystem;
import frc.robot.conveyor.ConveyorSubsystem;
import frc.robot.intake.IntakePowerAction;
import frc.robot.intake.IntakeSubsystem;

public class BunnyBotTestAutoMode extends TestAutoMode {
    public BunnyBotTestAutoMode(BunnyBotAutoController ctrl) throws Exception {
        super(ctrl, "Bunny2021-Test-Mode");

        BunnyBotSubsystem bunnybot = (BunnyBotSubsystem) ctrl.getRobot().getRobotSubsystem();
        TankDriveSubsystem db = bunnybot.getTankDrive();
        IntakeSubsystem intake = bunnybot.getIntake() ;
        ConveyorSubsystem conveyor = bunnybot.getConveyorSubsystem() ;
        MessageLogger logger = ctrl.getRobot().getMessageLogger() ;

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

            case 5:         // Run the path follower to follow a named path
                addSubActionPair(db, new TankDrivePathFollowerAction(db, getNameParam(), false), true) ;
                break ;                

            case 10:    // Run lower roller
                addSubActionPair(intake, new IntakePowerAction(logger, intake, getPower(), 0), true);
                addAction(new DelayAction(ctrl.getRobot(), getDuration())) ;
                addSubActionPair(intake, new IntakePowerAction(logger, intake, 0.0, 0.0), true);
                break ;

            case 11:    // Run upper intake
                addSubActionPair(intake, new IntakePowerAction(logger, intake, 0, getPower()), true);
                addAction(new DelayAction(ctrl.getRobot(), getDuration())) ;
                addSubActionPair(intake, new IntakePowerAction(logger, intake, 0.0, 0.0), true);
                break ;

            case 12:    // Run both intake motors
                addSubActionPair(intake, new IntakePowerAction(logger, intake, getPower(), getPower()), true);
                addAction(new DelayAction(ctrl.getRobot(), getDuration())) ;
                addSubActionPair(intake, new IntakePowerAction(logger, intake, 0.0, 0.0), true);
                break ;                

            case 20:    // Power action for conveyor
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, getPower(), getDuration()), true) ;
                break ;

            case 21:    // Simulate auto mode dump
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, 0.2, 0.2), true) ;
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, 0.4, 0.2), true) ;
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, 0.75, 1.2), true) ;
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, -0.5, 0.2), true) ;
                addAction(new DelayAction(ctrl.getRobot(), 1.0)) ;
                addSubActionPair(intake, new IntakePowerAction(logger, intake, 0, 0.5), true);
                addAction(new DelayAction(ctrl.getRobot(), 1.5)) ;
                addSubActionPair(intake, new IntakePowerAction(logger, intake, 0, 0), true);
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, 0.2, 0.2), true) ;
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, 0.4, 0.2), true) ;
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, 0.75, 1.2), true) ;
                break ;

            case 22:    // Simulate teleop dump   
                addSubActionPair(intake, new IntakePowerAction(logger, intake, 0, 0.6), false);
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, 0.2, 0.2), true) ;
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, 0.4, 0.2), true) ;
                addSubActionPair(conveyor, new MotorPowerAction(conveyor, 0.6, 5.0), true) ;
                break ;            
        }
    }
}