package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallTransfer extends SubsystemBase {

  private boolean motorPowerLimitActive = false;
  private double motorPowerLimitAmount = 0.0;

  private final SpeedControllerGroup transferMotor;

  /** Creates a new BallTransfer subsystem. */
  public BallTransfer(SpeedControllerGroup transferMotor) {

    this.transferMotor = transferMotor;

  }

    // Check that the motor power has not exceeded a set limit 
    private double checkPowerLimit(double power) {
      if (motorPowerLimitActive) {
  
        if (power > motorPowerLimitAmount) {
          power = motorPowerLimitAmount;
        } else if (power < -motorPowerLimitAmount) {
          power = -motorPowerLimitAmount;
        } 
      } 
  
      return power;
    }

  public void moveBalls(double power) {
    transferMotor.set(checkPowerLimit(power));
  }

}
