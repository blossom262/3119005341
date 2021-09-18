import DuplicateCheck.SimHash;
import DuplicateCheck.Similarity;
import org.junit.Test;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

public class testSimHash {
    public String text1 = "Hello World";
    public String text2 = "";
    public String text3 = "CHINA";
    @Test
    public void testhash() throws IOException { //测试simhash值计算函数
        SimHash s = new SimHash(text1,64);
        BigInteger f = s.hash(text3);
        System.out.println(Long.toBinaryString(f.longValue()));
    }
    @Test
    public void testsimHash1() throws IOException { //测试simhash值计算函数
        SimHash s = new SimHash(text1,64);
        System.out.println(s.strSimHash);
    }
    @Test
    public void testsimHash2() throws IOException { //测试simhash值计算函数（文本为空）
        SimHash s = new SimHash(text2,64);
        System.out.println(s.strSimHash);
    }
}
