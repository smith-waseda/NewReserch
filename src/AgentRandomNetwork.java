import java.util.ArrayList;

/**
 * Created by Sumi on 2016/10/10.
 */
public class AgentRandomNetwork{
    private static final double p = Paramerter.probability;
    private static final int maxagentnumber = Paramerter.agentnumber;
    public Agent[] agent;
    public ArrayList<Agent>[] friendagent;

    private static StringBuffer buf = new StringBuffer();

    public AgentRandomNetwork(int layernumber) {
        agent =new Agent[maxagentnumber];
        for (int i = 0; i < maxagentnumber; i++){
            agent[i] = new Agent(i,layernumber);
        }
        friendagent = new ArrayList[maxagentnumber];
        for (int i = 0; i < maxagentnumber; i++){
            friendagent[i] = new ArrayList<>();
        }
    }

    public void generateGraph() {
        for (int i = 0; i < maxagentnumber; i++) {
            for (int j = i; j < maxagentnumber; j++) {
                if (i == j) continue;
                if (p >= Paramerter.rand.nextDouble()) {
                    friendagent[i].add(agent[j]);
                }
            }
        }
        correctionToUndirectedGraph();
    }

    public void correctionToUndirectedGraph() {
        int addnumber;
        for (int i = 0; i < maxagentnumber; i++) {
            for (int j = 0; j < friendagent[i].size(); j++) {
                addnumber = friendagent[i].get(j).number;
                if (!friendagent[addnumber].contains(agent[i]))
                    friendagent[addnumber].add(agent[i]);
            }
        }
    }

    public void displayLinking() {
        for (int i = 0; i < maxagentnumber; i++) {
            for (int j = 0; j < friendagent[i].size(); j++) {
                String element = String.valueOf(friendagent[i].get(j).number);
                buf.append(element);
                buf.append(",");
            }
            String elementline = buf.toString();
            buf.delete(0, buf.length());
            System.out.println(i + ":" + elementline);
        }
    }

    public void displayOpinion(){
        for(int i = 0; i < maxagentnumber; i++) {
            System.out.println("Agentnumber["+i+"] Opinion: "+agent[i].opinion);
        }
    }

    public void displayExpress(){
        for(int i = 0; i < maxagentnumber; i++) {
            System.out.println("Agentnumber["+i+"] Express: "+agent[i].express);
        }
    }
}
