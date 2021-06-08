// Tara Ghazanfari
// CSE 143: Assignment #2
// TA: Khusi Chaudhari
// This program will keep track of a musical instrument with multiple strings
// it will turn the computer keyboard into a piano keyboard that plays strings
// on the guitar

public class Guitar37 implements Guitar {
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   private GuitarString[] guitar; // holds the keyboard
   private int timevar; // keeps track of time for tic
   private int minpitch; // smallest pitch
   private int maxpitch; // largest pitch
   
   
   // pre: sets up the boundaries for the smallest and largest pitch
   // post: creates the guitarstring frequencies 
   public Guitar37() {
      this.timevar = 0;
      this.minpitch = -24;
      this.maxpitch = 12;
      this.guitar = new GuitarString[KEYBOARD.length()];
      for(int i = 0; i < KEYBOARD.length(); i++) {
         guitar[i] = new GuitarString(440.0*Math.pow(2,(i - 24.0)/12));
      }
   
   }
   
   // post: plays the note at the given pitch 
   public void playNote(int pitch) {
      int findindex = pitch + 24;
      if(pitch >= minpitch && pitch <= maxpitch) { 
         guitar[findindex].pluck(); 
      }
   
   }
   
   // post: if the note that wants to be played is valid
   public boolean hasString(char string) {
      return (KEYBOARD.indexOf(string) > -1); 
   }
   
   // pre: if the note is not one of the 37 keys throws IllegalArgumentException 
   // post: plucks the given not if it is valid
   public void pluck(char string) {
      if(!hasString(string)) {
         throw new IllegalArgumentException();     
      }  
      int findindex = KEYBOARD.indexOf(string);
      guitar[findindex].pluck();   
   }
   
   // post: adds the sum of the current samples together and returns
   // the total
   public double sample() {
      double gtot = 0.0;
      for(int i = 0; i < KEYBOARD.length(); i++) {
         gtot += guitar[i].sample();
         
      }
      return gtot;
   }
   
   // post: allows the time forward by one while keeping track
   // of the times as it moves forward
   public void tic() {
      for(int i = 0; i < KEYBOARD.length(); i++) {
         guitar[i].tic();
      }
      timevar++;
   }
   
   // post: returns times the amount of calls on tic
   public int time() {
      return timevar;
   }

}
