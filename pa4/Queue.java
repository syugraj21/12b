/*Yugraj Singh
 * Queue ADT Class
 *
 */
public class Queue implements QueueInterface{
  
  //Inner private node class which constructs the queue
  private class Node{
    Object item;
    Node next;
    
    Node(Object item){
      this.item = item;
      next = null;
    }
  }
  //private fields
  private Node front;
  private Node back;
  private int numItems;
  //Constructor 
  Queue(){
    front = null;
    back  = null;
    numItems = 0;
  } 
  
  /*isEmpty()
   *Pre:none
   *Pos:returns true or flase if the queue is empty
   */
  public boolean isEmpty(){
    return (numItems==0);
  }
  //tells the size of the Queue
  public int length(){
    return numItems;
  }
  /*enqueue
   *Pre:None 
   *Pos:Inserts an item in to the top of the stack 
   */ 
  public void enqueue(Object newItem){
    if(front == null){
      front = new Node(newItem);
      numItems++;
    }
    else{
      Node N = front;
      while( N.next != null){
        N = N.next;
      }
      N.next = new Node(newItem);
      back = N.next;
      numItems++;
    } 
  }
  /*dequeue
   *Pre: the Queue can not be empty 
   *Pos: deletes the item on the top of the stack 
   */ 
  public Object dequeue() throws QueueEmptyException{
    if(front == null){
      throw new QueueEmptyException("Usage: using dequeue() on empty queue stack");
    }
    else{
      Node N = front;
      front = N.next;
      numItems--;
      return N.item;
    }
  }
  /*peek
   *Pre: the Queue can not be empty 
   *Pos: returns the item on top of the list 
   */
  public Object peek() throws QueueEmptyException{
    if(front == null){
      throw new QueueEmptyException("Usage: using peek() on empty queue stack");
    }
    else{
      return front.item;
    }
  }
  /*dequeueAll
   *Pre: the Queue can not be empty 
   *Pos: empties the stack 
   */
  public void dequeueAll() throws QueueEmptyException{
    if(front == null){
      throw new QueueEmptyException("Usage: using dequeueAll() on empty queue stack");
    }
    else{
      front = null;
      back = null;
      numItems = 0;
    }
  }
  /*toString
   *Pre:none 
   *Pos:prints whats in the stack 
   */ 
  public String toString(){
    String s=""; 
    Node N = front;
    while(N != null){
      s += N.item + " " ;
      N = N.next;
    }
    return s;
  }
  
  
}