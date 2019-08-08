import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

public class question {
    int qNum;
    String pick;
    String tell;
    generator g;
    String[] questionParts;
    /*  questionParts array
        0- Question
        1-4 Answers 1-4*/

    JPanel bodyPanel;

    public void showHidePanel(boolean b){
        bodyPanel.setVisible(b);
    }

    public question(String[] quiz, int questionNumber){
        pick = quiz[0];
        tell = quiz[1];
        qNum = questionNumber;
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
                questionParts[i] = fileOut.get(i);
            }

        }
        catch(IOException e){
            System.out.println("Exception occured in generator.java\nMessage:\t" + e.getMessage());
        }
    }

    public int ask(){
        int returnVar = 0;

        System.out.println("Question " + qNum + "\n" + questionParts[0] + "\nA - " + questionParts[1] + "\nB - " + questionParts[2] + "\nC - " + questionParts[3] + "\nD - " + questionParts[4] + "\n\n");

        return returnVar;
    }
    
}