package frc.robot.commands;

import frc.robot.subsystems.Indexer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexBallAutoStop extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Indexer indexer;
  private boolean commandOver = false;

  /**
   * Creates a new IndexBallAutoStop.
   *
   * @param shooter The subsystem used by this command.
   */
  public IndexBallAutoStop(Indexer indexer) {
    this.indexer = indexer;

    addRequirements(indexer);
  }

  @Override
  public void initialize() {

    if (indexer.getDetectionSwitch()) {
      commandOver = true;
    } else {
      indexer.setIndexerPower(1.0);
    }   

  }

  @Override
  public void execute() {

    if (indexer.getDetectionSwitch()) {
      commandOver = true;
    }

  }

  @Override
  public void end(boolean interrupted) {
    indexer.setIndexerPower(0.0);
  }

  @Override
  public boolean isFinished() {
    return commandOver;
  }
}
