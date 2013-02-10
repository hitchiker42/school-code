package cs671;
/**
   HiLo Class
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
   *Range Class
   *Really simple class to hold the minimum and maximum values
   *over multiple games.
   */
  static class Range {
    static int min;
    static int max;
    Range(int min,int max){
      Range.min=min;
      Range.max=max;
    }
  }
  /**
   *HiLo Constructor
   */
  public HiLo(int min,int max){
    if (min>max){
      throw new IllegalArgumentException();
    }
    this.max=max;
    this.min=min;
    range= new Range(min,max);
    this.x=(min+max)/2;
    this.total=(double)(max-min);
  }
  /**
   *Get Secret method
   */
  public Integer getSecret(){
    if (solved != true){
      throw new IllegalStateException() ;
    }
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
    return "";
  }
  /**
   *Make Question Method
   */
  public String makeQuestion(){
    if (init != true || solved==true || qmade==true){
      throw new IllegalStateException();
    }
    qmade=true;
    x=((min + max)/2+(min + max)%2); //rounds up
    String question=String.format("Is your number greater than or equal to %d",x);
    return question;
  }
  /**
   *no method
   */
  public void no(){
    if (init!=true || qmade!=true){
      throw new IllegalStateException();
    }
    qmade=false;
    this.max=(x-1);
  }
  /**
   *yes Method
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
    Double current=(double)(max-min);
    return(1-current/total);
  }
}
