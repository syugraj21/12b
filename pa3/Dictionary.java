/*Yugraj Singh
 * Dicionary class which has the method definition for the  
 *dictionary ADT  
 */ 
public class Dictionary implements DictionaryInterface{
  
  /*Inner private class Node
   *Pre: takes an  string key an value  
   *Pos: creates a structure for a linked list  
   */
  private class Node{
    String key;
    String value;
    Node next;
    //node class constructor
    Node(String key, String value){
      this.key = key;
      this.value = value;
      next = null;
    }
  }
  //private feild which control the linked list ADT 
  private Node front;
  private int numItems;
  //dictionary constructor which initializes the private feild
  public Dictionary(){
    front = null;
    numItems = 0;
  }
  /*isEmpty
   *Pre:None 
   *Pos:Returns true or false
   */
  public boolean isEmpty(){
    return (numItems==0);
  }
  /*size
   *Pre:None
   *Pos:returns the number of items in linked list
   */
  public int size(){
    return numItems;
  }
  /*lookup
   *Pre:takes a string 'key' 
   *Pos:return the value associated with the key, or  
   *    returns null if 'key' is not in the list
   */
  public String lookup(String key){
    Node N = front;
    while( N != null){
      if( N.key.equals(key)){
        return N.value;
      }
      N = N.next;
    }
    return null;
  }
  /*insert
   *Pre:takes in two strings a 'key' and 'value' which 
   *    are not already in the linked list
   *Pos:adds 'key' and 'value' into the linked list or throws 
   *    an exception 
   */
  public void insert(String key, String value) throws KeyCollisionException{
    if( lookup(key) != null){
      throw new KeyCollisionException("cannot insert dublicate key");
    }
    else{
      if( front == null){
        Node N = new Node(key,value);
        front = N;
        numItems++;
      }
      else{
        Node N = front;
        while( N != null){
          if(N.next == null){
            break;
          }
          N = N.next;
        }
        N.next = new Node(key,value);
        numItems++;
      }
    }
  }
  /*delete
   *Pre:takes in a string 'key' that is in the list
   *Pos:removes the 'key'  and 'value' from the list  
   */
  public void delete(String key) throws KeyNotFoundException{
    if( lookup(key) == null){
      throw new KeyNotFoundException("cannot delete non-existent key");
    }
    else{
      if(numItems <= 1){//removes first element
        Node N = front;
        front = front.next;
        N.next = null;
        numItems--;
      }
      else{//removes the first and anyother element
        Node N = front;
        if(N.key.equals(key)){
          front = N.next;
          numItems--;
        }
        else{
          while(!N.next.key.equals(key)){
            N = N.next;
          }
          N.next = N.next.next;
          numItems--;
        }
      }
    }
  }
  /*makeEmpty
   *Pre:none 
   *Pos:makes the linked list empty 
   */ 
  public void makeEmpty(){
    front = null;
    numItems =0;
  }
  /*toString
   *Pre:none
   *Pos:allows you to print the contents of the
   *    linked list
   */ 
  public String toString(){
    String s = "";
    Node N = front;
    while( N != null){
      s += N.key + " " + N.value + "\n"; 
      N = N.next;
    }
    return s;
  }
  /*findKey
   *Pre:a string 'key' 
   *Pos:returns the reference to where the 'key' is, or
   *    returns null if there is no 'key' in the list
   */
  private Node findKey(String key){
    Node N = front;
    while(N != null){
      if(N.key.equals(key)){
        return N; 
      }
      else{
        N = N.next; 
      }
    }
    return null;
  }
  
}