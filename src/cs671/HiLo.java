package cs671;
/**
   HiLo Class
 */
public class HiLo implements Guesser<Integer> {
  int min,max,x;
  int range_min,range_max;
  double total;
  Range range;
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
    return x;
  }
  /**
     Has Solved method
   */
  public boolean hasSolved(){
    //System.out.println(String.format("min:%d,max:%d",min,max));
    if (this.min == this.max){
      x=min;
      assert(x==max);
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
    return "";
  }
  /**
     Make Question Method
   */
  public String makeQuestion(){
    x=((min + max)/2+(min + max)%2); //might not work like this, b/c of rounding
    String question=String.format("Is your number greater than or equal to %d",x);
    return question;
  }
  /**
     No method
   */
  public void no(){
    this.max=(x-1);
  }
  /**
     Yes Method
   */
  public void yes(){
    this.min=x;
  }
  /**
     progress method
   */
  public double progress(){
    Double current=(double)(max-min);
    return(1-current/total);
  }
}