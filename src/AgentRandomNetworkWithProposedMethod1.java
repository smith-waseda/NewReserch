import java.util.ArrayList;

/**
 * Created by Sumi on 2016/12/16.
 */

public class AgentRandomNetworkWithProposedMethod1 {
    private static final double p = Paramerter.probability;
    private static final int maxagentnumber = ParamerterWithProposedMethod1.agentnumber;
    public int choosedagentnumber = 100; //デフォルト
    public Agent[] agent;
    public ArrayList<Agent>[] friendagent;

    private static StringBuffer buf = new StringBuffer();

    /**
     *↑ランダムに選んだ100体のエージェントを格納する
     */
    public ArrayList<Integer> choosedagent = new ArrayList<>();

    /**
     * 全エージェントの中から、choosedagentnumber体のエージェントを選ぶ
     */
    public void ChoosingAgent(){
        int tmp;
        for(int i=0; i<choosedagentnumber;i++){
            tmp =Paramerter.rand.nextInt(ParamerterWithProposedMethod1.agentnumber);
            while(choosedagent.contains(tmp)){
                tmp = Paramerter.rand.nextInt(ParamerterWithProposedMethod1.agentnumber);
            }
            choosedagent.add(tmp);
        }
    }

    public AgentRandomNetworkWithProposedMethod1(int layernumber, int agentnumberinnetwork) {
        choosedagentnumber = agentnumberinnetwork;
        agent = new Agent[maxagentnumber];
        ChoosingAgent();
        for (int i = 0; i < choosedagentnumber; i++){
            agent[choosedagent.get(i)] = new Agent(choosedagent.get(i),layernumber);
        }
        friendagent = new ArrayList[maxagentnumber];
        for (int i = 0; i < maxagentnumber; i++){
            friendagent[i] = new ArrayList<>();
        }
    }

    public void generateGraph() {
        for (int i = 0; i < choosedagentnumber; i++) {
            for (int j = i; j <choosedagentnumber; j++) {
                if (choosedagent.get(i) == choosedagent.get(j)) continue;
                if (p >= Paramerter.rand.nextDouble()) {
                    friendagent[choosedagent.get(i)].add(agent[choosedagent.get(j)]);
                }
            }
        }
        correctionToUndirectedGraph();
    }

    public void correctionToUndirectedGraph() {
        int addnumber;
        for (int i = 0; i < choosedagentnumber; i++) {
            for (int j = 0; j < friendagent[choosedagent.get(i)].size(); j++) {
                addnumber = friendagent[choosedagent.get(i)].get(j).number;
                if (!friendagent[addnumber].contains(agent[choosedagent.get(i)]))
                    friendagent[addnumber].add(agent[choosedagent.get(i)]);
            }
        }
    }

    public void displayLinking() {
        for (int i = 0; i < choosedagentnumber; i++) {
            for (int j = 0; j < friendagent[choosedagent.get(i)].size(); j++) {
                String element = String.valueOf(friendagent[choosedagent.get(i)].get(j).number);
                buf.append(element);
                buf.append(",");
            }
            String elementline = buf.toString();
            buf.delete(0, buf.length());
            System.out.println(choosedagent.get(i) + ":" + elementline);
        }
    }

    public void displayOpinion(){
        for(int i = 0; i < choosedagentnumber; i++) {
            System.out.println("Agentnumber["+choosedagent.get(i)+"] Opinion: "+agent[choosedagent.get(i)].opinion);
        }
    }

    public void displayExpress(){
        for(int i = 0; i < choosedagentnumber; i++) {
            System.out.println("Agentnumber["+choosedagent.get(i)+"] Express: "+agent[choosedagent.get(i)].express);
        }
    }

    public void changeChosedAgentNumber(int i){
        choosedagentnumber = i;
    }
}
