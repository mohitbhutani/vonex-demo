package demo.vonex.services;

import demo.vonex.AnswerDTO;
import demo.vonex.domain.Answer;
import demo.vonex.domain.InitialRequest;
import demo.vonex.domain.Token;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import static org.junit.Assert.*;

/**
 * Created by mohit bhutani on 26/10/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeServiceTest {

    @Autowired
    private HomeService homeService;
    @Test
    public void testGetInitialRequest() throws Exception {
        InitialRequest initialRequest = homeService.getInitialRequest();
        assertTrue(!ObjectUtils.isEmpty(initialRequest) && initialRequest.getRequest() !=null);
    }

    @Test
    public void testGetToken() throws Exception {
        InitialRequest initialRequest = homeService.getInitialRequest();
        assertTrue(!ObjectUtils.isEmpty(initialRequest) && initialRequest.getRequest() !=null);
        Token token = homeService.getToken(initialRequest);
        assertTrue("Answer was received",!ObjectUtils.isEmpty(token) && token.getAnswer() !=null);
    }

    @Test
    public void testGetAnswer() throws Exception {
        InitialRequest initialRequest = homeService.getInitialRequest();
        assertTrue(!ObjectUtils.isEmpty(initialRequest) && initialRequest.getRequest() !=null);
        Token token = homeService.getToken(initialRequest);
        assertTrue("Answer was received",!ObjectUtils.isEmpty(token) && token.getAnswer() !=null);
        Answer answer = homeService.getAnswer(token);
        assertTrue("Answer was received",!ObjectUtils.isEmpty(answer) && answer.getAnswer() !=null);
    }

    @Test
    public void testGetAnswerDTO() throws Exception {
        InitialRequest initialRequest = homeService.getInitialRequest();
        assertTrue(!ObjectUtils.isEmpty(initialRequest) && initialRequest.getRequest() !=null);
        AnswerDTO answer = homeService.getAnswerDTO(initialRequest);
        assertTrue("Answer was received",!ObjectUtils.isEmpty(answer) && answer.getStatus().equals("SUCCESS"));
    }
}