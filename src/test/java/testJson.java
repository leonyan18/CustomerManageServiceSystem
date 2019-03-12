import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ResultUtil;

import java.util.ArrayList;
import java.util.List;

public class testJson {
    private static final Logger logger = LogManager.getLogger(testJson.class);
    public static void main(String[] args) {
        logger.info("ok");
        List<String> strs=new ArrayList<>();
        strs.add("天气真好");
        strs.add("天气真好");
        strs.add("天气真好");
        strs= ResultUtil.getResult(strs,"吗");
        for (String s:strs) {
            System.out.println(s);
        }
        logger.info("ok");
        System.out.println(ResultUtil.getSentiment("垃圾"));
    }
}
