// Find the fastest runner from a list
// the program will take an input array of integers
// and returns the index corresponding to the person
// with the lowest time.

// here we need to read columns of a file

import java.io.*;

class Marathon{

  public static int findMinIndex(int[] values){
    int minValue = Integer.MAX_VALUE;
    int minIndex = -1;

    for(int i=0; i < values.length; i++)
      if (values[i] < minValue){
        minValue = values[i];
        minIndex = i;
      }

    return minIndex;
  }


  public static int findSecMinIndex(int[] values, int minIndex){
    int secMinValue = Integer.MAX_VALUE;
    int secMinIndex = -1;

    for(int i=0; i < values.length; i++)
      if (values[i] < secMinValue && i != minIndex){
        secMinValue = values[i];
        secMinIndex = i;
      }

    return secMinIndex;
  }

  public static void main(String[] arguments){
    String[] names = {
            "Elena", "Thomas", "Hamilton", "Suzie", "Phil", "Matt", "Alex",
            "Emma", "John", "James", "Jane", "Emily", "Daniel", "Neda",
            "Aaron", "Kate"
        };
    int[] values = {
            341, 273, 278, 329, 445, 402, 388, 275, 243, 334, 412, 393, 299,
            343, 317, 265
        };


    int minIndex = findMinIndex(values);
    int secMinIndex = findSecMinIndex(values, minIndex);

    System.out.println("The best runner was " + names[minIndex]
      + " with " + values[minIndex] + " seconds.");
    System.out.println("The second best runner was " + names[secMinIndex]
      + " with " + values[secMinIndex] + " seconds.");

  }

}
