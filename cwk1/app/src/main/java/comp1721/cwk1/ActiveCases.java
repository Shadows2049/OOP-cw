package comp1721.cwk1;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ActiveCases {
    public static void main(String[] args){
        String loadFile;
        String saveFile;

        System.out.println("Provide the loadFile");         //scam for loadFile
        Scanner sc = new Scanner(System.in);
        loadFile = sc.next();

        if(loadFile==null){
            System.out.println("Error");
        }

        System.out.println("Provide the saveFile");         //scam for saveFile
        saveFile = sc.next();

        if(saveFile==null){
            System.out.println("Error");
        }

        sc.close();

        CovidDataset newCs = new CovidDataset();            //new CovidDataset object
        try{newCs.readDailyCases(loadFile);}                //READ FILE
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

        try{newCs.writeActiveCases(saveFile);}              //SAVE FILE
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Number of data in CovidDataset are: "+newCs.size());

    }
}
