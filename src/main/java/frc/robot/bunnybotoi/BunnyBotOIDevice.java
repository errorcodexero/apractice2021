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
import frc.robot.gamepiecemanipulator.GamePieceManipulatorSubsystem;
import frc.robot.gamepiecemanipulator.DepositAction;
import frc.robot.gamepiecemanipulator.StopAction;
import frc.robot.gamepiecemanipulator.EjectAction;
import frc.robot.intake.IntakeSubsystem;

public class BunnyBotOIDevice extends OIPanel {

    private int intake_on_ ;

    private int gpm_deposit_ ;
    private int gpm_stop_ ;

    private int eject_true_ ;

    private Action intake_on_action_ ;
    private Action intake_off_action_ ;
    private Action intake_eject_action_ ;

    private Action gpm_deposit_action_ ;
    private Action gpm_stop_action_ ; 
    private Action gpm_eject_action_ ;

    private boolean eject_mode_ ;

    
    public BunnyBotOIDevice(OISubsystem sub, String name, int index)
            throws BadParameterTypeException, MissingParameterException {
        super(sub, name, index);

        initializeGadgets();
        eject_mode_ = false ;
    }

    public void createStaticActions() throws Exception {

        GamePieceManipulatorSubsystem gpm = getBunnyBotSubsystem().getGamePieceManipulator() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;

        gpm_deposit_action_ = new DepositAction(gpm) ;
        gpm_stop_action_ = new StopAction(gpm) ;
        gpm_eject_action_ = new EjectAction(gpm) ;

        intake_on_action_ = new MotorPowerAction(intake, "motor:on:power") ;
        intake_off_action_ = new MotorPowerAction(intake, 0.0) ;
        intake_eject_action_ = new MotorPowerAction(intake, "motor:eject:power") ;
    }

    private BunnyBotSubsystem getBunnyBotSubsystem() {
        return (BunnyBotSubsystem) getSubsystem().getRobot().getRobotSubsystem();
    }

    @Override
    public void generateActions(SequenceAction seq) throws InvalidActionRequest {

        GamePieceManipulatorSubsystem gpm = getBunnyBotSubsystem().getGamePieceManipulator() ;
        IntakeSubsystem intake = getBunnyBotSubsystem().getIntake() ;

        //off is always off, despite what the eject mode is in
        if (getValue(gpm_stop_) == 1) {
            seq.addSubActionPair(gpm, gpm_stop_action_, false);
        }
        else if (getValue(gpm_deposit_) == 1) {
            if (getValue(eject_true_) == 0) {
                if (eject_mode_ || !gpm.getConveyor().isRunning())
                    seq.addSubActionPair(gpm, gpm_deposit_action_, false);
            }
            else {
                if (!eject_mode_ || !gpm.getConveyor().isRunning())
                    seq.addSubActionPair(gpm, gpm_eject_action_, false);
            }
        }

        if (getValue(intake_on_) == 0) {
            if (intake.isRunning())
                seq.addSubActionPair(intake, intake_off_action_, false);
        }
        else {
            if (getValue(eject_true_) == 0) {
                if (eject_mode_ || !intake.isRunning())
                    seq.addSubActionPair(intake, intake_on_action_, false);
            }
            else {
                if (!eject_mode_ || !intake.isRunning())                
                    seq.addSubActionPair(intake, intake_eject_action_, false);
            }
        }

        eject_mode_ = getValue(eject_true_) == 1 ;
    }

    private void initializeGadgets() throws BadParameterTypeException, MissingParameterException {
        int num;
       
    ///intake on/off switch
       
        num = getSubsystem().getSettingsValue("oi:gadgets:intake_on_mode").getInteger() ;
        intake_on_ = mapButton(num, OIPanelButton.ButtonType.Level) ;
        
    ///gpm (conveyor + chute) deploy/stop buttons

        num = getSubsystem().getSettingsValue("oi:gadgets:gpm_deploy_mode").getInteger() ;
        gpm_deposit_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ;

        num = getSubsystem().getSettingsValue("oi:gadgets:gpm_stop_mode").getInteger() ;
        gpm_stop_ = mapButton(num, OIPanelButton.ButtonType.HighToLow) ;

    ///"eject mode" switch - what intake/gpm does if it's in eject mode

        num = getSubsystem().getSettingsValue("oi:gadgets:eject_true").getInteger() ;
        eject_true_ = mapButton(num, OIPanelButton.ButtonType.Level) ;

    }

}
