package frc.robot.water;

import org.xero1425.base.actions.Action;
import org.xero1425.misc.MessageLogger;

public class WaterSquirtDownAction extends Action {


//TODO: update the WaterSquirt Action + implement
    // The WaterSquirtUpAction and the WaterSquirtDownAction are going to be identical except 
    // for the valves they turn on or off.  I would either create a base class for these two 
    // that has all the code, or (my preference) just create a WaterSquirtAction where one of the
    // arguments in the constructor is an enum that give direction (e.g. SquirtDirection.Up vs 
    // SquirtDirection.Down).
    
    public WaterSquirtDownAction(MessageLogger logger) {
        super(logger);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString(int indent) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
