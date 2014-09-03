/*Exception class
 *Throws an Error
 */ 
public class QueueEmptyException extends RuntimeException{
  public QueueEmptyException(String s){
    super(s);
  }
}