import org.apache.commons.math3.random.MersenneTwister;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Sumi on 2016/10/13.
 */
public class Test {
    public static File file;
    public static FileWriter filewriter;
    public static AgentRandomNetwork network;
    public static AgentRandomNetworkWithProposedMethod1 network1;
    public static void main(String[] args) {

        OriginalNetworkLayerWithProposedMethod1.init(0);
        //OriginalNetworkLayerWithProposedMethod1.displayNetwork();
        for(int i=0;i<Paramerter.convergencenumber;i++){
            OriginalNetworkLayerWithProposedMethod1.formationOfOpinion();
            OriginalNetworkLayerWithProposedMethod1.pressureAndSilence();
        }
        OriginalNetworkLayerWithProposedMethod1.displayNetwork();
        //OriginalNetworkLayerWithProposedMethod1.displayAllFriendsNetwork();
    }

    public static void GenerateGraph() {
            network = new AgentRandomNetwork(0);
            network.generateGraph();
            //network.displayLinking();
            //network.displayOpinion();

        double rand;
        for(int i=0;i<Paramerter.agentnumber;i++){
            rand = Paramerter.rand.nextDouble();
                network.agent[i].opinion = rand;
        }
    }

    public static void generateGraphWithProposedMethod1(){
        network1 = new AgentRandomNetworkWithProposedMethod1(0,500);
        network1.generateGraph();
        network1.displayLinking();
        //network1.displayOpinion();
    }


    public static double averageOfUniformRandomNumbers(){
        double total=0,average=0;
        for(int i=0;i<Paramerter.agentnumber;i++){
            total+=network.agent[i].opinion;
        }
        average=total/100;
        return average;
    }

    public static void graphicRandomNetwork(int layernumber){
        try {
            file = new File("C:\\Users\\s.nakamura\\Google ドライブ\\graphicRandomNetwork.csv");
            if (checkBeforeWritefile(file)){
                filewriter = new FileWriter(file);
                filewriter.write(0+","+1+"\n");
                for(int i=0;i<Paramerter.agentnumber;i++){
                    for(int j=0;j<OriginalNetworkLayer.network[layernumber].friendagent[i].size();j++){
                        filewriter.write(i+","+OriginalNetworkLayer.network[layernumber].friendagent[i].get(j).number+"\n");
                    }
                }
                filewriter.close();
            }
        } catch(IOException e){
            System.out.println(e);
        }
    }

    private static boolean checkBeforeWritefile(File file) {
        if (file.exists()) {
            if (file.isFile() && file.canWrite()) {
                return true;
            }
        }
        return false;
    }

    public static void originalReserchfottest(){
        OriginalNetworkLayerWithProposedMethod2.init();
        for(int i=0;i<Paramerter.convergencenumber;i++){
            OriginalNetworkLayerWithProposedMethod2.formationOfOpinion();
            OriginalNetworkLayerWithProposedMethod2.pressureAndSilence();
            if(i==9999)
                opinionAspect(9999);
        }
    }

    public static void opinionAspect(int loopnumber) {
        for (int i = 0; i < Paramerter.agentnumber; i++) {
            System.out.println(loopnumber + "," +"0" + "," + i + "," + OriginalNetworkLayerWithProposedMethod2.network[0].agent[i].opinion);
        }
        try {
            file = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法2\\opinionaspect7.csv");
            if (checkBeforeWritefile(file)){
                filewriter = new FileWriter(file);
                filewriter.write(0+","+1+"\n");
                filewriter.write("接続次数"+Paramerter.probability+"許容度"+Paramerter.confornitybias+"ネットワークの接続性"+Paramerter.connectivity+"\n");
                for(int i=0;i<Paramerter.agentnumber;i++){
                    filewriter.write(loopnumber + "," + "0" + ","+i+"," + OriginalNetworkLayerWithProposedMethod2.network[0].agent[i].opinion
                            +"1" + ","+i+"," + OriginalNetworkLayerWithProposedMethod2.network[1].agent[i].opinion
                            +"2" + ","+i+"," + OriginalNetworkLayerWithProposedMethod2.network[2].agent[i].opinion
                            +"3" + ","+i+"," + OriginalNetworkLayerWithProposedMethod2.network[3].agent[i].opinion
                            +"4" + ","+i+"," + OriginalNetworkLayerWithProposedMethod2.network[4].agent[i].opinion
                            +"5" + ","+i+"," + OriginalNetworkLayerWithProposedMethod2.network[5].agent[i].opinion
                            +"6" + ","+i+"," + OriginalNetworkLayerWithProposedMethod2.network[6].agent[i].opinion
                            +"7" + ","+i+"," + OriginalNetworkLayerWithProposedMethod2.network[7].agent[i].opinion+"\n");
                }
                filewriter.close();
            }
        } catch(IOException e){
            System.out.println(e+"testaaa");
        }
    }
}
