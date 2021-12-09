package frc.robot.water;

import org.xero1425.base.Subsystem;

import edu.wpi.first.wpilibj.Relay;

// TODO: CHANGE WATER TO MOTOR POWER SUBSYSTEM

public class WaterSubsystem {
    public static final String SubsystemName = "water";

    private Relay water_relay_ ;
    // TODO : add the hardware stuff for the up/down water shooters - might be a relay, may not be one tho...

    enum OnOffState {
        ON,
        OFF, 
        UNKNOWN
    }

    private OnOffState water_relay_state_ = OnOffState.UNKNOWN;
    private OnOffState squirt_up_state_ = OnOffState.UNKNOWN;
    private OnOffState squirt_down_state_ = OnOffState.UNKNOWN;

    public WaterSubsystem(Subsystem parent) throws Exception {
        water_relay_ = new Relay(0, Relay.Direction.kForward) ; 
        // TODO: get the "channel" for relay (currently set to "0") from params file

    }

    public void setPump (OnOffState relay) {
        if (relay == OnOffState.ON) {
            water_relay_state_ = OnOffState.ON ;
            water_relay_.set(Relay.Value.kOn) ;
        }
        else if (relay == OnOffState.OFF) {
            water_relay_state_ = OnOffState.OFF ;
            water_relay_.set(Relay.Value.kOff) ;
        }
    }


    // TODO: set the hardware for all 4 following cases of setting squirter state
    public void setSquirtUp (OnOffState squirter) {
        if (squirter == OnOffState.ON) {
            squirt_up_state_ = OnOffState.ON ;
        }
        else if (squirter == OnOffState.ON) {
            squirt_up_state_ = OnOffState.OFF ;
        }
    }
    
    public void setSquirtDown (OnOffState squirter) {
        if (squirter == OnOffState.ON) {
            squirt_down_state_ = OnOffState.ON ;
        }
        else if (squirter == OnOffState.ON) {
            squirt_down_state_ = OnOffState.OFF ;
        }
    }


}

