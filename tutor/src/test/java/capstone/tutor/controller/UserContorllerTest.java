package capstone.tutor.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserContorllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 회원가입(){
        String userName = "userName";
        String password = "password";

    }
}
