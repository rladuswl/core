package hello.core.singleton;

// 싱글톤으로 만들기 - 해당 클래스 내부 말고는 SingletonService 생성 불가능
public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성 (instance) -> DIP 위반 부분
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용 즉, instance를 꺼낼 수 있는 방법은 아래 메소드 뿐
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private으로 선언하여 외부에서 new 키워드를 사용한 객체를 생성할 수 없게 막기
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
