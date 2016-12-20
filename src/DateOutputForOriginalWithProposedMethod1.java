import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Sumi on 2016/12/18.
 */
public class DateOutputForOriginalWithProposedMethod1 {
    public static File file1,file2,file3,file4,file5;
    public static FileWriter filewriter1,filewriter2,filewriter3,filewriter4,filewriter5;
    public static int looptime = Paramerter.numberoftrial;
    public static void main(String[] args) {
        outPut(0.3,0);
    }


    public static void outPut(double allowance,int type){
        double total411,average411;
        double total412,average412;
        double total511,average511;
        double total512,average512;
        System.out.println("test:"+allowance);
        try {
            file1 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法1\\411.csv");
            if ( !file1.exists())
                file1 = new File("E:\\卒論出力\\提案手法1\\411.csv");
            if ( !file1.exists())
                file1 = new File("C:\\Users\\s.nakamura\\Google ドライブ\\test1.csv");

            file2 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法1\\412.csv");
            if ( !file2.exists())
                file2 = new File("E:\\卒論出力\\提案手法1\\412.csv");
            if ( !file2.exists())
                file2 = new File("C:\\Users\\s.nakamura\\Google ドライブ\\test2.csv");

            file3 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法1\\511.csv");
            if ( !file3.exists())
                file3 = new File("E:\\卒論出力\\提案手法1\\511.csv");
            if ( !file3.exists())
                file3 = new File("C:\\Users\\s.nakamura\\Google ドライブ\\test3.csv");

            file4 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法1\\512.csv");
            if ( !file4.exists())
                file4 = new File("E:\\卒論出力\\提案手法1\\512.csv");
            if ( !file4.exists())
                file4 = new File("C:\\Users\\s.nakamura\\Google ドライブ\\test4.csv");

            file5 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法1\\opinionaspect2.csv");
            if ( !file5.exists())
                file5 = new File("E:\\卒論出力\\提案手法1\\opinionaspect2.csv");
            if ( !file5.exists())
                file5 = new File("C:\\Users\\s.nakamura\\Google ドライブ\\test5.csv");

            if (checkBeforeWritefile(file1) && checkBeforeWritefile(file2) && checkBeforeWritefile(file3) && checkBeforeWritefile(file4) && checkBeforeWritefile(file5)) {
                filewriter1 = new FileWriter(file1);
                filewriter2 = new FileWriter(file2);
                filewriter3 = new FileWriter(file3);
                filewriter4 = new FileWriter(file4);
                filewriter5 = new FileWriter(file5,true);

                for (int i = 0; i <= 10; i++) {
                    Paramerter.setConnectivity(0.1 * i);
                    Paramerter.setAllowance(allowance);
                    total411=0;
                    total412=0;
                    total511=0;
                    total512=0;
                    for (int j = 0; j < looptime; j++) {
                        ReserchExperiment.originalReserchWithProposedMethod1(type);
                        total411 += evaluateDispersion();
                        total412 += differenceBetweenNetworkLayer();
                        total511 += percentageOfExpress();
                        total512 += consensusBias();
                    }
                    average411 = total411 / looptime;
                    average412 = total412 / looptime;
                    average511 = total511 / looptime;
                    average512 = total512 / looptime;
                    System.out.println("now complete" + average512 + "%");
                    filewriter1.write(allowance + "," + String.format("%2f", Paramerter.getConnectivity()) + "," + Math.sqrt(average411)+"\n");
                    filewriter2.write(allowance + "," + String.format("%2f", Paramerter.getConnectivity()) + "," + average412+"\n");
                    filewriter3.write(allowance + "," + String.format("%2f", Paramerter.getConnectivity()) + "," + average511+"\n");
                    filewriter4.write(allowance + "," + String.format("%2f", Paramerter.getConnectivity()) + "," + average512+"\n");
                }
                filewriter1.write("\n");
                filewriter1.close();
                filewriter2.write("\n");
                filewriter2.close();
                filewriter3.write("\n");
                filewriter3.close();
                filewriter4.write("\n");
                filewriter4.close();
                filewriter5.write("\n");
                filewriter5.close();
            } else {
                System.out.println("ファイルに書き込めません");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static boolean checkBeforeWritefile(File file) {
        if (file.exists()) {
            if (file.isFile() && file.canWrite()) {
                return true;
            }
        }
        return false;
    }

    public static double evaluateAverage() {
        int an;
        double total = 0,average;
        for (int i = 0; i < Paramerter.layernumber; i++) {
            for (int j = 0; j < OriginalNetworkLayerWithProposedMethod1.network[i].choosedagentnumber; j++) {
                an = OriginalNetworkLayerWithProposedMethod1.network[i].choosedagent.get(j);
                total += OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].opinion;
            }
        }
        average = total / ParamerterWithProposedMethod1.returnAllAgentNumberInNetwork();
        return average;
    }

    public static double evaluateDispersion() {
        int an;
        double average = evaluateAverage();
        double total = 0,dispersion;
        for (int i = 0; i < ParamerterWithProposedMethod1.layernumber; i++) {
            for (int j = 0; j < OriginalNetworkLayerWithProposedMethod1.network[i].choosedagentnumber; j++) {
                an = OriginalNetworkLayerWithProposedMethod1.network[i].choosedagent.get(j);
                total += (OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].opinion- average) * (OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].opinion- average);
            }
        }
        dispersion = total / ParamerterWithProposedMethod1.returnAllAgentNumberInNetwork();
        return dispersion;
    }

