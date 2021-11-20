package frc.robot.bunnybotoi;

import org.xero1425.base.actions.InvalidActionRequest;
import org.xero1425.base.actions.SequenceAction;
import org.xero1425.base.oi.OISubsystem;
import org.xero1425.base.oi.OIPanel;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class BunnyBotOIDevice extends OIPanel {

    public BunnyBotOIDevice(OISubsystem sub, String name, int index)
            throws BadParameterTypeException, MissingParameterException {
        super(sub, name, index);

        initializeGadgets();
    }

    public void createStaticActions() throws Exception {
    }

    // private PractBotSubsystem getPractBotSubsystem() {
    //     return (PractBotSubsystem) getSubsystem().getRobot().getRobotSubsystem();
    // }

    @Override
    public void generateActions(SequenceAction seq) throws InvalidActionRequest {
    }

    private void initializeGadgets() throws BadParameterTypeException, MissingParameterException {
    }
}
