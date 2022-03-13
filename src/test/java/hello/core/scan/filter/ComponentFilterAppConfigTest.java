package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        // BeanA
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull(); // beanA는 null이 아니다. 컴포넌트 스캔 대상을 추가로 지정한 클래스이기 때문

        // BeanB
//        ac.getBean("beanB", BeanB.class); // 예외 발생 -> 컴포넌트 스캔에서 제외한 클래스이기 때문
        assertThrows(
                NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("beanB", BeanB.class));
    }

    // 해당 test에서 사용할 AppConfig 만들기
    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {
    }
}
