/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	//Subsystems
	private final Drivetrain m_drivetrain = new Drivetrain();
	private final Arm m_arm = new Arm();
	private final Cargo m_cargo = new Cargo();
	
	//Commands
	private final TeleopArm m_teleopArm = new TeleopArm(m_arm);
	private final TeleopDrive m_teleopDrive = new TeleopDrive(m_drivetrain);
	private final TeleopCargo m_teleopCargo = new TeleopCargo(m_cargo);

	// OI Devices
	public static Joystick driverLeft = new Joystick(Constants.driverJoyLeft);
	public static Joystick driverRight = new Joystick(Constants.driverJoyRight);
	public static XboxController operator = new XboxController(Constants.operatorController);

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		// Default Commands
		m_drivetrain.setDefaultCommand(m_teleopDrive);
		m_arm.setDefaultCommand(m_teleopArm);
		m_cargo.setDefaultCommand(m_teleopCargo);

		// Configure the button bindings
		configureButtonBindings();
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// An ExampleCommand will run in autonomous
		return m_teleopDrive;
	}
}