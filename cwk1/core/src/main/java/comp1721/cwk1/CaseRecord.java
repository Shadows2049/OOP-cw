package comp1721.cwk1;

import java.time.LocalDate;

public class CaseRecord {

  // TODO: Write stub for constructor
 private LocalDate date;
 private int staffCases;
 private int studentCases;
 private int otherCases;
 private int totalCases;
 private String toString;



  CaseRecord(LocalDate dateIn, int staffCasesIn,int studentCasesIn, int otherCasesIn){     //Constructor
    if(staffCasesIn<0||studentCasesIn<0||otherCasesIn<0)
  {throw new DatasetException("Invalid input");}
      date = dateIn;
      staffCases = staffCasesIn;
      studentCases = studentCasesIn;
      otherCases = otherCasesIn;
  }


  // TODO: Write stubs for four getter methods
    public LocalDate getDate(){         //need getter functions to get data from CaseRecord
     return this.date;

    }

    public int getStaffCases(){
      return this.staffCases;
    }

    public int getStudentCases(){
      return this.studentCases;
    }

    public int getOtherCases(){
      return this.otherCases;
    }
  // TODO: Write stub for totalCases()

    public int totalCases(){                //Total Cases
      totalCases = staffCases + studentCases + otherCases;
      return this.totalCases;
    }
  
  // TODO: Write stub for toString()

    public String toString(){                //Format
      toString = date + ": " + this.staffCases + " staff, " + this.studentCases + " students, " + this.otherCases + " other";

      return this.toString;
    }

 
}
