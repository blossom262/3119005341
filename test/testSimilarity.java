import DuplicateCheck.SimHash;
import DuplicateCheck.Similarity;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

public class testSimilarity {
    public String text1 = "HelloWorld";
    public String text2 = "HellWorld";
    public String text3 = "CHINA";
    @Test
    public void testhash1() throws IOException { //测试汉明距离计算函数
        SimHash s1 = new SimHash(text1,64);
        SimHash s2 = new SimHash(text2,64);
        int dis = Similarity.hammingDistance(s1,s2);
        System.out.println(dis);
    }
    @Test
    public void testhash2() throws IOException { //测试汉明距离计算函数
        SimHash s1 = new SimHash(text1,64);
        SimHash s2 = new SimHash(text3,64);
        int dis = Similarity.hammingDistance(s1,s2);
        System.out.println(dis);
    }
    @Test
    public void testgetSimliar()  { //测试simhash值计算函数
        int a = 1;
        double rate = Similarity.getSimliar(a);
        System.out.println(rate);
    }
}
