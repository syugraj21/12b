/*Yugraj Singh
 *Test the method and the Queue to see if the stack is functioning 
 *correctly  
 */ 

public class QueueTest{
  public static void main (String[] args){
    Queue A = new Queue();
    
    System.out.println(A.isEmpty());
    //inserts
    A.enqueue((int)4);
    A.enqueue((int)2);
    A.enqueue((int)3);
    A.enqueue((int)7);
    A.enqueue((int)6);
    A.enqueue((int)8);
    A.enqueue((int)9);
    A.enqueue((int)13);
    A.enqueue((int)17);
    A.enqueue((int)14);
    //lets the size and if its empty
   System.out.println(A.isEmpty());
   System.out.println(A.length());
    
    System.out.println(A);
    //deletes 
    A.dequeue();
    A.dequeue();
    //
    System.out.println(A.length());
    System.out.println(A);
    
    //throws exception
    //A.dequeueAll();
    //System.out.println(A.peek);
  }
}