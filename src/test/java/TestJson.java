import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ResultUtil;

import java.util.ArrayList;
import java.util.List;

public class TestJson {
    private static final Logger logger = LogManager.getLogger(TestJson.class);
    public static void main(String[] args) {
        System.out.println(ResultUtil.checkMotion("垃圾"));
        String test="[{\"problem\":\"股票的交易时间？\",\"answer\":\"您好，沪深两市接受竞价交易申报的时间为每个交易日(周一至周五，法定节假日除外)的9：15-9：25，9：30-11：30，13：00-15：00。其中，沪深两市9：15-9：25为开盘集合竞价，9：30-11：30，13：00-15：00为连续竞价，14：57-15：00为收盘集合竞价。 \\n\"},{\"problem\":\"多少资金能达到多少的佣金？\",\"answer\":\"您好，您的佣金是营业部根据资金量、交易量以及营业部的运营成本综合考量制定的，如需调整，可咨询开户营业部了解。\"},{\"problem\":\"手机账号忘记了怎么办\",\"answer\":\"您好，股票交易登录涉及的账号主要是沪深股东账号和资金账号。如果您记得其中的任意一个账号和交易密码的情况下，您可以登录我司任意交易系统（[stock:sh,601211,国泰君安/]君弘，富易，锐智）查询到其他的所有账号。如忘记上述账号，客户可以尝试用身份证号+一户通密码登录查看客户所有账号信息（必须已开通过一户通功能，未开通无法登录）。如果上述账号都忘记了身份证号也无法登录的客户就需要带好身份证交易时间前往开户营业部查询。查询路径为： \\n①最新富易：登录后点击左下角\\\"更多\\\"再选择设置菜单→系统设置→关于本系统。会出现一个新窗口有客户的账号信息。 ②君弘：登录账户后→交易→更多→综合查询→账号查询。\"},{\"problem\":\"忘记交易密码怎么办？\",\"answer\":\"您好，如忘记交易密码，则需要您本人携带二代身份证交易时间前往营业部柜台重置。必须柜台办理，手机、网上无法重置。 \"},{\"problem\":\"创业板如何开通\"}]";
        JSONArray jsonArray=JSONArray.parseArray(test);
        for (Object j:jsonArray) {
            System.out.println(j);
        }
        logger.info("ok");
        List<String> strs=new ArrayList<>();
        strs.add("天气真好");
        strs.add("天气真好");
        strs.add("天气真好");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("sajdio","dsjakd");
        jsonObject.put("23","dsjakd");
        System.out.println(jsonObject.toJSONString());
        strs= ResultUtil.getResult(strs,"吗");
        for (String s:strs) {
            System.out.println(s);
        }
        logger.info("ok");
        System.out.println(ResultUtil.getSentiment("垃圾"));
    }
}
