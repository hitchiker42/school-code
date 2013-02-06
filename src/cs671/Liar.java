package cs671;
import java.util.*;
import java.util.Collections.*;
import java.util.EnumSet.*;
import java.lang.reflect.*;
public class Liar<T> implements Guesser<Liar.Secret<T>>{
  static void println(String arg){
    System.out.println(arg);
  }
  LiarTable liarTable;
  ArrayList<T> candidates;
  int items;
  public final int maxLies;
  public final String name;
  public boolean hasSolved(){
    return liarTable.hasSolved();
  }
  public double progress(){
    return(1-(liarTable.progress()/liarTable.total));
  }


  //Type of Secrets, just used an interface to outside
  //All internal manipulations done w/LiarTable
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
    double total;//# of total lies at star
    /**
       LiarTable Constructor
    */
    LiarTable(ArrayList<T> candidates,int lies){
      this.top=new TreeMap<>();
      this.bottom=new TreeMap<>();
      this.top.put(0,new ArrayList<T>(candidates.subList(0,items/2)));
      this.bottom.put(0,new ArrayList<T>(candidates.subList(items/2,items)));
      for (int i=1;i<=lies;i++){
        this.top.put(i,new ArrayList<T>(items/2+items%2));
        this.bottom.put(i,new ArrayList<T>(items/2+items%2));
      }
      this.total=items*lies;
      println(this.top.toString()+this.bottom.toString()+'\n');
    }

    /**
       Increment lies on top/bottom
    */
    @SuppressWarnings("unchecked")
    void increment(boolean inTop){
      println("increment\n");
      //might use teritary operator here, but removed for debugging
      TreeMap<Integer,ArrayList<T>> liarList;
      if (inTop==true){
        liarList=bottom;
      } else {
        liarList=top;
      }
      //println(liarList.toString());
      ArrayList<T> temp;
      //do stuff
      for (int i=maxLies;i>0;i--){
        try{
        temp=(ArrayList<T>)liarList.get(i-1).clone();
        liarList.put(i,temp);
        } catch(ClassCastException ex){
        }
      }
      //temp=liarList.get(0);...keeping just in case
      liarList.get(0).clear();
      //println(top.toString());
      //println(bottom.toString());
    }

    /**
       Swap part of top and bottom w/each other
    */
    void swap(){
      //move the last n/2 items from top to temp array
      //move the first n/2+n%2 items from bottom to temp array
      //merge top and bottom, this is new top
      //temp array is new bottom
      println("swap\n");
      println("before\n"+top.toString()+'\n'+bottom.toString());
      ArrayList<T> up;
      ArrayList<T> down;
      ArrayList<T> temp;
      for (int i=0;i<=maxLies;i++){
        up=top.get(i);
        down=bottom.get(i);
        temp=new ArrayList<>(up.size());
        //with temp variables, Should this have a new namespace?
        if (down.size()==1){
          up.addAll(down);
          down.clear();
        } else if (up.size()==1 && i!=maxLies){
          continue;
        } else {
          temp.addAll(up.subList(up.size()/2,up.size()));
          up.subList(up.size()/2,up.size()).clear();
          temp.addAll(down.subList(0,(down.size()/2+down.size()%2)));
          down.subList(0,(down.size()/2+down.size()%2)).clear();
          up.addAll(down);
        }
        //change real variables
        top.put(i,up);
        bottom.put(i,temp);
      }
      println("After\n"+top.toString()+'\n'+bottom.toString());
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
      for(int i=0;i<=maxLies;i++){
        sum += top.get(i).size()*(maxLies-i);
        sum += bottom.get(i).size()*(maxLies-i);
      }
      return sum;
    }

    /**
       if only 1 item left than we've solved the problem
       if not keep going
    */
    boolean hasSolved(){
      println("hasSolved\n");
      int sum=top.get(0).size()+bottom.get(0).size();
      println(String.format("sum:%d",sum));
      for (int i=1;i<=maxLies;i++){
        if (sum>1){
          return false;
        }
        sum+=(top.get(i).size()+bottom.get(i).size());
      }
      if (sum>1){
        return false;
      } else if (sum==1){
        return true;
      } else {
        println("What the hell happened");
        System.exit(89);
        return true;
      }
    }
  }

  //Constructor
  public Liar(Set<? extends T> candidates, int lies, String name){
    items=candidates.size();
    try{
      //check if candidates is empty
      //check if lies <0
      //check if name == null
      throw new IllegalArgumentException();
    } catch (IllegalArgumentException ex){
    }
    this.maxLies=lies;
    this.name=name;
    this.candidates=new ArrayList<>(candidates);
    this.liarTable=new LiarTable(this.candidates,lies);
  }
  //Get Secret
  public Secret<T> getSecret(){
    Secret<T> ans=null;
    for (int i=0;i<=maxLies;i++){
      if (!liarTable.top.get(i).isEmpty()){
        ans=new Secret<>(i,liarTable.top.get(i).get(0));
        break;
        }
      if (!liarTable.bottom.get(i).isEmpty()){
        ans=new Secret<>(i,liarTable.bottom.get(i).get(0));
        break;
        }
    }
    return(ans);
  }


  public String initialize(){
    //reset liarTable
    this.liarTable=new LiarTable(this.candidates,maxLies);
    return "";
  }

  public String makeQuestion(){
    StringBuilder question = new StringBuilder(String.format("Is your %s one of: ",name));
    for (ArrayList<T> i:liarTable.top.values()){
      Iterator<T> q = i.iterator();
      while (q.hasNext()){
        question.append(q.next());
        question.append(' ');
      }
    }
    question.deleteCharAt(question.length()-1);//length-1?
    question.append('?');
    return question.toString();
  }

  public void no(){
    //add 1 lie to each entry in top and remove any with lies>maxlies
    liarTable.increment(false);
    liarTable.swap();
  }

  public void yes(){
    //add 1 lie to each entry in bottom and remove any with lies>maxlies
    liarTable.increment(true);
    liarTable.swap();
  }

  public int selectCandidates(int n){
    if (n<=0){
      return -1;
    } else if (n>=candidates.size()){
      return candidates.size();
    }
    //Random rand=new Random();
    Collections.shuffle(candidates);
    candidates=(ArrayList<T>)candidates.subList(0,n);
    return n;
  }
}
