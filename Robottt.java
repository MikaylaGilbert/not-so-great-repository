/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.print.DocFlavor.INPUT_STREAM;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.graalvm.compiler.core.phases.LowTier;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import jdk.javadoc.internal.doclets.toolkit.util.DocFinder.Output;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 * 
 * @param <include>
 */
public class Robot<include> extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  XboxController controller = new XboxController(0);
  Talon shootTop = new Talon(1);
  Talon shootBot = new Talon(0);
  Talon loader = new Talon(2);
  WPI_TalonSRX driveLeft = new WPI_TalonSRX(14);
  WPI_TalonSRX driveRight = new WPI_TalonSRX(15);
  WPI_TalonSRX driveLeftSlave = new WPI_TalonSRX(17);
  WPI_VictorSPX driveRightSlave = new WPI_VictorSPX(18);

  Servo regulator = new Servo(3);
  
 
}
//For clockwise rotation
digitalWrite (motor_pin_1, LOW);
digitalWrite(motor_pin_2, motor_speed);

  void setDriveMotors(double left, double right){
    driveLeft.set(ControlMode.PercentOutput, left);
    driveLeftSlave.set(driveLeft.getMotorOutputPercent());
    driveRight.set(ControlMode.PercentOutput, right);
    driveRightSlave.set(driveRight.getMotorOutputPercent());}
/*
  void setDriveMotors(double left, double right){
    driveLeft.set(ControlMode.PercentOutput, left);
    driveLeftSlave.set(driveLeft.getMotorOutputPercent());
    driveRight.set(ControlMode.PercentOutput, right);
    driveRightSlave.set(driveRight.getMotorOutputPercent());
  
  }

*/

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    // drive
    driveRight.configFactoryDefault();
    driveLeft.configFactoryDefault();
    driveRightSlave.configFactoryDefault();
    driveLeftSlave.configFactoryDefault();



    driveRight.setNeutralMode(NeutralMode.Brake);
    driveLeft.setNeutralMode(NeutralMode.Brake);
    driveRightSlave.setNeutralMode(NeutralMode.Brake);
    driveLeftSlave.setNeutralMode(NeutralMode.Brake);

    driveLeftSlave.follow(driveLeft);
    driveRightSlave.follow(driveRight);

    driveRight.setInverted(true);
    driveLeft.setInverted(false);
    driveRightSlave.setInverted(InvertType.FollowMaster);
    driveLeftSlave.setInverted(InvertType.FollowMaster);
    //shooter
   /*
    shootTop.configFactoryDefault();
    shootBot.configFactoryDefault();
    loader.configFactoryDefault();

    shootTop.setNeutralMode(NeutralMode.Coast);
    shootBot.setNeutralMode(NeutralMode.Coast);
    loader.setNeutralMode(NeutralMode.Brake);
    */
    shootTop.setInverted(false);
    shootBot.setInverted(true);
    loader.setInverted(true);
  
  }

   
  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    // drive train code

    

    // declare doubles for left and right output
    double driveLeftSpeed ;
    double driveRightSpeed ;
    

    // get joystick left hand Y and right hand X

    // set left output to x-y
    
    // right output to x+y
 
    driveLeftSpeed = controller.getRawAxis(4) - controller.getRawAxis(1);
    driveRightSpeed = controller.getRawAxis(4) + controller.getRawAxis(1);
    driveLeft.set(ControlMode.PercentOutput, driveLeftSpeed);
    driveRight.set(ControlMode.PercentOutput, driveRightSpeed);
        
    // shooter code
    double speedTop = 0;
    double speedBot = 0;
    double speedLoader = 0;
  
    if (controller.getRawButton(2)){
      speedTop = 0.9;
      speedBot = 1;
    }
    if (controller.getRawButton(8)){
      speedLoader = 0.9;
    }
    if (controller.getRawButton(3)){
      speedTop = 0.5;
      speedBot = 0.55;
    }
    if (controller.getRawButton(1)){
      speedTop = 0.55;
      speedBot = 0.55;
    }
    // set speeds to motors
    shootTop.set(speedTop);
    shootBot.set(speedBot);
    loader.set(speedLoader);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
