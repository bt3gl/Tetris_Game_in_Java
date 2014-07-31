// Find the fastest runner from a list
// the program will take an input array of integers
// and returns the index corresponding to the person
// with the lowest time.

// here we need to read columns of a file

import java.io.*;
import com.google.common.io.*;
import com.google.common.base.*;
import java.util.*;
import com.google.common.collect.Lists;

class MarathonAdv{

  public static int findMinIndex(List<String> values){
    int minValue = Integer.MAX_VALUE;
    int minIndex = -1;

    for(int i=0; i < values.size(); i++){
      int valueHere = Integer.parseInt(values.get(i));
      if(valueHere < minValue){
        minValue = valueHere;
        minIndex = i;
      }
    }
    return minIndex;
  }


  public static int findSecMinIndex(List<String> values, int minIndex){
    int minSecValue = Integer.MAX_VALUE;
    int minSecIndex = -1;

    for(int i=0; i < values.size(); i++){
      int valueHere = Integer.parseInt(values.get(i));
      if(valueHere < minSecValue && i != minIndex){
        minSecValue = valueHere;
        minSecIndex = i;
      }
    }
    return minSecIndex;
  }



  public static void main(String[] arguments){

    try{
      File mara = new File("marathon.txt");
      String contents =  Files.toString(mara, Charsets.UTF_8);

      List<String> names = Lists.newArrayList();
      List<String> values = Lists.newArrayList();

      String[] rows = contents.split("\n");

      for (String row: rows){
          String[] cells = row.split(" ");
          names.add(cells[0]);
          values.add(cells[1]);
        }


      int minIndex = findMinIndex(values);
      int secMinIndex = findSecMinIndex(values, minIndex);

      System.out.println("The best runner was " + names.get(minIndex)
      + " with " + values.get(minIndex) + " seconds.");
      System.out.println("The second best runner was " + names.get(secMinIndex)
      + " with " + values.get(secMinIndex) + " seconds.");

    }

    catch(IOException e){
      e.printStackTrace();
    }

  }

}


