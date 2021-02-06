// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.misc2021.EnhancedJoystick;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  EnhancedJoystick leftJoystick;
  EnhancedJoystick rightJoystick;

  SpeedControllerGroup leftDriveGroup;
  SpeedControllerGroup rightDriveGroup;
  SpeedControllerGroup centerDriveGroup;

  VictorSP leftDrive0;
  VictorSP leftDrive1;

  VictorSP rightDrive0;
  VictorSP rightDrive1;

  VictorSP centerDrive0;
  VictorSP centerDrive1;

  DriveBase driveBase;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    leftJoystick = new EnhancedJoystick(Constants.LEFT_JOYSTICK_PORT);
    rightJoystick = new EnhancedJoystick(Constants.RIGHT_JOYSTICK_PORT);

    leftDrive0 = new VictorSP(Constants.LEFT_DRIVE_MOTOR_0_ID);
    leftDrive1 = new VictorSP(Constants.LEFT_DRIVE_MOTOR_1_ID);

    rightDrive0 = new VictorSP(Constants.RIGHT_DRIVE_MOTOR_0_ID);
    rightDrive1 = new VictorSP(Constants.RIGHT_DRIVE_MOTOR_1_ID);

    centerDrive0 = new VictorSP(Constants.CENTER_DRIVE_MOTOR_0_ID);
    centerDrive1 = new VictorSP(Constants.CENTER_DRIVE_MOTOR_1_ID);

    leftDriveGroup = new SpeedControllerGroup(leftDrive0, leftDrive1);
    rightDriveGroup = new SpeedControllerGroup(rightDrive0, rightDrive1);
    centerDriveGroup = new SpeedControllerGroup(centerDrive0, centerDrive1);

    driveBase = new DriveBase();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
