import java.util.ArrayList;
import java.util.Objects;

public class Transcript {
    private String grade;
    private String semester;
    private String courseStatus;
    private ArrayList<Course> courses;
    private CourseCatalog catalogCopy;

    public Transcript(){
        this.courseStatus = null;
        this.grade = null;
        this.semester = null;
        this.courses = new ArrayList<>();
        this.catalogCopy = new CourseCatalog();
    }

    /*Deep Copy Constructor*/
    public Transcript(Transcript transcript){
        this.courseStatus = transcript.getCourseStatus();
        this.grade = transcript.getCourseGrade();
        this.semester = transcript.getSemesterTaken();
        this.courses = transcript.getCourses();
    }

    public void setCourseStatus(String courseStatus) {
        if (courseStatus != null && !courseStatus.isEmpty()) {
            this.courseStatus = courseStatus;
        }
    }

    public void setCourseStatus(String courseCode, String semester, String courseStatus, Transcript transcript) {
        for (Course c : this.courses) {
            if (c.getCourseCode() != null && transcript.getSemesterTaken() != null && c.getCourseCode().equals(courseCode) && transcript.getSemesterTaken().equals(semester)) {
                transcript.setCourseStatus(courseStatus);
            }
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

    public void setCourseGrade(String courseCode, String semester, String grade, Transcript transcript) {
        for (Course c : this.courses) {
            if (c.getCourseCode() != null && transcript.getSemesterTaken() != null && c.getCourseCode().equals(courseCode) && transcript.getSemesterTaken().equals(semester)) {
                transcript.setCourseGrade(grade);
                System.out.println("Grade updated.");
                return;
            }
        }
        System.out.println("Grade could not be updated.");
    }

    public void setSemesterTaken(String semester) {
        if (semester != null && !semester.isEmpty()) {
            this.semester = semester;
        }
    }

    public void setCourses(ArrayList<Course> courses){
        this.courses = courses;
    }
    public void setCatalog(CourseCatalog catalog) {
        this.catalogCopy = catalog;
    }


    public String getCourseStatus() { return this.courseStatus; }

    public String getCourseGrade() { return this.grade; }

    public String getSemesterTaken() { return this.semester; }

    public ArrayList<Course> getCourses() { return this.courses; }


    public CourseCatalog getCatalog() {
        return this.catalogCopy;
    }
    public Course getCourse(String courseCode, String semester, Transcript transcript) {
        for (Course c : transcript.courses) {
            if (c.getCourseCode() != null && transcript.getSemesterTaken() != null && c.getCourseCode().equals(courseCode) && transcript.getSemesterTaken().equals(semester)) {
                return c;
            }
        }
        return null;
    }

    public Course findCourse(String courseCode) {
        Course found;
        if ((found = this.catalogCopy.findCourse(courseCode)) != null) {
            return found;
        }
        return null;
    }

    public void addCourse(String courseCode, String semester, Transcript transcript) {
        boolean alreadyAdded = false;
        for (Course code : transcript.catalogCopy.getCourseCatalog()) {
            if (courseCode.equals(code.getCourseCode())) {
                for (Course c : this.courses) {
                    if (c.getCourseCode().equals(courseCode) && transcript.getSemesterTaken().equals(semester)) {
                        alreadyAdded = true;
                        System.out.println("Already in the Plan of Study.");
                    }
                }
            }
        }
        if (!alreadyAdded) {
            Course found = isValidCourse(courseCode);
            if (found != null) {
                Course toAdd = new Course(found);
                this.setSemesterTaken(semester);
                this.setCourseStatus("Planned");
                System.out.println(toAdd.toString());
                this.courses.add(toAdd);
                System.out.println("Course added.");
            } else {
                System.out.println("No such course in the catalog.");
            }
        }
    }

    private Course isValidCourse(String courseCode) {
        Course found = this.catalogCopy.findCourse(courseCode);
        if (found != null) {
            return found;
        }
        return null;
    }

    public void removeCourse(String courseCode, String semester,Transcript transcript) {
        for (Course c : transcript.courses) {
            if (c.getCourseCode() != null && transcript.getSemesterTaken() != null && c.getCourseCode().equals(courseCode) && transcript.getSemesterTaken().equals(semester)) {
                this.courses.remove(c);
                return;
            }
        }
    }

    public double totalCredits(Transcript transcript) {
        double totalCredits = 0.0;
        for (Course c : transcript.courses) {
            if (transcript.getCourseStatus() != null && transcript.getCourseStatus().equals("Completed")) {
                totalCredits += c.getCourseCredit();
            }
        }
        return totalCredits;
    }

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

        Transcript transcript = (Transcript) o;
        if (transcript.courseStatus == null || !(this.courseStatus.equals(transcript.courseStatus))) {
            return false;
        }
        if (this.grade != null && transcript.grade != null) {
            if (!(this.grade.equals(transcript.grade))) {
                return false;
            }
        }
        if (this.semester != null && transcript.semester != null) {
            if (!(this.semester.equals(transcript.semester))) {
                return false;
            }
        }

        return this.equals(transcript);
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
