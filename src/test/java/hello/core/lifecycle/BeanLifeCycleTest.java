package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // close() 쓰기 위해서는 ConfigurableApplicationContext 사용
    }

    @Configuration
    static class LifeCycleConfig {

        // destroyMethod의 특별한 기능 : 기본값이 inferred (추론) 으로 등록되어 있어 이 기능은 'close', 'shutdown' 라는 이름의 메서드를 자동으로 호출해준다.
        // @Bean(initMethod = "init", destroyMethod = "close")
        @Bean // @PostConstruct, @PreDestroy 사용하여 빈 생명주기 콜백
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }

    }

}
