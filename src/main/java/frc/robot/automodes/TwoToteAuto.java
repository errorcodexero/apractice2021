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
import frc.robot.water.WaterSubsystem;

public class TwoToteAuto extends BunnyBotAutoMode {

    private Action conveyor_deploy_right_action_ ;
    private Action conveyor_close_gate_action_ ; 
    private Action water_squirt_action_ ;
    private Action water_prime_action_ ;

    public TwoToteAuto(BunnyBotAutoController ctrl, String name, boolean onetote, String path1, String path2, String delay, String delay_close_gate) 
            throws Exception {
        super(ctrl, name) ;

        MessageLogger logger = ctrl.getRobot().getMessageLogger() ;

        TankDriveSubsystem db = getBunnyBotSubsystem().getTankDrive() ;
        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getConveyorSubsystem() ; 
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;
        WaterSubsystem water = getBunnyBotSubsystem().getWater() ;

        double [] times = new double[] { 0.2, 0.2, 1.2} ;
        double [] powers = new double[] { 0.2, 0.4, 0.75} ;
        conveyor_deploy_right_action_ = new MotorPowerSequenceAction(conveyor, times, powers);

        conveyor_close_gate_action_ = new MotorPowerAction(conveyor, "closegate:power", "closegate:delay") ;

        water_squirt_action_ = new MotorPowerAction(water, "automode:power", "automode:delay") ;
        water_prime_action_ = new MotorPowerAction(water, "prime:power", "prime:delay") ;
       
        
        //
        // Drive the first path
        //
        
        ParallelAction primewater = new ParallelAction(logger, DonePolicy.All) ;
        // first path
        primewater.addSubActionPair(db, new TankDrivePathFollowerAction(db, path1, false), true) ;
        // prime water by starting pump
        primewater.addSubActionPair(water, water_prime_action_, true) ;
        addAction(primewater) ;

        //
        //first tote----
        //

        ParallelAction convnwater = new ParallelAction(logger, DonePolicy.All) ;
        // Dump in 1st tote
        convnwater.addSubActionPair(conveyor, conveyor_deploy_right_action_, true) ;
        // water in 1st tote
        convnwater.addSubActionPair(water, water_squirt_action_, true) ;
        addAction(convnwater) ;

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
            sa.addSubActionPair(intake, new IntakePowerAction(logger, intake, "auto:power", "auto:power"), false);
            sa.addAction(new DelayAction(ctrl.getRobot(), 1.0)) ;
            sa.addSubActionPair(intake, new IntakePowerAction(logger, intake, 0.0, 0.0), false);

            pa.addAction(sa) ;
            pa.addSubActionPair(db, new TankDrivePathFollowerAction(db, path2, false), true) ;

            addAction(pa) ;

            addAction(convnwater) ;

            // //
            // // Dump in 2nd tote
            // //
            // addSubActionPair(conveyor, conveyor_close_gate_action_, true) ;
        }
    }
}
