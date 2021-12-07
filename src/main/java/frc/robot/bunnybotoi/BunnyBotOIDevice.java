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

    private int conveyor_right_ ;
    private int conveyor_left_ ;
    private int conveyor_r_stop_ ;
    private int conveyor_l_stop_ ;

    private int eject_true_ ;

    private int water_squirt_ ;
    private int water_stop_ ;

    private int automode_ ;

    private Action intake_on_action_ ;
    private Action intake_off_action_ ;
    private Action intake_eject_action_ ;

    private Action conveyor_right_deposit_ ;
    private Action conveyor_stop_action_ ;    
    private Action conveyor_left_deposit_ ;

    // TODO: fix these water actions
    private Action water_squirt_action_ ;
    private Action water_stop_action_ ;

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

        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getConveyorSubsystem() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;

        conveyor_stop_action_ = new MotorPowerAction(conveyor, 0.0) ; //"motor:off:power"
        conveyor_right_deposit_ = new MotorPowerAction(conveyor, "motor:right:power") ;
        conveyor_left_deposit_ = new MotorPowerAction(conveyor, "motor:left:power") ; 

        intake_off_action_ = new MotorPowerAction(intake, 0.0) ; //"motor:off:power"
        intake_on_action_ = new MotorPowerAction(intake, "motor:on:power") ;
        intake_eject_action_ = new MotorPowerAction(intake, "motor:eject:power") ;

        // TODO: water actions in OI
        // water_squirt_action_ = ...
        // water_stop_action_ = ...

    }

    private BunnyBotSubsystem getBunnyBotSubsystem() {
        return (BunnyBotSubsystem) getSubsystem().getRobot().getRobotSubsystem();
    }

    @Override
    public void generateActions(SequenceAction seq) throws InvalidActionRequest {
        int ejvalue = getValue(eject_true_) ;
        boolean eject_mode = (ejvalue == 1) ;

        //
        // Conveyor, Intake, and water are the 2 subsystems here
        //  -- Conveyor can either be stopped, going right, or going left
        //  -- Intake can either run ON or OFF and can be set to eject mode
        //  -- Water can either be shooting or off
        // 

        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getConveyorSubsystem() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;

        /// CONVEYOR
        if (getValue(conveyor_l_stop_) == 1) {
            //
            // So, first priority, if the conveyor_right button was released, we stop the conveyor
            //            
            seq.addSubActionPair(conveyor, conveyor_stop_action_, false) ;
        }
        else if (getValue(conveyor_r_stop_) == 1) {
            //
            // So, second-first priority, if the conveyor_right button was released, we stop the conveyor
            //            
            seq.addSubActionPair(conveyor, conveyor_stop_action_, false) ;
        }
        else if (getValue(conveyor_right_) == 1) {
            //
            // If we are here, the conveyor right-deposit button was pressed, so we assign the action
            // * Right always takes priority over left
            //
            seq.addSubActionPair(conveyor, conveyor_right_deposit_, false) ;
        } 
        else if (getValue(conveyor_left_) == 1) {
            //
            // If we are here, the conveyor left-deposit button was pressed, so we assign the action
            // * Left takes 2nd priority with respect to right, if pressed at the same time, then 
            //    right-deposit will run
            //
            seq.addSubActionPair(conveyor, conveyor_left_deposit_, false) ;
        }

        /// INTAKE
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

        // WATER SHOOTER
        if (getValue(water_squirt_) == 1) {
            
            //turn on water shooter for as long as pressed

            // TODO: seq.addSubActionPair(sub, act, block);
        } 
        else if (getValue(water_stop_) == 1) {

            //turn off water

            // TODO: seq.addSubActionPair(sub, act, block);
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
        
        // // conveyor right and left deploy  + stop buttons
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:conveyor_right_mode").getInteger() ;
        conveyor_right_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ;

        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:conveyor_r_stop_mode").getInteger() ;
        conveyor_r_stop_ = mapButton(num, OIPanelButton.ButtonType.HighToLow) ;
        
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:conveyor_left_mode").getInteger() ;
        conveyor_left_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ;

        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:conveyor_l_stop_mode").getInteger() ;
        conveyor_l_stop_ = mapButton(num, OIPanelButton.ButtonType.HighToLow) ;

        // "eject mode" switch - what intake/gpm does if it's in eject mode
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:eject_true").getInteger() ;
        eject_true_ = mapButton(num, OIPanelButton.ButtonType.Level) ;

        // dial for intake velocity tune


        // dial for conveyor velocity tune


        // button for squirt water
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:water_squirt").getInteger() ;
        water_squirt_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ;

        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:water_stop").getInteger() ;
        water_stop_ = mapButton(num, OIPanelButton.ButtonType.HighToLow) ;

    }

}
