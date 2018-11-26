
import java.util.ArrayList;
import java.util.Objects;

public class Course {

    private String courseCode;
    private String courseTitle;
    private ArrayList<Course> preReqList;
    private double credit;
    private String semesterOffered;

    public Course() {
        this.courseCode = null;
        this.courseTitle = null;
        this.preReqList = new ArrayList<>();
        this.credit = 0;
    }

    /*  Deep Copy Constructor for Course */
    public Course(Course course) {
        this.courseCode = course.getCourseCode();
        this.courseTitle = course.getCourseTitle();
        this.preReqList = course.getPrerequisites();
        this.credit = course.getCourseCredit();
    }

    protected void setCourseCode(String courseCode) {
        if (courseCode != null && !courseCode.isEmpty()) {
            this.courseCode = courseCode;
        }
    }

    protected void setCourseTitle(String courseTitle) {
        if (courseTitle != null && !courseTitle.isEmpty()) {
            this.courseTitle = courseTitle;
        }
    }

    protected void setPrerequisites(ArrayList<Course> preReqList) {
        if (preReqList == null) {
            this.preReqList = null;
        } else {
            this.preReqList = preReqList;
        }
    }

    protected void setCourseCredit(Double credit) {
        if (credit != null && credit >= 0 && credit <= 1.0) {
            this.credit = credit;
        }
    }

    protected void setSemesterOffered(String sem){
        if(sem == null){
            this.semesterOffered = null;
        }
        else{
            this.semesterOffered = sem;
        }
    }

    public String getCourseCode() { return this.courseCode; }

    public String getCourseTitle() { return this.courseTitle; }

    public double getCourseCredit() { return this.credit; }

    public ArrayList<Course> getPrerequisites() { return this.preReqList; }

    public String getSemesterOffered(){
        return this.semesterOffered;
    }

    public String toFile() {
        String toFile = "";
        toFile += this.getCourseCode();
        toFile += ",";
        toFile += Double.toString(this.getCourseCredit());
        toFile += ",";
        toFile += this.getCourseTitle();
        toFile += ",";
        toFile += this.getSemesterOffered();
        toFile += ",";
        for (Course preReq : this.getPrerequisites())  {
            toFile += preReq.getCourseCode();
            toFile += ":";
        }
        if (toFile.charAt(toFile.length() - 1) == ':') {
            toFile = toFile.substring(0, toFile.length() - 1);
        }
        /*?*/
        toFile += "?";
        return toFile;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if (this.courseCode != null) {
            toString = new StringBuilder(("Code: " + this.courseCode + System.getProperty("line.separator")));
        }
        if (this.courseTitle != null) {
            toString.append("Title: ").append(this.courseTitle).append(System.getProperty("line.separator"));
        }
        if (this.credit > 0) {
            toString.append("Credit: ").append(this.getCourseCredit()).append(System.getProperty("line.separator"));
        }
        if (this.semesterOffered != null){
            toString.append("Semester: ").append(this.getSemesterOffered()).append(System.getProperty("line.separator"));
        }
        if (this.preReqList != null) {
            toString.append("Prerequisites: ");
            for (Course c : this.preReqList) {
                toString.append(c.getCourseCode());
            }
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

        Course course = (Course) o;
        if (course.courseCode == null || !(this.courseCode.equals(course.courseCode))) {
            return false;
        }
        if (course.courseTitle == null || !(this.courseTitle.equals(course.courseTitle))) {
            return false;
        }

        return this.preReqList.equals(course.preReqList);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.courseCode);
        hash = 53 * hash + Objects.hashCode(this.courseTitle);
        hash = 53 * hash + Objects.hashCode(this.preReqList);
        return hash;
    }
}
