package frc.robot.automodes;

import org.xero1425.base.controllers.AutoMode;
import frc.robot.bunnybotsubsystem.BunnyBotSubsystem;

public class BunnyBotAutoMode extends AutoMode {
    public BunnyBotAutoMode(BunnyBotAutoController ctrl, String name) {
        super(ctrl, name) ;
    }

    protected BunnyBotSubsystem getBunnyBotSubsystem() {
        return (BunnyBotSubsystem) getAutoController().getRobot().getRobotSubsystem();
    }

}
