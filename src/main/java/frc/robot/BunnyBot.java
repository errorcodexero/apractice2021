package frc.robot;

import org.xero1425.base.XeroRobot;
import org.xero1425.base.controllers.AutoController;
import org.xero1425.misc.SimArgs;

import frc.robot.bunnybotsubsystem.PractBotSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class BunnyBot extends XeroRobot {
  public BunnyBot() {
    super(0.02);
  }

  public String getName() {
    return "BunnyBot";
  }

  public AutoController createAutoController() {
    return null;
  }

  protected void hardwareInit() throws Exception {
    PractBotSubsystem robotsub = new PractBotSubsystem(this) ;
    setRobotSubsystem(robotsub) ;
  }
  
  public String getSimulationFileName() {
    String ret = SimArgs.InputFileName;
    if (ret != null)
      return ret;

    return "nop";
  }

  protected void addRobotSimulationModels() {
  }
}