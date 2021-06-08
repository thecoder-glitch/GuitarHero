

public class Guitar37 implements Guitar {
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
   private GuitarString[] guitar; // holds KEYBOARD contents
   private int timevar; // 
   private int minpitch;
   private int maxpitch;
   
   // post: 
   public Guitar37() {
      this.timevar = 0;
      this.minpitch = -24;
      this.maxpitch = 12;
      this.guitar = new GuitarString[KEYBOARD.length()];
      for(int i = 0; i < KEYBOARD.length(); i++) {
         guitar[i] = new GuitarString(440*Math.pow(2,(i - 24)/12));
      }
   
   }
   
   // post: 
   public void playNote(int pitch) {
      int findindex = pitch + 24;
       if(pitch >= minpitch && pitch <= maxpitch) {
         //for(int i = 0; i < KEYBOARD.length(); i++) {
            guitar[findindex].pluck(); 
         //}
       }
   
   }
   
   public boolean hasString(char string) {
      return (KEYBOARD.indexOf(string) > -1); 
      
   }
   
   public void pluck(char string) {
      if(!hasString(string)) {
         throw new IllegalArgumentException();     
      }  
      int findindex = KEYBOARD.indexOf(string);
      guitar[findindex].pluck();   
   }
   
   public double sample() {
      double gtot = 0.0;
      for(int i = 0; i < KEYBOARD.length(); i++) {
         gtot += guitar[i].sample();
         
      }
      return gtot;
   }
   
   public void tic() {
      for(int i = 0; i < KEYBOARD.length(); i++) {
         guitar[i].tic();
      }
      timevar++;
   }
   
   public int time() {
      return timevar;
   }

}
