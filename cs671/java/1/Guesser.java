package cs671;
public interface Guesser<T>{
    String initalize();
    T getSecret();
    boolean hasSolved() throws IllegalStateException;
    String makeQuestion();
    void no();
    double progress();
    void yes();
}