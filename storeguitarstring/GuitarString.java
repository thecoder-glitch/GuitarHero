import java.util.*;

public class GuitarString {
   private Queue<Double> q;
   private int capacity;
   
   public static final double ENERGY_DECAY_FACTOR =  0.996;
  
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
   
   public GuitarString(double[] init) {
      
      q = new LinkedList<Double>();
      
      
      
      int n = init.length;
      if(n < 2) {
         throw new IllegalArgumentException();
         
      }
      for(int i = 0; i < n; i++) {
         //System.out.println(q);
         q.add(init[i]);
      }
      
      
   }
   
   public void pluck() {
      double ogsize = q.size();
      for(int i = 0; i < ogsize; i++) {
         q.remove();
         q.add(Math.random()* -0.5);
         
      }
   }
   
   public void tic() {
     
      double firstvalue = q.remove();
      double newfirstvalue = q.peek();
      double ksupdate = (((firstvalue+newfirstvalue)/2)*ENERGY_DECAY_FACTOR);
      q.add(ksupdate);
      
      
     
   
   }
   
   public double sample() {
      
      return q.peek();
     
   }
   
   /*
   private Queue<Double> ringbuffer(Queue<Double> q) {
      q = new LinkedList<Double>();
      for(int i = 0; i < capacity; i++) {
         q.add(0.0);
      }
      
      return q;

  
   }
   */
}