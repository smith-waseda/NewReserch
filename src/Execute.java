/**
 * Created by Sumi on 2016/12/16.
 */
public class Execute {
    private static int looptime = Paramerter.numberoftrial;
    public static void main(String[] args) {
        for(int x=0;x<10;x++) {
            Paramerter.connectivity=x*0.1;
            MakeCSVFile.makeFiles("D:\\4年\\研究室\\卒業論文\\研究出力\\平均データ", ","+Paramerter.connectivity+","+0.3+"412");
            MakeCSVFile.makeFiles("D:\\4年\\研究室\\卒業論文\\研究出力\\平均データ", ","+Paramerter.connectivity+","+0.3+"512");
            for (int i = 0; i < looptime; i++) {
                System.out.println(i);
                ReserchExperiment.originalReserchWithProposedMethod1(1);
                DataOutputWithProposedMethod.evaluateAverage(MakeCSVFile.returnLatestFIlename("D:\\4年\\研究室\\卒業論文\\研究出力\\平均データ", ","+Paramerter.connectivity+","+0.3+"412"));
                DataOutputWithProposedMethod.evaluatePersentage(MakeCSVFile.returnLatestFIlename("D:\\4年\\研究室\\卒業論文\\研究出力\\平均データ", ","+Paramerter.connectivity+","+0.3+"512"));
            }
        }
    }
}
