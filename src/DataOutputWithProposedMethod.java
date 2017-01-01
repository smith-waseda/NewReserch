import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Sumi on 2016/12/22.
 */
public class DataOutputWithProposedMethod {
    private static File file;
    private static FileWriter filewriter;
    private static StringBuffer buf = new StringBuffer();
    private static StringBuffer sub = new StringBuffer();
    private static String writing,subwrinting;

    private static  ArrayList<Double>[] totaldata;

    public static void initArray(){
        totaldata = new ArrayList[6];
        for(int i=0;i<6;i++){
            totaldata[i] = new ArrayList<>();
        }
    }

    public static void clearArray(){
        for(int i=0;i<6;i++){
            totaldata[i].clear();
        }
    }
    /**
     * これはたぶん使わない
     * @param pathname
     */
    public static void opinionData(String pathname) {
        try {
            file = new File(pathname);
            if (checkBeforeWritefile(file)) {
                filewriter = new FileWriter(file);

                for (int i = 0; i < ParamerterWithProposedMethod.layernumber; i++) {
                    for (int j = 0; j < ParamerterWithProposedMethod.agentnumber; j++) {
                        buf.append(OriginalNetworkLayerWithProposedMethod.network[i].agent[j].opinion);
                        buf.append(",");
                    }
                    writing = buf.toString();
                    buf.delete(0, buf.length());
                    filewriter.write(writing + "\n");
                }

                filewriter.close();
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

    public static void evaluateAverage(String pathname) {
        /**
         * anはagent number
         * total,averageはあるネットワーク内の意見の合計と平均
         * alltotal,allaverageはすべてのネットワーク上の意見の合計と平均
         */
        int an,choosedagentinlayer=0;
        double total = 0, average, alltotal=0,allaverage;
        double maxaverage=0,minaverage=1;
        double dispersiontotal =0,dispersionaverage;

        try {
            file = new File(pathname);
            if (checkBeforeWritefile(file)) {
                filewriter = new FileWriter(file,true);

                for (int i = 0; i < ParamerterWithProposedMethod.layernumber; i++) {
                    /*平均を求める処理*/
                    for (int j = 0; j < ParamerterWithProposedMethod.agentnumberinnetwork[i]; j++) {
                        an = OriginalNetworkLayerWithProposedMethod.network[i].choosedagent.get(j);
                        total += OriginalNetworkLayerWithProposedMethod.network[i].agent[an].opinion;
                    }
                    //すべてのネットワーク上の意見の合計と平均を求めるための処理
                    alltotal += total;
                    choosedagentinlayer+= ParamerterWithProposedMethod.agentnumberinnetwork[i];

                    average = total / ParamerterWithProposedMethod.agentnumberinnetwork[i];
                    buf.append(average);
                    buf.append(",");
                    total = 0;

                    //各ネットワーク間の最大と最小を求めるための処理
                    if(maxaverage<average){
                        maxaverage = average;
                    }
                    if(minaverage>average) {
                        minaverage = average;
                    }

                    /*分散を求める処理*/
                    for (int j = 0; j < ParamerterWithProposedMethod.agentnumberinnetwork[i]; j++) {
                        an = OriginalNetworkLayerWithProposedMethod.network[i].choosedagent.get(j);
                        dispersiontotal += (OriginalNetworkLayerWithProposedMethod.network[i].agent[an].opinion- average) * (OriginalNetworkLayerWithProposedMethod.network[i].agent[an].opinion- average);
                    }
                    dispersionaverage = Math.sqrt(dispersiontotal/ ParamerterWithProposedMethod.agentnumberinnetwork[i]);
                    totaldata[1].add(dispersionaverage);
                    sub.append(dispersionaverage);
                    sub.append(",");
                    dispersiontotal = 0;
                }
                allaverage = alltotal / choosedagentinlayer;
                totaldata[0].add(allaverage);
                buf.append(allaverage);
                buf.append(",");
                totaldata[2].add(maxaverage-minaverage);
                buf.append(maxaverage-minaverage+",");

                //ファイルへの書き込み処理
                writing = buf.toString();
                subwrinting = sub.toString();
                buf.delete(0, buf.length());
                sub.delete(0, sub.length());
                filewriter.write(writing+"XXX,"+subwrinting + "\n");
                filewriter.close();
            } else {
                System.out.println("ファイルに書き込めません");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void evaluatePersentage(String pathname) {
        /**
         * choosedagentinlayerはすべてのネットワークのエージェントの数の合計
         * opinionagentはあるネットワークの中の意見を表明しているエージェントの数
         * opinionagentinlayerはすべてのネットワークの意見を表明しているエージェントの数
         * edgetotalはネットワーク内のエッジの数
         * consensusinnetworkはネットワーク内のエッジでコンセンサスを得ている数
         * consensusinnetworkはそれらの割合
         */
        int an,choosedagentinlayer=0,opinionagent=0,opinionagentinlayer=0;
        double expresspersentage,expresspersentageinlayer;
        int edgetotal=0,consensusinnetwork=0;
        double consensuspersentage;
        try {
            file = new File(pathname);
            if (checkBeforeWritefile(file)) {
                filewriter = new FileWriter(file, true);

                for (int i = 0; i < ParamerterWithProposedMethod.layernumber; i++) {
                    for (int j = 0; j < ParamerterWithProposedMethod.agentnumberinnetwork[i]; j++) {

                        /*意見を表明している割合*/
                        an = OriginalNetworkLayerWithProposedMethod.network[i].choosedagent.get(j);
                        if (OriginalNetworkLayerWithProposedMethod.network[i].agent[an].express) {
                            opinionagent++;
                        }

                        /*コンセンサスの取れている割合*/
                        if (OriginalNetworkLayerWithProposedMethod.network[i].friendagent[an].size() == 0){
                            continue;
                        }
                        edgetotal+= OriginalNetworkLayerWithProposedMethod.network[i].friendagent[an].size();
                        for(int k = 0; k< OriginalNetworkLayerWithProposedMethod.network[i].friendagent[an].size(); k++){
                            if(Math.abs(OriginalNetworkLayerWithProposedMethod.network[i].agent[an].opinion- OriginalNetworkLayerWithProposedMethod.network[i].friendagent[an].get(k).opinion)<=Paramerter.confornitybias)
                                consensusinnetwork++;
                        }
                    }

                    //すべての層での意見表明の割合の準備
                    opinionagentinlayer += opinionagent;
                    choosedagentinlayer += ParamerterWithProposedMethod.agentnumberinnetwork[i];

                    expresspersentage = (double)opinionagent / (double) ParamerterWithProposedMethod.agentnumberinnetwork[i];
                    opinionagent=0;
                    buf.append(expresspersentage);
                    buf.append(",");

                    consensuspersentage = (double)consensusinnetwork / (double) edgetotal;
                    edgetotal=0;
                    consensusinnetwork=0;
                    totaldata[4].add(consensuspersentage);
                    sub.append(consensuspersentage);
                    sub.append(",");
                }
                expresspersentageinlayer = (double)opinionagentinlayer/(double)choosedagentinlayer;
                totaldata[3].add(expresspersentageinlayer);
                buf.append(expresspersentageinlayer);
                buf.append(",");

                writing = buf.toString();
                buf.delete(0, buf.length());
                subwrinting = sub.toString();
                sub.delete(0,sub.length());
                filewriter.write(writing + "XXX,"+subwrinting+"\n");

                filewriter.close();
            } else {
                System.out.println("ファイルに書き込めません");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static double calculateA(ArrayList list){
        double Atotal=0;
        for(int i=0;i<list.size();i++){
            Atotal = Atotal + (double)list.get(i);
        }
        return Atotal/(double)list.size();
    }

    private static double calculateD(ArrayList list){
        double Dtotal=0;
        double average = calculateA(list);
        for(int i=0;i<list.size();i++){
            Dtotal+=(average - (double)list.get(i))* (average - (double)list.get(i));
        }
        return Math.sqrt(Dtotal/(double)list.size());
    }

    public static void totalData(String pathname) {
        try {
            file = new File(pathname);
            if (checkBeforeWritefile(file)) {
                filewriter = new FileWriter(file,true);

                for (int i = 0; i < 6; i++) {
                    buf.append(calculateA(totaldata[i])+","+calculateD(totaldata[i])+",");
                }
                writing = buf.toString();
                buf.delete(0, buf.length());
                filewriter.write(writing + "\n");

                filewriter.close();
            } else {
                System.out.println("ファイルに書き込めません");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
