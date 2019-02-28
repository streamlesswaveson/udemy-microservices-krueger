package udemy.lab6sentenceserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import udemy.lab6sentenceserver.feign.*;

@Slf4j
@Service
@Primary
public class SentenceServiceWithFeignImpl implements SentenceService{

    private final WordService wordService;

    public SentenceServiceWithFeignImpl(WordService wordService) {
        this.wordService = wordService;
    }

    @Override
    public String buildSentence() {
//        String sentence = "There was a problem assembling the sentence!";

        StringBuilder builder = new StringBuilder();
        builder.append(wordService.getSubject().getString()).append(" ");
        builder.append(wordService.getVerb().getString()).append(" ");
        builder.append(wordService.getArticle().getString()).append(" ");
        builder.append(wordService.getAdjective().getString()).append(" ");
        builder.append(wordService.getNoun().getString());
        return builder.toString();

    }
}
