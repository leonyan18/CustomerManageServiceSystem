package config;

import interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author yan
 * @date 2018/11/3 19:54
 * @descripition
 */
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = {"listener"})
@PropertySource("classpath:rabbitmq.properties")
public class StompConfig implements WebSocketMessageBrokerConfigurer {
    private final Environment env;

    @Autowired
    public StompConfig(Environment env) {
        this.env = env;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/marcopolo").setAllowedOrigins("*").addInterceptors(new HttpSessionHandshakeInterceptor()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //带有app前缀
        config.setApplicationDestinationPrefixes("/app");
        //消息处理带/topic,/queue前缀的
//        config.enableSimpleBroker("/topic", "/queue");
        config.enableStompBrokerRelay("/topic", "/queue")
//                .setClientLogin(env.getProperty("mq.username"))
//                .setClientPasscode(env.getProperty("mq.password"))
//                .setSystemLogin(env.getProperty("mq.username"))
//                .setSystemPasscode(env.getProperty("mq.password"))
//                .setRelayPort(Integer.parseInt(env.getProperty("mq.port")))
//                .setRelayHost(env.getProperty("mq.host"))
        ;
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(createUserInterceptor());
    }
    @Bean
    public UserInterceptor createUserInterceptor() {
        return new UserInterceptor();
    }
}
