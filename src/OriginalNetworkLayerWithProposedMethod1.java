import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sumi on 2016/12/16.
 */
public class OriginalNetworkLayerWithProposedMethod1 {
    public static final int maxlayernumber = ParamerterWithProposedMethod1.layernumber;
    public static final int maxagentnumber = ParamerterWithProposedMethod1.agentnumber;
    public static AgentRandomNetworkWithProposedMethod1[] network;
    public static ArrayList<Integer>[] shufflenumber;
    public static ArrayList<Integer>[] allfriends;

    private static StringBuffer buf = new StringBuffer();

    public static void init(){
        /**
         * make shuffle arraylist
         * make network
         */
        initOriginal();

        /**
         * set agent opinion
         */
        double rand;
        for(int i=0;i<maxlayernumber;i++){
            for(int j=0;j<network[i].choosedagentnumber;j++){
                rand = Paramerter.rand.nextDouble();
                network[i].agent[network[i].choosedagent.get(j)].opinion = rand;
            }
        }

        /**
         * make allfriend list(リストの中で同じエージェントは重複しない)
         */
        allfriends = new ArrayList[maxagentnumber];
        for(int i=0; i< maxagentnumber; i++){
            allfriends[i] = new ArrayList<>();
        }
        for(int i=0;i<maxlayernumber;i++){
            for(int j=0;j<network[i].choosedagentnumber;j++){
                if(network[i].friendagent[network[i].choosedagent.get(j)].size() ==0)
                    continue;;
                for(int k=0;k<network[i].friendagent[network[i].choosedagent.get(j)].size();k++){
                    if(!allfriends[network[i].choosedagent.get(j)].contains(network[i].friendagent[network[i].choosedagent.get(j)].get(k).number))
                        allfriends[network[i].choosedagent.get(j)].add(network[i].friendagent[network[i].choosedagent.get(j)].get(k).number);
                }
            }
        }
    }

    public static void displayNetwork(){
        for(int i=0;i<maxlayernumber;i++) {
            System.out.println("network["+i+"]");
            network[i].displayLinking();
            //network[i].displayOpinion();
            //network[i].displayExpress();
        }
    }

    public static void formationOfOpinion(){
        /**
         * an1 = agentnumber1 an2 = agentnumber2
         * an2number = agentnumber1のactivefriendの中のagentnumber2がいる番号
         * ln = layernumber
         */
        int an1;
        for(int i=0; i<ParamerterWithProposedMethod1.layernumber;i++) {
            Collections.shuffle(shufflenumber[i]);
            for (int j = 0; j < network[i].choosedagentnumber; j++) {
                an1 = network[i].choosedagent.get(shufflenumber[i].get(j));
                if (network[i].friendagent[an1].size() == 0)
                    continue;
                infuluenceOpinion(i, an1);
            }
        }
    }


    public static void infuluenceOpinion(int ln,int an1){
        int an2;
        for(int i=0;i<network[ln].friendagent[an1].size();i++) {
            an2 = network[ln].friendagent[an1].get(i).number;
            if (network[ln].agent[an1].express && network[ln].agent[an2].express) {
                if (Math.abs(network[ln].agent[an1].opinion - network[ln].agent[an2].opinion) < Paramerter.confornitybias) {
                    network[ln].agent[an2].opinion = (network[ln].agent[an1].opinion + network[ln].agent[an2].opinion) / 2;
                }
            }
        }
    }

    public static void pressureAndSilence(){
        /**
         * fa = friendagent
         */
        int fa;
        for(int i=0;i<maxagentnumber;i++){
            if(allfriends[i].size() ==0 || Paramerter.rand.nextDouble() > Paramerter.connectivity)
                continue;
            fa = allfriends[i].get(Paramerter.rand.nextInt(allfriends[i].size()));
            silenceAgent(i,fa);
        }
    }

    public static void silenceAgent(int an1,int an2){
        double maxopinion=0;
        double minopinion=1;
        for(int i=0;i<maxlayernumber;i++){
            if(network[i].friendagent[an1].contains(network[i].agent[an2])) {
                if (network[i].agent[an1].opinion > maxopinion)
                    maxopinion = network[i].agent[an1].opinion;
                if (network[i].agent[an1].opinion < minopinion)
                    minopinion = network[i].agent[an1].opinion;
            }
        }
        if((maxopinion-minopinion)>Paramerter.allowance){
            for(int i=0;i<maxlayernumber;i++){
                if(network[i].friendagent[an1].contains(network[i].agent[an2])){
                    network[i].agent[an2].express = false;
                }
            }
        }
    }

