

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
public class GTest {
    //授权文件地址
    private static String path1="C:\\Users\\ASUS\\Desktop\\licese\\金航数码专用授权Avicit.license2021-12-31.res";

    //解码文件地址
    private static String path2="C:\\Users\\ASUS\\Desktop\\licese\\123.txt";
    //编码文件地址
    private static String path3="C:\\Users\\ASUS\\Desktop\\licese\\123_code.txt";
    //生成授权文件地址
    private static String path4="C:\\Users\\ASUS\\Desktop\\licese\\lty.res";
    //是否进行修改 如不修改为false反之为true
    private static boolean isModified=true;
    /**
     * 进行第一次运行，即可获得 path2,3,4三个文件
     * 如需要修改则在path2中进行明文修改并将isTrue改为true，然后再运行
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        if(!isModified){
            System.out.println("开始读压缩文件...");
            //使用GZIPInputStream包装InputStream流，使其具有解压特性
            BufferedReader in2 = new BufferedReader(new InputStreamReader(
                    new GZIPInputStream(new FileInputStream(path1)), "UTF-8"));
            String s;
            StringBuilder a=new StringBuilder();
            //读取压缩文件里的内容
            while ((s = in2.readLine()) != null) {
                a.append(s);
            }
            in2.close();
            String lty_s= new String(new BASE64Decoder().decodeBuffer(a.toString()));
            System.out.println(lty_s);
            writeFile(lty_s,path2);
        }
        byte lty [] =readFile(path2).getBytes();
        String encodes= new BASE64Encoder().encodeBuffer(lty);
        System.out.println(encodes);
        writeFile(encodes,path3);
        encodeFile_lty();
    }

    public static void encodeFile_lty() throws Exception, Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(
                path3), "UTF-8"));
        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(
                new FileOutputStream(path4)));
        System.out.println("开始写压缩文件...");
        int c;
        while ((c = in.read()) != -1) {
            out.write(String.valueOf((char) c).getBytes("UTF-8"));
        }
        in.close();
        out.close();
    }

    /**
     * 读取文件
     * @param path 文件路径
     */
    public static String readFile(String path) {
        StringBuffer lty_StringBuf=new StringBuffer();
        try (FileReader reader = new FileReader(path);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                lty_StringBuf.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return lty_StringBuf.toString();
    }

    /**
     * 写入文件
     */
    public static void writeFile(String data,String path) {
        try {
            File writeName = new File(path);
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write(data);
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}