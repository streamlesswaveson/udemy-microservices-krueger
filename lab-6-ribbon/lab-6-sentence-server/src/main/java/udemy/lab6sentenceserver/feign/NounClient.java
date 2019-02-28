package udemy.lab6sentenceserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import udemy.lab6sentenceserver.model.Word;

@FeignClient(name = "NOUN")
public interface NounClient {

    @GetMapping("/")
    Word getWord();
}
