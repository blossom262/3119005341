package DuplicateCheck;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.HashMap;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class SimHash {
    private String text;  //用于保存查重文本

    public BigInteger intSimHash;   //用于记录签名值

    private String strSimHash;  //

    private int hashbits = 64;  //hash签名的位数

    public SimHash(String text) throws IOException {  //构造函数
        this.text = text;
        this.intSimHash = this.simHash();
    }

    public SimHash(String text, int hashbits) throws IOException {  //构造函数
        this.text = text;
        this.hashbits = hashbits;
        this.intSimHash = this.simHash();
    }

    HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

    public BigInteger simHash() throws IOException {
        int[] v = new int[this.hashbits];  // 定义向量v用于计算签名值
        StringReader sr = new StringReader(text);  // 创建文本输入流，读入文本
        IKSegmenter cutword = new IKSegmenter(sr, true); // 利用IKSegmenter将文本去掉标点符号后分词
        Lexeme lex = null;
        //计算特征的向量和
        while ((lex = cutword.next()) != null) {
            BigInteger Sequence = this.hash(lex.getLexemeText());  // 将每一个分词hash为一组固定长度的数列
            for (int i = 0; i < this.hashbits; i++) {
                BigInteger bitmask = new BigInteger("1").shiftLeft(i); // 对分词hash后的数列进行判断,逢1加1,逢0减1
                if (Sequence.and(bitmask).signum() != 0) { // 计算整个文档的所有特征的向量和，这里实际使用中需要 +- 权重
                    v[i] += 1;
                } else {
                    v[i] -= 1;
                }
            }
        }
        BigInteger fingerprint = new BigInteger("0");
        StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < this.hashbits; i++) { // 对数组进行判断,大于0的记为1,小于等于0的记为0,得到签名
            if (v[i] >= 0) {
                fingerprint = fingerprint.add(new BigInteger("1").shiftLeft(i));
                simHashBuffer.append("1");
            } else {
                simHashBuffer.append("0");
            }
        }
        this.strSimHash = simHashBuffer.toString();
        return fingerprint;
    }

    public BigInteger hash(String source) {  //hash算法
        if (source == null || source.length() == 0) {
            return new BigInteger("0");
        } else {
            char[] sourceArray = source.toCharArray();
            BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
            BigInteger m = new BigInteger("1000003");
            BigInteger mask = new BigInteger("2").pow(this.hashbits).subtract(new BigInteger("1"));
            for (char item : sourceArray) {
                BigInteger temp = BigInteger.valueOf((long) item);
                x = x.multiply(m).xor(temp).and(mask);
            }
            x = x.xor(new BigInteger(String.valueOf(source.length())));
            if (x.equals(new BigInteger("-1"))) {
                x = new BigInteger("-2");
            }
            return x;
        }
    }

    public int hammingDistance(SimHash other) {  //计算汉明距离

        BigInteger x = this.intSimHash.xor(other.intSimHash);
        int tot = 0;

        // 统计x中二进制位数为1的个数
        // 一个二进制数减去1，从最后那个1（包括那个1）后面的数字全都反了，然后，n&(n-1)就相当于把后面的数字清0，
        // 计算n能够完成多少次以上操作

        while (x.signum() != 0) {
            tot += 1;
            x = x.and(x.subtract(new BigInteger("1")));
        }
        return tot;
    }

}
