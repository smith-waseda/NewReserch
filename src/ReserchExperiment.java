/**
 * Created by Sumi on 2016/10/13.
 */
public class ReserchExperiment {
    public static void priorReserch(){
        PriorNetworkLayer.init();
        for(int i=0;i<Paramerter.convergencenumber;i++){
            PriorNetworkLayer.formationOfOpinion();
            PriorNetworkLayer.pressureAndSilence();
        }
    }

    public static void originalReserch(){
        OriginalNetworkLayer.init();
        for(int i=0;i<Paramerter.convergencenumber;i++){
            OriginalNetworkLayer.formationOfOpinion();
            OriginalNetworkLayer.pressureAndSilence();
        }
    }

    public static void originalReserchWithProposedMethod1(int type){
        OriginalNetworkLayerWithProposedMethod1.init(type);
        for(int i=0;i<ParamerterWithProposedMethod1.convergencenumber;i++){
            OriginalNetworkLayerWithProposedMethod1.formationOfOpinion();
            OriginalNetworkLayerWithProposedMethod1.pressureAndSilence();
        }
    }

    public static void originalReserchWithProposedMethod2(){
        OriginalNetworkLayerWithProposedMethod2.init();
        for(int i=0;i<Paramerter.convergencenumber;i++){
            OriginalNetworkLayerWithProposedMethod2.formationOfOpinion();
            OriginalNetworkLayerWithProposedMethod2.pressureAndSilence();
        }
    }
}
