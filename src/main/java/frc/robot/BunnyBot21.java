package frc.robot;

import org.xero1425.base.XeroRobot;
import org.xero1425.base.controllers.AutoController;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;
import org.xero1425.misc.SimArgs;
import org.xero1425.misc.XeroPathType;
import org.xero1425.simulator.engine.ModelFactory;
import org.xero1425.simulator.engine.SimulationEngine;

import frc.robot.automodes.BunnyBotAutoController;
import frc.robot.bunnybotsubsystem.BunnyBotSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class BunnyBot21 extends XeroRobot {
  public BunnyBot21() {
    super(0.02);
  }

  protected XeroPathType getPathType() {
    return XeroPathType.TankPathFollowing ;
  }  

  public String getName() {
    return "BunnyBot";
  }

  public AutoController createAutoController() throws MissingParameterException, BadParameterTypeException {
    return new BunnyBotAutoController(this);
  }

  protected void hardwareInit() throws Exception {
    BunnyBotSubsystem robotsub = new BunnyBotSubsystem(this);
    setRobotSubsystem(robotsub);
  }

  public String getSimulationFileName() {
    String ret = SimArgs.InputFileName;
    if (ret != null)
      return ret;

    // The name of the stimulus file to use if one was not provided from the command line
    return "5_teleop_cc";
  }

  protected void addRobotSimulationModels() {
    ModelFactory factory = SimulationEngine.getInstance().getModelFactory();
    factory.registerModel("bunnybotoi", "frc.model.BunnyBotOIModel");
  }
}