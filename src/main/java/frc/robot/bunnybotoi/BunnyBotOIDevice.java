package frc.robot.bunnybotoi;

import org.xero1425.base.actions.Action;
import org.xero1425.base.actions.InvalidActionRequest;
import org.xero1425.base.actions.SequenceAction;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.oi.OISubsystem;
import org.xero1425.base.oi.OIPanel;
import org.xero1425.base.oi.OIPanelButton;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

import frc.robot.bunnybotsubsystem.BunnyBotSubsystem;
import frc.robot.conveyor.ConveyorSubsystem;
import frc.robot.intake.IntakeSubsystem;

public class BunnyBotOIDevice extends OIPanel {

    private int intake_on_ ;

    private int conveyor_deposit_ ;
    private int conveyor_stop_ ;

    private int eject_true_ ;

    private int automode_ ;

    private Action intake_on_action_ ;
    private Action intake_off_action_ ;
    private Action intake_eject_action_ ;

    private Action conveyor_deposit_action_ ;
    private Action conveyor_stop_action_ ; 
    private Action conveyor_eject_action_ ;

    private boolean prev_eject_mode_ ;
    
    public BunnyBotOIDevice(OISubsystem sub, String name, int index)
            throws BadParameterTypeException, MissingParameterException {
        super(sub, name, index);

        initializeGadgets();
        prev_eject_mode_ = false ;
    }

    @Override
    public int getAutoModeSelector() {
        int v = getValue(automode_) ;
        return v ;
    }

    public void createStaticActions() throws Exception {

        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getGamePieceManipulator() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;

        // TODO: add a deposit right and deposit left action instead of "deposit vs eject"
        conveyor_deposit_action_ = new MotorPowerAction(conveyor, "motor:right:power") ;
        conveyor_stop_action_ = new MotorPowerAction(conveyor, 0.0) ;
        conveyor_eject_action_ = new MotorPowerAction(conveyor, "motor:left:power") ;

        intake_on_action_ = new MotorPowerAction(intake, "motor:on:power") ;
        intake_off_action_ = new MotorPowerAction(intake, 0.0) ;
        intake_eject_action_ = new MotorPowerAction(intake, "motor:eject:power") ;
    }

    private BunnyBotSubsystem getBunnyBotSubsystem() {
        return (BunnyBotSubsystem) getSubsystem().getRobot().getRobotSubsystem();
    }

    @Override
    public void generateActions(SequenceAction seq) throws InvalidActionRequest {
        boolean gpm_set = false ;
        int ejvalue = getValue(eject_true_) ;
        boolean eject_mode = (ejvalue == 1) ;

        //
        // So, the key here is that the gpm_stop_ and gpm_deposit_ buttons are single
        // events and will not assert their value when triggered except for a single
        // event loop.  The intake_on_ and the eject_true_ are level buttons and will
        // assert their values every event loop.  The logic below must do the right
        // things under these conditions. 
        //

        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getGamePieceManipulator() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;

        if (getValue(conveyor_stop_) == 1) {
            //
            // So, first priority, if the gpm_deposit_ button was released, we stop the conveyor
            // and the chute.  Eject does not matter since there is no direction to stopped
            //            
            seq.addSubActionPair(conveyor, conveyor_stop_action_, false) ;
            gpm_set = true ;
        }
        else if (getValue(conveyor_deposit_) == 1) {
            //
            // If we are here, the gpm_deposit_ button was pressed.  The eject button will
            // determine the direction of the conveyor and chute.  Note, since this button
            // type is an edge type (low to  high), it will happen for only one robot loop
            // and we don't need to check if the motor is running before assigning.
            //
            if (!eject_mode) {
                seq.addSubActionPair(conveyor, conveyor_deposit_action_, false) ;
            }
            else {
                seq.addSubActionPair(conveyor, conveyor_eject_action_, false) ;  
            }
            gpm_set = true ;
        }

        //
        // This is a new if because the intake is controlled independently of the GPM.
        //
        if (getValue(intake_on_) == 0) {
            //
            // If the intake switch is off, turn off the intake.
            //
            if (intake.isRunning()) {
                seq.addSubActionPair(intake, intake_off_action_, false) ;
            }
        }  
        else {
            //
            // The intake switch is on, so turn on the intake if it is not running.  The direction will be determined
            // by the eject switch.  We change the intake, if it is currently not running, or if the eject state is
            // different than the previous eject state.
            //
            if (!intake.isRunning() || eject_mode != prev_eject_mode_) {
                if (eject_mode != prev_eject_mode_)
                    System.out.println("Triggered due to reversed eject switch") ;

                if (!eject_mode)
                    seq.addSubActionPair(intake, intake_on_action_, false) ;
                else
                    seq.addSubActionPair(intake, intake_eject_action_, false) ;
            }
        }

        if (eject_mode != prev_eject_mode_)
        {
            //
            // Ok, the eject mode switch has changed.  Since the GPM is edge triggered, it would have
            // only been adjusted by the logic above if the GPM switch also changed in the same robot
            // loop, which is highly unlikely.  This checks for that state and adjusts the gpm.
            //
            if (conveyor.isRunning() && !gpm_set)
            {
                //
                // So the GPM is running but the direction was not reset above, so we need to set
                // the GPM direction here.
                //
                if (!eject_mode) {
                    seq.addSubActionPair(conveyor, conveyor_deposit_action_, false) ;
                }
                else {
                    seq.addSubActionPair(conveyor, conveyor_eject_action_, false) ;  
                }                
            }
        }

        prev_eject_mode_ = eject_mode ;
    }

    private void initializeGadgets() throws BadParameterTypeException, MissingParameterException {
        int num;

        // Automode switch
        num = getSubsystem().getSettingsValue("oi:gadgets:axes:automode").getInteger() ;
        Double [] map = { -0.9, -0.75, -0.5, -0.25, 0.0, 0.2, 0.4, 0.6, 0.8, 1.0 } ;
        automode_ = mapAxisScale(num, map) ;
       
        // intake on/off switch
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:intake_on_mode").getInteger() ;
        intake_on_ = mapButton(num, OIPanelButton.ButtonType.Level) ;
        
        // gpm (conveyor + chute) deploy/stop buttons
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:gpm_deploy_mode").getInteger() ;
        conveyor_deposit_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ;

        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:gpm_stop_mode").getInteger() ;
        conveyor_stop_ = mapButton(num, OIPanelButton.ButtonType.HighToLow) ;

        // "eject mode" switch - what intake/gpm does if it's in eject mode
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:eject_true").getInteger() ;
        eject_true_ = mapButton(num, OIPanelButton.ButtonType.Level) ;

    }

}
