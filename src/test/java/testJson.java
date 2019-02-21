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
        String orgin="WebSocketSession[0 current WS(0)-HttpStream(0)-HttpPoll(0), 0 total, 0 closed abnormally (0 connect failure, 0 send limit, 0 transport error)], stompSubProtocol[processed CONNECT(0)-CONNECTED(0)-DISCONNECT(0)], stompBrokerRelay[null], inboundChannel[pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0], outboundChannelpool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0], sockJsScheduler[pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]";
        orgin=orgin.split(" ")[0];
        orgin=orgin.split("\\[")[1];
        System.out.println(orgin);
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
