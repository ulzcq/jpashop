package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /** 회원가입 */
    @Transactional
    public Long join(Member member){

        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    } //실무에서는 멀티스레드 상황(똑같은 이름이 동시에 insert)을 고려해서 DB에 member에 name을 unique 제약조건으로 잡는다.

    /** 회원 전체 조회 */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /** 회원 조회 */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id); //영속성 컨텍스트에서 가져온다
        member.setName(name); //값을 수정하면 트랜잭션이 끝나고 커밋되는 시점에서 JPA가 변경감지 -> update 쿼리를 DB에 날림
    }
}
