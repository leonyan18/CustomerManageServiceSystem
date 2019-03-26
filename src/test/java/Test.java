import com.google.common.hash.BloomFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;

public class Test {
    private static final Logger logger = LogManager.getLogger(Test.class);
    public static void main(String[] args) {
        ApplicationEvent applicationEvent=new BrokerAvailabilityEvent(true,"1");
        System.out.println(applicationEvent.getClass()==BrokerAvailabilityEvent.class);
        System.out.println(BrokerAvailabilityEvent.class);
        logger.info("tetetete");
    }
}
