package capstone.tutor.member;

import capstone.tutor.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);     // 이메일로 사용자 계정 단건 조회, @param email 조회할 대상 계정의 이메일
}
