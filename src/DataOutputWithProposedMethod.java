import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Sumi on 2016/12/22.
 */
public class DataOutputWithProposedMethod {
    private static File file;
    private static FileWriter filewriter;
    private static StringBuffer buf = new StringBuffer();
    private static StringBuffer sub = new StringBuffer();
    private static String writing,subwrinting;

    /**
     * これはたぶん使わない
     * @param pathname
     */
    public static void opinionData(String pathname) {
        try {
            file = new File(pathname);
            if (checkBeforeWritefile(file)) {
                filewriter = new FileWriter(file);

                for (int i = 0; i < ParamerterWithProposedMethod1.layernumber; i++) {
                    for (int j = 0; j < ParamerterWithProposedMethod1.agentnumber; j++) {
                        buf.append(OriginalNetworkLayerWithProposedMethod1.network[i].agent[j].opinion);
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

                for (int i = 0; i < ParamerterWithProposedMethod1.layernumber; i++) {
                    /*平均を求める処理*/
                    for (int j = 0; j < ParamerterWithProposedMethod1.agentnumberinnetwork[i]; j++) {
                        an = OriginalNetworkLayerWithProposedMethod1.network[i].choosedagent.get(j);
                        total += OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].opinion;
                    }
                    //すべてのネットワーク上の意見の合計と平均を求めるための処理
                    alltotal += total;
                    choosedagentinlayer+=ParamerterWithProposedMethod1.agentnumberinnetwork[i];

                    average = total / ParamerterWithProposedMethod1.agentnumberinnetwork[i];
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
                    for (int j = 0; j < ParamerterWithProposedMethod1.agentnumberinnetwork[i]; j++) {
                        an = OriginalNetworkLayerWithProposedMethod1.network[i].choosedagent.get(j);
                        dispersiontotal += (OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].opinion- average) * (OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].opinion- average);
                    }
                    dispersionaverage = dispersiontotal/ParamerterWithProposedMethod1.agentnumberinnetwork[i];
                    sub.append(dispersionaverage);
                    sub.append(",");
                    dispersiontotal = 0;
                }
                allaverage = alltotal / choosedagentinlayer;
                buf.append(allaverage);
                buf.append(",");
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
         * expressの方はそれらの割合
         */
        int an,choosedagentinlayer=0,opinionagent=0,opinionagentinlayer=0;
        double expresspersentage,expresspersentageinlayer;
        try {
            file = new File(pathname);
            if (checkBeforeWritefile(file)) {
                filewriter = new FileWriter(file, true);

                for (int i = 0; i < ParamerterWithProposedMethod1.layernumber; i++) {
                    for (int j = 0; j < ParamerterWithProposedMethod1.agentnumberinnetwork[i]; j++) {
                        an = OriginalNetworkLayerWithProposedMethod1.network[i].choosedagent.get(j);
                        if (OriginalNetworkLayerWithProposedMethod1.network[i].agent[an].express) {
                            opinionagent++;
                        }
                    }

                    //すべての層での意見表明の割合の準備
                    opinionagentinlayer += opinionagent;
                    choosedagentinlayer += ParamerterWithProposedMethod1.agentnumberinnetwork[i];

                    expresspersentage = opinionagent / ParamerterWithProposedMethod1.agentnumberinnetwork[i];
                    opinionagent=0;
                    buf.append(expresspersentage);
                    buf.append(",");
                }
                expresspersentageinlayer = opinionagentinlayer/choosedagentinlayer;
                buf.append(expresspersentageinlayer);
                buf.append(",");

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
