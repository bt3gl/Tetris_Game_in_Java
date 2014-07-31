// Foo Corporation needs a program to calculate how
// much to pay their hourly employees.
// For any hour more than 40, they get paid 1.5x
// The min wage is $8.0
// No one should work more than 60 hours in a week





class FooCorporation{

  public static double pay(double hours){
    double limitHours1 = 40.0;
    double limitHours2 = 60.0;
    double minWage = 8.0;
    double salary = 0;

    if (hours <= limitHours1){
      salary = minWage*hours;
    }
    else if (hours > limitHours1 && hours < limitHours2) {
      salary = minWage*1.5*hours;
    }
    else{
      System.out.println("Not a valid work hours!");
      return -1;
    }

    return salary;
  }

  public static void main(String[] args){

    String hours_s = args[0];
    double hours = Integer.parseInt(hours_s);
    double salary = pay(hours);

    System.out.println("The salary is " + salary + ".");
  }
}


