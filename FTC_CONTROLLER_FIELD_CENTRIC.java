package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import java.util.regex.Matcher;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.util.*;

@TeleOp(name="FTC: CONTROLLER FIELD CENTRIC2", group="Linear Opmode")
public class FTCControllerFieldCentric extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor M0 = null;
    private DcMotor M1 = null;
    private DcMotor M2 = null;
    private DcMotor M3 = null;
   

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        M0  = hardwareMap.get(DcMotor.class, "M0");
        M1  = hardwareMap.get(DcMotor.class, "M1");
        M2  = hardwareMap.get(DcMotor.class, "M2");
        M3  = hardwareMap.get(DcMotor.class, "M3");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        M0.setDirection(DcMotor.Direction.REVERSE);
        M2.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // gets the inputs from the controller
            float gp1l_x = -gamepad1.left_stick_x;
            float gp1l_y = gamepad1.left_stick_y;
            float gp1r_x = gamepad1.right_stick_x;
            
            // calculates the power and angel
            double power = Math.sqrt((gp1l_x*gp1l_x) + (gp1l_y*gp1l_y));
            double angle = Math.atan2(gp1l_x,gp1l_y) + 3.14;
            
            // makes the robot move at a designated angel
            M0.setPower(power*Math.sin(angle + (3.14/4)) + gp1r_x);
            M1.setPower(power*Math.cos(angle + (3.14/4)) - gp1r_x);
            M2.setPower(power*Math.cos(angle + (3.14/4)) + gp1r_x);
            M3.setPower(power*Math.sin(angle + (3.14/4)) - gp1r_x);
            
            // for debug
            telemetry.addData("power", power);
            telemetry.addData("angle", angle);
            telemetry.addData("rotating", gp1r_x);
            telemetry.update();
        }
    }
}
