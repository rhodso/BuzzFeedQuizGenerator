import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class generator{

    //  Standard quiz layout is as follows:
    //  PICK and we'll tell you TELL, for example
    //  "Choose some pizza toppings and we'll tell you which GOT character would've killed you"

    String[] pickList;
    String[] tellList;

    public generator(){
        //Create some vars to read from a file
        BufferedReader br;
        ArrayList<String> fileOut = new ArrayList<>(0);
        String s;

        try{
            //Read pick topics from the pick list
            br = new BufferedReader(new FileReader(new File("assets/pick/pickList.txt")));
            s = br.readLine();
            while(s != null)
            {
                fileOut.add(s);
                s = br.readLine();
            }
            br.close();
            //And add to the array
            pickList = new String[fileOut.size()];
            for(int i = 0; i < fileOut.size(); i++){
                pickList[i] = fileOut.get(i);
            }

            //Read tell answers from the tell list
            br = new BufferedReader(new FileReader(new File("assets/tell/tellList.txt")));
            fileOut = new ArrayList<>(0);
            s = br.readLine();
            while(s != null)
            {
                fileOut.add(s);
                s = br.readLine();
            }
            br.close();
            //And add to array
            tellList = new String[fileOut.size()];
            for(int i = 0; i < fileOut.size(); i++){
                tellList[i] = fileOut.get(i);
            }
        }
        catch (IOException e){
            System.out.println("Exception occured in generator.java\nMessage:\t" + e.getMessage());
        }
    };

    //Allows random quiz generation. Quizzes are stored so that they can be easily searched for answers, but also so
    //they can be easily read by user, for example: quiz[0] + " and we'll tell you " + quiz[1]
    public String[] newQuiz(){
        String[] quiz = {"",""};
        Random rng = new Random();
        quiz[0] = pickList[rng.nextInt(pickList.length)];
        quiz[1] = tellList[rng.nextInt(tellList.length)];
        return quiz;
    }

    //Searches for a specified quiz part, so appropriate assetts can be located
    public int search(String listType, String searchTerm){
        int result = -1;

        //From pick list
        if(listType.equals("PICK")){
            for(int i = 0; i < pickList.length; i++){
                if(searchTerm.toLowerCase().equals(pickList[i].toLowerCase())){
                    result = i;
                    break;
                }
            } 
        }

        //From tell list
        else if(listType.equals("TELL")){
            for(int i = 0; i < tellList.length; i++){
                if(searchTerm.toLowerCase().equals(tellList[i].toLowerCase())){
                    result = i;
                    break;
                }
            }
        }

        //no list chosen
        else{}

        //Returns -1 if not found or incorrect list
        return result;
    }

}