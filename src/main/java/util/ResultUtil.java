package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ResultUtil {
//    private static final double THRESHOLD=0.8;
    public static List<String> getResult(List<String> stringList,String problem){
        JSONArray jsonArray3=new JSONArray();
        JSONArray jsonArray4=new JSONArray();
        jsonArray3.add(problem);
        jsonArray4.addAll(stringList);
        jsonArray3.add(jsonArray4);
        String js=HttpUtil.sendPost("http://119.23.44.109:8080/predict","data="+jsonArray3.toString());
        JSONArray jsonArray1=JSONArray.parseArray(js);
        List<String> newstrs=new ArrayList<>();
        for (Object o : jsonArray1) {
            JSONArray jsonArray2 = JSONArray.parseArray(o.toString());
//            if (jsonArray2.getDouble(2) >= THRESHOLD) {
                newstrs.add(jsonArray2.getString(1));
//            }
            if(newstrs.size()>=5){
                break;
            }
        }
        return newstrs;
    }
    public static Boolean checkMotion(String words){
        String param="key=d74e55d83b8e412a9a7f47a0ea39e0bb&words="+words;
        String result=HttpUtil.sendGet("http://www.txtai.com/emotion/analyze?"+param,null);
//        System.out.println(result);
        JSONObject jsonObject=JSONObject.parseObject(result);
        jsonObject= (JSONObject) jsonObject.get("data");
//        System.out.println(jsonObject.toString());
        return Double.parseDouble( (String)jsonObject.get("negative"))>0.8;
    }

    public static double getSentiment(String problem) {
        return Double.parseDouble(HttpUtil.sendPost("http://119.23.44.109:8080/sentiment","data="+problem));
    }
}
