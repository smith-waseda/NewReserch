/**
 * Created by Sumi on 2016/10/10.
 */
import org.apache.commons.math3.random.MersenneTwister;

public class Paramerter {
    /**
     * 実験でのループ回数
     */
    public static  final int numberoftrial = 100;
    public static final int convergencenumber = 10000;
    public static int agentnumber = 100;
    public static int layernumber = 8;
    /**
     * すり合わせ許容度
     */
    public static final double confornitybias = 0.4;
    /**
     * ネットワーク間の接続性
     */
    public static double connectivity = 1.0;
    /**
     * 一貫性のなさへの許容度
     */
    public static double allowance = 0.5;
    /**
     * ランダムネットワークの接続次数の確率
     */
    public static double probability = 0.1;
    /**
     * 乱数生成
     */
    public static MersenneTwister rand = new MersenneTwister(12);

    public static void generateRandom(long seed) {
        rand = new MersenneTwister(seed);
    }

    public static void setConnectivity(double i) {
        connectivity = i;
    }

    public static void setAllowance(double i) {
        allowance = i;
    }

    public static double getConnectivity() {
        return connectivity;
    }

    public static double getAllowance() {
        return allowance;
    }
}
