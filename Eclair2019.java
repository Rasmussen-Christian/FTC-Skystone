package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Eclair2019", group = "")
public class Eclair2019 extends LinearOpMode {
    @Override
    public void runOpMode() {
        boolean capstoneArmUp = true;
        boolean intakeStopped = true;
        boolean jointUp = true;
        double speedMulti = .7;
        DcMotor motorZero = hardwareMap.dcMotor.get("motorZero");
        DcMotor motorOne = hardwareMap.dcMotor.get("motorOne");
        DcMotor motorTwo = hardwareMap.dcMotor.get("motorTwo");
        DcMotor motorThree = hardwareMap.dcMotor.get("motorThree");
        DcMotor motorFour = hardwareMap.dcMotor.get("motorFour");
        DcMotor motorFive = hardwareMap.dcMotor.get("motorFive");
        DcMotor slideMotor = hardwareMap.dcMotor.get("slideMotor");
        Servo capstoneServo = hardwareMap.servo.get("capstoneServo");
        Servo servoZero = hardwareMap.servo.get("servoZero");
        Servo shoulderServo = hardwareMap.servo.get("servoOne");
        Servo jointServo = hardwareMap.servo.get("servoTwo");
        
        waitForStart();
        
        // reset encoder count kept by left motor.
       // slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       // telemetry.addData("encoder-fwd", slideMotor.getCurrentPosition() + "  busy=" + slideMotor.isBusy());
      //  telemetry.update();
        
        if (opModeIsActive())
            while (opModeIsActive()) {
                double rightPower = gamepad1.right_stick_y;
                double leftPower = gamepad1.left_stick_y;
                double secondPowerRight = (gamepad2.right_stick_x);
                double secondPowerLeft = gamepad2.left_stick_y;
                boolean goingIn = true;
                motorZero.setPower(leftPower);
                motorOne.setPower(-rightPower);
                motorTwo.setPower(leftPower);
                motorThree.setPower(-rightPower);
    
                shoulderServo.setPosition(secondPowerRight);
                if(gamepad2.x)
                    jointUp = !jointUp;
                if(!jointUp)
                    jointServo.setPosition(.47);
                else
                    jointServo.setPosition(0);
                if (capstoneArmUp) {
                    capstoneServo.setPosition(0.5);
                    capstoneArmUp = !capstoneArmUp;
                }
                if (!capstoneArmUp && gamepad2.y) {
                    capstoneServo.setPosition(-0.5);
                    capstoneArmUp = !capstoneArmUp;
                }
                if (gamepad1.dpad_left) {
                    motorZero.setPower(-1);
                    motorOne.setPower(-1);
                    motorTwo.setPower(1);
                    motorThree.setPower(1);
                }
                if (gamepad1.dpad_right) {
                    motorZero.setPower(1);
                    motorOne.setPower(1);
                    motorTwo.setPower(-1);
                    motorThree.setPower(-1);
                }
                if (gamepad1.dpad_up) {
                    motorZero.setPower(1);
                    motorOne.setPower(1);
                    motorTwo.setPower(1);
                    motorThree.setPower(1);
                }
                if (gamepad1.dpad_down) {
                    motorZero.setPower(-1);
                    motorOne.setPower(-1);
                    motorTwo.setPower(-1);
                    motorThree.setPower(-1);
                }
                if(gamepad1.a){
                    motorZero.setPower(1);
                    motorThree.setPower(-1);
                }
                if(gamepad1.y){
                    motorZero.setPower(-1);
                    motorThree.setPower(1);
                }
                if(gamepad1.x){
                    motorOne.setPower(-1);
                    motorTwo.setPower(1);
                }
                if(gamepad1.b){
                    motorOne.setPower(1);
                    motorTwo.setPower(-1);
                }
                if (gamepad1.left_trigger > 0) {
                    motorZero.setPower(1);
                    motorThree.setPower(-1);
                }
                if (gamepad1.right_trigger > 0) {
                    motorZero.setPower(-1);
                    motorThree.setPower(1);
                }
                if(gamepad2.dpad_up)
                    slideMotor.setPower(-.4);
                else if(gamepad2.dpad_down)
                    slideMotor.setPower(.4);
                else
                    slideMotor.setPower(0);
                if(gamepad2.right_bumper)
                    goingIn = true;
                else if(gamepad2.left_bumper)
                    goingIn = false;
                if(gamepad2.b)
                    intakeStopped = true;
                if(gamepad2.a)
                    intakeStopped = false;
                if(!intakeStopped && !goingIn){
                    motorFour.setPower(-.5);
                    motorFive.setPower(.5 * speedMulti);
                }
                if(gamepad2.y && gamepad2.x)
                   shoulderServo.setPosition(2);
                if(!intakeStopped && goingIn){
                    motorFour.setPower(.5);
                    motorFive.setPower(-.5);
                }
                if(intakeStopped){
                    motorFour.setPower(0);
                    motorFive.setPower(0);
                }
                telemetry.update();
            }
    }
}
