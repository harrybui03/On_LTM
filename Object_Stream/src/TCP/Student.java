package TCP;


import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 20151107;
    public int id;
    public String code, gpaLetter;
    public float gpa;

    public void setGpaLetter(String gpaLetter) {
        this.gpaLetter = gpaLetter;
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID=" + id +
                ", code='" + code + '\'' +
                ", gpaLetter='" + gpaLetter + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
