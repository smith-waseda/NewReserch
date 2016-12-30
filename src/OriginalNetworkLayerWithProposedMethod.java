import java.util.ArrayList;

/**
 * Created by Sumi on 2016/12/16.
 */
public class OriginalNetworkLayerWithProposedMethod {
    public static int maxlayernumber;
    public static int maxagentnumber;
    public static AgentRandomNetworkWithProposedMethod[] network;
    public static ArrayList<Integer> shufflenumber;
    public static ArrayList<Integer>[] allfriends;
    private static StringBuffer buf = new StringBuffer();

    public static void init(int type,int networktype){
        /**
         * make network
         */
        initNetwork(type,networktype);

        /**
         * set agent opinion
         */
        maxagentnumber = ParamerterWithProposedMethod.agentnumber;
        ArrayList<Double> rand = new ArrayList<Double>();
        for(int i=0;i<maxagentnumber;i++){
            rand.add(Paramerter.rand.nextDouble());
        }
        for(int i=0;i<maxlayernumber;i++){
            for(int j=0;j<maxagentnumber;j++){
                network[i].agent[j].opinion = rand.get(j);
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
                    continue;
                for(int k=0;k<network[i].friendagent[network[i].choosedagent.get(j)].size();k++){
                    if(!allfriends[network[i].choosedagent.get(j)].contains(network[i].friendagent[network[i].choosedagent.get(j)].get(k).number))
                        allfriends[network[i].choosedagent.get(j)].add(network[i].friendagent[network[i].choosedagent.get(j)].get(k).number);
                }
            }
        }


        /**
         * make shuffle arraylist
         */
        shufflenumber = new ArrayList<>();
        for(int i = 0; i< maxagentnumber; i++){
            shufflenumber.add(i);
        }
    }

    /**
     * ネットワークの構成
     * @param type　ネットワークの組み方
     */
    public static void initNetwork(int type,int networktype) {
        ParamerterWithProposedMethod.Dataset(type);
        maxlayernumber = ParamerterWithProposedMethod.layernumber;
        network = new AgentRandomNetworkWithProposedMethod[maxlayernumber];
        //先行研究、SNS型
        if(type ==0 || type==1) {
            for (int i = 0; i < maxlayernumber; i++) {
                network[i] = new AgentRandomNetworkWithProposedMethod(i, ParamerterWithProposedMethod.agentnumberinnetwork[i]);
                if(networktype == 0)
                    network[i].generateGraph();
                if(networktype == 1)
                    network[i].generateBAGraph();
                if(networktype == 2)
                    network[i].generateWSGraph();
            }
        }
        //会社型
        if(type == 2){
            for (int i = 0; i < maxlayernumber; i++) {
                network[i] = new AgentRandomNetworkWithProposedMethod(i, ParamerterWithProposedMethod.agentnumberinnetwork[i],0);
                network[i].generateGraph();
            }
        }
        //友達の輪型
        if(type ==3){
            network[0] = new AgentRandomNetworkWithProposedMethod(0, ParamerterWithProposedMethod.agentnumberinnetwork[0]);
            network[0].generateGraph();
            for (int i = 1; i < maxlayernumber; i++) {
                network[i] = new AgentRandomNetworkWithProposedMethod(i, ParamerterWithProposedMethod.agentnumberinnetwork[i],i*100);
                network[i].generateGraph();
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

    public static void displayAllFriendsNetwork(){
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
        int an1,ln;
        for(int i=0;i<shufflenumber.size();i++){
            an1 = shufflenumber.get(i);
            while (allfriends[an1].size() == 0) {
                an1 = Paramerter.rand.nextInt(ParamerterWithProposedMethod.agentnumber);
            }
            ln = Paramerter.rand.nextInt(ParamerterWithProposedMethod.layernumber);
            while (network[ln].friendagent[an1].size() == 0) {
                ln = Paramerter.rand.nextInt(ParamerterWithProposedMethod.layernumber);
            }
            infuluenceOpinion(ln,an1);
        }
    }


    public static void infuluenceOpinion(int ln,int an1){
        int an2;
        for(int i=0;i<network[ln].friendagent[an1].size();i++) {
            an2 = network[ln].friendagent[an1].get(i).number;
            if (network[ln].agent[an1].express && network[ln].agent[an2].express) {
                if ((network[ln].agent[an1].opinion - network[ln].agent[an2].opinion) < Paramerter.confornitybias) {
                        network[ln].agent[an2].opinion = (network[ln].agent[an1].opinion + network[ln].agent[an2].opinion) / 2;
                }
            }
        }
    }

    public static void formationOfOpinionforPrior(){
        /**
         * an1 = agentnumber1 an2 = agentnumber2
         * an2number = agentnumber1のactivefriendの中のagentnumber2がいる番号
         * ln = layernumber
         */
        int an1,ln;
        for(int i=0;i<shufflenumber.size();i++){
            an1 = shufflenumber.get(i);
            while (allfriends[an1].size() == 0) {
                an1 = Paramerter.rand.nextInt(ParamerterWithProposedMethod.agentnumber);
            }
            ln = Paramerter.rand.nextInt(ParamerterWithProposedMethod.layernumber);
            while (network[ln].friendagent[an1].size() == 0) {
                ln = Paramerter.rand.nextInt(ParamerterWithProposedMethod.layernumber);
            }
            exchangeOpinion(ln,an1);
        }
    }

    public static void exchangeOpinion(int ln,int an1){
        double tmp;
        int an2;
        an2 = Paramerter.rand.nextInt(allfriends[an1].size());
        if(network[ln].agent[an1].express && network[ln].agent[an2].express){
            if(Math.abs(network[ln].agent[an1].opinion - network[ln].agent[an2].opinion) < Paramerter.confornitybias) {
                tmp = (network[ln].agent[an1].opinion + network[ln].agent[an2].opinion)/2;
                network[ln].agent[an1].opinion = tmp;
                network[ln].agent[an2].opinion = tmp;
            }
        }
    }

    public static void pressureAndSilence(){
        /**
         * fa = friendagentnunmber
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
                if (network[i].agent[an2].opinion > maxopinion)
                    maxopinion = network[i].agent[an2].opinion;
                if (network[i].agent[an2].opinion < minopinion)
                    minopinion = network[i].agent[an2].opinion;
            }
        }
        if((maxopinion-minopinion)>Paramerter.allowance){
            //System.out.println(maxopinion-minopinion);
            for(int i=0;i<maxlayernumber;i++){
                if(network[i].friendagent[an1].contains(network[i].agent[an2])){
                    network[i].agent[an2].express = false;
                }
            }
        }
    }
}

