/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * An example command.  You can replace me with your own command.
 */
public class AutoDriveCommand extends Command {
  static final double kTargetAngle = 180.0;
  private double rotation;
 
  static double kP = Robot.m_drivesub.kP;
  static double kI = Robot.m_drivesub.kI;
  static double kD = Robot.m_drivesub.kD;
  private PIDSource pidSource = Robot.m_drivesub.ahrs;
  private final PIDController pidController = new PIDController(kP, kI, kD, pidSource, new PIDOutput() {
    public void pidWrite(double input) {
      rotation = input;
      SmartDashboard.putNumber("rotation error", rotation);
    }
  });

  public AutoDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivesub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    pidController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("current rotation", Robot.m_drivesub.ahrs.getAngle());
    curveExecute();
  }

  protected void curveExecute() {
    
    
    // Robot.m_drivesub.curvatureDrive(Math.sin(sineMovement),
    // rotation * 0.00, // set to 0 to disable pid, breaking in gearboxes
    // true);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
