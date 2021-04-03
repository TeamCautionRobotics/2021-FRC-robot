package frc.robot.commands;

import frc.robot.subsystems.Shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class RunFlywheel extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter shooter;

  private DoubleSupplier power;

  /**
   * Creates a new RunFlywheel.
   *
   * @param shooter The subsystem used by this command.
   */
  public RunFlywheel(Shooter shooter, DoubleSupplier power) {

    this.power = power;

    this.shooter = shooter;

    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.setFlywheelPower(power.getAsDouble());
    SmartDashboard.putNumber("moar powa baby", power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.setFlywheelPower(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
