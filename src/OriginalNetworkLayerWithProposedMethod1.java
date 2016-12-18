import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sumi on 2016/12/16.
 */
public class OriginalNetworkLayerWithProposedMethod1 {
    public static final int maxlayernumber = ParamerterWithProposedMethod1.layernumber;
    public static final int maxagentnumber = ParamerterWithProposedMethod1.agentnumber;
    public static final int chosedagentnumber = ParamerterWithProposedMethod1.agentnumberinnetwork;
    public static AgentRandomNetworkWithProposedMethod1[] network;
    public static ArrayList<Integer> shufflenumber;
    public static ArrayList<Integer>[] allfriends;

    private static StringBuffer buf = new StringBuffer();

    public static void init(){
        /**
         * make shuffle arraylist
         */
        shufflenumber = new ArrayList<>();
        for(int i = 0; i<chosedagentnumber ; i++){
            shufflenumber.add(i);
        }

        /**
         * make network
         */
        network = new AgentRandomNetworkWithProposedMethod1[maxlayernumber];
        for(int i=0; i<maxlayernumber; i++){
            network[i] = new AgentRandomNetworkWithProposedMethod1(i);
            network[i].generateGraph();
        }

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
            //network[i].displayLinking();
            network[i].displayOpinion();
            //network[i].displayExpress();
        }
    }

    public static void formationOfOpinion(){
        /**
         * an1 = agentnumber1 an2 = agentnumber2
         * an2number = agentnumber1のactivefriendの中のagentnumber2がいる番号
         * ln = layernumber
         */
        int an1,ln;
        Collections.shuffle(shufflenumber);
        for(int i=0;i<shufflenumber.size();i++){
            ln = Paramerter.rand.nextInt(Paramerter.layernumber);
            an1 = network[ln].choosedagent.get(shufflenumber.get(i));
            if(network[ln].friendagent[an1].size() ==0)
                continue;
            infuluenceOpinion(ln,an1);
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
            //fa = allfriends[i].get(Paramerter.rand.nextInt(allfriends[i].size()));
            for(int j=0;j<allfriends[i].size();j++){
                fa = allfriends[i].get(j);
                silenceAgent(i,fa);
            }
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
}

