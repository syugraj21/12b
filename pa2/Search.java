/*Yugraj Singh w1234081
 *Search.java
 *
 * This Program reads in a file from the command line to search for 'target' words
 * entered in as ars[1]...args[n]. Uses mergeSort and binarySearch
 *
 */

import java.io.*;
import java.util.Scanner;

public class Search{
  public static void main(String[] args) throws IOException{
    Scanner fileInput = null;
    String line = null;
    String[] token = null;
    int numOfLines = 0;
    int[] lineNumber = null;
    
  
    if(args.length < 2){
      System.err.println("Usage: Search infile target(1)...target(N)");
      System.exit(1);
    }
    
    //while loop scans and counts the number of lines in the file
    fileInput = new Scanner(new File(args[0]));
    while( fileInput.hasNextLine() ){
      numOfLines++;
      line = fileInput.nextLine();
    }
    
    //intialize's the length of the String and int array
    token = new String[numOfLines];
    lineNumber = new int[numOfLines];
    fileInput = new Scanner(new File(args[0]));//re-scans the file
    
    //adds number to the array
    for(int i=1; i<=lineNumber.length; i++){
      lineNumber[i-1] = i;
    }
    //Scans the file, putting the word in the String array
    for(int i =0; fileInput.hasNextLine(); i++){
      line = fileInput.nextLine();
      token[i] = line;
    }
    //puts the string Array in order
    mergeSort(token,lineNumber, 0, token.length-1);
    //prints if the target is found and on what line
    for(int i=1; i<args.length; i++){
      System.out.println( binarySearch(token, lineNumber, 0, token.length-1, args[i]));
    }
    
    fileInput.close();
    
  }
  
  //mergeSort 
  //Pre: takes two Arrays, along with two integers p and r. 
  //     p and r must be >= 0 && < the length of the Array 
  //Pos: gives the task to merge
  static void mergeSort(String[] word, int[] lineNumber, int p, int r){
    int q;
    if(p<r){
      q = (p+r)/2;
      mergeSort(word, lineNumber, p, q);
      mergeSort(word, lineNumber, q+1, r); 
      merge(word, lineNumber, p, q, r);
    }
  }
  //merge 
  //Pre: takes two Arrays, along with two integers p and r. 
  //     that are given from mergeSort
  //Pos: changes the order of the Array putting them in lexical order
  static void merge(String[] word, int[] lineNumber, int p, int q, int r){
    int n1 = q-p+1;
    int n2 = r-q;
    String[] left = new String[n1];
    String[] right = new String[n2];
    int[] leftNum = new int[n1];
    int[] rightNum = new int[n2];
    int i, j, k;
    
    for(i=0; i<n1; i++){
      left[i] = word[p+i];
      leftNum[i] = lineNumber[p+i];
    }
    for(j=0; j<n2; j++){
      right[j] = word[q+j+1];
      rightNum[j] = lineNumber[q+j+1];
    }
    i = 0;
    j = 0;
    for(k=p; k<=r; k++){
      if( i<n1 && j<n2){
        if( left[i].compareTo(right[j])>0 ){
          word[k] = left[i];
          lineNumber[k] = leftNum[i]; 
          i++;
        }
        else{
          word[k] = right[j];
          lineNumber[k] = rightNum[j];
          j++;
        } 
      }
      else if( i<n1){
        word[k] = left[i];
        lineNumber[k] = leftNum[i];
        i++;
      }
      else{  // j<n2
        word[k] = right[j];
        lineNumber[k] = rightNum[j];
        j++;
      } 
    }
  }
  //binarySearch 
  //Pre: takes two Arrays, a target, along with two integers p and r. 
  //     p and r must be >= 0 && < the length of the Array 
  //Pos: returns a string with the word and line it was found on
  public static String binarySearch(String[] word, int[] lineNumber, int p, int r, String target){
    int q;
    if( p == r ){
      return target + " not found";
    }
    else{
      q = (p+r)/2;
      if( word[q].compareTo(target) == 0){
        return target + " found on line " + lineNumber[q];
      }
      else if( word[q].compareTo(target)<0 ) {
        return binarySearch(word, lineNumber, p, q, target);
      }
      else{ 
        return binarySearch(word, lineNumber, q+1, r, target);
      }
    }
    
  } 
}
