package frc.robot.bunnybotoi; //directory path from "java"

import org.xero1425.base.Subsystem; //import from 1425!!!
import org.xero1425.base.oi.OISubsystem;
import org.xero1425.base.tankdrive.TankDriveSubsystem;
import org.xero1425.misc.BadParameterTypeException;
import org.xero1425.misc.MessageLogger;
import org.xero1425.misc.MessageType;
import org.xero1425.misc.MissingParameterException;

public class BunnyBotOISubsystem extends OISubsystem {
    
    private BunnyBotOIDevice oi_;
    
    public final static String SubsystemName = "bunnybotoi";
    private final static String OIHIDIndexName = "oi:index";

    public BunnyBotOISubsystem(Subsystem parent, TankDriveSubsystem db)
            throws BadParameterTypeException, MissingParameterException {
        super(parent, SubsystemName, GamePadType.Xero1425Historic, db);

        int index ;
        MessageLogger logger = getRobot().getMessageLogger() ;

        //
        // Add the custom OI for droid to the OI subsystem
        //
        try {
            index = getSettingsValue(OIHIDIndexName).getInteger() ;
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

        if (index != -1) {
            try {
                oi_ = new BunnyBotOIDevice(this, "OI", index, logger, getGamePad()) ;
                addHIDDevice(oi_) ;
            }
            catch(Exception ex) {
                logger.startMessage(MessageType.Error) ;
                logger.add("OI HID device was not created ") ;
                logger.add(ex.getMessage()).endMessage() ; //tells what param is missing from json file
            }
        }
    }
}