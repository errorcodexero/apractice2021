package frc.robot.bunnybotoi;

import org.xero1425.base.actions.Action;
import org.xero1425.base.actions.InvalidActionRequest;
import org.xero1425.base.actions.SequenceAction;
import org.xero1425.base.motorsubsystem.MotorPowerAction;
import org.xero1425.base.oi.OISubsystem;
import org.xero1425.base.oi.OIPanel;
import org.xero1425.base.oi.OIPanelButton;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MissingParameterException;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.bunnybotsubsystem.BunnyBotSubsystem;
import frc.robot.conveyor.ConveyorSubsystem;
import frc.robot.intake.IntakePowerAction;
import frc.robot.intake.IntakeSubsystem;

public class BunnyBotOIDevice extends OIPanel {

    private int intake_dir_ ;
    private int intake_on_ ;
    private int intake_off_ ;

    private int conveyor_dump_on_ ;
    private int conveyor_dump_off_ ;
    private int conveyor_reverse_on_ ;
    private int conveyor_reverse_off_ ;

    private int automode_ ;

    private Action intake_off_action_ ;
    private Action conveyor_close_gate_action_ ;
    private Action conveyor_off_action_ ;

    private int conveyor_vel_axis_ ;
    private int intake_vel_axis_ ;
    private double conveyor_vel_value_ ;
    private double intake_vel_value_ ;
    

    private MessageLogger logger_ ;
    
    public BunnyBotOIDevice(OISubsystem sub, String name, int index, MessageLogger logger)
            throws BadParameterTypeException, MissingParameterException {
        super(sub, name, index) ;

        initializeGadgets();

        logger_ = logger ;

        conveyor_vel_value_ = 0 ;
        intake_vel_value_ = 0 ;

        conveyor_vel_axis_ = sub.getSettingsValue("oi:axis:conveyor").getInteger() ;
        intake_vel_axis_ = sub.getSettingsValue("oi:axis:intake").getInteger() ;
    }

    @Override
    public int getAutoModeSelector() {
        int v = getValue(automode_) ;
        return v ;
    }

    public void createStaticActions() throws Exception {

        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getConveyorSubsystem() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;

        conveyor_close_gate_action_ = new MotorPowerAction(conveyor, "closegate:power", "closegate:delay") ;
        conveyor_off_action_ = new MotorPowerAction(conveyor, 0.0) ;
        intake_off_action_ = new IntakePowerAction(logger_, intake, 0.0, 0.0) ;
    }

    private BunnyBotSubsystem getBunnyBotSubsystem() {
        return (BunnyBotSubsystem) getSubsystem().getRobot().getRobotSubsystem();
    }

    @Override
    public void generateActions(SequenceAction seq) throws InvalidActionRequest {
        
        ConveyorSubsystem conveyor = getBunnyBotSubsystem().getConveyorSubsystem() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;

        DriverStation ds = DriverStation.getInstance() ;

        double intake_cur_val = ds.getStickAxis(getIndex(), intake_vel_axis_) ;
        double conveyor_cur_val = ds.getStickAxis(getIndex(), conveyor_vel_axis_) ;

        //
        // Conveyor, Intake, and water are the 2 subsystems here
        //  -- Conveyor can either be stopped, going right, or going left
        //  -- Intake can either run ON or OFF and can be set to eject mode
        //  -- Water can either be shooting or off
        // 


        /// CONVEYOR
        if (getValue(conveyor_dump_off_) == 1) {
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
            MotorPowerAction act = new MotorPowerAction(conveyor, conveyor_cur_val) ;
            seq.addSubActionPair(conveyor, act, false) ;
            conveyor_vel_value_ = conveyor_cur_val ;
        }
        else if (getValue(conveyor_reverse_off_) == 1)
        {
            seq.addSubActionPair(conveyor, conveyor_off_action_, false) ;
        }
        else if (getValue(conveyor_reverse_on_) == 1) 
        {
            MotorPowerAction act = new MotorPowerAction(conveyor, -conveyor_cur_val) ;
            seq.addSubActionPair(conveyor, act, false) ;
            conveyor_vel_value_ = conveyor_cur_val ;
        }
        else if (Math.abs(conveyor_cur_val - conveyor_vel_value_) > 0.05 && conveyor.isRunning()) {
            double sign = Math.signum(conveyor.getPower()) ;
            MotorPowerAction act = new MotorPowerAction(conveyor, sign * conveyor_cur_val) ;
            seq.addSubActionPair(conveyor, act, false) ;
            conveyor_vel_value_ = conveyor_cur_val ;
        }

        /// INTAKE
        //
        // This is a new if because the intake is controlled independently of the GPM.
        //
        if (getValue(intake_off_) == 1) {
            seq.addSubActionPair(intake, intake_off_action_, false) ;
        }
        else if (getValue(intake_on_) == 1) {
            double vel = (getValue(intake_dir_) == 1) ? intake_cur_val : -intake_cur_val ;
            IntakePowerAction act = new IntakePowerAction(logger_, intake, vel, vel) ;
            seq.addSubActionPair(intake, act, false);
            intake_vel_value_ = intake_cur_val ;
        }  
        else if (Math.abs(intake_cur_val - intake_vel_value_) > 0.05 && intake.isRunning()) {
            IntakePowerAction act = new IntakePowerAction(logger_, intake, intake_cur_val, intake_cur_val) ;
            seq.addSubActionPair(intake, act, false);            
            intake_vel_value_ = intake_cur_val ;
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
        intake_dir_ = mapButton(num, OIPanelButton.ButtonType.Level) ;
        
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
