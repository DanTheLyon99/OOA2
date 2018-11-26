import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class CS extends HonoursDegree{
    private static final double maxOneSubjectCredits = 11.25;
    private static final double max1000LvlCredits = 6.00;
    private static final double rqrd3000orHigherCredits = 6.00;
    private static final double rqrdCisStat2000orHigherCredits = 0.5;
    private static final double maxAreaOfApplicationOrElective = 8.75;

    public CS() {
        super();
    }

    public boolean meetsRequirements(Transcript transcript) {
        double totalCredits = 0.0, credits3000 = 0.0, credits1000 = 0.0, creditsSubject = 0.0, creditsCisStat2000 = 0.0;
        String[] courseCodeParts;
        for (Course c : transcript.getCourses()) {
            if (transcript.getCourseStatus().equals("Completed")) {
                courseCodeParts = c.getCourseCode().split("\\*", 2);
                if (courseCodeParts[0].equals("CIS")) {
                    creditsSubject += c.getCourseCredit();
                }
                if (Double.parseDouble(courseCodeParts[1]) < 2000) {
                    credits1000 += c.getCourseCredit();
                }
                if (Double.parseDouble(courseCodeParts[1]) >= 3000) {
                    credits3000 += c.getCourseCredit();
                }
                if ((courseCodeParts[0].equals("CIS") || courseCodeParts[0].equals("STAT")) && Double.parseDouble(courseCodeParts[1]) >= 2000) {
                    creditsCisStat2000 += c.getCourseCredit();
                }
                /*try-catch method needed for this conditional*/
                if (creditsSubject < maxOneSubjectCredits && credits1000 < max1000LvlCredits) {
                    totalCredits += c.getCourseCredit();
                }
            }
        }
        /*?*/return totalCredits >= rqrdNumberOfCredits && credits3000 >= rqrd3000orHigherCredits && creditsCisStat2000 >= rqrdCisStat2000orHigherCredits;
    }


    public double numberOfCreditsRemaining(Transcript transcript) {
        double remainingCredits = 0;
        boolean completed = false;
        CourseCatalog catalog = transcript.getCatalog();
        ArrayList<Course> courses = transcript.getCourses();
        for (Course c : courses) {
            if (! transcript.getCourseStatus().equals("Completed")){
                if (!completed) {
                    remainingCredits += c.getCourseCredit();
                }
            }
        }
        return remainingCredits;
    }

    public ArrayList<Course> remainingRequiredCourses(Transcript transcript) {
        boolean completed = false;
        CourseCatalog catalog = transcript.getCatalog();
        ArrayList<Course> remainingRequiredCourses = new ArrayList<>();
        ArrayList<Course> courses = transcript.getCourses();
        for (String needed : this.listOfRequiredCourseCodes) {
            for (Course c : courses) {
                if ((c.getCourseCode() != null && c.getCourseCode().equals(needed)) && (transcript.getCourseStatus() != null && transcript.getCourseStatus().equals("Completed"))) {
                    completed = true;
                    break;
                }
            }
            if (!completed) {
                if (catalog.findCourse(needed) != null) {
                    remainingRequiredCourses.add(catalog.findCourse(needed));
                } else {
                    System.out.println("Course not in catalog: " + needed);
                }
            }
            completed = false;
        }
        return remainingRequiredCourses;
    }

    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder();
        if (this.title != null) {
            toString = new StringBuilder(("Code: " + this.title + System.getProperty("line.separator")));
        }
        if (this.listOfRequiredCourseCodes != null) {
            toString.append("Required Course Codes: ");
            for (String s : listOfRequiredCourseCodes) {
                toString.append(s).append(" ");
            }
            toString.append(System.getProperty("line.separator"));
        }
        return toString.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Degree)) {
            return false;
        }

            CS cs = (CS) o;
        if (!(this.title.equals(cs.title))) {
            return false;
        }
        return this.listOfRequiredCourseCodes.equals(cs.listOfRequiredCourseCodes);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(getDegreeTitle());
        hash = 41 * hash + Objects.hashCode(this.listOfRequiredCourseCodes);
        return hash;
    }
}

