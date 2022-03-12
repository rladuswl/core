package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 생성자를 통해서 memberRepository에 무엇이 들어갈지 결정 -> AppConfig 에서 설정
    @Autowired // 컴포넌트 스캔을 사용하기 위해서는 자동 의존관계 주입 설정, ac.getBean(MemberRepository.class) 처럼 동작
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // AppConfig에서 싱글톤 깨지는지 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
