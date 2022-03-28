package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient { // implements InitializingBean->afterPropertiesSet() 메서드로 초기화 지원, DisposableBean->destroy() 메서드로 소멸을 지원 ===> 이 인터페이스들은 스프링 전용 인터페이스

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    //@Override
    @PostConstruct
    public void init() throws Exception { // afterPropertiesSet -> 의존관계 주입이 끝나면 호출한다는 의미
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    //@Override
    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
