package config;

import interceptor.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
public class StompConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/marcopolo").setAllowedOrigins("*").addInterceptors(new HttpSessionHandshakeInterceptor()).withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //带有app前缀
        config.setApplicationDestinationPrefixes("/app");
        //消息处理带/topic,/queue前缀的
        config.enableSimpleBroker("/topic", "/queue");
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
