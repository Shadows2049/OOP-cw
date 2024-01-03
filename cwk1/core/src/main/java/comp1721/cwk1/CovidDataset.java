package comp1721.cwk1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;


public class CovidDataset {

   private int mSize;
   public CaseRecord[] caseR;               //Array for CaseRecord
   private CaseRecord[] newArray;           //Array for extending caseR's space
  
    CovidDataset(){
      caseR = new CaseRecord[10];            //Constructor
    }
    

  // TODO: Write stub for size()
      public int size(){                    //Number of elements in array

        int n=0;
        int i; 
        for(i=0; i < caseR.length; i++)
        {
        if(caseR[i]!=null) 
        {n++;}

        }
        return n;
      }

  // TODO: Write stub for getRecord()
      public CaseRecord getRecord(int index){   //get record from array with given index
        
        if(index<0||index>=mSize){
          throw new DatasetException("error");
        }
        return caseR[index];

      }

  // TODO: Write stub for addRecord()
      public void addRecord(CaseRecord rec){         //add record to caseRecord
        

        System.out.println("length is: "+caseR.length);
        if(mSize==caseR.length){                      //use resize function to extend array
          resize(2 * (caseR.length));
        }
          System.out.println("mSize is:"+mSize);

        caseR[mSize] = rec;
        mSize++;

      }

  // TODO: Write stub for dailyCasesOn()
      public CaseRecord dailyCasesOn(LocalDate day){            //use given date tot retrieve data
        for(int i=0; i<mSize;++i)
        {
          if(caseR[i].getDate() == day)
          {return caseR[i];}
        }
        throw new DatasetException("No statics found");
      }

  // TODO: Write stub for readDailyCases()
      public void readDailyCases(String filename)throws FileNotFoundException    //read data from csv file
      { int gg;
        int j;

        for(j=0;j<size();++j)
        {caseR[j] = null;}

        File file = new File(filename);
        Scanner scanner = new Scanner(file);                                    //scanner
        scanner.nextLine();                                                 //skip the first line(headings)
        while (scanner.hasNext()) {
            String string = scanner.nextLine();                             //scan a line and sava data

            gg = 0;
            for (String retval : string.split(",")) {                  //count element, if < 4 then throw exception
                gg = gg + 1;
            }

            if (gg < 4) {
                throw new DatasetException("gg");
            }


       Scanner input = new Scanner(string);

        input.useDelimiter(",");                                          //separate each element

        String date= input.next();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");    //change string format to localDate
        LocalDate ldt = LocalDate.parse(date,df);

        int staffCases=input.nextInt();
        int studentCases=input.nextInt();
        int otherCases=input.nextInt();

        System.out.println("date is: "+ ldt);
        System.out.println("staffCases is: "+ staffCases);
        System.out.println("studentCases is: "+ studentCases);
        System.out.println("otherCases is: "+ otherCases);

        CaseRecord newCase = new CaseRecord(ldt,staffCases,studentCases,otherCases);          //set a new CaseRecord object
        addRecord(newCase);                                                                 //add data
        System.out.println("CaseRecord is: "+ caseR[mSize-1]);

        input.close();                                                                       //close input


        }
        scanner.close();                                                                    //close scanner
      }

  // TODO: Write stub for writeActiveCases()
     public void writeActiveCases(String filename)throws FileNotFoundException              //write data to local file
      {
          int i;
          int t;
          int count = size();

          LocalDate dateSum;

          int staffCasesSum;
          int studentCasesSum;
          int otherCasesSum;

          if(count<10)                                                                      //if total days less than 10, throw exception
          {throw new DatasetException("not enough");}

          File file = new File(filename);

          PrintWriter output = new PrintWriter(file);                                        //write headings
          output.print("Date");
          output.print(",");
          output.print("Staff");
          output.print(",");
          output.print("Students");
          output.print(",");
          output.print("Other");
          output.print("\n");
          for(i=0;i<count;++i){                                                             //process data and write to local file
              if((count-i)>=10){
                  staffCasesSum = 0;
                  studentCasesSum = 0;
                  otherCasesSum = 0;
                  for(t=0;t<10;++t)
                  {
                      staffCasesSum = staffCasesSum + caseR[i+t].getStaffCases();
                      studentCasesSum = studentCasesSum + caseR[i+t].getStudentCases();
                      otherCasesSum = otherCasesSum + caseR[i+t].getOtherCases();
                  }

                  dateSum = caseR[i+9].getDate();
                  DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                  String dateStr = dateSum.format(fmt);

                  output.print(dateStr);
                  output.print(",");
                  output.print(staffCasesSum);
                  output.print(",");
                  output.print(studentCasesSum);
                  output.print(",");
                  output.print(otherCasesSum);
                  output.print("\n");

              }
          }
          output.close();                                                   //close output
      }


      private void resize(int newLength) {                                  //function to resize array
 
        newArray = new CaseRecord[newLength];
        
        for (int i = 0; i < mSize; i++) {                                   //copy elements
    
            newArray[i] = caseR[i];
        }
        caseR = newArray;


    } 

    public static void main(String[] args)throws FileNotFoundException {           //main function for testing
        CovidDataset newCs = new CovidDataset();
        newCs.readDailyCases("D:/coursework/cwk1/datafiles/testing/gg2.csv");
    }

    }