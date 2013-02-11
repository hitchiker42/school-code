package cs671;
/**
 *A simple guessing game. This game illustrates the Guesser interface. The user
 *picks a number between a specified minimum and maximum and the guesser will
 *discover the number by a repeated interaction of YES/NO questions. It proceeds
 *by dichotomy, splitting the range of possible values in half with each
 *question. After it's found, the number is returned as an instance of Integer. 
 *@author Tucker DiNapoli from api by Michel Charpentier
 */
public class HiLo implements Guesser<Integer> {
  //min max and number to ask, min & max are changeable over
  //each game
  private int min,max,x;
  double total;
  Range range;
  private boolean init=false;
  private boolean solved=false;
  private boolean qmade=false;
  /**
   *Range of values, kept via min and max values.
   *Really simple class to hold the minimum and maximum values
   *over multiple games.
   */
  private static class Range {
    static int min;
    static int max;
    Range(int min,int max){
      Range.min=min;
      //seems a bit cheap to do this, but because abs(int_max)=abs(int_min)-1
      //it turns out to be necessary
      if (min==Integer.MIN_VALUE){
        Range.min=min+1;
      }
      Range.max=max;
    }
  }
  /**
   *Builds an instance of the engine for the given minimum and maximum.
   *@param min - the lower bound of the range
   *@param max - the upper bounh of the range
   */
  public HiLo(int min,int max){
    if (min>max){
      throw new IllegalArgumentException();
    }
    this.max=max;
    this.min=min;
    range= new Range(min,max);
    this.x=(int)((long)min+(long)max)/2;
    this.total=(double)((long)max-(long)min);
    //if (min==Integer.MIN_VALUE
  }
  /**
   *Get Secret method
   */
  public Integer getSecret(){
    if (solved != true){
      throw new IllegalStateException() ;
    }
    solved=false;
    return x;
  }
  /**
   *Has Solved method
   */
  public boolean hasSolved(){
    if (init!=true){
      throw new IllegalStateException();
    }
    if (this.min == this.max){
      x=min;
      assert(x==max);
      solved=true;
      return true;
    } else {
      return false;
    }
  }
  /**
   *Initalize Method
   */
  public String initialize(){
    max=Range.max;
    min=Range.min;
    init=true;
    return String.format("Pick a number between %d and %d",min,max);
  }
  /**
   *Make Question Method
   */
  public String makeQuestion(){
    if (init != true || solved==true || qmade==true){
      throw new IllegalStateException();
    }
    qmade=true;
    x=(int)(((long)min + (long)max)/2+((long)min + (long)max)%2); //rounds up
    if(min<0 || max<0){
      x=(int)((long)min + (long)max)/2;
    }
    String question=String.format("Is your number greater than or equal to %d",x);
    return question;
  }
  /**
   *{@inheritDoc}
   */
  public void no(){
    if (init!=true || qmade!=true){
      throw new IllegalStateException();
    }
    qmade=false;
    this.max=(x-1);
    //negitive numbers only
    if (Math.abs(max)>Math.abs(range.min)){
      max=range.min;
    }
  }
  /**
   *{@inheritDoc}
   */
  public void yes(){
    if (init!=true || qmade!=true){
      throw new IllegalStateException();
    }
    qmade=false;
    this.min=x;
  }
  /**
   *progress method
   *Measures progress my number of items remaining over
   *the total number of items.
   */
  public double progress(){
    //scaling of progress is a bit off because it uses remaining/total
    //but it works and so thats the way I'm leaving it.
    if (init!=true){
      throw new IllegalStateException();
    }
    Double current=(double)((long)max-(long)min);
    return(1-current/total);
  }
}
