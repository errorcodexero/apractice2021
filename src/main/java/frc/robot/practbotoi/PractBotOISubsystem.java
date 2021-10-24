package frc.robot.practbotoi; //directory path from "java"

import org.xero1425.base.Subsystem; //import from first or 1425? - from 1425!!!
import org.xero1425.base.oi.OISubsystem;
import org.xero1425.base.tankdrive.TankDriveSubsystem;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MessageType;
import org.xero1425.misc.MissingParameterException;

public class PractBotOISubsystem extends OISubsystem {
    
    private PractBotOIDevice oi_;
    
    public final static String SubsystemName = "practbotoi";
    private final static String OIHIDIndexName = "hw:driverstation:hid:oi";

    public PractBotOISubsystem(Subsystem parent, TankDriveSubsystem db) {
        super(parent, SubsystemName, db);

        int index ; //usually, this = 2
        MessageLogger logger = getRobot().getMessageLogger() ;

        //following from droid
        try {
            index = getRobot().getSettingsParser().get(OIHIDIndexName).getInteger() ;
        } catch (BadParameterTypeException e) {
            logger.startMessage(MessageType.Error) ;
            logger.add("parameter ").addQuoted(OIHIDIndexName) ;
            logger.add(" exists, but is not an integer").endMessage();
            index = -1 ;
        } catch (MissingParameterException e) {
            logger.startMessage(MessageType.Error) ;
            logger.add("parameter ").addQuoted(OIHIDIndexName) ;
            logger.add(" does not exist").endMessage();
            index = -1 ;            
        }

        oi_ = new PractBotOIDevice(this, "OI", index) ;

    }

}