package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너") // 스프링이 없다? -> ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); 로 스프링 컨테이너를 만들지 않았다.
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 1과 2가 참조값이 다른 것을 확인해보기
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 테스트는 println 처럼 눈으로 확인하는 것 보다, 자동화 되도록 해야 한다.
        // memberService1 != memberService2 인 것을 검증하기
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}


