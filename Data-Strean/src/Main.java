import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("203.162.10.109", 2207);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Send initial code to the server
            String code = "B21DCCN427;SwqxAjUZ";
            out.writeUTF(code);
            out.flush();

            // Read responses from the server
            String s = in.readUTF(); // Read the string
            int n = in.readInt();    // Read the integer

            // Display server responses
            System.out.println("Received string: " + s);
            System.out.println("Shift value: " + n);

            // Process the string to shift characters
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (Character.isUpperCase(ch)) {
                    // Shift uppercase letters
                    ch = (char) ('A' + (ch - 'A' + n) % 26);
                } else if (Character.isLowerCase(ch)) {
                    // Shift lowercase letters
                    ch = (char) ('a' + (ch - 'a' + n) % 26);
                }
                ans.append(ch);
            }

            // Send the processed string back to the server
            out.writeUTF(ans.toString());
            out.flush();

            // Close resources
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            // Handle exceptions
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
