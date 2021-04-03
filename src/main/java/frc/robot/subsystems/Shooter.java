package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;


public class Shooter extends SubsystemBase {

  private final Limelight limelight;
  private double limelightTa;
  private double limelightTv;

  private double flywheelAutoPower;
  private double currentFlywheelRate;
  private double limelightOffset;

  private boolean motorPowerLimitActive = false;
  private double motorPowerLimitAmount = 1.0;

  private final SpeedControllerGroup flywheelMotor;
  private final DigitalInput detectionSwitch;

  private final Encoder flywheelEncoder;

  public Shooter(SpeedControllerGroup flywheelMotor, DigitalInput detectionSwitch, Encoder flywheelEncoder) {

    limelight = new Limelight();

    this.flywheelMotor = flywheelMotor;
    this.detectionSwitch = detectionSwitch;
    this.flywheelEncoder = flywheelEncoder;

    flywheelEncoder.setDistancePerPulse((4 * Math.PI)/1024.0);
    
  }

  public Shooter(SpeedControllerGroup flywheelMotor) {

    limelight = new Limelight();

    this.flywheelMotor = flywheelMotor;
    this.detectionSwitch = null;
    this.flywheelEncoder = null;

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
    flywheelMotor.set(checkPowerLimit(flywheelAutoPower));
  }

  public boolean getDetectionSwitch() {
    return !detectionSwitch.get();
  }

  @Override
  public void periodic() {

    // Keep limelight data up to date
    limelightTa = limelight.getTa();
    limelightTv = limelight.getTv();

    SmartDashboard.putNumber("limelight tv", limelightTv);
    SmartDashboard.putNumber("limelight ta", limelightTa);
    SmartDashboard.putNumber("auto pwr", flywheelAutoPower);

    // Calculate the limelight's speed modifier based on target size
    limelightOffset = limelightTa;

    if (limelightTv == 1.0) {                                       // Calculate flywheel power using limelight if target is detected

      flywheelAutoPower = (Math.pow((2.0238 * (185.05 * (limelightTa))), -0.735) + 53.518) / 100.0 ;

    } else {                                                      // Run flywheel at half power if no target is detected for quick spool
        flywheelAutoPower = 0.5;
    }

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
