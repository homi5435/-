package capstone.tutor.board.domain;

import capstone.tutor.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Entity
@ToString
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    private String registerDate;

    public Board(String title, String content, Member member, LocalDateTime registerDate){
        this.title = title;
        this.content = content;
        this.member = member;
        this.registerDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(registerDate);
    }
    //==생성 메서드==//
    public static Board createBoard(String title, String content, Member member) {
        return Board.builder()
                .title(title)
                .content(content)
                .member(member)
                .registerDate(String.valueOf(LocalDateTime.now()))
                .build();
    }
    //==비즈니스 메서드==//
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
