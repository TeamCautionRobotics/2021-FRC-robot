package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {

  SpeedControllerGroup indexerMotor;

  public Indexer(SpeedControllerGroup indexerMotor) {

    this.indexerMotor = indexerMotor;

  }

  public void setIndexerPower(double power) {
    indexerMotor.set(power);
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
