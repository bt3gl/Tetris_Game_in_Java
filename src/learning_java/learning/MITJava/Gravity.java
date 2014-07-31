// In this program we compute the distances an object
// will fall in Earth's gravity

class GravityCalculator{
  public static void main(String[] arguments){
    double gravity = -9.81;
    double initialVelocity = 0.0;
    double fallingTime = 10.0;
    double initialPosition = 0.0;
    double finalPosition = 0.0;
    double t = 10.0;

    finalPosition = 0.5*gravity*Math.pow(t,2) + initialVelocity*t +
    initialPosition;

    System.out.println("The object's position after " +
      fallingTime + " seconds is " + finalPosition + "m.");
  }
}