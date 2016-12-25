/**
 * Created by Sumi on 2016/12/16.
 */
public class Execute {
    private static int looptime = Paramerter.numberoftrial;
    private static String dir;
    private static String file1,file2;
    public static void main(String[] args) {
        Paramerter.allowance=0.5;
        for(int x=0;x<=10;x++) {
            Paramerter.connectivity=x*0.1;
            dir = "E:\\卒論出力\\new\\提案手法2のみ";
            file1 = String.format("%2f", Paramerter.getConnectivity())+","+0.3+"412";
            MakeCSVFile.makeFiles(dir, file1);
            file2 =String.format("%2f", Paramerter.getConnectivity())+","+0.3+"512";
            MakeCSVFile.makeFiles(dir, file2);
            for (int i = 0; i < looptime; i++) {
                ReserchExperiment.originalReserchWithProposedMethod1(1);
                DataOutputWithProposedMethod.evaluateAverage(MakeCSVFile.returnLatestFIlename(dir, file1));
                DataOutputWithProposedMethod.evaluatePersentage(MakeCSVFile.returnLatestFIlename(dir, file2));
                System.out.println((x*100/10 + i/10 )+"% completed");
            }
        }
    }
}
