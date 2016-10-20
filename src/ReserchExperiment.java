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
}
