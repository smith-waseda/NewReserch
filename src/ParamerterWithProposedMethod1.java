/**
 * Created by Sumi on 2016/12/16.
 */
public class ParamerterWithProposedMethod1 extends Paramerter {
    public static final int agentnumber = 100;
    public static int layernumber = 0;
    public static int[] agentnumberinnetwork;
    //public static int allagentnumberinnetwork;

    public static int returnAllAgentNumberInNetwork(){
        int total=0;
        for(int i=0;i<layernumber;i++){
            total += agentnumberinnetwork[i];
        }
        return total;
    }


    public static void Dataset(int type){
        switch(type){
            case 0:
                layernumber = 8;
                agentnumberinnetwork = new int[ParamerterWithProposedMethod1.layernumber];
                agentnumberinnetwork[0] = 100;
                agentnumberinnetwork[1] = 100;
                agentnumberinnetwork[2] = 100;
                agentnumberinnetwork[3] = 100;
                agentnumberinnetwork[4] = 100;
                agentnumberinnetwork[5] = 100;
                agentnumberinnetwork[6] = 100;
                agentnumberinnetwork[7] = 100;
                break;

            case 1:
                layernumber = 8;
                agentnumberinnetwork = new int[ParamerterWithProposedMethod1.layernumber];
                agentnumberinnetwork[0] = 100;
                agentnumberinnetwork[1] = 100;
                agentnumberinnetwork[2] = 100;
                agentnumberinnetwork[3] = 100;
                agentnumberinnetwork[4] = 100;
                agentnumberinnetwork[5] = 100;
                agentnumberinnetwork[6] = 100;
                agentnumberinnetwork[7] = 100;
                break;
            case 2:
                layernumber=8;
                agentnumberinnetwork[0] = 100;
                agentnumberinnetwork[1] = 100;
                agentnumberinnetwork[2] = 100;
                agentnumberinnetwork[3] = 100;
                agentnumberinnetwork[4] = 100;
                agentnumberinnetwork[5] = 100;
                agentnumberinnetwork[6] = 100;
                agentnumberinnetwork[7] = 100;
                break;
            case 3:
                layernumber=2;
                agentnumberinnetwork = new int[ParamerterWithProposedMethod1.layernumber];
                agentnumberinnetwork[0] = 10;
                agentnumberinnetwork[1] = 10;
                break;

        }
    }
}
