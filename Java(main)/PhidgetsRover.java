package gettingstarted1;
import com.phidget22.*;
public class FinalProject {
    public static void main(String[] args) throws Exception {

        // Connect to wireless rover
        Net.addServer("", "192.168.100.1", 5661, "", 0);

        // Create
        LightSensor leftLight = new LightSensor();
        LightSensor rightLight = new LightSensor();
        DCMotor leftMotors = new DCMotor();
        DCMotor rightMotors = new DCMotor();
        VoltageRatioInput vAxis = new VoltageRatioInput(); 
        VoltageRatioInput hAxis = new VoltageRatioInput(); 

        // Address
        leftMotors.setChannel(0);
        rightMotors.setChannel(1);
        rightLight.setHubPort(1);
        leftLight.setHubPort(0);
        vAxis.setChannel(1);
        hAxis.setChannel(0);

        // Open
        leftLight.open(5000);
        rightLight.open(5000);
        leftMotors.open(5000);
        rightMotors.open(5000);
        vAxis.open(5000);
        hAxis.open(5000);

        // Increase acceleration
        leftMotors.setAcceleration(leftMotors.getMaxAcceleration());
        rightMotors.setAcceleration(rightMotors.getMaxAcceleration());
        

        // Use your Phidgets
        while(true) {
            //Get data from vertical and horizontal axis (values between -1 and 1)
            double verticalAxis = vAxis.getVoltageRatio();
            double horizontalAxis = hAxis.getVoltageRatio();

            //Use thumbstick data to figure how each side of rover should move
            double leftMotorsSpeed = verticalAxis + horizontalAxis;
            double rightMotorsSpeed = verticalAxis - horizontalAxis;

            //Limit values to between -1 and 1
            if(leftMotorsSpeed > 1) leftMotorsSpeed = 1;
            if(leftMotorsSpeed < -1) leftMotorsSpeed = -1;
            if(rightMotorsSpeed > 1) rightMotorsSpeed = 1;
            if(rightMotorsSpeed < -1) rightMotorsSpeed = -1;

            //Apply values 
            leftMotors.setTargetVelocity(leftMotorsSpeed); 
            rightMotors.setTargetVelocity(rightMotorsSpeed);
 
            //Wait for 100 milliseconds
            Thread.sleep(100);
            
            // Determine which direction to move
            double lightDiff = leftLight.getIlluminance() - rightLight.getIlluminance();
            System.out.println("Light Diff: " + lightDiff);

            // If difference is low, do nothing
            if (Math.abs(lightDiff) < 10) {
                leftMotors.setTargetVelocity(0);
                rightMotors.setTargetVelocity(0);
            }
            // If there is more light to the left, perform a left "dance"
            else if (lightDiff > 0) {
                System.out.println("Dancing left!");
                
                // ITERATIVE ALGORITHM (Left Dance)
                for (int i = 0; i < 5; i++) {
                    // Forward movement
                    leftMotors.setTargetVelocity(1);
                    rightMotors.setTargetVelocity(0.3);
                    Thread.sleep(300);

                    // Spin in place
                    leftMotors.setTargetVelocity(-0.3);
                    rightMotors.setTargetVelocity(0.3);
                    Thread.sleep(300);

                    // Zig-zag left movement
                    leftMotors.setTargetVelocity(0.4);
                    rightMotors.setTargetVelocity(-0.2);
                    Thread.sleep(300);
                }
            }
            // If there is more light to the right, perform a right "dance"
            else {
                System.out.println("Dancing right!");

                // ITERATIVE ALGORITHM (Right Dance)
                for (int i = 0; i < 5; i++) {
                    // Backward movement
                    leftMotors.setTargetVelocity(-0.3);
                    rightMotors.setTargetVelocity(-1);
                    Thread.sleep(300);

                    // Spin in the opposite direction
                    leftMotors.setTargetVelocity(0.3);
                    rightMotors.setTargetVelocity(-0.3);
                    Thread.sleep(300);

                    // New movement: Zig-zag right
                    leftMotors.setTargetVelocity(-0.2);
                    rightMotors.setTargetVelocity(0.4);
                    Thread.sleep(300);
                }
            }
            // Pause between light checks
            Thread.sleep(250);
        }
    }
}