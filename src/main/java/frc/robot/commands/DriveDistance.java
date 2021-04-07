package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class DriveDistance extends CommandBase{
    private final DriveBase driveBase;
    private final double distance;
    private final double speed;

    public DriveDistance(DriveBase driveBase, double distance, double speed){
        this.driveBase = driveBase;
        this.distance = distance;
        this.speed = speed;
        addRequirements(driveBase);
    }

    @Override
    public void initialize(){
        driveBase.resetEncoders();
        driveBase.setLeftPower(speed);
        driveBase.setRightPower(speed);
    }

    @Override
  public void execute() {
    driveBase.setLeftPower(speed);
    driveBase.setRightPower(speed);
  }

  @Override
  public void end(boolean interrupted) {
    driveBase.setLeftPower(0);
    driveBase.setRightPower(0);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(driveBase.getForwardDistance()) >= distance;
  }
}
