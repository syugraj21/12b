/*yugraj singh
 *This program is a simulation that find the time it takes to  
 *complete m number of Jobs 
 * 
 */ 
import java.io.*;
import java.util.Scanner;

public class Simulation{
  public static void main(String[] args) throws IOException{
    //declares the variables
    Scanner fileIn = null;
    PrintWriter report = null;
    PrintWriter trace = null;
    Queue storQueue = new Queue();
    int n, m, time;
    //throws an exception
    if (args.length < 3){
      System.err.println("Usage: Simulation fileIn fileOut");
      System.exit(1);
    }
    
    //intializes the input and output files
    fileIn = new Scanner(new File(args[0]));
    report = new PrintWriter( new FileWriter(args[1]+".rpt"));
    trace = new PrintWriter( new FileWriter(args[2]+ ".trc"));
    
    //declares and intializes the m job and the Queue array
    m = numOfJobs(fileIn);
    while(fileIn.hasNext()){
      storQueue.enqueue((Job)getJob(fileIn));
    }
    trace.println("Trace file: " + args[2] +".trc");
    trace.println(m + " Jobs:");
    trace.println(storQueue+"\n");
    
    for( n=1; n<m; n++){
      time = 0;
      
      Queue[] sim = new Queue[n+1];
      for(int i=0; i<n+1;i++){
        sim[i]= new Queue();
      }
      
      trace.println("*****************************");
      trace.println(n +" processor:");
      trace.println("*****************************");
      
      sim[0]=storQueue;
      while( ((Job)sim[0].peek()).getFinish() == -1  || sim[0].length()!=m){
        
        if(time == 0){
         
          printTrace(trace,sim,n,time);
          time = ((Job)sim[0].peek()).getArrival();
          sim[1].enqueue(sim[0].dequeue());
          Job temp = (Job)sim[1].peek();
          temp.computeFinishTime(time);
        }
        else if(((Job)sim[0].peek()).getFinish() != -1){
          int small = getIndex(sim);
          time = ((Job)sim[small].peek()).getFinish();
          sim[0].enqueue(sim[small].dequeue());
          printTrace(trace,sim,n,time);
          
       
        }
        else{
          
          printTrace(trace,sim,n,time);
          int small = getIndex(sim);
          
          if( sim[small].length() == 0){
           time = ((Job)sim[0].peek()).getArrival();
           sim[small].enqueue(sim[0].dequeue());
           Job temp = (Job)sim[small].peek();
           temp.computeFinishTime(time);
          
           printTrace(trace,sim,n,time);
           
           small = getIndex(sim);
           time = ((Job)sim[small].peek()).getFinish();
           sim[0].enqueue(sim[small].dequeue());
           printTrace(trace,sim,n,time);
           
          
           small = getIndex(sim);
           time = ((Job)sim[0].peek()).getArrival();
           sim[small].enqueue(sim[0].dequeue());
           temp = (Job)sim[small].peek();
           temp.computeFinishTime(time);
           printTrace(trace,sim,n,time);
           
           time = ((Job)sim[small+1].peek()).getFinish();
           sim[0].enqueue(sim[small+1].dequeue());
           printTrace(trace,sim,n,time);
       
           small = getIndex(sim);
           time = ((Job)sim[small-1].peek()).getFinish();
           sim[0].enqueue(sim[small-1].dequeue());
           printTrace(trace,sim,n,time);
         
          
          }
          
           else if( ((Job)sim[0].peek()).getArrival() <= ((Job)sim[small].peek()).getFinish() ){
            time = ((Job)sim[0].peek()).getArrival();
            sim[small].enqueue(sim[0].dequeue());
           
           
            printTrace(trace,sim,n,time);
            
            time = ((Job)sim[small].peek()).getFinish();
            sim[0].enqueue(sim[small].dequeue());
            Job temp = (Job)sim[small].peek();
            temp.computeFinishTime(time);
           
            printTrace(trace,sim,n,time);  
          }
          else{
            Job temp = (Job)sim[small].peek();
            temp.computeFinishTime(time);
            
            printTrace(trace,sim,n,time);
            
            time = ((Job)sim[small].peek()).getFinish();
            sim[0].enqueue(sim[small].dequeue());
            
            printTrace(trace,sim,n,time);
            
          }
       
        }
      
      }
      
      if(n==1){
        report.println("Report file: "+ args[1] + ".rpt");
        report.println( m + " Jobs:");
        report.println(sim[0] +"\n");
        report.println("************************************************");
      }
      float averageWait=0;
      int totalWait=0;
      int maxWait=0;
      int max=0;
      Queue holdQueue = new Queue();
      while( sim[0].length() != 0 ){
        max = ((Job)sim[0].peek()).getWaitTime();
        if( maxWait < max){
          maxWait = max;
        }
        totalWait += ((Job)sim[0].peek()).getWaitTime();
        holdQueue.enqueue((Job)sim[0].dequeue());
      }
      averageWait =(float)totalWait/m;
      report.println( n + " processor: totalWait="+ totalWait+ " maxWait="+maxWait+" averageWait="+averageWait);
      
      while(holdQueue.length() != 0){
        ((Job)holdQueue.peek()).resetFinishTime();
        storQueue.enqueue((Job)holdQueue.dequeue());
      }
      trace.println();
      
    }
    
    
    
    
    
    fileIn.close();
    report.close();
    trace.close();
    
  }
  
  
  
  public static void printTrace(PrintWriter trace,Queue[] s, int n, int time){
    trace.println("time = " +time);
    for(int i =0; i<n+1;i++){
      trace.println(i+": "+ s[i]);
    }
  }
  
  public static int getIndex(Queue[] s){
    int index =0;
    if( ((Job)s[index].peek()).getFinish() == -1){
        index = 1;
      }
    for(int i =1; i<s.length;i++){
      if(s[i].length() < s[index].length()){
        if(s[i].length() == s[index].length()){
          index = index;
        }
        else{
          index = i;
        }
      }
      else if( s[i].length() < s[index].length() && ((Job)s[index].peek()).getFinish() !=-1){// < ((Job)s[index].peek()).getFinish()){
        index = index;
      }
    }
    return index;
  }
  
  
  /*numOfJobs
   *Pre: takes a scanner 
   *Pos: tell the number of jobs needed to compute 
   */ 
  public static int numOfJobs(Scanner in){
    int x;
    String s = in.nextLine();
    x = Integer.parseInt(s);
    return x;
  }
  /*getJob
   *Pre: takes a scanner 
   *Pos: creates Job object
   */
  public static Job getJob(Scanner in){
    String[] s = in.nextLine().split(" ");
    int a = Integer.parseInt(s[0]);
    int d = Integer.parseInt(s[1]);
    return new Job(a,d);
  }
}


