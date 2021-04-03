// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Macro;

import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class MacroRecord extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Macro macroSubsystem;
  private final DriveBase driveBase;
  private final Intake intake;

  private long startTime;

  private FileWriter writer;

  /**
   * Creates a new MacroRecord.
   *
   * @param subsystem The subsystem used by this command.
   */
  public MacroRecord(Macro macroSubsystem, DriveBase driveBase, Intake intake) throws IOException {

    this.macroSubsystem = macroSubsystem;
    this.driveBase = driveBase;
    this.intake = intake;

    writer = new FileWriter(macroSubsystem.getAutoFile());

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(macroSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    startTime = System.currentTimeMillis();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    try {
      if (writer != null) {

        writer.append("" + (System.currentTimeMillis() - startTime));

        writer.append("," + driveBase.leftDrive.get());
        writer.append("," + driveBase.rightDrive.get());
        writer.append("," + driveBase.centerDrive.get());

        writer.append("," + intake.intakeWheelMotor.get() + "\n");


      }
    } catch (IOException ie) {
      ie.printStackTrace();
    }



  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    try {
      if (writer != null) {
        writer.flush();
        writer.close();
      }
    } catch (IOException ie) {
      ie.printStackTrace();
    }

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
