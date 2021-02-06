// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveBase;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DriveBase driveBase;

  private DoubleSupplier leftJoystickX;
  private DoubleSupplier leftJoystickY;

  private DoubleSupplier rightJoystickX;
  private DoubleSupplier rightJoystickY;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TankDrive(DriveBase driveBase, DoubleSupplier leftJoystickX, DoubleSupplier leftJoystickY, DoubleSupplier rightJoystickX, DoubleSupplier rightJoystickY) {

    this.driveBase = driveBase;

    this.leftJoystickX = leftJoystickX;
    this.leftJoystickY = leftJoystickY;

    this.rightJoystickX = rightJoystickX;
    this.rightJoystickY = rightJoystickY;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    if (leftJoystickY.getAsDouble() > 0.5) {
      driveBase.setLeftPower(0.5);
    } if (leftJoystickY.getAsDouble() < -0.5) {
      driveBase.setLeftPower(-0.5);
    } else {
      driveBase.setLeftPower(leftJoystickY.getAsDouble());
    }

    if (rightJoystickY.getAsDouble() > 0.5) {
      driveBase.setRightPower(0.5);
    } if (rightJoystickY.getAsDouble() < -0.5) {
      driveBase.setRightPower(-0.5);
    } else {
      driveBase.setRightPower(rightJoystickY.getAsDouble());
    }

    if (leftJoystickX.getAsDouble() > 0.5) {
      driveBase.setCenterPower(0.5);
    } if (leftJoystickX.getAsDouble() < -0.5) {
      driveBase.setCenterPower(-0.5);
    } else {
      driveBase.setCenterPower(leftJoystickX.getAsDouble());
    }

  }

  @Override
  public void end(boolean interrupted) {
    driveBase.setLeftPower(0);
    driveBase.setRightPower(0);
    driveBase.setCenterPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
