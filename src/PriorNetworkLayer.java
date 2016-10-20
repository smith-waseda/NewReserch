import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Sumi on 2016/10/13.
 */
public class PriorNetworkLayer {
    public static final int maxlayernumber = Paramerter.layernumber;
    public static final int maxagentnumber = Paramerter.agentnumber;
    public static AgentRandomNetwork[] network;
    public static ArrayList<Integer> shufflenumber;
    public static ArrayList<Agent>[] activefriend;
    public static ArrayList<Integer>[] allfriends;

    private static StringBuffer buf = new StringBuffer();

    public static void init(){
        /**
         * make shuffle arraylist
         */
        shufflenumber = new ArrayList<>();
        for(int i = 0; i< maxagentnumber; i++){
            shufflenumber.add(i);
        }

        /**
         * make network
         */
        network = new AgentRandomNetwork[maxlayernumber];
        for(int i=0; i<maxlayernumber; i++){
            network[i] = new AgentRandomNetwork(i);
            network[i].generateGraph();
        }

        /**
         * set agent opinion
         */
        double rand;
        for(int i=0;i<maxagentnumber;i++){
            rand = Paramerter.rand.nextDouble();
            for(int j=0;j<maxlayernumber;j++){
                network[j].agent[i].opinion = rand;
            }
        }

        /**
         * make activefriend list
         */
        activefriend = new ArrayList[maxagentnumber];
        for(int i=0;i<maxagentnumber;i++){
            activefriend[i] = new ArrayList<>();
            for(int j=0;j<maxlayernumber;j++){
                if(network[j].friendagent[i].size() ==0)
                    continue;;
                for(int k=0;k<network[j].friendagent[i].size();k++){
                    activefriend[i].add(network[j].friendagent[i].get(k));
                }
            }
        }

        /**
         * make allfriend list(リストの中で同じエージェントは重複しない)
         */
        allfriends = new ArrayList[maxagentnumber];
        for(int i=0;i<maxagentnumber;i++){
            allfriends[i] = new ArrayList<>();
            for(int j=0;j<maxlayernumber;j++){
                if(network[j].friendagent[i].size() ==0)
                    continue;;
                for(int k=0;k<network[j].friendagent[i].size();k++){
                    if(!allfriends[i].contains(network[j].friendagent[i].get(k).number))
                        allfriends[i].add(network[j].friendagent[i].get(k).number);
                }
            }
        }
    }

    public static void displayShuffle(){
        for (int i = 0; i < shufflenumber.size(); i++) {
            String element = String.valueOf(shufflenumber.get(i));
            buf.append(element);
            buf.append(",");
        }
        String elementline = buf.toString();
        buf.delete(0, buf.length());
        System.out.println(elementline);
    }

    public static void displayNetwork(){
        for(int i=0;i<maxlayernumber;i++) {
            System.out.println("network["+i+"]");
            //network[i].displayLinking();
            network[i].displayOpinion();
            //network[i].displayExpress();
        }
    }

    public static void displayActiveFriend(){
        for(int i=0;i<maxagentnumber;i++){
            for (int j = 0; j < activefriend[i].size(); j++) {
                String element = String.valueOf(activefriend[i].get(j).number);
                buf.append(element);
                buf.append("_");
                element = String.valueOf(activefriend[i].get(j).layer);
                buf.append(element);
                buf.append(",");
            }
            String elementline = buf.toString();
            buf.delete(0, buf.length());
            System.out.println(i + ":" + elementline);
        }
    }

    public static void displayAllFriends(){
        for(int i=0;i<maxagentnumber;i++){
            for (int j = 0; j < allfriends[i].size(); j++) {
                String element = String.valueOf(allfriends[i].get(j));
                buf.append(element);
                buf.append(",");
            }
            String elementline = buf.toString();
            buf.delete(0, buf.length());
            System.out.println(i + ":" + elementline);
        }
    }

    public static void formationOfOpinion(){
        /**
         * an1 = agentnumber1 an2 = agentnumber2
         * an2number = agentnumber1のactivefriendの中のagentnumber2がいる番号
         * ln = layernumber
         */
        int an1,an2;
        int an2number,ln;
        Collections.shuffle(shufflenumber);
        for(int i=0;i<shufflenumber.size();i++){
            an1 = shufflenumber.get(i);
            if(activefriend[an1].size() == 0)
                continue;
            an2number = Paramerter.rand.nextInt(activefriend[an1].size());
            an2 = activefriend[an1].get(an2number).number;
            ln = activefriend[an1].get(an2number).layer;
            exchangeOpinion(ln,an1,an2);
        }
    }

    public static void exchangeOpinion(int ln,int an1,int an2){
        double tmp;
        if(Math.abs(network[ln].agent[an1].opinion - network[ln].agent[an2].opinion) < Paramerter.confornitybias) {
            tmp = (network[ln].agent[an1].opinion + network[ln].agent[an2].opinion)/2;
            network[ln].agent[an1].opinion = tmp;
            network[ln].agent[an2].opinion = tmp;
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
                    network[i].agent[an1].express = false;
                    for(int j=0;j<maxagentnumber;j++){
                        if(activefriend[j].contains(network[i].agent[an1]))
                            activefriend[j].remove(network[i].agent[an1]);
                    }
                }
            }
        }
    }
}
