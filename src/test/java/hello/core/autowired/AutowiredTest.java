package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출되지 않는다
        // 호출 자체가 안됨
        @Autowired(required = false) // 디폴트 true -> true이면 주입될 대상이 없어서 예외 발생
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // org.springframework.lang.@Nullable : 자동 주입할 대상이 없으면 호출은 되지만 null이 입력된다
        // null 호출
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional<> : 자동 주입할 대상이 없으면 Optional.empty가 입력된다
        // Optional.empty 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
