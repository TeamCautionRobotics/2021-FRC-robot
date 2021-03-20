// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private boolean motorPowerLimitActive = false;
  private double motorPowerLimitAmount = 0.0;

  private final SpeedControllerGroup flywheelMotor;
  private final SpeedControllerGroup indexerMotor;

  /** Creates a new Shooter subsystem. */
  public Shooter(SpeedControllerGroup flywheelMotor, SpeedControllerGroup indexerMotor) {

    this.flywheelMotor = flywheelMotor;
    this.indexerMotor = indexerMotor;

  }

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

  public void setFlywheelPower(double power) {
    flywheelMotor.set(checkPowerLimit(power));
  }

  public void setIndexerPower(double power) {
    indexerMotor.set(checkPowerLimit(power));
  };

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
