package cs671;
/**
   HiLo Class
 */
public class HiLo implements Guesser<Integer> {
  int min,max,x;
  int range_min,range_max;
  double total;
  Range range;
  boolean init=false;
  boolean solved=false;
  boolean qmade=false;
  /**
     Range Class
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
     HiLo Constructor
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
     Get Secret method
   */
  public Integer getSecret(){
    if (solved != true){
      //throw new IllegalStateException() ;
    }
    return x;
  }
  /**
     Has Solved method
   */
  public boolean hasSolved(){
    //System.out.println(String.format("min:%d,max:%d",min,max));
    if (init!=true){
      //throw new IllegalStateException();
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
     Initalize Method
   */
  public String initialize(){
    max=Range.max;
    min=Range.min;
    init=true;
    return "";
  }
  /**
     Make Question Method
   */
  public String makeQuestion(){
    if (init != true || solved==true || qmade==true){
      throw new IllegalStateException();
    }
    qmade=true;
    x=((min + max)/2+(min + max)%2); //might not work like this, b/c of rounding
    String question=String.format("Is your number greater than or equal to %d",x);
    return question;
  }
  /**
     No method
   */
  public void no(){
    if (init!=true || qmade!=true){
      throw new IllegalStateException();
    }
    qmade=false;
    this.max=(x-1);
  }
  /**
     Yes Method
   */
  public void yes(){
    if (init!=true || qmade!=true){
      throw new IllegalStateException();
    }
    qmade=false;
    this.min=x;
  }
  /**
     progress method
   */
  public double progress(){
    if (init!=true){
      throw new IllegalStateException();
    }
    Double current=(double)(max-min);
    return(1-current/total);
  }
}
