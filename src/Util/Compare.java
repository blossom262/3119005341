package Util;

import java.io.*;
import java.text.DecimalFormat;

import DuplicateCheck.SimHash;
import DuplicateCheck.Similarity;

public class Compare {
    public static void main(String[] args) throws Exception { //主函数，用于进行文件的读写
        if(args.length!=3){
            System.out.println("请输入三个参数");
            return;
        }
        String path1 = args[0]; // 原始文本路径
        String path2 = args[1]; // 抄袭文本路径
        String path3 = args[2]; // 查重结果文件路径
        String name1 = args[0].substring(args[0].lastIndexOf("\\")+1);//原始文本文件名
        String name2 = args[1].substring(args[1].lastIndexOf("\\")+1);//抄袭文本文件名
        System.out.println("原始文本与抄袭文本的相似度为：");
        double ans = Compare.ans(path1, path2);
        String rate = Double.toString(ans);
        File outputFile = new File(path3);
        FileWriter output = new FileWriter(outputFile);  //用于写入文件
        output.write("原始文本："+name1+ "\n");
        output.write("抄袭文本："+name2+ "\n");
        output.write("查重率：");
        output.write(rate);
        output.close();

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
            throw new EmptyException("文本无内容");
        }

        double ans;
        SimHash hash1 = new SimHash(oldText, 64);
        SimHash hash2 = new SimHash(newText, 64);
        int d = Similarity.hammingDistance(hash1,hash2);// 计算汉明距离
        ans = Similarity.getSimliar(d);
        System.out.println(ans);
        return ans;
    }

}
