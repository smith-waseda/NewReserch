import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sumi on 2016/12/20.
 */
public class MakeCSVFile {
    private static final String DELIM = "-"; //デリミタ
    private static final String DATE_FORMAT = "yyyyMMdd";
    private static File dir;


    /**
     *注意：一日作れる同じファイルは100個まで！！！！
     * @param x dirname
     * @param y filename
     */
    public static void makeFiles(String x,String y){
        dir = new File(x);

        String datePrefix = calcPrefix();
        String fileName;
        int lastId = findLastId(datePrefix,y);
        int currentId = ++lastId;

        if(currentId < 10){
            fileName =  "0"+currentId + DELIM + y + DELIM +datePrefix+".csv";
        }else{
            fileName =  currentId + DELIM + y + DELIM + datePrefix+".csv";
        }
        File newfile = new File(x+"\\"+fileName);
        try{
            newfile.createNewFile();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    private static String calcPrefix(){
        Date date1 = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
        return sdf1.format(date1);
    }//calcPrefix()

    private static int findLastId(String datePrefix,String fn){
        FileFilter fileFilter = new RegexFileFilter("[0-9]+"+DELIM+ fn + DELIM + datePrefix +".csv");
        File[] files = dir.listFiles(fileFilter);
        List<String> fileList = new ArrayList<>();
        String largestFileName;

        for (int i = 0; i < files.length; i++) {
            fileList.add(files[i].getName());
        }

        //１つも見つからなかったら、IDは0
        if(fileList.isEmpty()){
            return 0;
        }else{
        //String型の自然順序と逆順でソート（一番大きいものが前）
            Collections.sort(fileList, Collections.reverseOrder());
            largestFileName = fileList.get(0);
        }
        //Idを取り出す
        Scanner scanner = new Scanner(largestFileName).useDelimiter("[-.]+");
        //assert文はデバッグ用
        //String actualDatePrefix = scanner.next();
        //assert actualDatePrefix.equals(datePrefix);
        assert scanner.hasNextInt();
        int lastId = scanner.nextInt();
        //String remains = scanner.next();
        //assert remains.equals(filename);
        scanner.close();

        return lastId;
    }//findLastId()

    public static String returnLatestFIlename(String dirname,String fn){
        String datePrefix = calcPrefix();
        String fileName;
        int Id;
        Id =  findLastId(datePrefix,fn);
        if(Id < 10){
            fileName =  "0"+Id + DELIM + fn + DELIM +datePrefix+".csv";
        }else{
            fileName =  Id + DELIM + fn + DELIM + datePrefix+".csv";
        }
        return dirname+"\\"+fileName;
    }

}
