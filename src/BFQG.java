import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class BFQG {

    JFrame testFrame;
    JLabel randomQuizTestLabelPart1;
    JLabel randomQuizTestLabelPart2;
    JLabel randomQuizTestLabelPart3;
    String slogan;


    //Show or hide the frame
    void showHideFrame(boolean state) {
        testFrame.setVisible(state);
    }

    //Plays music from file given by filepath
    void playThatFunkyMusic(String musicFilepath) {
        try {
            File yourFile = new File(musicFilepath);
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            JOptionPane j = new JOptionPane(
                    "Something appears to have fucked up with the music.\nI'm not going to force you to restart but like, music and shit. Pls?");
            j.setVisible(true);
        }
    }

    //Constructor
    public BFQG() {
        //Get the slogan
        slogan = getSlogan();

        //Setup testFrame
        testFrame = new JFrame();
        testFrame.setSize(new Dimension(700, 500));
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setLayout(new BorderLayout());

        //HeaderPanel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(2,1));
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        headerPanel.setBorder(padding);

        //Header label
        JLabel headerLabel = new JLabel("Welcome to the BuzzFeed quiz generator");
        headerLabel.setFont(new Font(headerLabel.getFont().getName(), Font.PLAIN, 24));
        headerPanel.add(headerLabel);
        
        //Slogan label
        JLabel sloganLabel = new JLabel(slogan);
        sloganLabel.setFont(new Font(headerLabel.getFont().getName(), Font.ITALIC, 18));
        headerPanel.add(sloganLabel);
        
        //Add testframe to headerpanel
        testFrame.add(headerPanel, BorderLayout.PAGE_START);

        //RandomQuizPanel
        JPanel randomQuizPanel = new JPanel();
        GridLayout randomQuizGridLayout = new GridLayout(3,1);
        randomQuizPanel.setLayout(randomQuizGridLayout);
        randomQuizGridLayout.setHgap(20);

        //RandomQuizTestLabel P1
        randomQuizTestLabelPart1 = new JLabel("Label1", SwingConstants.CENTER);
        randomQuizTestLabelPart1.setFont(new Font(randomQuizTestLabelPart1.getFont().getName(), Font.BOLD, 16));
        randomQuizPanel.add(randomQuizTestLabelPart1);

        //RandomQuizTestLabel P2
        randomQuizTestLabelPart2 = new JLabel("Label2", SwingConstants.CENTER);
        randomQuizTestLabelPart2.setFont(new Font(randomQuizTestLabelPart2.getFont().getName(), Font.ITALIC, 14));
        randomQuizPanel.add(randomQuizTestLabelPart2);

        //RandomQuizTestLabel P3
        randomQuizTestLabelPart3 = new JLabel("Label3", SwingConstants.CENTER);
        randomQuizTestLabelPart3.setFont(new Font(randomQuizTestLabelPart3.getFont().getName(), Font.BOLD, 16));
        randomQuizPanel.add(randomQuizTestLabelPart3);

        //Add randomQuizPanel to testFrame
        testFrame.add(randomQuizPanel, BorderLayout.CENTER);

        //New Quiz Button
        JButton newQuizButton = new JButton();
        newQuizButton.setText("New quiz");
        newQuizButton.setSize(new Dimension(100,100));
        newQuizButton.addActionListener(new ActionListener() {

            //Action listener for newQuizButton
            @Override
            public void actionPerformed(ActionEvent arg0) {
                newQuizButtonAction();
            }
            
        });

        //Add newQuizButton to testFrame
        testFrame.add(newQuizButton, BorderLayout.PAGE_END);

        //Generate a new quiz, then start playing music
        //newQuizButtonAction();
        playThatFunkyMusic("assets/music/Renegade_Jubilee_Wav.wav");
    }

    //Actual action for newQuizButton
    void newQuizButtonAction(){
        //Create generator, get new quiz, set text in labels
        generator quizGen = new generator();
        String[] quiz = quizGen.newQuiz();
        randomQuizTestLabelPart1.setText(quiz[0]);
        randomQuizTestLabelPart2.setText(" and we'll tell you ");        
        randomQuizTestLabelPart3.setText(quiz[1]);
    }

    String getSlogan(){
        //Vars
        BufferedReader br;
        ArrayList<String> fileOut = new ArrayList<>(0);
        String[] sloganList;
        String s;

        try{
            //Read slogans from file
            br = new BufferedReader(new FileReader(new File("assets/slogans.txt")));
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
            return sloganList[rng.nextInt(sloganList.length)];

        }
        catch(Exception ex){
            //If error, return this
            return "Who even needs slogans?";
        }
    }

    public static void main(String[] args) {
        //Create instance of BFQG, and then make it visible
        BFQG bfqginstance = new BFQG();
        bfqginstance.showHideFrame(true);
    }
}