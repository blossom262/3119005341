import DuplicateCheck.SimHash;
import DuplicateCheck.Similarity;
import Util.Compare;
import org.junit.Test;

import java.io.IOException;


public class testCompare {
    String[] s = new String[3];
    public String path1 = "C:\\Users\\kai\\Desktop\\学习资料\\大三上\\ceshiwenben\\orig.txt";
    public String path2 = "C:\\Users\\kai\\Desktop\\学习资料\\大三上\\ceshiwenben\\orig_0.8_del.txt";
    public String path3 = "C:\\Users\\kai\\Desktop\\学习资料\\大三上\\ceshiwenben\\ans.txt";
    @Test
    public void testans() throws Exception {
        System.out.println(Compare.ans(path1,path2));
    }

}
