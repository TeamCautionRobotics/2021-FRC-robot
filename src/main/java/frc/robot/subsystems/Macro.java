// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Macro extends SubsystemBase {

  private boolean isRecording;
  private String autoFile;
  private int autoNum;

  private DriveBase driveBase;
  private Intake intake;  

  public Macro(DriveBase driveBase, Intake intake) {

    this.driveBase = driveBase;
    this.intake = intake;

    autoNum = 00;
    autoFile = new String("/home/lvuser/auto" + autoNum + ".csv");

  }

  public boolean getIsRecording() {
    return isRecording;
  }

  public void setIsRecording(boolean state) {
    isRecording = state;
  }

  public String getAutoFile() {
    return autoFile;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
