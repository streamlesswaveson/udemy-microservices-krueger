package udemy.luckywordservice.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LuckyWordApi {

    @Value("${lucky-word}")
    String luckyWord;

    @GetMapping("/lucky-word")
    public LuckyWord getLuckyWord() {
        return LuckyWord.builder()
                .luckyWord(luckyWord)
                .build();
    }

    @Data
    @Builder
    public static class LuckyWord {
        String luckyWord;
    }
}
