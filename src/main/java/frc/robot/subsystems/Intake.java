// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private boolean motorPowerLimitActive = false;
  private double motorPowerLimitAmount = 0.0;

  private final SpeedControllerGroup intakeWheelMotor;

  /** Creates a new Intake subsystem. */

  public Intake(SpeedControllerGroup intakeWheelMotor) {

    this.intakeWheelMotor = intakeWheelMotor;

  }

  // Check that the motor power has not exceeded a set limit 
  private double checkPowerLimit(double power) {
    if (motorPowerLimitActive) {

      if (power > motorPowerLimitAmount) {
        power = motorPowerLimitAmount;
      } else if (power < -motorPowerLimitAmount) {
        power = -motorPowerLimitAmount;
      } 
    } 

    return power;
  }

  public void setIntakeWheelPower(double power) {
    intakeWheelMotor.set(checkPowerLimit(power));
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
