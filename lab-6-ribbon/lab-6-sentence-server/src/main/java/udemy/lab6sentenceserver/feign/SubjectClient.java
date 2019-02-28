package udemy.lab6sentenceserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import udemy.lab6sentenceserver.model.Word;

@FeignClient("SUBJECT")
public interface SubjectClient {
    @GetMapping("/")
    Word getWord();
}
