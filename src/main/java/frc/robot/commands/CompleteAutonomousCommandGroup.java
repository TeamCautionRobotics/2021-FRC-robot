
package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.misc2021.AutonomousRouteDataWrapperClass;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;

public class CompleteAutonomousCommandGroup extends SequentialCommandGroup {

  public boolean isRunningAutonomousA;

  public CompleteAutonomousCommandGroup(DriveBase drivebase, Intake intake, AutonomousRouteDataWrapperClass autonomousRouteDataWrapperClass) {

    addCommands(

        new LimeLightPathSelector(autonomousRouteDataWrapperClass));
    
     //this runs path A
    if(autonomousRouteDataWrapperClass.getIsRunningAutonomousA()){
      addCommands(
      );
    }

    //this runs path B
    else{
      addCommands(
      );
    }
  }
}