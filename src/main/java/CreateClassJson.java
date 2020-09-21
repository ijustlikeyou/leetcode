import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateClassJson {

    public static void main(String[] args) {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        //数据行
        List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
        //列名
        String[] lineName=new String[]{"name","test","test2"};
        try {
            String str = "";
            String str1 = "";
            fis = new FileInputStream("C:\\Users\\Administrator.WIN-AVI0HHBELTV\\Desktop\\点名列表(2).txt");
            isr = new InputStreamReader(fis,"utf-8");
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                Map<String, Object> rowsData=new HashMap<String, Object>();
                rowsData.put("1999",str);
                rows.add(rowsData);
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Map<String,Object> line:rows){
            String [] data=line.get("1999").toString().split(",");
           for(int i=0;i<lineName.length;i++){
               line.put(lineName[i],data[i]);
           }
            line.remove("1999");
        }
        for (Map<String,Object> line:rows){
            for (String key:line.keySet()){
               System.out.print("key:"+key+"  value:"+line.get(key)+",");
            }
            System.out.println();
        }
    }


}
