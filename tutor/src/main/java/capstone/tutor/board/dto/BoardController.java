package capstone.tutor.board.dto;

import capstone.tutor.board.domain.Board;
import capstone.tutor.board.domain.PostForm;
import capstone.tutor.member.domain.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.SessionConfig;
import org.apache.kafka.clients.consumer.StickyAssignor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public String postList(Model model,
                           @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 5) Pageable pageable) {
        model.addAttribute("resultMap", boardService.findAll(pageable));
        return "board/postList";
    }
    @GetMapping("/{postId}")
    public String postView(@PathVariable Long postId, Model model){
        log.info("postView");

        Board post = boardService.findOne(postId).orElseThrow();
        model.addAttribute("post",post);

        return "board/post";
    }
    @GetMapping("/register")
    public String registerForm(@ModelAttribute PostForm postForm){
        return "board/registerForm";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute PostForm postForm,
                           BindingResult bindingResult,
                           @SessionAttribute(name = "user_id", required = false)Member loginMember){
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "board/registerForm";
        }
        boardService.register(postForm.getTitle(), postForm.getContent(), loginMember.getId());
        return "redirect:/board";
    }
    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {

        Board post = boardService.findOne(postId).orElseThrow();

        PostForm postForm = new PostForm();
        postForm.setTitle(post.getTitle());
        postForm.setContent(post.getContent());

        model.addAttribute("postForm", postForm);
        model.addAttribute("postId", postId);

        return "board/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @Valid @ModelAttribute PostForm postForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors = {}", bindingResult);
            return "board/editForm";
        }

        boardService.updateBoard(postId, postForm.getTitle(), postForm.getContent());

        return "redirect:/board/{postId}";
    }

    @PostMapping("/{postId}/delete")
    public String delete(@PathVariable Long postId) {
        boardService.deleteById(postId);
        return "redirect:/board";
    }

}
