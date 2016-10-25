package demo.vonex.services;

import demo.vonex.AnswerDTO;
import demo.vonex.domain.Answer;
import demo.vonex.domain.InitialRequest;
import demo.vonex.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mohit bhutani on 25/10/16.
 */
@Service
public class HomeService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${spring.api.retryLimit}")
    byte retryLimit;

    public InitialRequest getInitialRequest(){
        ResponseEntity<InitialRequest> response = restTemplate.getForEntity("https://test.vonex.com.au/api/initial-request",
                InitialRequest.class);
        if (HttpStatus.OK == response.getStatusCode()) {
            System.out.println(response);
        }

        return response.getBody();
    }

    public Token getToken (InitialRequest initialRequest){
        ResponseEntity<Token> response = new ResponseEntity<Token>(HttpStatus.OK);
        byte count = 0;
            do {
                count++;
                String url = "https://test.vonex.com.au/api/ask";
                MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
                params.add("request", initialRequest.getRequest());
                UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();
                response = restTemplate.getForEntity(uriComponents.toUri().toString(),
                        Token.class, params);
                System.out.println(response.getStatusCode());
            }
            while ((ObjectUtils.isEmpty(response) || ObjectUtils.isEmpty(response.getBody().getAnswer())) && count<=retryLimit);

        return response.getBody();
    }

    public Answer getAnswer(Token token){
        ResponseEntity<Answer> response = new ResponseEntity<>(HttpStatus.OK);
        if(!ObjectUtils.isEmpty(token.getAnswer())){
            String url = "https://test.vonex.com.au/api/answer";
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("request", token.getAnswer());
            UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();
            response = restTemplate.getForEntity(uriComponents.toUriString(),Answer.class);
        }
        return response.getBody();
    }

    public AnswerDTO getAnswerDTO(InitialRequest initialRequest){
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setStatus("ERROR");
        if(!ObjectUtils.isEmpty(initialRequest)){
            Token token = getToken(initialRequest);
            if(!ObjectUtils.isEmpty(token.getAnswer())){
                answerDTO.setAnswer(getAnswer(token).getAnswer());
                answerDTO.setStatus("SUCCESS");
            }
        }
        return answerDTO;
    }
}
