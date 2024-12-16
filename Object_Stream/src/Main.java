import TCP.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("203.162.10.109",2209);
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        String code = "B21DCCN427;zp6gEyQ3";
        objectOutputStream.writeObject(code);
        objectOutputStream.flush();

        Student student = (Student) objectInputStream.readObject();
        System.out.println(student);

        // Switch using ranges
        if (student.gpa >= 3.7 && student.gpa <= 4.0) {
            student.setGpaLetter("A");
        } else if (student.gpa >= 3.0 && student.gpa < 3.7) {
            student.setGpaLetter("B");
        } else if (student.gpa >= 2.0 && student.gpa < 3.0) {
            student.setGpaLetter("C");
        } else if (student.gpa >= 1.0 && student.gpa < 2.0) {
            student.setGpaLetter("D");
        } else if (student.gpa >= 0 && student.gpa < 1.0) {
            student.setGpaLetter("F");
        } else {
            student.gpaLetter = "Invalid GPA"; // Handle out-of-range GPA
        }

        System.out.println("Output: "+ student);

        objectOutputStream.writeObject(student);
        objectOutputStream.flush();
        objectOutputStream.close();
        objectInputStream.close();
        socket.close();


    }
}