import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Sumi on 2016/11/16.
 */
public class DateOutputForOrignalWithProposedMethod2 {
    public static File file1,file2,file3,file4,file5;
    public static FileWriter filewriter1,filewriter2,filewriter3,filewriter4,filewriter5;
    public static int looptime = Paramerter.numberoftrial;
    public static void main(String[] args) {
        outPut(0.3);
    }

    public static void outPut(double allowance){
        double total411,average411;
        double total412,average412;
        double total511,average511;
        double total512,average512;
        System.out.println("test:"+allowance);
        try {
            file1 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法2\\411.csv");
            if ( !file1.exists())
                file1 = new File("E:\\先行研究\\test.txt");
            if ( !file1.exists())
                file1 = new File("C:\\Users\\s.nakamura\\Google ドライブ\\test1.csv");

            file2 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法2\\412.csv");
            if ( !file2.exists())
                file2 = new File("E:\\先行研究\\test.txt");
            if ( !file2.exists())
                file2 = new File("C:\\Users\\s.nakamura\\Google ドライブ\\test2.csv");

            file3 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法2\\511.csv");
            if ( !file3.exists())
                file3 = new File("E:\\先行研究\\test3.txt");
            if ( !file3.exists())
                file3 = new File("C:\\Users\\s.nakamura\\Google ドライブ\\test3.csv");

            file4 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法2\\512.csv");
            if ( !file4.exists())
                file4 = new File("E:\\先行研究\\test.txt");
            if ( !file4.exists())
                file4 = new File("C:\\Users\\s.nakamura\\Google ドライブ\\test4.csv");

            file5 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\提案手法2\\opinionaspect2.csv");
            if ( !file5.exists())
                file5 = new File("E:\\先行研究\\test.txt");
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
                        ReserchExperiment.originalReserch();
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
        double total = 0,average;
        for (int i = 0; i < Paramerter.layernumber; i++) {
            for (int j = 0; j < Paramerter.agentnumber; j++) {
                total += OriginalNetworkLayer.network[i].agent[j].opinion;
            }
        }
        average = total / (Paramerter.agentnumber * Paramerter.layernumber);
        return average;
    }

    public static double evaluateDispersion() {
        double average = evaluateAverage();
        double total = 0,dispersion;
        for (int i = 0; i < Paramerter.layernumber; i++) {
            for (int j = 0; j < Paramerter.agentnumber; j++) {
                total += (OriginalNetworkLayer.network[i].agent[j].opinion- average) * (OriginalNetworkLayer.network[i].agent[j].opinion- average);
            }
        }
        dispersion = total / (Paramerter.agentnumber * Paramerter.layernumber);
        return dispersion;
    }

    public static double differenceBetweenNetworkLayer(){
        double maxaverage=0,minaverage=1;
        double tmptotal,tmpaverage;
        for(int i=0; i<Paramerter.layernumber;i++){
            tmptotal=0;
            for(int j=0;j<Paramerter.agentnumber;j++){
                if(OriginalNetworkLayer.network[i].agent[j].express)
                    tmptotal+=OriginalNetworkLayer.network[i].agent[j].opinion;
            }
            tmpaverage = tmptotal/Paramerter.agentnumber;
            if(maxaverage<tmpaverage)
                maxaverage = tmpaverage;
            if(minaverage>tmpaverage)
                minaverage = tmpaverage;
        }
        return maxaverage-minaverage;
    }

    public static double percentageOfExpress(){
        int total=0;
        double persentage;
        for(int i=0;i<Paramerter.layernumber;i++){
            for(int j=0;j<Paramerter.agentnumber;j++){
                if(OriginalNetworkLayer.network[i].agent[j].express)
                    total++;
            }
        }
        persentage =(double) total / (Paramerter.agentnumber * Paramerter.layernumber);
        return persentage;
    }

    public static double consensusBias(){
        int total=0,consensus=0;
        double persentage;
        for(int i=0;i<Paramerter.layernumber;i++){
            for(int j=0;j<Paramerter.agentnumber;j++){
                if(OriginalNetworkLayer.network[i].friendagent[j].size()==0)
                    continue;
                for(int k=0;k<OriginalNetworkLayer.network[i].friendagent[j].size();k++){
                    total++;
                    if(Math.abs(OriginalNetworkLayer.network[i].agent[j].opinion-OriginalNetworkLayer.network[i].friendagent[j].get(k).opinion)==0)
                        consensus++;
                }
            }
        }
        persentage = (double)consensus/total;
        return persentage;
    }
}
