package frc.robot.gamepiecemanipulator.turret;

import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorRequestFailedException;
import org.xero1425.base.motorsubsystem.MotorEncoderGotoAction;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MissingParameterException;

public class TurretTurnAction extends MotorEncoderGotoAction {

    private TurretSubsystem sub_;
    private double turret_power_;

    public TurretTurnAction(TurretSubsystem sub) throws BadParameterTypeException, MissingParameterException {
        super(sub, "intake:motor:collect:power", true);
        sub_ = sub;
        turret_power_ = sub_.getRobot().getSettingsParser().get("turret:motor:power").getDouble();

    }

    @Override
    public void start() throws Exception {
        super.start();
        
        //TODO: check that turret_power_ is within a given range before assigning it to sub_
        sub_.setTurretPower(turret_power_);

    }

    @Override
    public void run() {
        
        if (isDone()) {
            try {
                sub_.setTurretPower(0.0);
            } catch (BadMotorRequestException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (MotorRequestFailedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public void cancel() {
        super.cancel() ;

        //
        // Set the turret power to zero
        //
        try {
            sub_.setTurretPower(0.0);
        } catch (BadMotorRequestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MotorRequestFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String toString(int indent) {
        return prefix(indent) + "FollowTargetAction" ;
    }

}
