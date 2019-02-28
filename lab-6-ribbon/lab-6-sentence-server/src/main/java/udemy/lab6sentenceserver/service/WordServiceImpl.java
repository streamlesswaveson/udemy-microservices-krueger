package udemy.lab6sentenceserver.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import udemy.lab6sentenceserver.feign.*;
import udemy.lab6sentenceserver.model.Word;

@Service
public class WordServiceImpl implements WordService{
    private final SubjectClient subjectClient;
    private final VerbClient verbClient;
    private final ArticleClient articleClient;
    private final AdjectiveClient adjectiveClient;
    private final NounClient nounClient;

    public WordServiceImpl(SubjectClient subjectClient, VerbClient verbClient, ArticleClient articleClient, AdjectiveClient adjectiveClient, NounClient nounClient) {
        this.subjectClient = subjectClient;
        this.verbClient = verbClient;
        this.articleClient = articleClient;
        this.adjectiveClient = adjectiveClient;
        this.nounClient = nounClient;
    }

    @Override
    public Word getSubject() {
        return subjectClient.getWord();
    }

    @Override
    public Word getVerb() {
        return verbClient.getWord();
    }

    @Override
    public Word getArticle() {
        return articleClient.getWord();
    }

    @Override
    public Word getAdjective() {
        return adjectiveClient.getWord();
    }

    @Override
    @HystrixCommand(fallbackMethod="getFallbackNoun")
    public Word getNoun() {
        return nounClient.getWord();
    }

    public Word getFallbackNoun() {
        return Word.builder()
                .word("fallback").build();
    }
}
