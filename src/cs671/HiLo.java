package cs671;
//NEEDS TO BE FIXED, SPECIFICALLY THE HAS SOLVED METHOD
public class HiLo implements Guesser<Integer> {
    int min,max,x;
    double total;
    public HiLo(int min,int max){
        this.max=max;
        this.min=min;
        this.x=(min+max)/2;
        this.total=(double)(max-min);
    }
    public Integer getSecret(){
        return x;
    }
    public boolean hasSolved(){
        if (min == max){
            return true;
        } else {
            return false;
        }
    }
    public String initalize(){
        return "";
    }
    public String makeQuestion(){
        x=(min + max)/2; //might not work like this, b/c of rounding
        String question=String.format("Is your number greater than %d",x);
        return question;
    }
    public void no(){
        this.max=this.x;
    }
    public void yes(){
        this.min=this.x;
    }
    public double progress(){
        Double current=(double)(max-min);
        return(1-current/total);
    }
}
