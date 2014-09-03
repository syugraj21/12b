/*Yugraj Singh W1234081
 *Extrema.java
 *This uses recursion to find the max and min of an array  
 *  
 */
class Extrema{
  public static void main(String[] args){  //Main class
    int[] B = {-1, 2, 6, 3, 9, 2, -3, -2, 11, 5, 7,};
    System.out.println( "max = " + maxArray(B, 0, B.length-1) );
    System.out.println( "min = " + minArray(B, 0, B.length-1) );
  }
  //maxArray
  //Pre: takes an Array, along with two integers p and r. 
  //     p and r must be >= 0 && < the length of the Array  
  //Pos: returns the max integer in the array
  public static int maxArray(int[] A, int p, int r){
    int q;
    if(p == r){
      return A[p];
    }
    else{
      q = (p+r)/2;
      return max(maxArray(A, p, q), maxArray(A, q+1, r));
    }
  }
  //minArray
  //Pre: takes an Array, along with two integers p and r.
  //     p and r must be >= 0 && < the length of the Array
  //Pos: returns the max integer in the array
  public static int minArray(int[] A, int p, int r){
    int q;
    if(p == r){
      return A[p];
    }
    else{
      q = (p+r)/2;
      return min(minArray(A, p, q), minArray(A, q+1, r));
    }
  }
  //max
  //Pre: takes two ints
  //Pos: returns the largest integers
  public static int max(int a, int b){
    if(a >= b){
      return a;
    }
    else{
      return b;
    }
  }
  
  //min 
  //Pre: takes two ints
  //Pos: return the smallest integer
  public static int min(int a, int b){
    if(a <= b){
      return a;
    }
    else{
      return b;
    }
  } 
}
