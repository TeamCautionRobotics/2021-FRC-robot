package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;


public class Shooter extends SubsystemBase {

  private final Limelight limelight;
  private double limelightTa;

  private boolean motorPowerLimitActive = false;
  private double motorPowerLimitAmount = 1.0;
  private final double flywheelPIDOutput = 0;

  private final SpeedControllerGroup flywheelMotor;
  private final DigitalInput detectionSwitch;

  public Shooter(SpeedControllerGroup flywheelMotor, DigitalInput detectionSwitch) {

    limelight = new Limelight();

    this.flywheelMotor = flywheelMotor;
    this.detectionSwitch = detectionSwitch;
    
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

  public void flyweelAuto() {
    flywheelMotor.set(checkPowerLimit(flywheelPIDOutput));
  }

  public boolean getDetectionSwitch() {
    return !detectionSwitch.get();
  }

  @Override
  public void periodic() {

    limelightTa = limelight.getTa();

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
