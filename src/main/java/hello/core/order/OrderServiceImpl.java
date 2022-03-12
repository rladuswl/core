package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // final은 값을 무조건 할당해야함, 구현체에 의존하지 않고 역할(인터페이스)에만 의존하는 중

    @Autowired // 컴포넌트 스캔과 의존관계 자동 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 할인은 내 책임이 아님! 할인에 관한건 전부 dicountPolicy에 넘김 (회원 등급, 상품 가격)
        // SRP(단일 책임 원칙) 잘 지켜짐
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // AppConfig에서 싱글톤 깨지는지 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
