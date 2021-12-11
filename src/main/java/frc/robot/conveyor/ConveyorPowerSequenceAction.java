package frc.robot.conveyor;

import org.xero1425.base.actions.Action;
import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorRequestFailedException;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MissingParameterException;

public class ConveyorPowerSequenceAction extends Action {
    private ConveyorSubsystem sub_;
    private double [] times_ ;
    private double [] powers_ ;
    private int index_ ;
    private double index_time_ ;

    public ConveyorPowerSequenceAction(MessageLogger logger, ConveyorSubsystem sub, double [] times, double [] powers) throws Exception {
        super(logger) ;
        
        sub_ = sub;
        times_ = times ;
        powers_ = powers ;

        if (times_.length == 0 || powers_.length == 0 || times_.length != powers_.length)
            throw new Exception("invalid arguments to Conveyor action") ;
    }

    @Override
    public void start() throws Exception {
        super.start();

        index_ = 0 ;
        index_time_ = sub_.getRobot().getTime() ;
        setupCurrentIndex();
    }

    private void setupCurrentIndex() {
        index_time_ = sub_.getRobot().getTime() ;
        sub_.setConveyorPower(powers_[index_]);
    }

    @Override
    public void run() throws Exception {
        super.run();

        if (sub_.getRobot().getTime() - index_time_ > times_[index_])
        {
            index_++ ;
            if (index_ == times_.length)
            {
                setDone() ;
            }
            else
            {
                setupCurrentIndex();
            }
        }
    }

    @Override
    public void cancel() {
        super.cancel() ;

        index_ = times_.length ;
        sub_.setConveyorPower(0.0);
    }

    @Override
    public String toString(int indent) {
        return "ConveyorPowerSequenceAction " ;
    }
}
