/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
	// USB
	public static final int driveController = 0;
	public static final int operatorController = 1;
	public static final int driverJoyLeft = 2;
	public static final int driverJoyRight = 3;

	// CAN
	public static final int leftDriveMotor = 2;
	public static final int leftDriveSPX1 = 1;
	public static final int leftDriveSPX2 = 2;
	public static final int rightDriveMotor = 1;
	public static final int rightDriveSPX1 = 3;
	public static final int rightDriveSPX2 = 4;

	// PCM
	public static final int doubleSolenoidA = 1;
	public static final int doubleSolenoidB = 0;

	// PWM
	public static final int armWinch = 0;
	public static final int cargoWheelMotor = 1;
	public static final int dartHandlerLeft = 2;
	public static final int dartHandlerRight = 3;
	public static final int stripLED = 7;

	// Analog Ports
	public static final int dartPOTRight = 0;
	public static final int dartPOTLeft = 1;
	public static final int stringPot = 2;
	public static final int pressureSensor = 3;

	// Digital Ports
	public static final int lineSensorLeft = 0;
	public static final int lineSensorRight = 1;

	
}
