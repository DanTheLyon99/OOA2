import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Planner extends JFrame {

    /*StartUp UI*/
    private JPanel startP = new JPanel();
    private JPanel labelP = new JPanel();
    private JLabel welcomeL = new JLabel("Welcome, Please Sign In");
    private JButton adminB = new JButton("Admin");
    private JButton userB = new JButton("User");

    /*User and Admin screens*/
    private JPanel inputP = new JPanel();
    private JLabel userPwL = new JLabel("Please enter your name and ID");
    private JLabel userL = new JLabel("User: ");
    private JLabel pwL = new JLabel("ID: ");
    private JTextField userText = new JTextField(10);
    private JPasswordField pwText = new JPasswordField(10);
    private JButton signInB = new JButton("Sign in");

    /*Home*/
    private JPanel homeP = new JPanel();
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
    private JTextArea tArea = new JTextArea(10,30);
    private JLabel degreeL = new JLabel("Degrees: ");
    private JTextField degreeF = new JTextField(10);
    private JButton submitDegree = new JButton("Submit");
    private ArrayList<String> degreeList = new ArrayList<>();
    private JPanel degreeP = new JPanel();
    private JPanel buttonP = new JPanel();

    public Planner(){
        super();
        gui();
    }

    public void gui(){
        setSize(600,400);
        setTitle("Welcome!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        startUp();
        adminOrUser();
        chooseDegree();
        Home();
        pack();
    }

    private void startUp(){
        startP.setVisible(false);
        labelP.setVisible(false);

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
        gbc.anchor = GridBagConstraints.WEST;

        //  inputP.add(userPwL, gbc);
        // gbc.gridy++;
        inputP.add(userL, gbc);
        gbc.gridx++;
        inputP.add(userText, gbc);
        gbc.gridx++;
        inputP.add(pwL, gbc);
        gbc.gridx++;
        inputP.add(pwText, gbc);
        gbc.gridy++;

        gbc.gridx = 2;
        inputP.add(signInB,gbc);


        startUpListener admin = new startUpListener();
        signInB.addActionListener(admin);

        add(inputP);
    }
    private void Home(){
        homeP.setVisible(true);
        homeP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        gbc.ipadx = 10;

        homeP.add(selectDegreeB, gbc);
        gbc.gridx++;
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

        add(homeP);


    }
    private void chooseDegree(){
        degreeP.setVisible(false);
        buttonP.setVisible(false);

        buttonP.setLayout(new BoxLayout(buttonP, BoxLayout.PAGE_AXIS));
        buttonP.add(degreeF);
        buttonP.add(submitDegree);
        add(buttonP);

        degreeP.setLayout(new BorderLayout());
        tArea.setEditable(false);
        tArea.setText("No Current Degrees");
        degreeP.add(degreeL, BorderLayout.NORTH);
        degreeP.add(tArea);

        degListener listen = new degListener();
        submitDegree.addActionListener(listen);
        degreeF.addActionListener(listen);

        add(degreeP);
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
                startP.setVisible(true);
                labelP.setVisible(true);
                inputP.setVisible(false);
                pack();
            }
        }
    }

    public class degListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String tempString;
            if(event.getSource() == submitDegree)
            {
                tempString = degreeF.getText();

                if(tempString == "General"){
                    BCG BachOfComp = new BCG();
                }
                tArea.setText(tempString);
                degreeF.setText(null);
            }
            if(event.getSource() == degreeF)
            {
                tempString = degreeF.getText();
                tArea.setText(tempString);
                degreeF.setText(null);
            }

        }

    }
    /** @author: Dan, by the way **/

    public String toStringList(ArrayList<String> list){

        String newString = "";

        for(String s : list){

            newString += s + "\n";
        }
        return newString;
    }

    public static void main(String[] args){

        Planner planner = new Planner();
        planner.setVisible(true);
    }
}
