package cs671;
import java.util.*;
import java.util.Collections.shuffle;
import java.util.EnumSet.range;
public class Liar<T> implements Guesser<Liar.Secret<T>>{
    static class Liar.Secret<E>{
        int lies;
        E secret;
        Liar.Secret<E>(int lies,E secret){
            this.lies=lies;
            this.secret=secret;
        }
        public int getLies(){
            return lies;
        }
        public E getSecret(){
            return secret;
        }
        public String toString(){
        }
    }

    class LiarTable {
        HashMap<int,ArrayList<T>> top;
        HashMap<int,ArrayList<T>> bottom;
        LiarTable(ArrayList<T> candidates,int lies){
            this.top=new HashMap<>;
            this.bottom=new HashMap<>;
            int items=candidates.size()
                this.top.put(0,candidates.sublist(0,items/2));
            this.bottom.put(0,candidates.sublist(items/2,items));
            for (i : range(1,lies)){
                this.top.put(i,ArrayList(items/2+items%2));
                this.bottom.put(i,ArrayList(items/2+items%2));
            }
        }
        void increment(boolean top){
            t_b==true ? liarList=this.bottom : liarList=this.top;
            //do stuff
        }
        double progress(){
            //sum lies left per object over all objects
        }
        double total;//# of total lies at start
    }

    LiarTable liarTable;
    public final int maxLies;
    public final String name;
    set<? extentd T> candidates;

    public Liar Liar(Set<? extends T> candidates, int lies, String name){
        items=candidates.size()
        try{
            //check if candidates is empty
            //check if lies <0
            //check if name == null
            throw(IllegalArgumentException);
        } catch {IllegalArgumentException}
        this.candidates=candidates;
        this.maxLies=lies;
        this.name=name;
        this.liarTable=new LiarTable(ArrayList(candidates),
    }

    public Liar.Secret<T> getSecret(){
        return(new Liar.Secret<T>)
    }

    public boolean hasSolved(){
    }

    public String initalize(){
        //setup some sort of 2d data structure
    }

    public String makeQuestion(){
    }

    public void no(){
        //add 1 lie to each entry in top and remove any with lies>maxlies
        liarTable.incremant(false);
    }

    public void yes(){
        //add 1 lie to each entry in bottom and remove any with lies>maxlies
        liarTable.increment(true);
    }

    public double progress(){
        return(1-(liarTable.progress()/liarTable.total))
    }

    public int selectCandidates(int n){
        if (n==0){
            return Error;
        } else if (n>=candidates.size()){
            return candidates.size();
        }
        shuffle(candidates);
        candidates=candidates.subList(0,n);
        return n;
    }
}