    /**
     * 100個のコミュニティ8個パターン
     */
    public static void initOriginal(){
        shufflenumber = new ArrayList[maxlayernumber];
        for(int i=0; i< maxlayernumber; i++){
            shufflenumber[i] = new ArrayList<>();
        }
        for(int i=0;i<ParamerterWithProposedMethod1.layernumber;i++) {
            for (int j = 0; j < 100; j++) {
                shufflenumber[i].add(j);
            }
        }
        network = new AgentRandomNetworkWithProposedMethod1[maxlayernumber];
        for(int i=0; i<maxlayernumber; i++){
            network[i] = new AgentRandomNetworkWithProposedMethod1(i,100);
            network[i].generateGraph();
        }
    }

    /**
     * 正規分布
     */
    public static void initNormalDistribution(){
        shufflenumber = new ArrayList[maxlayernumber];
        for(int i=0; i< maxlayernumber; i++){
            shufflenumber[i] = new ArrayList<>();
        }
        for (int j = 0; j < 25; j++) {
            shufflenumber[0].add(j);
        }
        for (int j = 0; j < 50; j++) {
            shufflenumber[1].add(j);
        }
        for (int j = 0; j < 100; j++) {
            shufflenumber[2].add(j);
        }
        for (int j = 0; j < 200; j++) {
            shufflenumber[3].add(j);
        }
        for (int j = 0; j < 200; j++) {
            shufflenumber[4].add(j);
        }
        for (int j = 0; j < 100; j++) {
            shufflenumber[5].add(j);
        }
        for (int j = 0; j < 50; j++) {
            shufflenumber[6].add(j);
        }
        for (int j = 0; j < 25; j++) {
            shufflenumber[7].add(j);
        }

        network = new AgentRandomNetworkWithProposedMethod1[maxlayernumber];
        network[0] = new AgentRandomNetworkWithProposedMethod1(0,25);
        network[1] = new AgentRandomNetworkWithProposedMethod1(1,50);
        network[2] = new AgentRandomNetworkWithProposedMethod1(2,100);
        network[3] = new AgentRandomNetworkWithProposedMethod1(3,200);
        network[4] = new AgentRandomNetworkWithProposedMethod1(4,200);
        network[5] = new AgentRandomNetworkWithProposedMethod1(5,100);
        network[6] = new AgentRandomNetworkWithProposedMethod1(6,50);
        network[7] = new AgentRandomNetworkWithProposedMethod1(7,25);

        for(int i=0;i<ParamerterWithProposedMethod1.layernumber;i++){
            network[i].generateGraph();
        }
    }

    /**
     * べき乗則
     */
    public static void initPower(){
        shufflenumber = new ArrayList[maxlayernumber];
        for(int i=0; i< maxlayernumber; i++){
            shufflenumber[i] = new ArrayList<>();
        }
        for (int j = 0; j < 500; j++) {
            shufflenumber[0].add(j);
        }
        for (int j = 0; j < 400; j++) {
            shufflenumber[1].add(j);
        }
        for (int j = 0; j < 400; j++) {
            shufflenumber[2].add(j);
        }
        for (int j = 0; j < 200; j++) {
            shufflenumber[3].add(j);
        }
        for (int j = 0; j < 200; j++) {
            shufflenumber[4].add(j);
        }
        for (int j = 0; j < 200; j++) {
            shufflenumber[5].add(j);
        }
        for (int j = 0; j < 200; j++) {
            shufflenumber[6].add(j);
        }
        for (int j = 0; j < 200; j++) {
            shufflenumber[7].add(j);
        }

        network = new AgentRandomNetworkWithProposedMethod1[maxlayernumber];
        network[0] = new AgentRandomNetworkWithProposedMethod1(0,500);
        network[1] = new AgentRandomNetworkWithProposedMethod1(1,400);
        network[2] = new AgentRandomNetworkWithProposedMethod1(2,400);
        network[3] = new AgentRandomNetworkWithProposedMethod1(3,200);
        network[4] = new AgentRandomNetworkWithProposedMethod1(4,200);
        network[5] = new AgentRandomNetworkWithProposedMethod1(5,200);
        network[6] = new AgentRandomNetworkWithProposedMethod1(6,200);
        network[7] = new AgentRandomNetworkWithProposedMethod1(7,200);

        for(int i=0;i<ParamerterWithProposedMethod1.layernumber;i++){
            network[i].generateGraph();
        }
    }
}

