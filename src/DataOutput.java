import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Sumi on 2016/10/13.
 */
public class DataOutput {
    public static int looptime = Paramerter.numberoftrial;
    public static void main(String[] args) {
        outPut(0.3);
    }

    public static void outPut(double allowance){
        double total411,average411;
        double total412,average412;
        double total511,average511;
                System.out.println("test:"+allowance);
                try {
                    File file1 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\411.csv");
                    if ( !file1.exists())
                        file1 = new File("E:\\先行研究\\test.txt");

                    File file2 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\412.csv");
                    if ( !file2.exists())
                        file2 = new File("E:\\先行研究\\test.txt");

                    File file3 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\511.csv");
                    if ( !file3.exists())
                        file3 = new File("E:\\先行研究\\test.txt");

                    File file4 = new File("D:\\4年\\研究室\\卒業論文\\研究出力\\512.csv");
                    if ( !file4.exists())
                        file4 = new File("E:\\先行研究\\test.txt");

                    if (checkBeforeWritefile(file1) && checkBeforeWritefile(file2) && checkBeforeWritefile(file3) && checkBeforeWritefile(file4)) {
                        FileWriter filewriter1 = new FileWriter(file1);
                        FileWriter filewriter2 = new FileWriter(file2);
                        FileWriter filewriter3 = new FileWriter(file3);
                        FileWriter filewriter4 = new FileWriter(file4);

                        for (int i = 0; i <= 10; i++) {
                            Paramerter.setConnectivity(0.1 * i);
                            Paramerter.setAllowance(allowance);
                            total411=0;
                            total412=0;
                            total511=0;
                            System.out.println(0.1*i);
                            for (int j = 0; j < looptime; j++) {
                                ReserchExperiment.priorReserch();
                                total411 += evaluateDispersion();
                                total412 += differenceBetweenNetworkLayer();
                                total511 += percentageOfExpress();
                                //filewriter1.write(allowance+","+Paramerter.connectivity+","+Math.sqrt(evaluateDispersion()));
                                //filewriter1.write(allowance+","+Paramerter.connectivity+","+differenceBetweenNetworkLayer());
                                //filewriter3.write(allowance+","+Paramerter.connectivity+","+percentageOfExpress());
                            }
                            average411 = total411 / looptime;
                            average412 = total412 / looptime;
                            average511 = total511 / looptime;
                            filewriter1.write("\n");
                            filewriter1.write(allowance + "," + String.format("%2f", Paramerter.getConnectivity()) + "," + Math.sqrt(average411) + "\n");
                            filewriter2.write("\n");
                            filewriter2.write(allowance + "," + String.format("%2f", Paramerter.getConnectivity()) + "," + average412 + "\n");
                            filewriter3.write("\n");
                            filewriter3.write(allowance + "," + String.format("%2f", Paramerter.getConnectivity()) + "," + average511 + "\n");
                        }
                        filewriter1.write("\n");
                        filewriter1.close();
                        filewriter2.write("\n");
                        filewriter2.close();
                        filewriter3.write("\n");
                        filewriter3.close();
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
                total += PriorNetworkLayer.network[i].agent[j].opinion;
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
                total += (PriorNetworkLayer.network[i].agent[j].opinion- average) * (PriorNetworkLayer.network[i].agent[j].opinion- average);
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
                tmptotal+=PriorNetworkLayer.network[i].agent[j].opinion;
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
                if(PriorNetworkLayer.network[i].agent[j].express)
                    total++;
            }
        }
        persentage =(double) total / (Paramerter.agentnumber * Paramerter.layernumber);
        return persentage;
    }
}
