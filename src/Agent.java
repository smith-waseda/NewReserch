import java.util.ArrayList;

/**
 * Created by Sumi on 2016/10/13.
 */
public class Agent {
    public boolean express;
    public double opinion;
    public int number;
    public int layer;

    public Agent(int agentnumber ,int layernumber){
        this.express = true;
        this.number = agentnumber;
        this.layer = layernumber;
    }
}
