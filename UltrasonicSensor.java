public class UL {
    import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class UltrasonicSensor {

    public static void main(String[] args) throws InterruptedException {
        // Create GPIO controller
        final GpioController gpio = GpioFactory.getInstance();

        // Define GPIO pins
        final GpioPinDigitalOutput trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "Trigger", PinState.LOW);
        final GpioPinDigitalInput echo = gpio.provisionDigitalInputPin(RaspiPin.GPIO_24, "Echo");

        while (true) {
            // Send a pulse to trigger the sensor
            trigger.high();
            Thread.sleep(0, 1000); // 10 microseconds
            trigger.low();

            // Wait for the echo pin to go high
            long startTime = System.nanoTime();
            while (echo.isLow()) {
                startTime = System.nanoTime();
            }

            // Wait for the echo pin to go low
            long travelTime = System.nanoTime();
            while (echo.isHigh()) {
                travelTime = System.nanoTime();
            }

            // Calculate distance in cm
            long duration = travelTime - startTime;
            double distance = (duration / 1e6) * 0.0343 / 2; // (duration in seconds) * speed of sound (cm/us)

            System.out.println("Distance: " + distance + " cm");

            // Wait before the next measurement
            Thread.sleep(1000); // 1 second
        }
    }
}

}
