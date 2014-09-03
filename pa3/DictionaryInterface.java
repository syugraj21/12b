// DictionaryInterface.java
// interface for the Dictionary ADT

public interface DictionaryInterface{

   // isEmpty
   // pre: none
   // post: returns true if this Dictionary is empty, false otherwise
   public boolean isEmpty();

   // size
   // pre: none
   // post: returns the number of entries in this Dictionary
   public int size();

   // lookup
   // pre: none
   // post: returns value associated key, or null reference if no such key exists
   public String lookup(String key);

   // insert
   // inserts new (key,value) pair into this Dictionary
   // pre: key currently does not exist in this Dictionary, i.e. lookup(key)==null
   // post: !isEmpty(), size() is increased by one
   public void insert(String key, String value) throws KeyCollisionException;

   // delete
   // deletes pair with the given key
   // pre: key currently exists in this Dictionary, i.e. lookup(key)!=null
   // post: size() is decreased by one
   public void delete(String key) throws KeyNotFoundException;

   // makeEmpty
   // pre: none
   // post: isEmpty()
   public void makeEmpty();

   // toString
   // overrides Object's toString() method
   // pre: none
   // post: returns a String representation of this Dictionary
   public String toString();
}