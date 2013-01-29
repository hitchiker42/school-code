package cs671.j1;
import java.util.Collections.shuffle;
public class Liar<T> implements Guesser<Liar.Secret<T>>{
    /*class Tuple<T1, T2> implements Comparable{
        T1 x;
        T2 y;
        Tuple(T1 x, T2 y){
            this.x=x;
            this.y=y;
        }
        int compareTo(Tuple<T1, T2> tuple){
            y.compareTo(tuple.y);
        }
        }*/
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
    public final int maxLies;
    public final String name;
    set<? extentd T> candidates;
    public Liar Liar(Set<? extends T> candidates, int lies, String name){
        items=candidates.size()
        try{
            //check if candidates is empty
            //check if lies <0
            //check if name == null
        } catch {
        }
        this.candidates=candidates;
        ArrayList<ArrayList<T>> liarList= new ArrayList<ArrayList<T>>(lies+2);
        liarTable.add(new ArrayList(candidates));
        //idea, top=(liarList.get(0)).sublist(0,liarList.get(0)/2)

        /*ArrayList<ArrayList<ArrayList<T>>> liarTable=new ArrayList<ArrayList<ArrayList<T>>>(2);
        ArrayList<ArrayList<T>> top=new ArrayList<ArrayList<T>(lies+2);
        ArrayList<ArrayList<T>> bottom=new ArrayList<ArrayList<T>(lies+2);
        top.add(ArrayList(candidates.subList(0,items/2));
        bottom.add(ArrayList(candidates.subList(items/2,items));
        liarTable.add(top);liarTable.add(bottom);
        for (int i=1;i<=lies;i++){
            top.add(new ArrayList<T>(items);
            bottom.add(new ArrayList<T>(items);
        }
        */
        for(int i=1; i<=lies;i++){
            liarTable.add(new ArrayList<T>);
        }
        this.maxLies=lies;
        this.name=name
    }
    public Liar.Secret<T> getSecret(){
        return(new Liar.Secret<T> (
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
    }
    public void yes(){
        //add 1 lie to each entry in bottom and remove any with lies>maxlies
    }
    public double progress(){
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




/*HashMap<String,HashMap<int,ArrayList<T>>> liarTable = new HashMap<String,HashMap<int,ArrayList<T>>>(maxLies);
top=HashMap<int,ArrayList<T>>;
top.put(0,new ArrayList<T>(candidates.subList(0,items/2))
bottom=HashMap<int,ArrayList<T>>;
bottom.put(0,new ArrayList<T>(candidates,subList(items/2,items)
for loop here
liarTable.put("top",top)
liarTable.put("bottom",bottom)
 */