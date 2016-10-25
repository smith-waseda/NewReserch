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
    public static void main(String[] args) {

        OriginalNetworkLayer.init();
        OriginalNetworkLayer.displayNetwork();
        for(int i=0;i<Paramerter.convergencenumber;i++){
            OriginalNetworkLayer.formationOfOpinion();
            OriginalNetworkLayer.pressureAndSilence();
            //OriginalNetworkLayer.displayNetwork();
        }
        OriginalNetworkLayer.displayNetwork();
        graphicRandomNetwork(0);
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
}
