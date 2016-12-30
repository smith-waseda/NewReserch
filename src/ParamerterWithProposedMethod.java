/**
 * Created by Sumi on 2016/12/16.
 */
public class ParamerterWithProposedMethod extends Paramerter {
    public static int agentnumber =100;
    public static int layernumber = 0;
    public static int[] agentnumberinnetwork;
    /**
     * 接続次数：Paramerの接続確率に代わるもの
     */
    public static int connectionorder = 10;
    /**
     * wsモデルのノードの張替え確率
     */
    public static double wsprobablity = 0.1;

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
                agentnumber = 100;
                layernumber = 8;
                agentnumberinnetwork = new int[ParamerterWithProposedMethod.layernumber];
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
                agentnumber = 800;
                layernumber = 8;
                agentnumberinnetwork = new int[ParamerterWithProposedMethod.layernumber];
                agentnumberinnetwork[0] = 800;
                agentnumberinnetwork[1] = 200;
                agentnumberinnetwork[2] = 200;
                agentnumberinnetwork[3] = 200;
                agentnumberinnetwork[4] = 200;
                agentnumberinnetwork[5] = 200;
                agentnumberinnetwork[6] = 200;
                agentnumberinnetwork[7] = 200;
                break;
            case 2:
                agentnumber = 800;
                layernumber=8;
                agentnumberinnetwork = new int[ParamerterWithProposedMethod.layernumber];
                agentnumberinnetwork[0] = 800;
                agentnumberinnetwork[1] = 700;
                agentnumberinnetwork[2] = 600;
                agentnumberinnetwork[3] = 500;
                agentnumberinnetwork[4] = 400;
                agentnumberinnetwork[5] = 300;
                agentnumberinnetwork[6] = 200;
                agentnumberinnetwork[7] = 100;
                break;
            case 3:
                agentnumber = 800;
                layernumber =8;
                agentnumberinnetwork = new int[ParamerterWithProposedMethod.layernumber];
                agentnumberinnetwork[0] = 800;
                agentnumberinnetwork[1] = 100;
                agentnumberinnetwork[2] = 100;
                agentnumberinnetwork[3] = 100;
                agentnumberinnetwork[4] = 100;
                agentnumberinnetwork[5] = 100;
                agentnumberinnetwork[6] = 100;
                agentnumberinnetwork[7] = 100;
                break;

        }
    }
}
