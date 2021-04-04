package frc.robot.commands;

import frc.robot.subsystems.Indexer;

import javax.naming.ldap.StartTlsRequest;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexBallAutoStop extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Indexer indexer;
  private boolean commandOver = false;

  private boolean startingState;
  private boolean rolledOverOne;

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

    // Did we start with a ball on the switch?
    startingState = indexer.getDetectionSwitch();

    indexer.setIndexerPower(1.0);

  }

  @Override
  public void execute() {

    // If we started with a ball on the switch
   if (startingState) {

    // Motor is running, so run until there's no ball on the switch
     if (!startingState == indexer.getDetectionSwitch()) {
       // Mark that the ball is now shot
       rolledOverOne = true;
     }

     // if we've shot the ball, detect another
     if (rolledOverOne) {
       if (indexer.getDetectionSwitch()) {
         // STOP! Another ball is on the switch!
         commandOver = true;
       }
     }

     // If there wasn't a ball on the switch
   } else {
     if (indexer.getDetectionSwitch()) {
       commandOver = true;
     }
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
