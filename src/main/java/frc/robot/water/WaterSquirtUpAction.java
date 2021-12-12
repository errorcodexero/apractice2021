package frc.robot.water;

import org.xero1425.base.actions.Action;
import org.xero1425.misc.MessageLogger;

import frc.robot.water.WaterSubsystem.OnOffState;

public class WaterSquirtUpAction extends Action {
    private WaterSubsystem sub_ ;
    double duration_ ;
    boolean isTimed_ = false ;
    // private double start_ ;

    public WaterSquirtUpAction(WaterSubsystem sub, MessageLogger logger) {
        super(logger) ;
        sub_ = sub ;
    }

    public WaterSquirtUpAction(WaterSubsystem sub, double duration, MessageLogger logger) {
        super(logger) ;
        sub_ = sub ;
        duration_ = duration ;
        isTimed_ = true ;
    }

    @Override
    public void start() throws Exception {
        super.start() ;

        sub_.setSquirtUp(OnOffState.ON) ;
        sub_.setPump(OnOffState.ON) ;

        // if (isTimed_) {
        //     start_ = getRobot().getTime() ;
        // }

    }

    @Override
    public void run() throws Exception {
        super.run() ;

        // cancel after 3 seconds
        if (isTimed_) {
            // if (getRobot().getTime() + start_ > duration_) {
            //     sub_.setPump(OnOffState.OFF) ;
            //     sub_.setSquirtUp(OnOffState.OFF) ;
            //     setDone() ;
            // }
        }

    }

    @Override
    public void cancel() {
        super.cancel() ;

        sub_.setPump(OnOffState.OFF) ;
        sub_.setSquirtUp(OnOffState.OFF) ;

    }

    @Override
    public String toString(int indent) {
        return null ;
    }

}
