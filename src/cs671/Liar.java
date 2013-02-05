package cs671;
import java.util.*;
import java.util.Collections.*;
import java.util.EnumSet.*;
import java.lang.reflect.*;
public class Liar<T> implements Guesser<Liar.Secret<T>>{
  LiarTable liarTable;
  Integer mLies=new Integer(0);
  String realName=new String("name");
  public final int maxLies=mLies;
  public final String name=realName;
  ArrayList<T> candidates;
  int items;
  public boolean hasSolved(){
    return liarTable.hasSolved();
  }
  public double progress(){
    return(1-(liarTable.progress()/liarTable.total));
  }
  static class Secret<E> {
    int lies;
    E secret;
    Secret(int lies,E secret){
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
      return String.format("Your object was %s and you told %d lies",secret,lies);//not sure how to format for generics, and should object be name?
    }
  }
  /**
     LiarTable class
     Data structure to hold information about items and lies
     Does all the manipulating and sorting of objects
  */
  class LiarTable {
    TreeMap<Integer,ArrayList<T>> top;
    TreeMap<Integer,ArrayList<T>> bottom;
    TreeMap<Integer,ArrayList<T>> liarList;
    /**
       LiarTable Constructor
    */
    LiarTable(ArrayList<T> candidates,int lies){
      this.top=new TreeMap();
      this.bottom=new TreeMap();
      this.top.put(0,new ArrayList<T>(candidates.subList(0,items/2)));
      this.bottom.put(0,new ArrayList<T>(candidates.subList(items/2,items)));
      for (int i=1;i<lies;i++){
        this.top.put(i,new ArrayList<T>(items/2+items%2));
        this.bottom.put(i,new ArrayList<T>(items/2+items%2));
      }
      this.total=items*lies;
    }
    /**
       Increment lies on top/bottom
    */
    void increment(boolean top){
      liarList = (top==true) ? this.bottom : this.top;
      //do stuff
      for (int i=(maxLies-1);i>0;i--){
        liarList.put(i,liarList.get(i-1));
      }
      liarList.get(0).clear();
    }
    /**
       Swap part of top and bottom w/each other
    */
    void swap(){
      //move the last n/2 items from top to temp array
      //move the first n/2+n%2 items from bottom to temp array
      //merge top and bottom, this is new top
      //temp array is new bottom
      for (int i=0;i<maxLies;i++){
        final ArrayList up=this.top.get(i);
        final ArrayList down=this.bottom.get(i);
        final ArrayList temp=new ArrayList(up.size());
        //with temp variables, Should this have a new namespace?
        try{
            Method m=up.getClass().getDeclaredMethod("removeRange",Integer.class,Integer.class);
            m.setAccessible(true);
            temp.addAll(up.subList(up.size()/2,up.size()));
            m.invoke(up,up.size()/2,up.size());
            //up.removeRange(up.size()/2,up.size());
            temp.addAll(down.subList(0,(down.size()/2+down.size()%2)));
            m.invoke(down,0,(down.size()/2+down.size()%2));
            //down.removeRange(0,(down.size()/2+down.size()%2));
            up.addAll(down);
            //change real variables
            top.put(i,up);
            bottom.put(i,temp);
        } catch(IllegalAccessException | InvocationTargetException | NoSuchMethodException ex){
        }
      }
    }
    /**
       return progress as a double giving the total number
       of possible lies left, this is compaired w/the value
       total ,which gives total lies ever possible, to get
       the actual progress value.
    */
    double progress(){
      //sum lies left per object over all objects
      double sum=0;
      for(int i=0;i<maxLies;i++){
        sum += top.get(i).size()*(maxLies-i);
        sum += bottom.get(i).size()*(maxLies-i);
      }
      return sum;
    }
    double total;//# of total lies at star
    /**
       if only 1 item left than we've solved the problem
       if not keep going
    */
    boolean hasSolved(){
      int sum=top.get(0).size()+bottom.get(0).size();
      for (int i=1;i<maxLies;i++){
        if (sum>1){
          return false;
        } else {
          sum+=(top.get(i).size()+bottom.get(i).size());
        }
      }
      return true;
    }
  }
  public Liar(Set<? extends T> candidates, int lies, String name){
    items=candidates.size();
    try{
      //check if candidates is empty
      //check if lies <0
      //check if name == null
      throw new IllegalArgumentException();
    } catch (IllegalArgumentException ex){
    }
    this.candidates=new ArrayList(candidates);
    this.mLies=lies;
    this.realName=name;
    this.liarTable=new LiarTable(this.candidates,lies);
  }
    public Secret<T> getSecret(){
      return(new Secret<T>(liarTable.top.firstKey(),liarTable.top.get(1).get(0)));
//need a way to get this value from liarTable
  }


  public String initialize(){
    //reset liarTable
    this.liarTable=new LiarTable(new ArrayList(candidates),maxLies);
    return "";
  }

  public String makeQuestion(){
    StringBuilder question = new StringBuilder(String.format("Is your %s one of: ",name));
    for (ArrayList<T> i:liarTable.top.values()){
      Iterator q = i.iterator();
      while (q.hasNext()){
        question.append(q.next());
        question.append(' ');
      }
    }
    question.deleteCharAt(question.length());//length-1?
    question.append('?');
    return question.toString();
  }

  public void no(){
    //add 1 lie to each entry in top and remove any with lies>maxlies
    liarTable.increment(false);
  }

  public void yes(){
    //add 1 lie to each entry in bottom and remove any with lies>maxlies
    liarTable.increment(true);
  }

  public int selectCandidates(int n){
    if (n<=0){
      return -1;
    } else if (n>=candidates.size()){
      return candidates.size();
    }
    //Random rand=new Random();
    Collections.shuffle(candidates);
    candidates=(ArrayList)candidates.subList(0,n);
    return n;
  }
}
