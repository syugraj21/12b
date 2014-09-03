/*Yugraj Singh
 * 
 *This program tests the function of the Dictionary ADT to  
 *to see if the ADT is working  
 */
class DictionaryTest{
  public static void main (String[] args){
    Dictionary A = new Dictionary();
    System.out.println(A.isEmpty());
    A.insert("4","f");
    System.out.println(A.isEmpty());
    A.insert("5","d");
    System.out.println(A.size());
    A.insert("6","3");
    A.insert("7","c");
    A.insert("3","e");
    A.insert("9","3");
   //A.delete("4");
    //A.insert("6","e"); //throws exception 
    System.out.println(A);
    System.out.println(A.lookup("7"));
    System.out.println("********");
    A.delete("6");
    System.out.println(A.size());
    System.out.println(A.lookup("6"));
    A.delete("5");
    A.delete("4");
    System.out.println(A.lookup("9"));
    System.out.println(A.size());
    System.out.println(A);
    A.makeEmpty();
    System.out.println(A.size() + " " + A.isEmpty());
    
  }
}
