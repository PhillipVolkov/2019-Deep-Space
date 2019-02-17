/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

// import edu.wpi.first.hal.can.CANMessageNotAllowedException;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ArmSubsystem extends Subsystem{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX armR = new WPI_TalonSRX(4);
  private WPI_TalonSRX armL = new WPI_TalonSRX(5);

  private double kP = 0;
  private double kI;
  private double kD;
  private double kF;
  public int kIzone;
  public double kPeakOutput;
  public boolean armToggle = false;

  private PIDSource source;
  private PIDOutput output;

  private PIDController pidController = new PIDController(kP, kI, kD, source, output, 0);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void startTheStuff(double randomValue) {
    armL.follow(armR);
    armL.setInverted(true);

    
    armR.config_kP(0, kP);
    armR.config_kI(0, kI);
    armR.config_kD(0, kD);
    armR.config_kF(0, kF);
    armR.configMotionAcceleration(10);
    armR.configMotionCruiseVelocity(5);
  }

  public void toggleArm() {
    if (armToggle == true) {
      armR.set(ControlMode.MotionMagic, 20);
    } else {
      armR.set(ControlMode.MotionMagic, 0);
    }

    armToggle = !armToggle;
  }




}
