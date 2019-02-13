
import com.kauailabs.navx.frc.AHRS;
public class AutoTurnCommandMP extends Command {
    private double rampRate;
    private double cruiseVelocity;
    private double currentVelocity;
    private double rampTicks;
    private double cruiseTicks;
    private double target;
    private int stage;
    private double time;
    public AutoTurnCommandMP(double targetRotation, double cruise, double ramp) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_subsystem);
        rampRate = ramp;
        cruiseVelocity = cruise;
        currentVelocity = 0.0;
        target = targetRotation;
        rampTicks = cruiseVelocity * 50/rampRate;
        cruiseTicks = 50 * ((target - ((rampTicks/50) * cruiseVelocity))/cruiseVelocity);
        time = 0.0;
        stage = 1;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if(stage == 1)
        {
            time += 1.0;
            currentVelocity = currentVelocity + (rampRate/50);
            //setsetpoint
            if(time > rampTicks)
            {
                stage = 2;
            }
        }
        else if(stage == 2)
        {
            time += 1.0;
            if(time >= cruiseTicks + rampTicks)
            {
                stage == 3;
            }
        }
        else if(stage == 3)
        {
            time += 1.0;
            currentVelocity = currentVelocity - (rampRate/50);
            //setsetpoint
            if(time > ((2 *rampTicks) + cruiseTicks))
            {
                stage = 4;
            }
        }
        @// TODO: 2/12/2019 SET SETPOINT OF VELO PID TO TARGET VELOCITY

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return (stage == 4);
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
