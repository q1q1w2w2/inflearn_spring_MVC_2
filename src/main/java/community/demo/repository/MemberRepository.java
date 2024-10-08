package community.demo.repository;

import community.demo.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findByLoginId(String loginId){
        List<Member> all = findAll();
        for (Member member : all) {
            if (member.getLoginId().equals(loginId)) {
                return Optional.of(member);
            }
        }

        return Optional.empty();
    }

}
