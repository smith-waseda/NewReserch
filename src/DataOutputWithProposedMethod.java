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
    private static String writing;

    public static void opinionData(String pathname){
        try {
            file = new File(pathname);
            if (checkBeforeWritefile(file)) {
                filewriter = new FileWriter(file);

                for(int i=0;i<ParamerterWithProposedMethod1.layernumber;i++) {
                    for (int j = 0; j < ParamerterWithProposedMethod1.agentnumberinnetwork[i]; j++) {
                        buf.append(OriginalNetworkLayerWithProposedMethod1.network[i].agent[j].opinion);
                        buf.append(",");
                    }
                    writing = buf.toString();
                    buf.delete(0, buf.length());
                    filewriter.write(writing+"\n");
                }

                filewriter.close();
            }else {
                System.out.println("ファイルに書き込めません");
            }
        }catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void evaluateDispersion(String pathname){
        try {
            file = new File(pathname);
            if (checkBeforeWritefile(file)) {
                filewriter = new FileWriter(file);

                for(int i=0;i<ParamerterWithProposedMethod1.layernumber;i++) {
                    for (int j = 0; j < ParamerterWithProposedMethod1.agentnumberinnetwork[i]; j++) {
                        buf.append(OriginalNetworkLayerWithProposedMethod1.network[i].agent[j].opinion);
                        buf.append(",");
                    }
                    writing = buf.toString();
                    buf.delete(0, buf.length());
                    filewriter.write(writing+"\n");
                }

                filewriter.close();
            }else {
                System.out.println("ファイルに書き込めません");
            }
        }catch (IOException e) {
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
}
