// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Macro;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class MacroPlay extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private boolean commandFinished = false;

  private final Macro macroSubsystem;
  private final DriveBase driveBase;
  private final Intake intake;

  private Scanner scanner;
  private boolean onTime = true;
  private double nextDouble;

  private long startTime;

  /**
   * Creates a new MacroRecord.
   *
   * @param subsystem The subsystem used by this command.
   */
  public MacroPlay(Macro macroSubsystem, DriveBase driveBase, Intake intake) throws IOException {

    this.macroSubsystem = macroSubsystem;
    this.driveBase = driveBase;
    this.intake = intake;

    scanner = new Scanner(new File(macroSubsystem.getAutoFile()));
    scanner.useDelimiter(",|\\n");

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(macroSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    startTime = System.currentTimeMillis();

    if (macroSubsystem.getIsRecording()) {    // ya can't record and play at the same time, dum dum
      commandFinished = true;
    }

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if ((scanner != null) && scanner.hasNextDouble()) {

      double t_delta;

      if (onTime) {
        nextDouble = scanner.nextDouble();
      }

      t_delta = nextDouble - (System.currentTimeMillis() - startTime);

      if (t_delta <= 0) {

        driveBase.leftDrive.set(scanner.nextDouble());
        driveBase.rightDrive.set(scanner.nextDouble());
        driveBase.centerDrive.set(scanner.nextDouble());

        intake.intakeWheelMotor.set(scanner.nextDouble());

        onTime = true;

      } else {
        onTime = false;
      }

    } else {
      if (scanner != null) {
        scanner.close();
        scanner = null;
        commandFinished = true;
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    driveBase.leftDrive.set(0);
    driveBase.rightDrive.set(0);
    driveBase.centerDrive.set(0);

    intake.intakeWheelMotor.set(0);

    commandFinished = false;

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return commandFinished;
  }
}
