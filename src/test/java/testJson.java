import com.alibaba.fastjson.JSONArray;
import config.DataConfig;
import config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.HttpUtil;

public class testJson {
    public static void main(String[] args) {
        JSONArray jsonArray3=new JSONArray();
        JSONArray jsonArray4=new JSONArray();
        jsonArray3.add("吗");
        jsonArray4.add("天气真好");
        jsonArray4.add("天气真好");
        jsonArray4.add("天气真好");
        jsonArray3.add(jsonArray4);
        String js=HttpUtil.sendPost("http://119.23.44.109:8080/predict","data="+jsonArray3.toString());
        System.out.println(js);
        JSONArray jsonArray1=JSONArray.parseArray(js);
        for (int i = 0; i < jsonArray1.size(); i++) {
            JSONArray jsonArray2=JSONArray.parseArray(jsonArray1.get(i).toString());
            for (int j = 0; j < 3; j++) {
                System.out.println(jsonArray2.get(j));
            }
        }
    }
}
