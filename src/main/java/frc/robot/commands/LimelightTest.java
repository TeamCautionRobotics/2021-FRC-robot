package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;


public class LimelightTest extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Limelight limelight;

  Timer testTimer;

  public LimelightTest(Limelight limelight) {

    this.limelight = limelight;
    testTimer = new Timer();
    
    addRequirements(limelight);
  }

  @Override
  public void initialize() {
    limelight.setLedMode(3);

  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("limelight tv", limelight.getTv());
    SmartDashboard.putNumber("limelight tx", limelight.getTx());
    SmartDashboard.putNumber("limelight ty", limelight.getTy());
    SmartDashboard.putNumber("limelight ta", limelight.getTa());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
