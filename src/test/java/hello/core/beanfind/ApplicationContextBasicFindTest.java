package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);  // 파라미터(이름, 타입)
//        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  // 이 코드 문장에서 Alt + Enter 하여 아래처럼 만든다. (static import)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  // 검증 (memberService가 MemberServiceImpl의 인스턴스이면 성공)
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);  // 파라미터(타입)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  // 검증 (memberService가 MemberServiceImpl의 인스턴스이면 성공)
    }

    @Test
    @DisplayName("구체 타입으로 조회")  // 구현체에 의존하는 것은 좋은 방법은 아님
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);  // 파라미터(구체 타입)
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  // 검증 (memberService가 MemberServiceImpl의 인스턴스이면 성공)
    }

    // 테스트 실패 코드
    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByNameX() {
//        MemberService xxxxx = ac.getBean("xxxxx", MemberService.class); -> xxxxx라는 이름을 가진 빈은 없음
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));  // 오른쪽 파라미터를 실행하였을 때, 왼쪽 파라미터에 명시한 예외가 터지면 성공!
    }

}
