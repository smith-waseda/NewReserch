/**
 * Created by Sumi on 2016/12/16.
 */
public class Execute {
    private static int looptime = Paramerter.numberoftrial;
    public static void main(String[] args) {
        MakeCSVFile.makeFiles("D:\\4年\\研究室\\卒業論文\\研究出力\\平均データ", "412");
        for (int i = 0; i < 50; i++) {
            System.out.println(i);
            ReserchExperiment.originalReserchWithProposedMethod1(1);
            DataOutputWithProposedMethod.evaluatePersentage(ParamerterWithProposedMethod1.pathname);
            //DataOutputWithProposedMethod.opinionData(ParamerterWithProposedMethod1.pathname);
        }
    }
}
