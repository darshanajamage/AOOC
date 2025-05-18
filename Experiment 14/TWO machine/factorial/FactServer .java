import java.io.*;
import java.net.*;

class FactServer {
    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(1520);
            System.out.println("Server is running. Waiting for client...");

            Socket s = ss.accept();
            System.out.println("Client connected.");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

           while (true) {
    String input = dis.readUTF();

    System.out.println("Received from client: " + input); // <--- ADD THIS LINE

    if (input.equalsIgnoreCase("STOP")) {
        System.out.println("Client terminated the connection.");
        break;
    }

    try {
        int num = Integer.parseInt(input);
        long fact = 1;
        for (int i = 2; i <= num; i++) {
            fact *= i;
        }
        dos.writeUTF(String.valueOf(fact));
    } catch (NumberFormatException e) {
        dos.writeUTF("Invalid input. Please enter a number.");
    }
}
            s.close();
            ss.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}