// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveBase extends SubsystemBase {

  private boolean motorPowerLimitActive = false;
  private double motorPowerLimitAmount = 0.0;

  private final SpeedControllerGroup leftDrive;
  private final SpeedControllerGroup rightDrive;
  private final SpeedControllerGroup centerDrive;

  private final Encoder leftEncoder;
  private final Encoder rightEncoder;
  private final Encoder centerEncoder;

  private final ADXRS450_Gyro gyro;

  private boolean usingLeftEncoder = false;

  private double heading;
  public double courseHeading;

  public DriveBase(SpeedControllerGroup leftDrive, SpeedControllerGroup rightDrive, SpeedControllerGroup centerDrive, int leftEncoderChannelA, int leftEncoderChannelB, 
                  int rightEncoderChannelA, int rightEncoderChannelB, int centerEncoderChannelA, int centerEncoderChannelB) {

    this.leftDrive = leftDrive;
    this.rightDrive = rightDrive;
    this.centerDrive = centerDrive;

    leftEncoder = new Encoder(leftEncoderChannelA, leftEncoderChannelB, false, EncodingType.k4X);
    rightEncoder = new Encoder(rightEncoderChannelA, rightEncoderChannelB, true, EncodingType.k4X);
    centerEncoder = new Encoder(centerEncoderChannelA, centerEncoderChannelB, false, EncodingType.k4X);

    leftEncoder.setDistancePerPulse((4 * Math.PI) / 1024.0);
    rightEncoder.setDistancePerPulse((4 * Math.PI) / 1024.0);
    centerEncoder.setDistancePerPulse((4 * Math.PI) / 1024.0);

    gyro = new ADXRS450_Gyro();
    gyro.calibrate();
    heading = gyro.getAngle();
    courseHeading = heading;

  }

  // Check that the motor power has not exceeded a set limit 
  private double checkPowerLimit(double power) {
    if (motorPowerLimitActive) {
      if (power > motorPowerLimitAmount) {
        power = motorPowerLimitAmount;
        return power;
      } else {
        return power;
      }
    } else {
      return power;
    }
  }

  public void setPowerLimitState(boolean state) {
      motorPowerLimitActive = state;
  }

  public boolean getPowerLimitState() {
    return motorPowerLimitActive;
  }

  public void setLeftPower(double power) {
    leftDrive.set(checkPowerLimit(power));
  }

  public void setRightPower(double power) {
    rightDrive.set(-(checkPowerLimit(power)));
  }

  public void setCenterPower(double power) {
    centerDrive.set(checkPowerLimit(power));
  }

  public void resetGyro() {
    gyro.reset();
  }

  public double getGyroAngle() {
    return gyro.getAngle();
  }

  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
    centerEncoder.reset();
  }

  public boolean usingLeftEncoder () {
    return usingLeftEncoder;
  }

  public void setUsingLeftEncoder(boolean usingLeftEncoder) {
    this.usingLeftEncoder = usingLeftEncoder;
  }

  public double getForwardDistance() {
    if (usingLeftEncoder) {
      return getLeftDistance();
    } else {
      return getRightDistance();
    }
  }

  public double getCenterDistance() {
    return centerEncoder.getDistance();
  }

  public double getForwardSpeed() {
    if (usingLeftEncoder) {
      return getLeftSpeed();
    } else {
      return getRightSpeed();
    }
  }

  public double getCenterSpeed() {
    return centerEncoder.getRate();
  }

  public double getLeftDistance() {
    return leftEncoder.getDistance();
  }

  public double getRightDistance() {
    return rightEncoder.getDistance();
  }

  public double getLeftSpeed() {
    return leftEncoder.getRate();
  }

  public double getRightSpeed() {
    return rightEncoder.getRate();
  }


  // Stuff below kept just in case we need them. Probably won't.
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
