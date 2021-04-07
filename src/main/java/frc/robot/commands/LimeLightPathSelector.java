// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.misc2021.AutonomousRouteDataWrapperClass;

public class LimeLightPathSelector extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


  private final Limelight limelight;
  private final AutonomousRouteDataWrapperClass autonomousRouteDataWrapperClass;


  public LimeLightPathSelector(AutonomousRouteDataWrapperClass autonomousRouteDataWrapperClass) {
    limelight = new Limelight();
    this.autonomousRouteDataWrapperClass = autonomousRouteDataWrapperClass;
    addRequirements(limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      limelight.setPipeline(1);
      if (limelight.getTx() > 0){
        autonomousRouteDataWrapperClass.setIsRunningAutonomousA(true);
      }
      else{
          autonomousRouteDataWrapperClass.setIsRunningAutonomousA(false);
      }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
