public class IR module {
    
}
import com.fazecast.jSerialComm.SerialPort;

public class IRSensorModule {
    private SerialPort serialPort;

    public IRSensorModule(String portName) {
        serialPort = SerialPort.getCommPort(portName);
    }

    public void connect() {
        if (serialPort.openPort()) {
            System.out.println("Connected to IR Sensor.");
            serialPort.setComPortParameters(9600, 8, 1, 0);
        } else {
            System.err.println("Failed to connect to IR Sensor.");
        }
    }

    public void readData() {
        byte[] readBuffer = new byte[1024];
        while (true) {
            int numRead = serialPort.readBytes(readBuffer, readBuffer.length);
            if (numRead > 0) {
                String data = new String(readBuffer, 0, numRead);
                System.out.println("Received IR Data: " + data.trim());
            }
        }
    }

    public void disconnect() {
        serialPort.closePort();
        System.out.println("Disconnected from IR Sensor.");
    }

    public static void main(String[] args) {
        IRSensorModule irSensor = new IRSensorModule("COM3"); // Change to your port
        irSensor.connect();

        // Read data from the sensor
        irSensor.readData();

        // Note: In a real application, you might want to add a way to exit the loop and disconnect.
    }
}
