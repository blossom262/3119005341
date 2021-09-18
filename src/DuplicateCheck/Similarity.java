package DuplicateCheck;

import java.math.BigInteger;
import java.text.DecimalFormat;
import static java.lang.Math.PI;
import DuplicateCheck.SimHash;

public class Similarity {
    public static int hammingDistance(SimHash first, SimHash second) {  //计算汉明距离
        BigInteger x = first.intSimHash.xor(second.intSimHash);
        int difference = 0;
        while (x.signum() != 0) {
            difference += 1;
            x = x.and(x.subtract(new BigInteger("1")));
        }
        return difference;
    }

    static public double getSimliar(int x) {
        double number1 = Math.sqrt(2 * PI * 0.16);
        double number2 = Math.pow(0.01 * x - 0.01, 2);
        double number3 = 2 * 0.0459 * 0.0459 * (-1.0);
        double form1 = 1 / number1;
        double form2 = Math.exp(number2 / number3);
        double result = form1 * form2;
        result = (double)Math.round(result*100)/100;
        return result;
    }
}
