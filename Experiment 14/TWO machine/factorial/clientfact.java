import java.util.*;
import java.net.*;
import java.io.*;

class clientfact {
    public static void main(String args[]) {
        try {
            Socket s1 = new Socket("10.1.137.245", 1520);

            DataInputStream dis = new DataInputStream(s1.getInputStream());
            DataOutputStream dos = new DataOutputStream(s1.getOutputStream());

            Scanner sc = new Scanner(System.in);
            String input;

            do {
                System.out.print("Enter number for factorial (or type STOP): ");
                input = sc.nextLine();
                dos.writeUTF(input); // Send input to server

                if (input.equalsIgnoreCase("STOP")) break;

                String response = dis.readUTF(); // Receive factorial from server
                System.out.println("Factorial is: " + response);
            } while (true);

            s1.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}