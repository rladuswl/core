package hello.core.singleton;

public class StatefulService {

    // stateful는 조심해야한다.
//    private int price; // 상태를 유지하는 필드
//
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제!!!
//    }
//
//    public int getPrice() {
//        return price;
//    }

    // staeless로 설계해야 한다.
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

}
