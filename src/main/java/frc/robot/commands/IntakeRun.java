package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeRun extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake intakeSubsystem;

  /**
   * Creates a new IntakeRun.
   *
   * @param intakeSubsystem The subsystem used by this command.
   */
  public IntakeRun(Intake intakeSubsystem) {
    this.intakeSubsystem = intakeSubsystem;

    addRequirements(intakeSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intakeSubsystem.setIntakeWheelPower(0.7);
  }

  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.setIntakeWheelPower(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
