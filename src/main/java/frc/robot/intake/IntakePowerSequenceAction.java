package frc.robot.intake;

import org.xero1425.base.actions.Action;
import org.xero1425.base.motors.BadMotorRequestException;
import org.xero1425.base.motors.MotorRequestFailedException;

public class IntakePowerSequenceAction extends Action {

    private IntakeSubsystem sub_;
    private double[] times_;
    private double[] lower_powers_;
    private double[] upper_powers_ ;
    private int index_;
    private double index_time_;

    public IntakePowerSequenceAction(IntakeSubsystem sub, double[] times, double[] lower, double[] upper) throws Exception {
        super(sub.getRobot().getMessageLogger());

        sub_ = sub;
        times_ = times;
        lower_powers_ = lower ;
        upper_powers_ = upper ;

        if (times_.length == 0 || lower_powers_.length == 0 || upper_powers_.length == 0 || 
                times_.length != lower_powers_.length || times_.length != upper_powers_.length)
            throw new Exception("invalid arguments to Conveyor action");
    }

    @Override
    public void start() throws Exception {
        super.start();

        index_ = 0;
        index_time_ = sub_.getRobot().getTime();
        setupCurrentIndex();
    }

    private void setupCurrentIndex() {
        index_time_ = sub_.getRobot().getTime();
        try {
            sub_.setBothMotorPowers(lower_powers_[index_], upper_powers_[index_]);
        } catch (BadMotorRequestException | MotorRequestFailedException e) {
        }
    }

    @Override
    public void run() throws Exception {
        super.run();

        if (sub_.getRobot().getTime() - index_time_ > times_[index_]) {
            index_++;
            if (index_ == times_.length) {
                setDone();
            } else {
                setupCurrentIndex();
            }
        }
    }

    @Override
    public void cancel() {
        super.cancel();

        index_ = times_.length;
        try {
            sub_.setBothMotorPowers(0.0, 0.0);
        } catch (BadMotorRequestException | MotorRequestFailedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(int indent) {
        return "ConveyorPowerSequenceAction" ;
    }
}
