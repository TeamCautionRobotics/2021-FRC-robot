// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveBase;

import java.util.function.DoubleSupplier;


import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DriveBase driveBase;

  private DoubleSupplier leftJoystickX;
  private DoubleSupplier leftJoystickY;

  private DoubleSupplier rightJoystickX;
  private DoubleSupplier rightJoystickY;

  private double lastRightDrive;
  private double lastLeftDrive;

  private double rightDriveValue;
  private double leftDriveValue;

  private double maxChange;

  /**
   * Creates a new arcadeDrive command.
   *
   * @param driveBase The subsystem used by this command.
   */
  public ArcadeDrive(DriveBase driveBase, 
                    DoubleSupplier leftJoystickX, DoubleSupplier leftJoystickY, 
                    DoubleSupplier rightJoystickX, DoubleSupplier rightJoystickY,
                    double maxChange) {

    this.driveBase = driveBase;

    this.leftJoystickX = leftJoystickX;
    this.leftJoystickY = leftJoystickY;

    this.rightJoystickX = rightJoystickX;
    this.rightJoystickY = rightJoystickY;

    this.maxChange = maxChange;


    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    // Arcade drive
    rightDriveValue = rightJoystickY.getAsDouble()-leftJoystickX.getAsDouble();
    leftDriveValue = rightJoystickY.getAsDouble()+leftJoystickX.getAsDouble();

    if(Math.abs(leftDriveValue-lastLeftDrive) < maxChange) {
      driveBase.setLeftPower(rightJoystickY.getAsDouble()+leftJoystickX.getAsDouble());
    }
    else if(leftDriveValue > lastLeftDrive){
      driveBase.setLeftPower(lastLeftDrive + maxChange);
    }
    else{
      driveBase.setLeftPower(leftDriveValue - maxChange);
    }

    if(Math.abs(leftDriveValue-lastLeftDrive) < maxChange) {
     driveBase.setRightPower(rightJoystickY.getAsDouble()-leftJoystickX.getAsDouble());
    }
    else if(rightDriveValue > lastRightDrive){
      driveBase.setRightPower(lastRightDrive + maxChange);
    }
    else{
      driveBase.setRightPower(rightDriveValue - maxChange);
    }

    lastLeftDrive = leftDriveValue;
    lastRightDrive = rightDriveValue;
    
    // Use right joystick's X axis for center drive
    driveBase.setCenterPower(rightJoystickX.getAsDouble());

  }

  @Override
  public void end(boolean interrupted) {

    // Stop all motors if command is killed

    driveBase.setLeftPower(0);
    driveBase.setRightPower(0);
    driveBase.setCenterPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
