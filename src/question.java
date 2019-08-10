import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class question {
    int qNum;
    String pick;
    String tell;
    generator g;
    String[] questionParts;
    /*  questionParts array
        0- Question
        1-4 Answers 1-4*/

    //UI vars
    JFrame mainFrame;
    JPanel randomQuizPanel;
    JLabel randomQuizTestLabelPart1;
    JLabel randomQuizTestLabelPart2;
    JLabel randomQuizTestLabelPart3;

    public question(String[] quiz, int questionNumber, int noOfQuestions) throws FileNotFoundException {
        //Set question variables
        pick = quiz[0];
        tell = quiz[1];
        qNum = questionNumber;
        questionParts = getQuestionParts(quiz);

        if(questionParts[0] == "!!Failure!!"){
            throw new FileNotFoundException("Question file: " + questionNumber + ".txt from quiz: " + pick + ". Not found");
        }
        
        //Create UI
        ImageIcon img = new ImageIcon("assets/images/Bfqg_H.png");
        mainFrame = new JFrame("Question " + qNum);
        mainFrame.setSize(new Dimension(700,500));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setIconImage(img.getImage());

        //randomQuizPanel
        randomQuizPanel = new JPanel();
        GridLayout randomQuizGridLayout = new GridLayout(3,1);
        randomQuizPanel.setLayout(randomQuizGridLayout);
        randomQuizGridLayout.setHgap(20);
        randomQuizPanel.setSize(new Dimension(700,100));
        randomQuizPanel.setPreferredSize((new Dimension(700,100)));

        //RandomQuizTestLabel P1
        randomQuizTestLabelPart1 = new JLabel(quiz[0], SwingConstants.CENTER);
        randomQuizTestLabelPart1.setFont(new Font(randomQuizTestLabelPart1.getFont().getName(), Font.BOLD, 16));
        randomQuizPanel.add(randomQuizTestLabelPart1);

        //RandomQuizTestLabel P2
        randomQuizTestLabelPart2 = new JLabel("and we'll tell you", SwingConstants.CENTER);
        randomQuizTestLabelPart2.setFont(new Font(randomQuizTestLabelPart2.getFont().getName(), Font.ITALIC, 14));
        randomQuizPanel.add(randomQuizTestLabelPart2);

        //RandomQuizTestLabel P3
        randomQuizTestLabelPart3 = new JLabel(quiz[1], SwingConstants.CENTER);
        randomQuizTestLabelPart3.setFont(new Font(randomQuizTestLabelPart3.getFont().getName(), Font.BOLD, 16));
        randomQuizPanel.add(randomQuizTestLabelPart3);

        //Add randomQuizPanel to testFrame
        mainFrame.add(randomQuizPanel, BorderLayout.CENTER);
            
        //Spacing panel
        JPanel spacingPanel = new JPanel();
        spacingPanel.setPreferredSize(new Dimension(700,50));
        spacingPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        mainFrame.add(spacingPanel);
        
        JLabel questionTextLabel = new JLabel(questionParts[0]);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(700, 200));
        buttonPanel.setLayout(new GridLayout(2, 2));
        buttonPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JButton a1Button = new JButton(questionParts[1]);
        a1Button.setPreferredSize(new Dimension(150, 100));
        a1Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    continueQuestions(quiz, noOfQuestions);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        JButton a2Button = new JButton(questionParts[2]);
        a2Button.setPreferredSize(new Dimension(150, 100));
        a2Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    continueQuestions(quiz, noOfQuestions);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        JButton a3Button = new JButton(questionParts[3]);
        a3Button.setPreferredSize(new Dimension(150, 100));
        a3Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    continueQuestions(quiz, noOfQuestions);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        JButton a4Button = new JButton(questionParts[4]);
        a4Button.setPreferredSize(new Dimension(150, 100));
        a4Button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    continueQuestions(quiz, noOfQuestions);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonPanel.add(a1Button);
        buttonPanel.add(a2Button);
        buttonPanel.add(a3Button);
        buttonPanel.add(a4Button);
        
        mainFrame.add(questionTextLabel, BorderLayout.CENTER);
        mainFrame.add(spacingPanel, BorderLayout.SOUTH);
        mainFrame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void continueQuestions(String[] quiz, int noOfQuestions) throws FileNotFoundException{
        if(qNum == noOfQuestions){

            //Vars
            BufferedReader br;
            ArrayList<String> fileOut = new ArrayList<>(0);
            String[] sloganList;
            String s;
            String dir = "assets/tell/" + g.getNamingScheme(quiz[1]) + ".txt";
            String quizAnswer; 

            try{
                //Read from file
                br = new BufferedReader(new FileReader(new File(dir)));
                s = br.readLine();
                while(s != null)
                {
                    fileOut.add(s);
                    s = br.readLine();
                }
                br.close();
                //And add to the array
                sloganList = new String[fileOut.size()];
                for(int i = 0; i < fileOut.size(); i++){
                    sloganList[i] = fileOut.get(i);
                }
    
                //Pick random slogan
                Random rng = new Random();
                quizAnswer = sloganList[rng.nextInt(sloganList.length)];
    
            }
            catch(Exception ex){
                //If error, return this
                quizAnswer = "Something went wrong lol. It doesn't even matter anyway";
                System.out.println("Exception occured in BFQG.java\nMessage:\t" + ex.getMessage());
            }
            this.mainFrame.setVisible(false);
            JOptionPane.showMessageDialog(mainFrame, quizAnswer, "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            question q = new question(quiz, qNum+1, noOfQuestions);
            q.ask();
            this.mainFrame.setVisible(false);
        }
    }

    public String[] getQuestionParts(String[] quiz){
        String[] questionPartsArray = new String[5];
        g = new generator();

        //Read content from file
        String dir = "assets/pick/" + g.getNamingScheme(quiz[0]) + "/" + qNum + ".txt";
        BufferedReader br;
        ArrayList<String> fileOut = new ArrayList<>(0);
        String s;

        try{
            //Read pick topics from the pick list
            br = new BufferedReader(new FileReader(new File(dir)));
            s = br.readLine();
            while(s != null)
            {
                fileOut.add(s);
                s = br.readLine();
            }
            br.close();
            //And add to the array
            questionParts = new String[fileOut.size()];
            for(int i = 0; i < fileOut.size(); i++){
                questionPartsArray[i] = fileOut.get(i);
            }
            return questionPartsArray;
        }
        catch(IOException e){
            String[] oShit = {"!!Failure!!"};
            return oShit;
        }
    }

    public void ask() {
        mainFrame.setVisible(true);
    }    
}