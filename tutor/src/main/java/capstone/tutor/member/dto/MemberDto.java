package capstone.tutor.member.dto;

import capstone.tutor.member.domain.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    @Schema(description = "회원 ID")
    private Long id;

    @NotBlank(message = "이메일은 필수 항목 입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 항목 입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 최소 8자, 최대 16자를 입력해주세요")
    private String password;

    @NotBlank(message = "이름을 입력해주세요")
    private String realName;


    @NotBlank(message = "닉네임을 입력해주세요")
    @Size(message = "닉네임은 2글자 이상, 10글자 이하입니다.", min = 2, max = 10)
    private String nickname;

    @NotBlank(message = "카카오톡 오픈채팅방 주소를 입력해주세요")
    private String kakaoOpen;

    @NotBlank(message = "생년월일을 입력해주세요")
    private java.sql.Date birthDate;

    @NotBlank(message = "주소를 입력해주세요")
    private String address;

    @NotBlank(message = "성별을 선택해주세요")
    private String gender;

    @NotBlank(message = "성향을 2개 이상 선택해주세요")
    private String tendency;

}
