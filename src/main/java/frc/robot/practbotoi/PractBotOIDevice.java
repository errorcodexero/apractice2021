package frc.robot.practbotoi;

import org.xero1425.base.actions.Action;
import org.xero1425.base.actions.InvalidActionRequest;
import org.xero1425.base.actions.SequenceAction;
import org.xero1425.base.oi.OISubsystem;
import org.xero1425.base.oi.OIPanel;
import org.xero1425.base.oi.OIPanelButton;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;
import org.xero1425.misc.SettingsParser;

import frc.robot.gamepiecemanipulator.GamePieceManipulatorSubsystem;
import frc.robot.gamepiecemanipulator.StartIntakeAction;
import frc.robot.gamepiecemanipulator.StopIntakeAction;
import frc.robot.gamepiecemanipulator.intake.IntakeSubsystem;
import frc.robot.gamepiecemanipulator.turret.TurretSubsystem;
import frc.robot.practbotsubsystem.PractBotSubsystem;

public class PractBotOIDevice extends OIPanel {

    private int start_intake_;
    private int stop_intake_;
    private double desired_turret_; // = 0;

    private Action start_intake_act_;
    private Action stop_intake_act_;

    private GamePieceManipulatorSubsystem gpm = getPractBotSubsystem().getGamePieceManipulator();

    public PractBotOIDevice(OISubsystem sub, String name, int index)
            throws BadParameterTypeException, MissingParameterException {
        super(sub, name, index);

        initializeGadgets();
    }

    public void createStaticActions() throws Exception {

        start_intake_act_ = new StartIntakeAction(gpm, desired_turret_);
        stop_intake_act_ = new StopIntakeAction(gpm);

    }

    private PractBotSubsystem getPractBotSubsystem() {
        return (PractBotSubsystem) getSubsystem().getRobot().getRobotSubsystem();
    }

    @Override
    public void generateActions(SequenceAction seq) throws InvalidActionRequest {
        
        if (getValue(stop_intake_) == 1) {
            seq.addSubActionPair(gpm, stop_intake_act_, false);
        } else if (getValue(start_intake_) == 1) {
            seq.addSubActionPair(gpm, start_intake_act_, false) ;
        } 
        
    }

    private void initializeGadgets() throws BadParameterTypeException, MissingParameterException {
        int num ;
        SettingsParser settings = getSubsystem().getRobot().getSettingsParser() ;

        num = settings.get("oi:start_intake_mode").getInteger() ;
        start_intake_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ; 
        
        num = settings.get("oi:stop_intake_mode").getInteger() ;
        stop_intake_ = mapButton(num, OIPanelButton.ButtonType.LowToHigh) ; 
    }
}