    public static double differenceBetweenNetworkLayer(){
        int an;
        double maxaverage=0,minaverage=1;
        double tmptotal,tmpaverage;
        for(int i=0; i<ParamerterWithProposedMethod1.layernumber;i++){
            tmptotal=0;
            for(int j=0;j<OriginalNetworkLayerWithProposedMethod1.network[i].choosedagentnumber;j++){
                an = OriginalNetworkLayerWithProposedMethod1.network[i].choosedagent.get(j);
                if(OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].express)
                    tmptotal+=OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].opinion;
            }
            tmpaverage = tmptotal/OriginalNetworkLayerWithProposedMethod1.network[i].choosedagentnumber;;
            if(maxaverage<tmpaverage)
                maxaverage = tmpaverage;
            if(minaverage>tmpaverage)
                minaverage = tmpaverage;
        }
        return maxaverage-minaverage;
    }

    public static double percentageOfExpress(){
        int an;
        int total=0;
        double persentage;
        for(int i=0;i<ParamerterWithProposedMethod1.layernumber;i++){
            for(int j=0;j<OriginalNetworkLayerWithProposedMethod1.network[i].choosedagentnumber;j++){
                an = OriginalNetworkLayerWithProposedMethod1.network[i].choosedagent.get(j);
                if(OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].express)
                    total++;
            }
        }
        persentage =(double) total / ParamerterWithProposedMethod1.returnAllAgentNumberInNetwork();
        return persentage;
    }

    public static double consensusBias(){
        int an;
        int total=0,consensus=0;
        double persentage;
        for(int i=0;i<Paramerter.layernumber;i++){
            for(int j=0;j<OriginalNetworkLayerWithProposedMethod1.network[i].choosedagentnumber;j++){
                an = OriginalNetworkLayerWithProposedMethod1.network[i].choosedagent.get(j);
                if(OriginalNetworkLayerWithProposedMethod1.network[i].friendagent[an].size()==0)
                    continue;
                for(int k=0;k<OriginalNetworkLayerWithProposedMethod1.network[i].friendagent[an].size();k++){
                    total++;
                    if(Math.abs(OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].opinion-OriginalNetworkLayerWithProposedMethod1.network[i].friendagent[an].get(k).opinion)<=Paramerter.confornitybias)
                        consensus++;
                }
            }
        }
        persentage = (double)consensus/total;
        return persentage;
    }
}
