package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

  private double tv;
  private double tx;
  private double ty;
  private double ta;
  private double ts;

  public Limelight() {

  }

  private void setVar(String var, int data) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry(var).setNumber(data);
  }

  public double getTv() {
    return tv;
  }

  public double getTx() {
    return tx;
  }

  public double getTy() {
    return ty;
  }

  public double getTa() {
    return ta;
  }

  public double getTs() {
    return ts;
  }

  public void setLedMode(int mode) {
    setVar("ledMode", mode);
  }

  public void setPipeline(int pipeline) {
    setVar("pipeline", pipeline);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    ts = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
