import jssc.SerialPort;
import jssc.SerialPortException;

public class RobotWiFiModule {
    private SerialPort serialPort;

    public RobotWiFiModule(String portName) {
        serialPort = new SerialPort(portName);
    }

    public void connect() {
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_115200, 
                                  SerialPort.DATABITS_8, 
                                  SerialPort.STOPBITS_1, 
                                  SerialPort.PARITY_NONE);
            System.out.println("Connected to Wi-Fi Module.");
        } catch (SerialPortException e) {
            System.err.println("Error opening port: " + e.getMessage());
        }
    }

    public void sendCommand(String command) {
        try {
            serialPort.writeString(command + "\n");
            System.out.println("Sent command: " + command);
        } catch (SerialPortException e) {
            System.err.println("Error sending command: " + e.getMessage());
        }
    }

    public String readResponse() {
        try {
            String response = serialPort.readString();
            System.out.println("Received response: " + response);
            return response;
        } catch (SerialPortException e) {
            System.err.println("Error reading response: " + e.getMessage());
            return null;
        }
    }

    public void disconnect() {
        try {
            serialPort.closePort();
            System.out.println("Disconnected from Wi-Fi Module.");
        } catch (SerialPortException e) {
            System.err.println("Error closing port: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        RobotWiFiModule robotWiFi = new RobotWiFiModule("COM3"); // Change to your port
        robotWiFi.connect();

        // Example commands
        robotWiFi.sendCommand("AT"); // Send AT command
        robotWiFi.readResponse(); // Read response from module

        robotWiFi.disconnect();
    }
}
