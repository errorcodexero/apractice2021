package frc.robot.automodes;

import org.xero1425.base.actions.Action;
import org.xero1425.base.actions.DelayAction;
import org.xero1425.base.actions.ParallelAction;
import org.xero1425.base.actions.SequenceAction;
import org.xero1425.base.actions.ParallelAction.DonePolicy;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.motorsubsystem.MotorPowerSequenceAction;
import org.xero1425.base.tankdrive.TankDrivePathFollowerAction;
import org.xero1425.base.tankdrive.TankDriveSubsystem;
import org.xero1425.misc.MessageLogger;

import frc.robot.conveyor.ConveyorSubsystem;
import frc.robot.intake.IntakePowerAction;
import frc.robot.intake.IntakeSubsystem;

public class TwoToteAuto extends BunnyBotAutoMode {

    private Action conveyor_close_gate_action_ ; 

    public TwoToteAuto(BunnyBotAutoController ctrl, String name, boolean onetote, String path1, String path2, String delay, String delay_close_gate) 
            throws Exception {
        super(ctrl, name) ;

        MessageLogger logger = ctrl.getRobot().getMessageLogger() ;

        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getConveyorSubsystem() ; 
        TankDriveSubsystem db = getBunnyBotSubsystem().getTankDrive() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;

        double [] times = new double[] { 0.2, 0.2, 1.2} ;
        double [] powers = new double[] { 0.2, 0.4, 0.75} ;
        conveyor_close_gate_action_ = new MotorPowerSequenceAction(conveyor, times, powers);

        //
        // Drive the first path
        //
        addSubActionPair(db, new TankDrivePathFollowerAction(db, path1, false), true) ;

        //
        // Dump in 1st tote
        //
        addSubActionPair(conveyor, conveyor_close_gate_action_, true) ;

        if (onetote) {
            addSubActionPair(conveyor, conveyor_close_gate_action_, true) ;            
        }
        else {
            //
            // Close the gate
            //
            ParallelAction pa = new ParallelAction(logger, DonePolicy.All) ;
            SequenceAction sa = new SequenceAction(logger) ;

            //
            // Turn on the intake
            //
            sa = new SequenceAction(logger) ;
            sa.addSubActionPair(conveyor, conveyor_close_gate_action_, true) ;
            sa.addSubActionPair(intake, new IntakePowerAction(logger, intake, 0.0, 0.5), false);
            sa.addAction(new DelayAction(ctrl.getRobot(), 1.0)) ;
            sa.addSubActionPair(intake, new IntakePowerAction(logger, intake, 0.0, 0.0), false);

            pa.addAction(sa) ;
            pa.addSubActionPair(db, new TankDrivePathFollowerAction(db, path2, false), true) ;

            addAction(pa);

            //
            // Dump in 2nd tote
            //
            addSubActionPair(conveyor, conveyor_close_gate_action_, true) ;
        }
    }
}
