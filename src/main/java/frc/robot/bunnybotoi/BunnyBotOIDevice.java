package frc.robot.bunnybotoi;

import org.xero1425.base.actions.Action;
import org.xero1425.base.actions.InvalidActionRequest;
import org.xero1425.base.actions.SequenceAction;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.motorsubsystem.MotorPowerSequenceAction;
import org.xero1425.base.oi.OISubsystem;
import org.xero1425.base.oi.OIPanel;
import org.xero1425.base.oi.OIPanelButton;
import org.xero1425.base.oi.Gamepad;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MissingParameterException;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.bunnybotsubsystem.BunnyBotSubsystem;
import frc.robot.conveyor.ConveyorSubsystem;
import frc.robot.intake.IntakePowerAction;
import frc.robot.intake.IntakePowerSequenceAction;
import frc.robot.intake.IntakeSubsystem;
import frc.robot.water.WaterSubsystem;

public class BunnyBotOIDevice extends OIPanel {

    private Gamepad gamepad_ ;

    private int intake_dir_ ;
    private int intake_on_ ;
    private int intake_off_ ;

    private int conveyor_dump_on_ ;
    private int conveyor_dump_off_ ;
    private int conveyor_reverse_on_ ;
    private int conveyor_reverse_off_ ;

    private int automode_ ;

    private Action intake_off_action_ ;
    private Action intake_collect_action_ ;
    private Action intake_reverse_action_ ;

    private Action conveyor_close_gate_action_ ;
    private Action conveyor_off_action_ ;
    private Action conveyor_dump_action_ ;
    private Action conveyor_reverse_action_ ;
    
    private Action water_on_action_ ;
    private Action water_off_action_ ;

    private boolean first ;

    private MessageLogger logger_ ;
    
    public BunnyBotOIDevice(OISubsystem sub, String name, int index, MessageLogger logger, Gamepad gamepad)
            throws BadParameterTypeException, MissingParameterException {
        super(sub, name, index) ;

        gamepad_ = gamepad ;
        logger_ = logger ;
    
        initializeGadgets();

        first = true ;
    }

    @Override
    public int getAutoModeSelector() {
        
        DriverStation ds = DriverStation.getInstance() ;
        String debug = "Axis:" ;
        for(int i = 6 ; i < 7 ; i++)
        {
            double val = ds.getStickAxis(getIndex(), i) ;
            debug += " " ;
            debug += Double.toString(val) ;
        }
        System.out.println(debug) ;

        int v = getValue(automode_) ;
        return v ;
    }

    public void createStaticActions() throws Exception {

        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getConveyorSubsystem() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;
        WaterSubsystem water = getBunnyBotSubsystem().getWater() ;

        double [] ctimes = { 0.2, 1000000} ;
        double [] cpowers = { -0.5, -0.1} ;
        conveyor_close_gate_action_ = new MotorPowerSequenceAction(conveyor, ctimes, cpowers) ;
        conveyor_off_action_ = new MotorPowerAction(conveyor, 0.0) ;
        conveyor_reverse_action_ = new MotorPowerAction(conveyor, "reverse:power") ;

        double [] times = { 0.2, 0.2, 1000000} ;
        double [] powers = { 0.2, 0.4, 0.6} ;
        conveyor_dump_action_ = new MotorPowerSequenceAction(conveyor, times, powers) ;

        double [] itimes = { 0.2, 0.2, 10000000} ;
        double [] lpowers = { 0.2, 0.4, 0.6 } ;
        double [] upowers = { 0.2, 0.4, 0.6} ;
        intake_collect_action_ = new IntakePowerSequenceAction(intake, itimes, lpowers, upowers) ;
        intake_off_action_ = new IntakePowerAction(logger_, intake, 0.0, 0.0) ;
        intake_reverse_action_ = new IntakePowerAction(logger_, intake, "reverse:lower:power", "reverse:upper:power") ;

        water_on_action_ = new MotorPowerAction(water, "teleop:power") ;
        water_off_action_ = new MotorPowerAction(water, 0.0) ;
    }

