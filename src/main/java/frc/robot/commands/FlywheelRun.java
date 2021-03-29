package frc.robot.commands;

import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class FlywheelRun extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter shooter;

  /**
   * Creates a new ShootBall.
   *
   * @param shooter The subsystem used by this command.
   */
  public FlywheelRun(Shooter shooter) {
    this.shooter = shooter;

    addRequirements(shooter);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    shooter.setFlywheelPower(0.7);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.setFlywheelPower(0.0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
