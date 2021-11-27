package frc.robot.automodes;

import org.xero1425.base.controllers.AutoController;
import org.xero1425.base.controllers.AutoMode;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MessageType;
import org.xero1425.misc.MissingParameterException;

import frc.robot.BunnyBot21;

//
// This class is the automode controller for Droid.  It basically creates all of the
// automodes and picks one based on test mode and the automode controller selected.
//
public class BunnyBotAutoController extends AutoController {
    private AutoMode test_mode_ ;
    private AutoMode [] modes_ ;

    public BunnyBotAutoController(BunnyBot21 robot) throws MissingParameterException, BadParameterTypeException {
        super(robot, "bunnybot-auto");

        MessageLogger logger = getRobot().getMessageLogger() ;
        modes_ = new AutoMode[10] ;

        try {
            test_mode_ = new BunnyBotTestAutoMode(this);
            modes_[0] = new TwoToteAuto(this, "BunnyBotAutoModeClose", "AutoMode_Path1", "AutoMode_Path2", "automodes:twototeauto:delay") ;
            modes_[1] = new TwoToteAuto(this, "BunnyBotAutoModeFar", "AutoMode_Path3", "AutoMode_Path4", "automodes:twototeauto:delay") ;
            modes_[2] = new NopAuto(this, "Nop-2") ;
            modes_[3] = new NopAuto(this, "Nop-3") ;
            modes_[4] = new NopAuto(this, "Nop-4") ;
            modes_[5] = new NopAuto(this, "Nop-5") ;
            modes_[6] = new NopAuto(this, "Nop-6") ;
            modes_[7] = new NopAuto(this, "Nop-7") ;
            modes_[8] = new NopAuto(this, "Nop-8") ;
            modes_[9] = new NopAuto(this, "Nop-9") ;                                                                                    
        }
        catch(Exception e) {
            logger.startMessage(MessageType.Error) ;
            logger.add("cannot create automode 'BunnyTestAutoMode', exception caught - ") ;
            logger.add(e.getMessage()) ;
            logger.endMessage();
        }
    }

    public void updateAutoMode(int mode, String gamedata) throws Exception {
        if (isTestMode()) {
            setAutoMode(test_mode_) ;
        }
        else {
            if (mode >= 0 && mode < modes_.length) {
                if (getAutoMode() != modes_[mode])
                    setAutoMode(modes_[mode]) ;
            }
        }
    }
}
