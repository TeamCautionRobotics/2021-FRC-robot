package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class IndexBall extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Shooter shooter;

  /**
   * Creates a new IndexBall.
   *
   * @param shooter The subsystem used by this command.
   */
  public IndexBall(Shooter shooter) {
    this.shooter = shooter;

    addRequirements(shooter);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooter.setIndexerPower(0.1);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.setIndexerPower(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
