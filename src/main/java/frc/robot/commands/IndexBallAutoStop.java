package frc.robot.commands;

import frc.robot.subsystems.Indexer;

import javax.naming.ldap.StartTlsRequest;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IndexBallAutoStop extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Indexer indexer;
  private boolean commandOver = false;

  private boolean startingState;
  private boolean rolledOverOne;
  private boolean timerHasBeenReset = false;

  private Timer reverseTimer;

  /**
   * Creates a new IndexBallAutoStop.
   *
   * @param shooter The subsystem used by this command.
   */
  public IndexBallAutoStop(Indexer indexer) {
    this.indexer = indexer;

    reverseTimer = new Timer();

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

  //   // If we started with a ball on the switch
  //  if (startingState) {

  //   // Motor is running, so run until there's no ball on the switch
  //    if (!startingState == indexer.getDetectionSwitch()) {
  //      // Mark that the ball is now shot
  //      rolledOverOne = true;
  //    }

  //    // if we've shot the ball, detect another
  //    if (rolledOverOne) {
  //      if (indexer.getDetectionSwitch()) {
  //        // STOP! Another ball is on the switch!

  //        if (!timerHasBeenReset) {
  //         reverseTimer.reset();
  //         timerHasBeenReset = true;
  //       }

  //        if (reverseTimer.get() < 0.2) {
  //          indexer.setIndexerPower(-1.0);
  //        } else {
  //         commandOver = true;
  //        }
  //      }
  //    }

     // If there wasn't a ball on the switch
  // } else {
    
  if (indexer.getDetectionSwitch()) {

  if (!timerHasBeenReset) {
    reverseTimer.reset();
    reverseTimer.start();
    timerHasBeenReset = true;
  }

  SmartDashboard.putNumber("timer", reverseTimer.get());
  SmartDashboard.putBoolean("timer reset", timerHasBeenReset);

  if (reverseTimer.get() < 0.1) {
    indexer.setIndexerPower(-0.25);
  } else {
    indexer.setIndexerPower(0.0);
    commandOver = true;
  }

  }
}

  // }

  @Override
  public void end(boolean interrupted) {
    indexer.setIndexerPower(0.0);
    commandOver = false;
    timerHasBeenReset = false;
  }

  @Override
  public boolean isFinished() {
    return commandOver;
  }
}
