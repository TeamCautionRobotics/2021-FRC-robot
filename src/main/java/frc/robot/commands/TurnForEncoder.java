// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TurnForEncoder extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveBase driveBase;
  private final boolean isTurningLeft;
  private final double outerWheelDrivingDistance;
  private final double wheelDrivingRatio;
  private final double speed;
  private double outerWheelPower;
  private double innerWheelPower;

  public TurnForEncoder(DriveBase driveBase, boolean isTurningLeft, double outerWheelDrivingDistance, double wheelDrivingRatio, double speed) {
    this.driveBase = driveBase;
    this.isTurningLeft = isTurningLeft;
    this.outerWheelDrivingDistance = outerWheelDrivingDistance;
    this.wheelDrivingRatio = wheelDrivingRatio;
    this.speed = speed;
    addRequirements(driveBase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      outerWheelPower = speed;
      innerWheelPower = speed * wheelDrivingRatio;

      if(isTurningLeft){
          driveBase.setLeftPower(innerWheelPower);
          driveBase.setRightPower(outerWheelPower);
      }
      else{
        driveBase.setLeftPower(outerWheelPower);
        driveBase.setRightPower(innerWheelPower);
      }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(isTurningLeft){
        driveBase.setLeftPower(innerWheelPower);
        driveBase.setRightPower(outerWheelPower);
    }
    else{
      driveBase.setLeftPower(outerWheelPower);
      driveBase.setRightPower(innerWheelPower);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      driveBase.setLeftPower(0);
      driveBase.setRightPower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isTurningLeft ? Math.abs(driveBase.getRightDistance()) >= outerWheelDrivingDistance : Math.abs(driveBase.getLeftDistance()) >= outerWheelDrivingDistance;

   }
}
