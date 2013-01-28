package cs671;
public class HiLo implements Guesser<Integer> {
    int min,max,x;
    public HiLo(int min,int max){
        this.max=max;
        this.min=min;
        this.x=(min+max)/2;
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
        //String formating unsure, use this.x as x for now
        String question=String.format("Is your number greater than %f",x);
        return question;
    }
    public void no(){
        max=x;
    }
    public void yes(){
        min=x;
    }
    public double progress(){
        return 0.0;
    }
}
