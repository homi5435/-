package capstone.tutor.board.dto;

import capstone.tutor.board.domain.Board;
import capstone.tutor.member.MemberRepository;
import capstone.tutor.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //게시글 생성
    @Transactional
    public Long register(String title, String content, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        Board board = Board.createBoard(title, content, member);
        boardRepository.save(board);
        return board.getId();
    }
    /**
     * 게시판 전체 조회
     */
    public HashMap<String, Object> findAll(Pageable page) {
        HashMap<String, Object> listMap = new HashMap<>();
        Page<Board> list = boardRepository.findAll(page);

        listMap.put("list", list);
        listMap.put("paging", list.getPageable());
        listMap.put("totalCnt", list.getTotalElements());
        listMap.put("totalPage", list.getTotalPages());
        return listMap;
    }

    /**
     * 게시글 단건 조회
     */
    public Optional<Board> findOne(Long id) {
        return boardRepository.findById(id);
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public void updateBoard(Long id, String title, String content) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.update(title, content);
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }
}
