/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.*;
import frc.robot.Constants;

public class ColorSpinner extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
   private String[] clrOrder = {"red", "green", "blue", "yellow"};
   String matchedClr = "not Spinning";
   private WPI_TalonSRX spinnerMotor = new WPI_TalonSRX(7);
   private ColorSensorV3 clrSensor = new ColorSensorV3(I2C.Port.kOnboard);
   private ColorMatch clrMatch = new ColorMatch();
   private boolean rotating = false;
   private boolean clockwise = true;
   private Color matchedColor;
   private int index = -1; 
   private double rotations = 0;

   //DoubleSolenoid doubleSolenoid = new DoubleSolenoid(Constants.doubleSolenoidA, Constants.doubleSolenoidB);
   
  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public ColorSpinner() {
    clrMatch.addColorMatch(kBlueTarget);
    clrMatch.addColorMatch(kGreenTarget);
    clrMatch.addColorMatch(kRedTarget);
    clrMatch.addColorMatch(kYellowTarget);   
    setColor();
    resetRotations();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    rotating();
    displayColor();
    SmartDashboard.putString("COLORS", clrOrder[getIndex(clrMatch.matchClosestColor(clrSensor.getColor()).color)]);
    displayRGB(); 
    SmartDashboard.putNumber("Rotations", rotations);
  }

  public void resetRotations() {
    rotations = 0;
  }

  public void rotating() {
    Color seenColor = clrMatch.matchClosestColor(clrSensor.getColor()).color;
    int seenIndex = getIndex(seenColor);
    if(!rotating && index!=seenIndex && (clockwise && (index+1)%4 == seenIndex || !clockwise && index == (seenIndex+1)%4)) {
      setColor();
      matchedClr = clrOrder[index];
      rotations +=.125;
    }

  }

  public void setColor() {
    matchedColor = clrMatch.matchClosestColor(clrSensor.getColor()).color;
    index = getIndex(matchedColor);
  }

  public int getIndex(Color clr) {

    if(clr.equals(kBlueTarget)){
      return 2;
    }
    else if(clr.equals(kYellowTarget)){
      return 3;
    }
    else if(clr.equals(kRedTarget)){
      return 0;
    }
    else if(clr.equals(kGreenTarget)){
      return 1;
    }
    return -1;
  }


  public void displayRGB() {
    SmartDashboard.putNumber("Red:", clrSensor.getRed());
    SmartDashboard.putNumber("Green:", clrSensor.getGreen());
    SmartDashboard.putNumber("Blue:", clrSensor.getBlue());
  }

  public void displayColor() {
    SmartDashboard.putString("Color", matchedClr);
    SmartDashboard.putNumber("Color Confidnece", clrMatch.matchClosestColor(clrSensor.getColor()).confidence);
  }

  /*public void up() {
    if (doubleSolenoid.get() != DoubleSolenoid.Value.kReverse) {
      doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
  }

  public void down() {
    if (doubleSolenoid.get() != DoubleSolenoid.Value.kForward) {
      doubleSolenoid.set(DoubleSolenoid.Value.kForward);
    }
  }*/

  public void setWheelSpeed(double speed) {
    if(Math.abs(speed)>.001) {
          rotating = true;
          setColor();
          if(speed>0) {
            clockwise = true;
          }
          else {
            clockwise = false;
          }
    }
    else {
      rotating = false;
      matchedClr = "not Spinning";
    }
    spinnerMotor.set(speed);
  }
}
