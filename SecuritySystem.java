public class HACK {
    import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class SecuritySystem {

    private static boolean motionDetected = false;

    public static void main(String[] args) {
        // Start the motion detection simulation
        startMotionDetection();

        // Listen for user input to simulate manual override or shutdown
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type 'exit' to shut down the system.");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Shutting down the security system.");
                break;
            }
        }
        scanner.close();
    }

    private static void startMotionDetection() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Simulate motion detection
                motionDetected = detectMotion();
                if (motionDetected) {
                    sendAlert();
                }
            }
        }, 0,

}
