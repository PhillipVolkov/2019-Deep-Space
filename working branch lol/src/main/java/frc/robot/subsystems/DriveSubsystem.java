/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
//import com.sun.net.ssl.TrustManager;

import frc.robot.commands.DriveCommand;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public WPI_VictorSPX victor_fl = new WPI_VictorSPX(RobotMap.victor_fl);
  public WPI_VictorSPX victor_bl = new WPI_VictorSPX(RobotMap.victor_bl);
  SpeedControllerGroup d_left = new SpeedControllerGroup(victor_fl, victor_bl);

  public WPI_VictorSPX victor_fr = new WPI_VictorSPX(RobotMap.victor_fr);
  public WPI_VictorSPX victor_br = new WPI_VictorSPX(RobotMap.victor_br);
  SpeedControllerGroup d_right = new SpeedControllerGroup(victor_fr, victor_br);
  

  DifferentialDrive d_drive = new DifferentialDrive(d_left, d_right);
 
  //drive = new RobotDrive(victor_fl, victor_bl, victor_fr, victor_br);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    setDefaultCommand(new DriveCommand());
    // Inverting these speed controller groups lets the xbox joystick
    // directions match the robot's direction.
    d_left.setInverted(true);
    d_right.setInverted(true);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
   d_drive.tankDrive(leftSpeed, rightSpeed);
  }

  public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    d_drive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
  }

 
}
