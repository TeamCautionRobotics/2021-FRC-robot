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
  private double lastCenterDrive;

  private double rightDriveValue;
  private double leftDriveValue;
  private double centerDriveValue;

  private double maxChange;
  private double maxCenterDriveChange;

  /**
   * Creates a new arcadeDrive command.
   *
   * @param driveBase The subsystem used by this command.
   */
  public ArcadeDrive(DriveBase driveBase, 
                    DoubleSupplier leftJoystickX, DoubleSupplier leftJoystickY, 
                    DoubleSupplier rightJoystickX, DoubleSupplier rightJoystickY,
                    double maxChange, double maxCenterDriveChange) {

    this.driveBase = driveBase;

    this.leftJoystickX = leftJoystickX;
    this.leftJoystickY = leftJoystickY;

    this.rightJoystickX = rightJoystickX;
    this.rightJoystickY = rightJoystickY;

    this.maxChange = maxChange;
    this.maxCenterDriveChange = maxCenterDriveChange;


    addRequirements(driveBase);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {

    // Arcade drive
    //rightDriveValue = rightJoystickY.getAsDouble()-leftJoystickX.getAsDouble();
    //leftDriveValue = rightJoystickY.getAsDouble()+leftJoystickX.getAsDouble();


    //sets value for LeftDrive. RightDrive and CenterDrive follow same format.
    //runs as normal if the change is less than the max change
    if(Math.abs(leftDriveValue-lastLeftDrive) < maxChange) {
      driveBase.setLeftPower(rightJoystickY.getAsDouble()+leftJoystickX.getAsDouble());
    }
    //caps the change at max change
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

    // Use right joystick's X axis for center drive
    //driveBase.setCenterPower(rightJoystickX.getAsDouble());

    if(Math.abs(leftDriveValue-lastLeftDrive) <= maxCenterDriveChange) {
      driveBase.setCenterPower(rightJoystickX.getAsDouble());
     }
     else if(rightDriveValue > lastRightDrive){
       driveBase.setCenterPower(lastCenterDrive + maxCenterDriveChange);
     }
     else{
       driveBase.setCenterPower(centerDriveValue - maxCenterDriveChange);
     }

     //updates the lastDrive values
    lastLeftDrive = leftDriveValue;
    lastRightDrive = rightDriveValue;
    lastCenterDrive = centerDriveValue;

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
