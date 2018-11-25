import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Planner extends JFrame {

    /*Class Object declerations*/
    Student student = new Student();
    ArrayList<String> studentInfo = new ArrayList<>();
    ArrayList<String> degMinInfo = new ArrayList<>();

    /*StartUp UI*/
    private JPanel startP = new JPanel();
    private JPanel labelP = new JPanel();
    private JLabel welcomeL = new JLabel("Welcome, Please Sign In");
    private JButton adminB = new JButton("Admin");
    private JButton userB = new JButton("User");

    /*User and Admin screens*/
    private JPanel inputP = new JPanel();
    private JLabel userIDL = new JLabel("Please enter your name and ID");
    private JLabel userL = new JLabel("First Name: ");
    private JLabel IDL = new JLabel("ID#: ");
    private JTextField userText = new JTextField(10);
    private JTextField IDText = new JTextField(10);
    private JButton signInB = new JButton("Sign in");
    private JLabel lastL = new JLabel("Last Name: ");
    private JTextField lastText = new JTextField(10);

    /*Home*/
    private JPanel homeP = new JPanel();
    private JLabel currStuL = new JLabel("Student Information: ");
    private JTextArea currStuArea = new JTextArea("No current Student", 10, 20);
    private JLabel currDegMinL = new JLabel("Student Degree/Major: ");
    private JTextArea currDegMinArea = new JTextArea("No current Degree", 10, 10);
    private JButton selectDegreeB = new JButton("Select Degree");
    private JButton selectMajorB = new JButton("Select Major");
    private JButton remainPreReqB = new JButton("Remaining Credits");
    private JButton listReqCoursesB = new JButton("List Required Courses");
    private JButton addRemoveCourseB = new JButton(" Add/Remove Courses");
    private JButton addRemoveChangeGradeB = new JButton("Add/Remove/Change Grades");
    private JButton saveProgramB = new JButton("SAVE");
    private JButton viewUnknownCoursesB = new JButton("View Unknown Courses");
    private JButton viewIncompleteCoursesB = new JButton("View Incomplete Courses");
    private JButton viewCurrReqCreditsB = new JButton("View Current Required Credits");
    private JButton viewPreReqs4CourseB = new JButton("View Prereqs for courses");
    private JButton viewCreditsB = new JButton("View Credits");
    private JButton viewGpaB = new JButton("View GPA");
    private JButton determineCompletionB = new JButton("Determine Completion");
    private JButton viewCoursePlanB = new JButton("View Course Plan");

    /*Degree UI*/
    private JLabel degreeL = new JLabel("Degrees: ");
    private String[] degreeList = {"BComp Honours", "BComp General"};
    private JComboBox degreeChoice = new JComboBox(degreeList);
    private JButton submitDegree = new JButton("Submit");
    private JPanel degreeP = new JPanel();

    /*Major UI*/
    private JPanel majorP = new JPanel();
    private JLabel majorL = new JLabel("Major: ");
    private JLabel majorChoiceL = new JLabel("Prerequisites for Major of Choice");
    private JButton majorB = new JButton("Choose Major");
    private JTextArea majorText = new JTextArea(10,30);
    private JComboBox majorChoice = new JComboBox();

    public Planner(){
        super();
        gui();
    }

    public void gui(){
        setSize(600,400);
        setTitle("Welcome!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        startUp();
        adminOrUser();
        Home();
        chooseDegree();
        chooseMajor();
        pack();
    }

    private void startUp(){
        //startP.setVisible(false);
        //labelP.setVisible(false);

        startP.setLayout(new BoxLayout(startP, BoxLayout.X_AXIS));
        labelP.setLayout(new CardLayout(5,5));

        labelP.add(welcomeL);
        startP.add(adminB);
        startP.add(userB);

        startUpListener start = new startUpListener();
        adminB.addActionListener(start);
        userB.addActionListener(start);

        add(labelP);
        add(startP);
    }

    private void adminOrUser(){
        inputP.setVisible(false);

        inputP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;

        //  inputP.add(userIDL, gbc);
        // gbc.gridy++;
        inputP.add(userL, gbc);
        gbc.gridx++;
        inputP.add(userText, gbc);
        gbc.gridx++;
        inputP.add(lastL, gbc);
        gbc.gridx++;
        inputP.add(lastText, gbc);
        gbc.gridx++;
        inputP.add(IDL, gbc);
        gbc.gridx++;
        inputP.add(IDText, gbc);
        gbc.gridy++;

        gbc.gridx = 5;
        inputP.add(signInB,gbc);

        startUpListener admin = new startUpListener();
        signInB.addActionListener(admin);

        add(inputP);
    }

    private void Home(){
        homeP.setVisible(false);

        homeP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;

        currStuArea.setEditable(false);
        currDegMinArea.setEditable(false);

        homeP.add(currStuL, gbc);
        gbc.gridx += 2;
        homeP.add(currDegMinL, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        homeP.add(currStuArea, gbc);
        gbc.gridx += 2;
        homeP.add(currDegMinArea, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        homeP.add(selectDegreeB, gbc);
        gbc.gridx++;
        selectMajorB.setEnabled(false);
        homeP.add(selectMajorB, gbc);
        gbc.gridx++;
        homeP.add(viewCreditsB,gbc);
        gbc.gridx++;
        homeP.add(viewGpaB,gbc);
        gbc.gridx++;
        homeP.add(remainPreReqB, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        homeP.add(listReqCoursesB, gbc);
        gbc.gridx++;
        homeP.add(addRemoveCourseB, gbc);
        gbc.gridx++;
        homeP.add(determineCompletionB, gbc);
        gbc.gridx++;
        homeP.add(viewCoursePlanB, gbc);
        gbc.gridx++;
        homeP.add(addRemoveChangeGradeB, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        homeP.add(viewUnknownCoursesB,gbc);
        gbc.gridx++;
        homeP.add(viewIncompleteCoursesB,gbc);
        gbc.gridx++;
        homeP.add(viewCurrReqCreditsB,gbc);
        gbc.gridx++;
        homeP.add(viewPreReqs4CourseB,gbc);

        homeListener home = new homeListener();
        selectDegreeB.addActionListener(home);
        selectMajorB.addActionListener(home);
        remainPreReqB.addActionListener(home);
        listReqCoursesB.addActionListener(home);
        addRemoveCourseB.addActionListener(home);
        addRemoveChangeGradeB.addActionListener(home);
        saveProgramB.addActionListener(home);
        viewUnknownCoursesB.addActionListener(home);
        viewIncompleteCoursesB.addActionListener(home);
        viewCurrReqCreditsB.addActionListener(home);
        viewPreReqs4CourseB.addActionListener(home);
        viewCreditsB.addActionListener(home);
        viewGpaB.addActionListener(home);
        determineCompletionB.addActionListener(home);
        viewCoursePlanB.addActionListener(home);

        add(homeP);
    }

    private void chooseDegree(){
        degreeP.setVisible(false);

        degreeP.setLayout(new BorderLayout());
        degreeP.add(degreeL, BorderLayout.NORTH);
        degreeP.add(degreeChoice, BorderLayout.CENTER);
        degreeP.add(submitDegree, BorderLayout.SOUTH);

        degListener listen = new degListener();
        submitDegree.addActionListener(listen);
        add(degreeP);
    }

    private void chooseMajor(){
        majorP.setVisible(false);

        majorP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        //gbc.fill = GridBagConstraints.BOTH;

        majorP.add(majorChoiceL, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy++;
        majorP.add(majorL,gbc);
        gbc.gridx++;
        majorP.add(majorChoice, gbc);
        gbc.gridx++;
        majorP.add(majorText,gbc);
        gbc.gridy++;
        majorP.add(majorB,gbc);

        minorListener minor = new minorListener();
        majorChoice.addActionListener(minor);
        majorB.addActionListener(minor);

        add(majorP);
    }

    public class startUpListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == adminB){
                startP.setVisible(false);
                labelP.setVisible(false);
                inputP.setVisible(true);
                pack();
            }
            else if(event.getSource() == userB){
                startP.setVisible(false);
                labelP.setVisible(false);
                inputP.setVisible(true);
                pack();
            }
            else if(event.getSource() == signInB){
                String first = null;
                String last = null;
                Integer ID = null;
                String fullInfo;

                try {

                    first = userText.getText();
                    last = lastText.getText();
                    ID = Integer.valueOf(IDText.getText());
                    if(userText.getText() == null) {
                        throw new NullException("NULL INPUT");

                    }
                }
                catch(NullException e){
                    System.out.println(e.getMessage());
                }


                student.setFirstName(first);
                student.setLastName(last);
                student.setStudentNumber(ID);

                studentInfo.add(first);
                studentInfo.add(last);
                studentInfo.add(IDText.getText());

                fullInfo = toStringList(studentInfo);

                currStuArea.setText(fullInfo);

                homeP.setVisible(true);
                inputP.setVisible(false);
                pack();

            }
        }
    }

    public class homeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == selectDegreeB){
                homeP.setVisible(false);
                degreeP.setVisible(true);
                pack();
            }
            else if(event.getSource() == selectMajorB){
                homeP.setVisible(false);
                majorP.setVisible(true);
                pack();
            }
            else if(event.getSource() == remainPreReqB){
                listReqCoursesB.setBackground(Color.GREEN);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == listReqCoursesB){
                remainPreReqB.setBackground(Color.MAGENTA);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == addRemoveCourseB){
                addRemoveChangeGradeB.setBackground(Color.RED);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == addRemoveChangeGradeB){
                addRemoveCourseB.setBackground(Color.BLUE);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == saveProgramB){
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewUnknownCoursesB){
                viewIncompleteCoursesB.setBackground(Color.PINK);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewIncompleteCoursesB){
                viewUnknownCoursesB.setBackground(Color.DARK_GRAY);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewCurrReqCreditsB){
                viewCreditsB.setBackground(Color.YELLOW);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewCreditsB){
                viewCurrReqCreditsB.setBackground(Color.CYAN);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewGpaB){
                determineCompletionB.setBackground(Color.BLACK);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == determineCompletionB){
                viewGpaB.setBackground(Color.ORANGE);
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            else if(event.getSource() == viewCoursePlanB){
                //topHomeP.setVisible(false);
                //homeP.setVisible(false);
            }
            /*selectDegreeB
                    selectMajorB
            remainPreReqB
                    listReqCoursesB
            addRemoveCourseB
                    addRemoveChangeGradeB
            saveProgramB
                    viewUnknownCoursesB
            viewIncompleteCoursesB
                    viewCurrReqCreditsB
            viewPreReqs4CourseB
                    viewCreditsB
            viewGpaB
                    determineCompletionB
            viewCoursePlanB*/
        }
    }

    public class degListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == submitDegree)
            {
                String degreeTitle;
                String fullDegreeTitle;
                String softEng = "SEng";
                String compSci = "CS";
                String genComp = "BCG";
                String honours = "BComp Honours";
                String general = "BComp General";
                ArrayList<String> minorList = new ArrayList<>();
                degreeTitle = degreeChoice.getSelectedItem().toString();
                degMinInfo.add(degreeTitle);
                fullDegreeTitle = toStringList(degMinInfo);
                currDegMinArea.setText(fullDegreeTitle);
                if(degreeTitle.equals(honours)){
                    minorList.add(softEng);
                    minorList.add(compSci);
                }
                else if(degreeTitle.equals(general)){
                    minorList.add(genComp);
                }
                majorChoice.setModel(new DefaultComboBoxModel(minorList.toArray()));

                selectDegreeB.setEnabled(false);
                selectMajorB.setEnabled(true);
                homeP.setVisible(true);
                degreeP.setVisible(false);
                pack();
            }
        }
    }
    /** @author: Dan, by the way **/

    public class minorListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == majorChoice){
                String string;
                String courses = null;
                String softEng = "SEng";
                String compSci = "CS";
                String genComp = "BCG";

                string = majorChoice.getSelectedItem().toString();

                if(string == softEng){
                    courses = "CIS*1250,CIS*1500,CIS*1910,CIS*2250,CIS*2500,\nCIS*2030," +
                            "CIS*2430,CIS*2520,CIS*3250,CIS*2750,\nCIS*3110," +
                            "CIS*3750,CIS*2460,STAT*2040,CIS*3760,\nCIS*3260,CIS*4150,CIS*4300,CIS*4250";
                }
                else if(string == compSci){
                    courses = "MATH*1200,CIS*1500,CIS*1910,CIS*2500,CIS*2030," +
                            "CIS*2430,\nCIS*2520,CIS*2910,CIS*2750,CIS*3110,CIS*3490," +
                            "CIS*3150,\nCIS*3750,CIS*2460,STAT*2040,CIS*3760,CIS*4650";
                }
                else if(string == genComp){
                    courses = "CIS*1500,CIS*1910,CIS*2430,CIS*2500,CIS*2520,\nCIS*2750," +
                            "CIS*2910,CIS*3530";
                }

                majorText.setText(courses);
            }
            else if(event.getSource() == majorB){
                String string;
                String fullDegMin;

                string = majorChoice.getSelectedItem().toString();
                degMinInfo.add(string);
                fullDegMin = toStringList(degMinInfo);
                currDegMinArea.setText(fullDegMin);

                majorP.setVisible(false);
                homeP.setVisible(true);
                pack();
            }
        }
    }

    public String toStringList(ArrayList<String> list){

        String newString = "";

        for(String s : list){

            newString += s + "\n";
        }
        return newString;
    }

    public class NullException extends Exception {
        public NullException(String message) { super(message); }

    }

    public static void main(String[] args){

        Planner planner = new Planner();
        planner.setVisible(true);
    }
}
