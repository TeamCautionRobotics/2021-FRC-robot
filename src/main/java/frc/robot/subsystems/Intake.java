// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private final SpeedControllerGroup intakeWheelMotor;

  public Intake(SpeedControllerGroup intakeWheelMotor) {
    this.intakeWheelMotor = intakeWheelMotor;
  }

  public void setIntakeWheelPower(double power) {
    intakeWheelMotor.set(power);
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
