package frc.robot.automodes;

import org.xero1425.base.actions.Action;
import org.xero1425.base.actions.DelayAction;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.tankdrive.TankDrivePathFollowerAction;
import org.xero1425.base.tankdrive.TankDriveSubsystem;
import org.xero1425.misc.MessageLogger;

import frc.robot.conveyor.ConveyorSubsystem;
import frc.robot.intake.IntakePowerAction;
import frc.robot.intake.IntakeSubsystem;

public class TwoToteAuto extends BunnyBotAutoMode {

    private Action conveyor_deploy_right_action_ ;
    private Action conveyor_close_gate_action_ ; 

    public TwoToteAuto(BunnyBotAutoController ctrl, String name, String path1, String path2, String delay, String delay_close_gate, MessageLogger logger) 
            throws Exception {
        super(ctrl, "TwoToteAuto") ;

        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getConveyorSubsystem() ; 
        TankDriveSubsystem db = getBunnyBotSubsystem().getTankDrive() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;
        IntakePowerAction intake_on_act_ = new IntakePowerAction(logger, intake, "on:lower:power", "on:upper:power") ;

        conveyor_deploy_right_action_ = new MotorPowerAction(conveyor, "dump:power") ;
        conveyor_close_gate_action_ = new MotorPowerAction(conveyor, "closegate:power", "closegate:delay") ;

        //turn on intake
        addSubActionPair(intake, intake_on_act_, false) ;
        //drive 1st path
        addSubActionPair(db, new TankDrivePathFollowerAction(db, path1, false), true) ;
        //deposit in 1st tote
        addSubActionPair(conveyor, conveyor_deploy_right_action_, false) ;
        addAction(new DelayAction(getAutoController().getRobot(), delay)) ;
        addSubActionPair(conveyor, conveyor_close_gate_action_, false) ;

        //drive 2nd path
        addSubActionPair(db, new TankDrivePathFollowerAction(db, path2, false), true) ;
        //deposit in 2nd tote
        addSubActionPair(conveyor, conveyor_deploy_right_action_, false) ;
        addAction(new DelayAction(getAutoController().getRobot(), delay)) ;
        addSubActionPair(conveyor, conveyor_close_gate_action_, false) ;
    }
}