    private BunnyBotSubsystem getBunnyBotSubsystem() {
        return (BunnyBotSubsystem) getSubsystem().getRobot().getRobotSubsystem();
    }

    @Override
    public void generateActions(SequenceAction seq) throws InvalidActionRequest {
        
        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getConveyorSubsystem() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;
        WaterSubsystem water = getBunnyBotSubsystem().getWater() ;

        //
        // Conveyor, Intake, and water are the 2 subsystems here
        //  -- Conveyor can either be stopped, going right, or going left
        //  -- Intake can either run ON or OFF and can be set to eject mode
        //  -- Water can either be shooting or off
        // 


        /// CONVEYOR
        if (first)
        {
            seq.addSubActionPair(conveyor, conveyor_close_gate_action_, false) ;
            first = false ;
        }
        else if (getValue(conveyor_dump_off_) == 1) {
            //
            // So, first priority, if the conveyor_right button was released, we stop the conveyor
            //            
            seq.addSubActionPair(conveyor, conveyor_close_gate_action_, false) ;
        }
        else if (getValue(conveyor_dump_on_) == 1) {
            //
            // If we are here, the conveyor right-deposit button was pressed, so we assign the action
            // * Right always takes priority over left
            //
            seq.addSubActionPair(conveyor, conveyor_dump_action_, false) ;
        }
        else if (getValue(conveyor_reverse_off_) == 1)
        {
            seq.addSubActionPair(conveyor, conveyor_off_action_, false) ;
        }
        else if (getValue(conveyor_reverse_on_) == 1) 
        {
            seq.addSubActionPair(conveyor, conveyor_reverse_action_, false) ;
        }

        /// INTAKE
        //
        // This is a new if because the intake is controlled independently of the GPM.
        //
        if (getValue(intake_off_) == 1) {
            seq.addSubActionPair(intake, intake_off_action_, false) ;
        }
        else if (getValue(intake_on_) == 1) {
            if (getValue(intake_dir_) == 1)
                seq.addSubActionPair(intake, intake_collect_action_, false);
            else
                seq.addSubActionPair(intake, intake_reverse_action_, false);
        }

        /// WATER
        if (gamepad_ != null) {
            if (gamepad_.isRTriggerPressed()) {
                if (!water.isRunning()) {
                    seq.addSubActionPair(water, water_on_action_, false);
                }
            }
            else {
                if (water.isRunning()) {
                    seq.addSubActionPair(water, water_off_action_, false);
                }            
            }
        }
    }

    private void initializeGadgets() throws BadParameterTypeException, MissingParameterException {
        int num;

        // Automode switch
        num = getSubsystem().getSettingsValue("oi:gadgets:axis:automode").getInteger() ;
        Double [] map = { -0.9, -0.75, -0.5, -0.25, 0.0, 0.2, 0.4, 0.6, 0.8, 1.0 } ;
        automode_ = mapAxisScale(num, map) ;
       
        // intake on/off switch
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:intake_on").getInteger() ;
        intake_on_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ;

        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:intake_off").getInteger() ;
        intake_off_ = mapButton(num, OIPanelButton.ButtonType.HighToLow) ;

        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:intake_dir").getInteger() ;
        intake_dir_ = mapButton(num, OIPanelButton.ButtonType.LevelInv) ;
        
        // // conveyor right and left deploy  + stop buttons
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:conveyor_dump_on").getInteger() ;
        conveyor_dump_on_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ;

        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:conveyor_dump_off").getInteger() ;
        conveyor_dump_off_ = mapButton(num, OIPanelButton.ButtonType.HighToLow) ;
        
        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:conveyor_reverse_on").getInteger() ;
        conveyor_reverse_on_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ;

        num = getSubsystem().getSettingsValue("oi:gadgets:buttons:conveyor_reverse_off").getInteger() ;
        conveyor_reverse_off_ = mapButton(num, OIPanelButton.ButtonType.HighToLow) ;
    }
}
