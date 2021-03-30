package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;


public class Shooter extends SubsystemBase {

  private final Limelight limelight;
  private double limelightTa;
  private double limelightTv;

  private double flywheelAutoPower;

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

    // Keep limeligh data up to date
    limelightTa = limelight.getTa();
    limelightTv = limelight.getTv();

    
    if (limelightTa == 1) {



    } else {                        // Run flywheel at half power if no target is detected for quick spool
        flywheelAutoPower = 0.5;
    }

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
