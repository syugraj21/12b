/*Yugraj Singh
 * keyNotFoundException
 * Throws an exception if you try to delete an nonexistent key
 */
public class KeyNotFoundException extends RuntimeException{
  public KeyNotFoundException(String s){
    super(s);
  }
}