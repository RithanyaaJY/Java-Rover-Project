# Java-Rover-Project
Project Overview

This project involves programming a wireless rover using Java and Phidgets hardware. The rover uses joystick inputs to control its movement and light sensors to detect differences in light intensity. Based on the difference between the left and right light sensor readings, the rover performs programmed movement patterns, creating a light-responsive dance routine.

Features
Wireless Communication: Connects to the rover through the Phidgets network server.
Joystick Control: Uses vertical and horizontal joystick inputs to control the rover's direction and movement.
Light Detection: Reads illuminance values from two light sensors and calculates the difference between them.
Responsive Movement: Uses the light difference to determine whether the rover performs a left or right dance routine.
Iterative Algorithms: Uses loops to repeat sequences of programmed movements.
Motor Control: Adjusts the velocity of the left and right DC motors to create forward, backward, spinning, and zigzag movements.

Technologies Used

Java
Phidgets Java Library
Phidgets Light Sensors
Phidgets DC Motors
Joystick inputs using voltage ratio sensors
Wireless rover hardware

How It Works

Connect to the rover: The program connects to the rover through a Phidgets network server and initializes the sensors and motors.
Read joystick inputs: The vertical and horizontal axis values are combined to calculate the target velocities for the left and right motors.
Limit motor speeds: The calculated motor velocities are constrained to values between -1 and 1.
Measure light intensity: The program reads both light sensors and calculates the difference between their illuminance values.
Trigger a dance routine: If the absolute light difference is less than 10, the rover stops. Otherwise, it executes a left or right dance routine based on which sensor detects more light.
Repeat movements: Each dance routine uses a loop to repeat three different motor movement patterns five times.

Programming Concepts Demonstrated

Object-oriented programming
Hardware and sensor integration
Conditional statements (if, else if, else)
Iteration using for and while loops
Variables and arithmetic operations
Real-time sensor readings and motor control
Basic differential-drive movement control

Project Goal

The goal of this project is to explore the integration of software and hardware by combining sensor inputs, motor control, and iterative algorithms to create an interactive robotic system.

Future Improvements

Refine the joystick and light-sensor control logic to make switching between manual control and automatic dance routines more predictable.
Experiment with different light thresholds and movement sequences.
Add safer shutdown handling so the motors stop when the program exits.
