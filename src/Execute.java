/**
 * Created by Sumi on 2016/12/16.
 */
public class Execute {
    private static int looptime = Paramerter.numberoftrial;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ReserchExperiment.originalReserchWithProposedMethod1(1);
            MakeCSVFile.makeFiles("D:\\4年\\研究室\\卒業論文\\研究出力\\未処理データ", "412");
            DataOutputWithProposedMethod.opinionData(ParamerterWithProposedMethod1.pathname);
        }
    }
}
