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

    public static void originalReserch(int type,int networktype){
        OriginalNetworkLayerWithProposedMethod.init(type,networktype);
        for(int i=0;i<Paramerter.convergencenumber;i++){
            OriginalNetworkLayerWithProposedMethod.formationOfOpinionforPrior();
            OriginalNetworkLayerWithProposedMethod.pressureAndSilence();
        }
    }

    public static void originalReserchWithProposedMethod(int type,int networktype){
        OriginalNetworkLayerWithProposedMethod.init(type,networktype);
        for(int i = 0; i< ParamerterWithProposedMethod.convergencenumber; i++){
            OriginalNetworkLayerWithProposedMethod.formationOfOpinion();
            OriginalNetworkLayerWithProposedMethod.pressureAndSilence();
        }
    }

    public static void originalReserchWithProposedMethod2(){
        OriginalNetworkLayerWithProposedMethod2.init();
        for(int i=0;i<Paramerter.convergencenumber;i++){
            OriginalNetworkLayerWithProposedMethod2.formationOfOpinion();
            OriginalNetworkLayerWithProposedMethod2.pressureAndSilence();
        }
    }

    /**
     *
     * @param type ネットワークの形
     * @param type1　交換方法
     * @param networktype　複雑ネットワークの形
     */
    public static void originalReserchWithSpecificNetWork(int type,int type1,int networktype){
        OriginalNetworkLayerWithProposedMethod.init(type,networktype);
        Paramerter.connectivity=1.0;
        for(int i=0;i<Paramerter.convergencenumber;i++){
            if(type1 == 0) {
                OriginalNetworkLayerWithProposedMethod.formationOfOpinionforPrior();
            }
            if(type1 ==1) {
                OriginalNetworkLayerWithProposedMethod.formationOfOpinion();
            }
            OriginalNetworkLayerWithProposedMethod.pressureAndSilence();
        }
    }
}
