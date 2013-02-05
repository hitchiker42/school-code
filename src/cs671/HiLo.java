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
      this.min=min;
      this.max=max;
    }
  }
  public HiLo(int min,int max){
    this.max=max;
    this.min=min;
    range= new Range(min,max);
    this.x=(min+max)/2;
    this.total=(double)(max-min);
  }
  public Integer getSecret(){
    return x;
  }
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
  public String initialize(){
    max=range.max;
    min=range.min;
    return "";
  }
  public String makeQuestion(){
    x=((min + max)/2+(min + max)%2); //might not work like this, b/c of rounding
    String question=String.format("Is your number greater than or equal to %d",x);
    return question;
  }
  public void no(){
    this.max=(x-1);
  }
  public void yes(){
    this.min=x;
  }
  public double progress(){
    Double current=(double)(max-min);
    return(1-current/total);
  }
}
