package frc.robot.gamepiecemanipulator;

import org.xero1425.base.Subsystem;

import frc.robot.gamepiecemanipulator.chute.ChuteSubsystem;
import frc.robot.gamepiecemanipulator.conveyor.ConveyorSubsystem;

public class GamePieceManipulatorSubsystem extends Subsystem {

    private ConveyorSubsystem conveyor_ ;
    private ChuteSubsystem chute_ ;

    public static final String SubsystemName = "gamepiecemanipulator" ;
    public GamePieceManipulatorSubsystem(Subsystem parent) throws Exception {
        super(parent, SubsystemName) ;

        conveyor_ = new ConveyorSubsystem(this) ;
        addChild(conveyor_) ;

        chute_ = new ChuteSubsystem(this) ;
        addChild(chute_) ;

    }

        
    public ConveyorSubsystem getConveyor() {
        return conveyor_ ;
    }

    
    public ChuteSubsystem getChute() {
        return chute_ ;
    }

    @Override
    public void run() throws Exception {
        super.run() ;
    }

}
