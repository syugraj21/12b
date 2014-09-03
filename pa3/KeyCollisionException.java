/*Yugraj Singh
 *keyCollisionException
 *Throws an exception if duplicate keys are being inserted 
 *into the linked list 
 */
public class KeyCollisionException extends RuntimeException{
  public KeyCollisionException( String s){
    super(s);
  }
}