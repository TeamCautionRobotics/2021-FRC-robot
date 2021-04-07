// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutoFlywheel;
import frc.robot.commands.CompleteAutonomousCommandGroup;
import frc.robot.commands.IndexBall;
import frc.robot.commands.IndexBallAutoStop;
import frc.robot.commands.IntakeRun;
import frc.robot.commands.RunFlywheel;
import frc.robot.misc2021.AutonomousRouteDataWrapperClass;
import frc.robot.misc2021.EnhancedJoystick;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
  SpeedControllerGroup indexerMotorGroup;
  SpeedControllerGroup intakeMotorGroup;
  SpeedControllerGroup flywheelMotorGroup;

  VictorSP leftDrive0;
  WPI_VictorSPX leftDrive1;

  VictorSP rightDrive0;
  VictorSP rightDrive1;

  VictorSP centerDrive0;
  WPI_VictorSPX centerDrive1;

  VictorSP intakeMotor;

  VictorSP indexerMotor0;

  VictorSP flywheelMotor0;
  VictorSP flywheelMotor1;

  Encoder flywheelEncoder;
  DigitalInput shooterSwitch;

  DriveBase driveBase;
  Indexer indexer;
  Shooter shooter;
  Intake intake;

  ArcadeDrive arcadeDriveCommand;

  AutonomousRouteDataWrapperClass autonomousRouteDataWrapperClass;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    leftJoystick = new EnhancedJoystick(Constants.LEFT_JOYSTICK_PORT);
    rightJoystick = new EnhancedJoystick(Constants.RIGHT_JOYSTICK_PORT);

    leftDrive0 = new VictorSP(Constants.LEFT_DRIVE_MOTOR_0_ID);
    leftDrive1 = new WPI_VictorSPX(Constants.LEFT_DRIVE_MOTOR_1_ID);

    rightDrive0 = new VictorSP(Constants.RIGHT_DRIVE_MOTOR_0_ID);
    rightDrive1 = new VictorSP(Constants.RIGHT_DRIVE_MOTOR_1_CAN_ID);

    centerDrive0 = new VictorSP(Constants.CENTER_DRIVE_MOTOR_0_ID);
    centerDrive1 = new WPI_VictorSPX(Constants.CENTER_DRIVE_MOTOR_1_CAN_ID);

    intakeMotor = new VictorSP(Constants.INTAKE_WHEEL_MOTOR_ID);

    indexerMotor0 = new VictorSP(Constants.INDEXER_MOTOR_0_ID);

    flywheelMotor0 = new VictorSP(Constants.FLYWHEEL_MOTOR_0_ID);
    flywheelMotor1 = new VictorSP(Constants.FLYWHEEL_MOTOR_1_ID);

    flywheelEncoder = new Encoder(Constants.FLYWHEEL_ENCODER_PORT_A, Constants.FLYWHEEL_ENCODER_PORT_B);
    shooterSwitch = new DigitalInput(Constants.SHOOTER_LIMIT_SWITCH_PORT);

    leftDriveGroup = new SpeedControllerGroup(leftDrive0, leftDrive1);
    rightDriveGroup = new SpeedControllerGroup(rightDrive0, rightDrive1);
    centerDriveGroup = new SpeedControllerGroup(centerDrive0, centerDrive1);
    indexerMotorGroup = new SpeedControllerGroup(indexerMotor0);
    intakeMotorGroup = new SpeedControllerGroup(intakeMotor);
    flywheelMotorGroup = new SpeedControllerGroup(flywheelMotor0, flywheelMotor1);

    rightDrive0.setInverted(true);
    rightDrive1.setInverted(true);

    centerDrive0.setInverted(true);
    centerDrive1.setInverted(true);

    indexerMotor0.setInverted(true);

    flywheelMotor0.setInverted(true);

    intakeMotor.setInverted(true);

    driveBase = new DriveBase(leftDriveGroup, rightDriveGroup, centerDriveGroup, 
                              Constants.LEFT_DRIVE_ENCODER_PORT_A, Constants.LEFT_DRIVE_ENCODER_PORT_B,
                              Constants.RIGHT_DRIVE_ENCODER_PORT_A, Constants.RIGHT_DRIVE_ENCODER_PORT_B,
                              Constants.CENTER_DRIVE_ENCODER_PORT_A, Constants.CENTER_DRIVE_ENCODER_PORT_B);

    indexer = new Indexer(indexerMotorGroup, shooterSwitch);

    intake = new Intake(intakeMotorGroup);

    shooter = new Shooter(flywheelMotorGroup, flywheelEncoder);

    autonomousRouteDataWrapperClass = new AutonomousRouteDataWrapperClass();

    // Configure the button bindings
    configureButtonBindings();

    driveBase.setDefaultCommand(new ArcadeDrive(driveBase, () -> leftJoystick.getX(), () -> leftJoystick.getY(), 
    () -> rightJoystick.getX(), () -> rightJoystick.getY(), 0.04, 0.04));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {


    new JoystickButton(rightJoystick, 1).whileHeld(new IndexBall(indexer));
    new JoystickButton(rightJoystick, 2).whenPressed(new IndexBallAutoStop(indexer));
    new JoystickButton(leftJoystick, 1).whileHeld(new IntakeRun(intake));
    new JoystickButton(rightJoystick, 3).toggleWhenActive(new AutoFlywheel(shooter));
    new JoystickButton(rightJoystick, 5).toggleWhenActive(new RunFlywheel(shooter, () -> leftJoystick.getZ()));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    
    // Do nothing in autonomous.

    return new CompleteAutonomousCommandGroup(driveBase, intake, autonomousRouteDataWrapperClass);
  }
}
