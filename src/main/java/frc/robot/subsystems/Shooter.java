package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;


public class Shooter extends SubsystemBase {

  private boolean motorPowerLimitActive = false;
  private double motorPowerLimitAmount = 1.0;
  private final double flywheelPIDOutput = 0;

  private final SpeedControllerGroup flywheelMotor;
  private final SpeedControllerGroup indexerMotor;
  private final DigitalInput detectionSwitch;
  private final PIDController flywheelPID;

  public Shooter(SpeedControllerGroup flywheelMotor, SpeedControllerGroup indexerMotor, int detectionSwitchPort) {

    this.flywheelMotor = flywheelMotor;
    this.indexerMotor = indexerMotor;
    detectionSwitch = new DigitalInput(detectionSwitchPort);
    flywheelPID = new PIDController(0, 0, 0);
    
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
  }

  public boolean getDetectionSwitch() {
    return !detectionSwitch.get();
  }

  @Override
  public void periodic() {
    flywheelPIDOutput = flywheelPID.calculate(measurement);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}