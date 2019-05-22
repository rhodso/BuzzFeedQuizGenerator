import javax.swing.*;
import java.awt.*;

public class question {
    int qNum;
    String pick;
    String tell;

    JPanel bodyPanel;
    
    public question(String[] quiz, int questionNumber){
        pick = quiz[0];
        tell = quiz[1];
        qNum = questionNumber;

        //Build layout
        bodyPanel = new JPanel();
        bodyPanel.setLayout(new GridLayout(2, 2, 25, 25));
        bodyPanel.setSize(new Dimension(700, 500));
        


    }

    
}