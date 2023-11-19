package capstone.tutor.member.domain;

import capstone.tutor.image.Image;
import capstone.tutor.member.dto.MemberDto;
import capstone.tutor.utils.audit.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;       //이메일(아이디)

    private String realname;    //이름

    private String nickname;    //별명

    private String password;    //비밀번호

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_path")
    private Image userProfileImage;

    private String address;     //주소

    private String tendency;    //성향

    private Date birthDate;     //생년월일

    private String kakaoOpen;   //카카오톡 오픈채팅방 주소

    private String gender;

    @Enumerated(EnumType.STRING)
    private Role role;



    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setRealname(memberDto.getRealName());
        member.setEmail(memberDto.getEmail());
        String password = passwordEncoder.encode(memberDto.getPassword());
        member.setPassword(password);
        member.setAddress(memberDto.getAddress());
        member.setBirthDate(memberDto.getBirthDate());
        member.setKakaoOpen(memberDto.getKakaoOpen());
        member.setGender(memberDto.getGender());
        member.setTendency(memberDto.getTendency());
        member.setRole(Role.ROLE_USER);
        return member;
    }

}
