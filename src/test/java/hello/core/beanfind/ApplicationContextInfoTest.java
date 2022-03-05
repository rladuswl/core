package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    // .getBeanDefinition() 은 AnnotationConfigApplicationContext 타입에서만 사용 가능
    // AnnotationConfigApplicationContext의 부모 클래스인 GenericApplicationContext에서 상속받은 메소드라서 인터페이스인 ApplicationContext는 타입으로 사용 불가
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {

        // 스프링 컨테이너에 등록된 모든 빈 이름을 조회
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // 리스트나 배열에서 iter + Tab 누르면 for문 자동완성
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);  // 빈 이름으로 빈 객체(인스턴스) 조회
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
        }
    }

    // 내가 등록한 빈 (appConfig, memberService, memberRepository, orderService, discountPolicy) 만 출력하기
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {

        // 스프링 컨테이너에 있는 모든 빈 가져오기
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // 리스트나 배열에서 iter + Tab 누르면 for문 자동완성
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);  // .getBeanDefinition() : 빈에 대한 정보들

            // Role ROLE_APPLICATION : 애플리케이션을 개발하기 위해 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            }
        }
    }
}
