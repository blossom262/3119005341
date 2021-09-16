package Util;

import java.io.*;
import java.text.DecimalFormat;

import DuplicateCheck.SimHash;
import DuplicateCheck.Similarity;

public class Compare {
    public static void main(String[] args) throws Exception {
        if(args.length!=3){
            System.out.println("输入更多");
            return;
        }
        String path1 = args[0];// 原文本绝对路径  E:\360MoveData\Users\L\Desktop\tests\orig.txt
        String path2 = args[1];// 抄袭文本路径    E:\360MoveData\Users\L\Desktop\tests\orig_0.8_add.txt、orig_0.8_del.txt、orig_0.8_dis_1.txt
        String path3 = args[2];// 结果文件路径    E:\360MoveData\Users\L\Desktop\tests\res.txt
        System.out.println(path1.substring(37) + "文本与" + path2.substring(37) + "文本的相似度为：");
        double ans = Compare.ans(path1, path2);
        String data = "" + ans;
        File outputFile = new File(path3);
        FileWriter output = new FileWriter(outputFile);
        char[] chars = data.toCharArray();
        output.write(chars);
        output.close();
        System.out.println("-----------------------------------------");
        //Thread.sleep(20000);
    }

    public static double ans(String filepath1, String filepath2) throws Exception {
        // 指定文件
        String oldText = new String();
        String newText = new String();

        // 指定文件路径
        String oldPath = filepath1;
        String newPath = filepath2;

        // 文件读入
        BufferedReader oldBuff = new BufferedReader(new InputStreamReader(new FileInputStream(oldPath), "UTF8"));
        BufferedReader newBuff = new BufferedReader(new InputStreamReader(new FileInputStream(newPath), "UTF8"));
        String str;
        // 读入原来的文件
        while ((str = oldBuff.readLine()) != null) {
            oldText += str;
        }
        // 读入新的文件
        while ((str = newBuff.readLine()) != null) {
            newText += str;
        }

        oldBuff.close();
        newBuff.close();

        if (oldText.length() == 0 || newText.length() == 0) {
            throw new EmptyException("文本为空");
        }

        double ans;
        SimHash hash1 = new SimHash(oldText, 64);
        SimHash hash2 = new SimHash(newText, 64);
        int d = hash1.hammingDistance(hash2);// 计算汉明距离
        ans = Similarity.getSimliar(d);
        System.out.println(ans);
        return ans;
    }

}
