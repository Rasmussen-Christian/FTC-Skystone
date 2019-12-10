package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "FTC_Controller", group = "Linear Opmode")
public class FTC_Controller extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Intialize variables
        boolean capstoneArmUp = true;
        boolean intakeStopped = true;
        boolean jointUp = true;
        double speedMulti = .4;
        double rightPower;
        double leftPower;
        double secondPowerRight;
        double secondPowerLeft;
        boolean goingIn;
        
        // Initialize components
        DcMotor motorZero = hardwareMap.dcMotor.get("motorZero");
        DcMotor motorOne = hardwareMap.dcMotor.get("motorOne");
        DcMotor motorTwo = hardwareMap.dcMotor.get("motorTwo");
        DcMotor motorThree = hardwareMap.dcMotor.get("motorThree");
        DcMotor motorFour = hardwareMap.dcMotor.get("motorFour");
        DcMotor motorFive = hardwareMap.dcMotor.get("motorFive");
        DcMotor slideMotor = hardwareMap.dcMotor.get("slideMotor");
        Servo capstoneServo = hardwareMap.servo.get("servoZero");
        Servo jointServo = hardwareMap.servo.get("servoOne");
        Servo shoulderServo = hardwareMap.servo.get("servoTwo");
        Servo platformServoOne = hardwareMap.servo.get("platformServo1");
        Servo platformServoTwo = hardwareMap.servo.get("platformServo2");
        
        // Set things to starting positions
        platformServoOne.setPosition(1);
        platformServoTwo.setPosition(.32);
        jointServo.setPosition(0);
        capstoneServo.setPosition(.5);
        waitForStart();
        // Reset encoder count kept by left motor.
        // slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        // telemetry.addData("encoder-fwd", slideMotor.getCurrentPosition() + "  busy=" + slideMotor.isBusy());
        //  telemetry.update();
        if (opModeIsActive())
            while (opModeIsActive()) {
                rightPower = gamepad1.right_stick_y;
                leftPower = gamepad1.left_stick_y;
                secondPowerRight = (gamepad2.right_stick_x);
                secondPowerLeft = gamepad2.left_stick_y;
                goingIn = true;
                motorZero.setPower(leftPower);
                motorOne.setPower(-rightPower);
                motorTwo.setPower(leftPower);
                motorThree.setPower(-rightPower);
                if (gamepad1.dpad_left) {
                    motorZero.setPower(-.4);
                    motorOne.setPower(-.4);
                    motorTwo.setPower(.4);
                    motorThree.setPower(.4);
                } if (gamepad1.dpad_right) {
                    motorZero.setPower(.4);
                    motorOne.setPower(.4);
                    motorTwo.setPower(-.4);
                    motorThree.setPower(-.4);
                } if (gamepad1.dpad_up) {
                    motorZero.setPower(-.4);
                    motorOne.setPower(.4);
                    motorTwo.setPower(-.4);
                    motorThree.setPower(.4);
                } if (gamepad1.dpad_down) {
                    motorZero.setPower(.4);
                    motorOne.setPower(-.4);
                    motorTwo.setPower(.4);
                    motorThree.setPower(-.4);
                } if(gamepad1.a){
                    motorZero.setPower(.4);
                    motorThree.setPower(-.4);
                } if(gamepad1.y){
                    motorZero.setPower(-.4);
                    motorThree.setPower(.4);
                } if(gamepad1.x){
                    motorOne.setPower(-.4);
                    motorTwo.setPower(.4);
                } if(gamepad1.b){
                    motorOne.setPower(.4);
                    motorTwo.setPower(-.4);
                } if (gamepad1.left_trigger > 0) {
                    motorZero.setPower(.7);
                    motorThree.setPower(-.7);
                } if (gamepad1.right_trigger > 0) {
                    motorZero.setPower(-.7);
                    motorThree.setPower(.7);
                }
                // Inverts the goingIn boolean that changes the direction of the intake motors
                if(gamepad1.back)
                    goingIn = !goingIn;
                // Stops the intake if the right bumper is pressed.
                if(gamepad1.right_bumper)
                    intakeStopped = true;
                // Starts the intake if the left bumper is pressed.
                if(gamepad1.left_bumper)
                    intakeStopped = false;
                if(gamepad2.dpad_up && slideMotor.getCurrentPosition() > -5300)
                    slideMotor.setPower(-.4);
                // Allows the slide motor to move down if shoulderServo is out.  Sets different val
                else if(gamepad2.dpad_down && (shoulderServo.getPosition() == .685 && slideMotor.getCurrentPosition() < -740))
                    slideMotor.setPower(.4);
                // Allows the slide motor to move down if shoulderServo is not out.  Sets different val
                else if(gamepad2.dpad_down && (shoulderServo.getPosition() == 0 && slideMotor.getCurrentPosition() < -140))
                    slideMotor.setPower(.4);
                else
                    slideMotor.setPower(0);
                // Makes the shoulder servo move in (not facing intake)
                if(gamepad2.dpad_left && slideMotor.getCurrentPosition() <= -3200)
                   shoulderServo.setPosition(0);
                // Makes the shoulder servo move out (facing intake)
                if(gamepad2.dpad_right && slideMotor.getCurrentPosition() <= -3200)
                    shoulderServo.setPosition(.685);
                // Makes the joint (clamp) servo move up
                if(gamepad2.y)
                    jointServo.setPosition(-1);
                // Makes the joint (clamp) servo move down
                if(gamepad2.x)
                    jointServo.setPosition(.5);
                // Makes the intake go in if the intake motors are currently going outwards
                if(!intakeStopped && !goingIn){
                    motorFour.setPower(-.5);
                    motorFive.setPower(.5 * speedMulti);
                }
                // Makes the intake go out (reverse) if the intake motors are currently going inwards
                if(!intakeStopped && goingIn){
                    motorFour.setPower(.5);
                    motorFive.setPower(-.5);
                }
                // Makes the intake stopped if intakeStopped is true
                if(intakeStopped){
                    motorFour.setPower(0);
                    motorFive.setPower(0);
                }
                if(gamepad2.right_bumper){
                    platformServoOne.setPosition(1);
                    platformServoTwo.setPosition(.3);
                }
                if(gamepad2.left_bumper){
                    platformServoOne.setPosition(.7);
                    platformServoTwo.setPosition(.7);
                }
                // Makes the capstone servo go down
                if(gamepad2.back)
                    capstoneServo.setPosition(0);
                if(gamepad2.start)
                    capstoneServo.setPosition(.5);
                telemetry.addData("Pos", "Slide: " + slideMotor.getCurrentPosition());
                telemetry.addData("Pos", "Servos " + platformServoOne.getPosition());
                telemetry.addData("Pos", "Servos2 " + platformServoTwo.getPosition());
                telemetry.update();
            }
    }
}
