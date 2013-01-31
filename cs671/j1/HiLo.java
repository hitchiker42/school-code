package cs671.j1;
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
        max=x;

    }
    public void yes(){
        min=x;
    }
    public double progress(){
        Double current=(double)(max-min);
        return(current/total);
    }
}
