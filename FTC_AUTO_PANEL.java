package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name="FTC: AUTO PANEL", group="Linear Opmode")
public class FTC_AUTO_PANEL extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor lb;
    private DcMotor rb;
    private DcMotor lf;
    private DcMotor rf;
    
    private DcMotor lr;
    private DcMotor rr;
    
    private Servo ps1;
    private Servo ps2;
    
    @Override
    public void runOpMode() {
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        lb = hardwareMap.dcMotor.get("motorZero");
        rb = hardwareMap.dcMotor.get("motorOne");
        lf = hardwareMap.dcMotor.get("motorTwo");
        rf = hardwareMap.dcMotor.get("motorThree");
        lr = hardwareMap.dcMotor.get("motorFour");
        rr = hardwareMap.dcMotor.get("motorFive");
        ps1 = hardwareMap.servo.get("platformServo1");
        ps2 = hardwareMap.servo.get("platformServo2");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();
          
        // -----------------------------------------

        ps1.setPosition(.9);
        ps2.setPosition(.5);
        
        moveLeft(3000, .5);
        
        
        ps1.setPosition(.7);
        ps2.setPosition(.7);
        
        moveRight(2500, .5);
    }
    
    void moveForward(int ticks, double speed){
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        rf.setTargetPosition(ticks);
        rb.setTargetPosition(ticks);
        lf.setTargetPosition(-ticks);
        lb.setTargetPosition(-ticks);
        
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        rf.setPower(speed);
        rb.setPower(speed);
        lf.setPower(-speed);
        lb.setPower(-speed);
        
        while (rf.isBusy()){
            telemetry.addData("tele", "Run Time: " + rf.getCurrentPosition());
            telemetry.update();
        }
    }
    
    void moveBackward(int ticks, double speed){
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        rf.setTargetPosition(-ticks);
        rb.setTargetPosition(-ticks);
        lf.setTargetPosition(ticks);
        lb.setTargetPosition(ticks);
        
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        rf.setPower(-speed);
        rb.setPower(-speed);
        lf.setPower(speed);
        lb.setPower(speed);
        
        while (rf.isBusy()){
            telemetry.addData("tele", "Run Time: " + rf.getCurrentPosition());
            telemetry.update();
        }
    }
    
    void moveRight(int ticks, double speed){
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        rf.setTargetPosition(-ticks);
        rb.setTargetPosition(ticks);
        lf.setTargetPosition(-ticks);
        lb.setTargetPosition(ticks);
        
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        rf.setPower(-speed);
        rb.setPower(speed);
        lf.setPower(-speed);
        lb.setPower(speed);
        
        while (rf.isBusy()){
            telemetry.addData("tele", "Run Time: " + rf.getCurrentPosition());
            telemetry.update();
        }
    }
    
    void moveLeft(int ticks, double speed){
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        rf.setTargetPosition(ticks);
        rb.setTargetPosition(-ticks);
        lf.setTargetPosition(ticks);
        lb.setTargetPosition(-ticks);
        
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        rf.setPower(speed);
        rb.setPower(-speed);
        lf.setPower(speed);
        lb.setPower(-speed);
        
        while (rf.isBusy()){
            telemetry.addData("tele", "Run Time: " + rf.getCurrentPosition());
            telemetry.update();
        }
    }
    
    void turnRight(int ticks, double speed){
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        rf.setTargetPosition(ticks);
        rb.setTargetPosition(ticks);
        lf.setTargetPosition(ticks);
        lb.setTargetPosition(ticks);
        
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        rf.setPower(speed);
        rb.setPower(speed);
        lf.setPower(speed);
        lb.setPower(speed);
        
        while (rf.isBusy()){
            telemetry.addData("tele", "Run Time: " + rf.getCurrentPosition());
            telemetry.update();
        }
    }
    
    void turnLeft(int ticks, double speed){
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        
        rf.setTargetPosition(-ticks);
        rb.setTargetPosition(-ticks);
        lf.setTargetPosition(-ticks);
        lb.setTargetPosition(-ticks);
        
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        rf.setPower(-speed);
        rb.setPower(-speed);
        lf.setPower(-speed);
        lb.setPower(-speed);
        
        while (rf.isBusy()){
            telemetry.addData("tele", "Run Time: " + rf.getCurrentPosition());
            telemetry.update();
        }
    }
}
