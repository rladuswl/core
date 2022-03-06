package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : 사용자A 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : 사용자B 10000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // (stateful) ThreadA : 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice(); // 사용자A의 주문 금액을 찾고 싶은데 사용자B의 주문 금액인 20000원이 나오는 문제 발생
//        System.out.println("price = " + price);
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        // (stateless) ThreadA : 사용자A 주문 금액 조회
        System.out.println("price = " + userAPrice); // 여기서는 10000원이 제대로 나올 것이다. StatefulService 클래스에서 price를 전역변수가 아닌 지역변수로 바꿨기 때문!
    }

    // test에서 쓸 config 만들기
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}