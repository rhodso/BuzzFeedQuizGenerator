import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class BFQG {

    JFrame testFrame;
    JTextArea randomQuizTestLabel;
    String slogan;

    void showHideFrame(boolean state) {
        testFrame.setVisible(state);
    }

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

    public BFQG() {
        slogan = getSlogan();

        testFrame = new JFrame();
        testFrame.setSize(new Dimension(700, 500));
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Welcome to the BuzzFeed quiz generator - " + slogan);
        testFrame.add(headerLabel, BorderLayout.PAGE_START);

        randomQuizTestLabel = new JTextArea();
        randomQuizTestLabel.setLineWrap(true);
        randomQuizTestLabel.setWrapStyleWord(true);
        testFrame.add(randomQuizTestLabel, BorderLayout.CENTER);

        JButton newQuizButton = new JButton();
        newQuizButton.setText("New quiz");
        newQuizButton.setMaximumSize(new Dimension(100,100));
        newQuizButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent arg0) {
                newQuizButtonAction();
            }
            
        });
        testFrame.add(newQuizButton, BorderLayout.PAGE_END);

        newQuizButtonAction();
        playThatFunkyMusic("assets/music/Renegade_Jubilee_Wav.wav");
    }

    void newQuizButtonAction(){
        generator quizGen = new generator();
        String[] quiz = quizGen.newQuiz();
        randomQuizTestLabel.setText(quiz[0] + " and we'll tell you " + quiz[1]);
        
    }

    String getSlogan(){
        //Vars
        BufferedReader br;
        ArrayList<String> fileOut = new ArrayList<>(0);
        String[] sloganList;
        String s;

        try{
            //Read slogans
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

            Random rng = new Random();
            return sloganList[rng.nextInt(sloganList.length)];

        }
        catch(Exception ex){
            return "Who even needs slogans?";
        }
    }

    public static void main(String[] args) {
        BFQG bfqginstance = new BFQG();
        bfqginstance.showHideFrame(true);
    }
    
}