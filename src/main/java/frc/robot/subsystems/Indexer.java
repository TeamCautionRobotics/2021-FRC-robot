package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {

  SpeedControllerGroup indexerMotor;
  DigitalInput detectionSwitch;

  public Indexer(SpeedControllerGroup indexerMotor, DigitalInput detectionSwitch) {

    this.indexerMotor = indexerMotor;
    this.detectionSwitch = detectionSwitch;

  }

  public void setIndexerPower(double power) {
    indexerMotor.set(power);
  }

  public boolean getDetectionSwitch() {
    return !detectionSwitch.get();
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
