package cs671;

public class Liar<T> implements Guesser<Liar.Secret<T>>{
    static class Liar.Secret<E>{
        public int getLies(){
        }
        public E getSecret(){
        }
        public String toString(){
        }
    }
    public final int maxLies;
    public final String name;
    public Liar Liar(Set<? extends T> candidates, int lies, String name){
    }
    public Liar.Secret<T> getSecret(){
    }
    public boolean hasSolved(){
    }
    public String initalize(){
        //setup some sort of 2d data structure
    }
    public String makeQuestion(){
    }
    public void no(){
    }
    public void yes(){
    }
    public double progress(){
    }
    public int selectCandidates(int n){
    }
}