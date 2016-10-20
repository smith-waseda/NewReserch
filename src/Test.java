import org.apache.commons.math3.random.MersenneTwister;

/**
 * Created by Sumi on 2016/10/13.
 */
public class Test {
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
}
