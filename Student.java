
import java.util.Objects;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class Student {

    private String first;
    private String last;
    private int studentNum;
    private Degree deg;
    private Transcript transcript;

    public Student() {
        this.first = null;
        this.last = null;
        this.studentNum = 0;
        this.deg = null;
    }

    public void setFirstName(String first) {
        if (first != null && !first.isEmpty())
            this.first = first;
    }

    public void setLastName(String last) {
        if (last != null && !last.isEmpty())
            this.last = last;
    }

    public void setStudentNumber(Integer studentNum) { this.studentNum = studentNum; }

    public String getFullName() {
        String fullName;
        if (this.first == null && this.last == null) {
            return null;
        } else if (this.first == null) {
            fullName = this.last;
        } else if (this.last == null) {
            fullName = this.first;
        } else {
            fullName = this.first + " " + this.last;
        }
        return fullName;
    }

    public void setDegreeProgram(Degree deg) {
        this.deg = deg;
    }

    public void setTranscript(Transcript transcript){
        this.transcript = transcript;
    }

    public String getFirstName() { return this.first; }

    public String getLastName() { return this.last; }

    public Integer getStudentNumber() { return this.studentNum; }

    public Degree getDegreeProgram() {
        return this.deg;
    }

    public Transcript getTranscript(){
        return this.transcript;
    }

    @Override
    public String toString() {
        String toString = "";
        if (this.first != null) {
            toString = ("First name: " + this.first + System.getProperty( "line.separator" ));
        }
        if (this.last != null) {
            toString += ("Last name: " + this.last + System.getProperty( "line.separator" ));
        }
        toString += ("Student number: " + this.studentNum + System.getProperty( "line.separator" ));

        return toString;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Student)) {
            return false;
        }

        Student student = (Student) o;
        if (!(this.first.equals(student.first))){
            return false;
        }
        if (!(this.last.equals(student.last))){
            return false;
        }
        return this.studentNum == student.studentNum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.first);
        hash = 37 * hash + Objects.hashCode(this.last);
        hash = 37 * hash + Objects.hashCode(this.studentNum);
        return hash;
    }
}
