package frc.robot.commands;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DropIntake extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMaD.SingularField"})

  private final Intake intakeSubsystem;
  private final Timer dropTimer;

  private boolean finishedState = false;

  /**
   * Creates a new DropIntake command.
   *
   * @param intakeSubsystem The subsystem used by this command.
   */
  public DropIntake(Intake intakeSubsystem) {

    this.intakeSubsystem = intakeSubsystem;
    dropTimer = new Timer();

    addRequirements(intakeSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    finishedState = false;
    dropTimer.reset();
    dropTimer.start();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (dropTimer.get() < 0.5 ) {
      intakeSubsystem.setIntakeWheelPower(1);
    } else {
      intakeSubsystem.setIntakeWheelPower(0);
      finishedState = true;
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.setIntakeWheelPower(0);
    dropTimer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finishedState;
  }
}
