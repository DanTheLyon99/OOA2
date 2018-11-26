import java.util.ArrayList;
import java.util.Objects;

public class Attempt {

    private Course courses;
    private String grade;
    private String semester;
    private String courseStatus;

    public Attempt(){
        this.courseStatus = null;
        this.grade = null;
        this.semester = null;
        this.courses = new Course();
    }

    public Attempt(Attempt attempt) {
        this.courseStatus = attempt.getCourseStatus();
        this.grade = attempt.getCourseGrade();
        this.semester = attempt.getSemesterTaken();
        this.courses = attempt.getCourses();
    }

    public void setCourseStatus(String courseStatus) {
        if (courseStatus != null && !courseStatus.isEmpty()) {
            this.courseStatus = courseStatus;
        }
    }

    public void setCourseGrade(String grade) {
        if (grade == null) {
            this.grade = null;
            return;
        }

        int gradeNum;
        /*Maybe check for accidental string being passed in*/
        try {
            gradeNum = Integer.parseInt(grade);
            if (gradeNum <= 100 && gradeNum >= 0) {
                this.grade = grade;
            }
        } catch (Exception ignored) {
            System.out.println("Grades must be between 0 and 100.");
        }
    }

    public void setSemesterTaken(String semester) {
        if (semester != null && !semester.isEmpty()) {
            this.semester = semester;
        }
    }

    public void setCourses(Course courses){
        this.courses = courses;
    }

    public String getCourseStatus() { return this.courseStatus; }

    public String getCourseGrade() { return this.grade; }

    public String getSemesterTaken() { return this.semester; }

    public Course getCourses() { return this.courses; }


    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if (this.courseStatus != null) {
            toString.append("Status: ").append(this.courseStatus).append(System.getProperty("line.separator"));
        }
        if (this.grade != null) {
            toString.append("Grade: ").append(this.grade).append("\n");
        }
        if (this.semester != null) {
            toString.append("Semester Taken: ").append(this.semester).append(System.getProperty("line.separator"));
        }
        return toString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Course)) {
            return false;
        }

        if (courseStatus == null || !(this.courseStatus.equals(courseStatus))) {
            return false;
        }
        if (this.grade != null && grade != null) {
            if (!(this.grade.equals(grade))) {
                return false;
            }
        }
        if (this.semester != null && semester != null) {
            if (!(this.semester.equals(semester))) {
                return false;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.courseStatus);
        hash = 53 * hash + Objects.hashCode(this.grade);
        hash = 53 * hash + Objects.hashCode(this.semester);
        return hash;
    }
}
