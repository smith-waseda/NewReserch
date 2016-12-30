/**
 * Created by Sumi on 2016/12/16.
 */
public class Execute {
    private static int looptime = Paramerter.numberoftrial;
    private static String dir="C:\\Users\\s.nakamura\\Google ドライブ\\卒論のデータ";
    private static String straightdir;
    private static String file1,file2;
    public static void main(String[] args) {
        /*
        exe1(0,0.3,0);
        exe1(0,0.5,0);
        exe1(1,0.3,0);
        exe1(1,0.5,0);
        */

        /*
        exe2(0,0.3,1,0);
        exe2(0,0.3,2,0);
        exe2(0,0.3,3,0);
        exe2(0,0.5,1,0);
        exe2(0,0.5,2,0);
        exe2(0,0.5,3,0);
        exe2(1,0.3,1,0);
        exe2(1,0.3,2,0);
        exe2(1,0.3,3,0);
        exe2(1,0.5,1,0);
        exe2(1,0.5,2,0);
        exe2(1,0.5,3,0);
        */

        exe3(0,0.3,1);
        exe3(0,0.5,1);
        exe3(1,0.3,1);
        exe3(1,0.5,1);

        exe3(0,0.3,2);
        exe3(0,0.5,2);
        exe3(1,0.3,2);
        exe3(1,0.5,2);

    }

    /**
     * @param type1　提案手法2
     * @param type2　許容度
     */
    public static void exe1(int type1,double type2){
        Paramerter.allowance=type2;
        if(type1 == 0)
            straightdir = "\\先行研究"+type2;
        if(type1 == 1)
            straightdir = "\\提案手法2,"+type2;
        for(int x=0;x<=10;x++) {
            Paramerter.connectivity=x*0.1;
            file1 = String.format("%.2f", Paramerter.getConnectivity())+","+ type2+"proposed2,412";
            file2 = String.format("%.2f", Paramerter.getConnectivity())+","+ type2+"proposed2,512";
            MakeCSVFile.makeFiles(dir+straightdir, file1);
            MakeCSVFile.makeFiles(dir+straightdir, file2);
            for (int i = 0; i < looptime; i++) {
                if(type1 == 0){
                    ReserchExperiment.originalReserch(0,0);
                }
                if(type1 == 1){
                    ReserchExperiment.originalReserchWithProposedMethod(0,0);
                }
                DataOutputWithProposedMethod.evaluateAverage(MakeCSVFile.returnLatestFIlename(dir+straightdir, file1));
                DataOutputWithProposedMethod.evaluatePersentage(MakeCSVFile.returnLatestFIlename(dir+straightdir, file2));
                System.out.println((x*100/10 + i/10 )+"% completed:second");
            }
        }
    }

    /**
     * @param type1 提案手法2
     * @param type2 許容度
     * @param type3 提案手法1
     * @param networktype 複雑ネットワーク
     */
    public static void exe2(int type1,double type2,int type3,int networktype){
        Paramerter.allowance=type2;
        straightdir = "\\提案手法1";
        file1 = type1+"-"+type2+"-"+type3+"proposed1,412";
        file2 = type1+"-"+type2+"-"+type3+"proposed1,512";
        MakeCSVFile.makeFiles(dir+straightdir, file1);
        MakeCSVFile.makeFiles(dir+straightdir, file2);
        for(int i=0;i<looptime;i++){
            ReserchExperiment.originalReserchWithSpecificNetWork(type3,type1,networktype);
            DataOutputWithProposedMethod.evaluateAverage(MakeCSVFile.returnLatestFIlename(dir+straightdir, file1));
            DataOutputWithProposedMethod.evaluatePersentage(MakeCSVFile.returnLatestFIlename(dir+straightdir, file2));
            System.out.println((i/10)+"% completed:second");
        }
    }

    /**
     * @param type1　提案手法2
     * @param type2　許容度
     * @param networktype　提案手法3
     */
    public static void exe3(int type1,double type2,int networktype){
        Paramerter.allowance=type2;
        if(type1 == 0)
            straightdir = "\\先行研究-提案手法3,"+type2;
        if(type1 == 1)
            straightdir = "\\提案手法2-提案手法3,"+type2;
        for(int x=0;x<=10;x++) {
            if(networktype == 2) {
                Paramerter.connectivity =x* 0.1;
                ParamerterWithProposedMethod.wsprobablity = 0.1;
            }else{
                Paramerter.connectivity=x*0.1;
            }
            file1 = networktype+","+String.format("%.2f", Paramerter.getConnectivity())+","+String.format("%.2f", ParamerterWithProposedMethod.wsprobablity)+","+"412";
            file2 = networktype+","+String.format("%.2f", Paramerter.getConnectivity())+","+String.format("%.2f", ParamerterWithProposedMethod.wsprobablity)+","+"512";
            MakeCSVFile.makeFiles(dir+straightdir, file1);
            MakeCSVFile.makeFiles(dir+straightdir, file2);
            for (int i = 0; i < looptime; i++) {
                if(type1 == 0){
                    ReserchExperiment.originalReserch(0,networktype);
                }
                if(type1 == 1){
                    ReserchExperiment.originalReserchWithProposedMethod(0,networktype);
                }
                DataOutputWithProposedMethod.evaluateAverage(MakeCSVFile.returnLatestFIlename(dir+straightdir, file1));
                DataOutputWithProposedMethod.evaluatePersentage(MakeCSVFile.returnLatestFIlename(dir+straightdir, file2));
                System.out.println((x*100/10 + i/10 )+"% completed:second");
            }
        }
    }
}
