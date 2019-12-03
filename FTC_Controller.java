package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="FTC: Controller", group="Linear Opmode")
public class FTC_Controller extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor motorZero;
    private DcMotor motorOne;
    private DcMotor motorThree;
    private DcMotor motorFour;
    private DcMotor motorFive;
    private DcMotor motorSix ;
    
    private double power_multi = .5;

    @Override
    public void runOpMode() {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        motorZero = hardwareMap.dcMotor.get("motorZero");
        motorOne = hardwareMap.dcMotor.get("motorOne");
        motorThree = hardwareMap.dcMotor.get("motorThree");
        motorFour = hardwareMap.dcMotor.get("motorFour");
        motorFive = hardwareMap.dcMotor.get("motorFive");
        motorSix = hardwareMap.dcMotor.get("motorSix");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            motorThree.setPower(-gamepad1.left_stick_y * power_multi);
            motorFour.setPower(gamepad1.right_stick_y * power_multi);
            motorFive.setPower(-gamepad1.left_stick_y * power_multi);
            motorSix.setPower(gamepad1.right_stick_y * power_multi);
            
            if(gamepad1.right_bumper)
            {
                motorZero.setPower(-.5 * power_multi);
                motorOne.setPower(.5 * power_multi);
            }
            if (gamepad1.left_bumper)
            {
                motorZero.setPower(.5 * power_multi);
                motorOne.setPower(-.5 * power_multi);
            }
            if (gamepad1.dpad_right)
            {
               motorThree.setPower(-.5 * power_multi);
                motorFour.setPower(-.5 * power_multi);
                motorFive.setPower(.5 * power_multi);
                motorSix.setPower(.5 * power_multi); 
            }
            if(gamepad1.dpad_left)
            {
                motorThree.setPower(.5 * power_multi);
                motorFour.setPower(.5 * power_multi);
                motorFive.setPower(-.5 * power_multi);
                motorSix.setPower(-.5 * power_multi);
            }
            if (gamepad1.dpad_up)
            {
                motorThree.setPower(.5 * power_multi);
                motorFour.setPower(-.5 * power_multi);
                motorFive.setPower(.5 * power_multi);
                motorSix.setPower(-.5 * power_multi); 
            }
            if(gamepad1.dpad_down)
            {
                motorThree.setPower(-.5 * power_multi);
                motorFour.setPower(.5 * power_multi);
                motorFive.setPower(-.5 * power_multi);
                motorSix.setPower(.5 * power_multi);
            }
            
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}
