//Constructs a guitar string of the given frequency. It creates a ring buffer of
//the desired capacity N (sampling rate divided by frequency, rounded to the
//nearest integer), and initializes it to represent a guitar string at rest by
//enqueueing N zeros. The sampling rate is specified by the constant
//StdAudio.SAMPLE_RATE. If the frequency is less than or equal to 0 or if
//the resulting size of the ring buffer would be less than 2, your method
//should throw an IllegalArgumentException.
//import java.util.*;


public class GuitarString {
   private Queue<Double> q; // keeps track of ring buffer
   private int capacity; // creates ring buffer size
   
   public static final double ENERGY_DECAY_FACTOR =  0.996;
   
   // pre : creates the desired capacity of the ring buffer
   // pre : frequency <= 0 || capacity < 2 throws IllegalArgumentExpcetion
   // post : creates a list the same size of the capactiy
   public GuitarString(double frequency) {
      capacity = (int)Math.round(StdAudio.SAMPLE_RATE / frequency);
      if(frequency <= 0 || capacity < 2) {
         throw new IllegalArgumentException();
      }
      q = new LinkedList<Double>();
      for(int i = 0; i < capacity; i++) {
         q.add(0.0);
         
      }   
      
   }
   
   // pre: checcks if the arraylength < 2 (throws IllegalArgumentException)
   // post: puts values of array into the ring buffer 
   public GuitarString(double[] init) {
      q = new LinkedList<Double>();
      int n = init.length;
      if(n < 2) {
         throw new IllegalArgumentException();      
      }
      for(int i = 0; i < n; i++) {
         q.add(init[i]);
      }
          
   }
   
   // post: replaces ring buffer values with random values from
   // -0.5 to 0.5
   public void pluck() {
      double ogsize = q.size();
      for(int i = 0; i < ogsize; i++) {
         q.remove();
         q.add(Math.random()* -0.5);
         
      }
   }
   
   // post: deletes the first value of the ring buffer and performs 
   // the Karplus-Strong update one time 
   public void tic() {
      double firstvalue = q.remove();
      double newfirstvalue = q.peek();
      double ksupdate = (((firstvalue+newfirstvalue)/2)*ENERGY_DECAY_FACTOR);
      q.add(ksupdate);   
   }
   
   // post: returns the current sample of the ring buffer
   public double sample() {   
      return q.peek();
     
   }
   

 
}