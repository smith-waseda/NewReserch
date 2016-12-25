/**
 * Created by Sumi on 2016/12/16.
 */
public class Execute {
    private static int looptime = Paramerter.numberoftrial;
    private static String dir;
    private static String file1,file2;
    public static void main(String[] args) {
        Paramerter.allowance=0.3;
        for(int x=0;x<=10;x++) {
            Paramerter.connectivity=x*0.1;
            dir = "C:\\Users\\s.nakamura\\Google ドライブ\\卒論のデータ\\提案手法2,0.3";
            file1 = String.format("%2f", Paramerter.getConnectivity())+","+ Paramerter.allowance+"proposed2,412";
            MakeCSVFile.makeFiles(dir, file1);
            file2 =String.format("%2f", Paramerter.getConnectivity())+","+ Paramerter.allowance+"proposed2,512";
            MakeCSVFile.makeFiles(dir, file2);
            for (int i = 0; i < looptime; i++) {
                ReserchExperiment.originalReserchWithProposedMethod1(0);
                DataOutputWithProposedMethod.evaluateAverage(MakeCSVFile.returnLatestFIlename(dir, file1));
                DataOutputWithProposedMethod.evaluatePersentage(MakeCSVFile.returnLatestFIlename(dir, file2));
                System.out.println((x*100/10 + i/10 )+"% completed");
            }
        }
        Paramerter.allowance=0.5;
        for(int x=0;x<=10;x++) {
            Paramerter.connectivity=x*0.1;
            dir = "C:\\Users\\s.nakamura\\Google ドライブ\\卒論のデータ\\提案手法2,0.5";
            file1 = String.format("%2f", Paramerter.getConnectivity())+","+ Paramerter.allowance+"proposed2412";
            MakeCSVFile.makeFiles(dir, file1);
            file2 =String.format("%2f", Paramerter.getConnectivity())+","+ Paramerter.allowance+"proposed2512";
            MakeCSVFile.makeFiles(dir, file2);
            for (int i = 0; i < looptime; i++) {
                ReserchExperiment.originalReserchWithProposedMethod1(0);
                DataOutputWithProposedMethod.evaluateAverage(MakeCSVFile.returnLatestFIlename(dir, file1));
                DataOutputWithProposedMethod.evaluatePersentage(MakeCSVFile.returnLatestFIlename(dir, file2));
                System.out.println((x*100/10 + i/10 )+"% completed:second");
            }
        }
        Paramerter.allowance=0.3;
        for(int x=0;x<=10;x++) {
            Paramerter.connectivity=x*0.1;
            dir = "C:\\Users\\s.nakamura\\Google ドライブ\\卒論のデータ\\先行研究０．３";
            file1 = String.format("%2f", Paramerter.getConnectivity())+","+ Paramerter.allowance+"original412";
            MakeCSVFile.makeFiles(dir, file1);
            file2 =String.format("%2f", Paramerter.getConnectivity())+","+ Paramerter.allowance+"original512";
            MakeCSVFile.makeFiles(dir, file2);
            for (int i = 0; i < looptime; i++) {
                ReserchExperiment.originalReserch(0);
                DataOutputWithProposedMethod.evaluateAverage(MakeCSVFile.returnLatestFIlename(dir, file1));
                DataOutputWithProposedMethod.evaluatePersentage(MakeCSVFile.returnLatestFIlename(dir, file2));
                System.out.println((x*100/10 + i/10 )+"% completed");
            }
        }
        Paramerter.allowance=0.5;
        for(int x=0;x<=10;x++) {
            Paramerter.connectivity=x*0.1;
            dir = "C:\\Users\\s.nakamura\\Google ドライブ\\卒論のデータ\\先行研究０．５";
            file1 = String.format("%2f", Paramerter.getConnectivity())+","+ Paramerter.allowance+"original412";
            MakeCSVFile.makeFiles(dir, file1);
            file2 =String.format("%2f", Paramerter.getConnectivity())+","+ Paramerter.allowance+"original512";
            MakeCSVFile.makeFiles(dir, file2);
            for (int i = 0; i < looptime; i++) {
                ReserchExperiment.originalReserch(0);
                DataOutputWithProposedMethod.evaluateAverage(MakeCSVFile.returnLatestFIlename(dir, file1));
                DataOutputWithProposedMethod.evaluatePersentage(MakeCSVFile.returnLatestFIlename(dir, file2));
                System.out.println((x*100/10 + i/10 )+"% completed:second");
            }
        }
    }
}
