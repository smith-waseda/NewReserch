import java.util.ArrayList;

/**
 * Created by Sumi on 2016/12/16.
 */

public class AgentRandomNetworkWithProposedMethod {
    private static final int maxagentnumber = ParamerterWithProposedMethod.agentnumber;
    private static double p;
    public Agent[] agent;
    public ArrayList<Agent>[] friendagent;

    private static StringBuffer buf = new StringBuffer();

    /**
     *ランダムに選んだ100体のエージェントを格納する
     */
    public int choosedagentnumber; //デフォルト
    public ArrayList<Integer> choosedagent = new ArrayList<>();

    public AgentRandomNetworkWithProposedMethod(int layernumber, int agentnumberinnetwork) {
        choosedagentnumber = agentnumberinnetwork;
        ChoosingAgent();
        agent = new Agent[maxagentnumber];
        for (int i = 0; i < maxagentnumber; i++){
            agent[i] = new Agent(i,layernumber);
        }
        friendagent = new ArrayList[maxagentnumber];
        for (int i = 0; i < maxagentnumber; i++){
            friendagent[i] = new ArrayList<>();
        }
    }

    public AgentRandomNetworkWithProposedMethod(int layernumber,int agentnumberinnetwork,int startagent){
        choosedagentnumber = agentnumberinnetwork;
        for(int i=startagent;i<startagent+agentnumberinnetwork;i++){
            choosedagent.add(i);
        }
        agent = new Agent[maxagentnumber];
        for (int i = 0; i < maxagentnumber; i++){
            agent[i] = new Agent(i,layernumber);
        }
        friendagent = new ArrayList[maxagentnumber];
        for (int i = 0; i < maxagentnumber; i++){
            friendagent[i] = new ArrayList<>();
        }
    }


    /**
     * 全エージェントの中から、choosedagentnumber体のエージェントを選ぶ
     */
    public void ChoosingAgent(){
        int tmp;
        for(int i=0; i<choosedagentnumber;i++){
            tmp =Paramerter.rand.nextInt(ParamerterWithProposedMethod.agentnumber);
            while(choosedagent.contains(tmp)){
                tmp = Paramerter.rand.nextInt(ParamerterWithProposedMethod.agentnumber);
            }
            choosedagent.add(tmp);
        }
    }

    /**
     * これはランダムネットワーク
     */
    public void generateGraph() {
        p = ParamerterWithProposedMethod.connectionorder/choosedagentnumber;
        for (int i = 0; i < choosedagentnumber; i++) {
            for (int j = i; j <choosedagentnumber; j++) {
                if (i == j) continue;
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
}
