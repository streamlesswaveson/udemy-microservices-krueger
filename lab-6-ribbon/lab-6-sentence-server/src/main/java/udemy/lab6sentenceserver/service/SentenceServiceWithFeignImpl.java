package udemy.lab6sentenceserver.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import udemy.lab6sentenceserver.feign.*;

@Slf4j
@Service
@Primary
public class SentenceServiceWithFeignImpl implements SentenceService{
    private final SubjectClient subjectClient;
    private final VerbClient verbClient;
    private final ArticleClient articleClient;
    private final AdjectiveClient adjectiveClient;
    private final NounClient nounClient;

    public SentenceServiceWithFeignImpl(SubjectClient subjectClient, VerbClient verbClient, ArticleClient articleClient,
                                        AdjectiveClient adjectiveClient, NounClient nounClient) {
        this.subjectClient = subjectClient;
        this.verbClient = verbClient;
        this.articleClient = articleClient;
        this.adjectiveClient = adjectiveClient;
        this.nounClient = nounClient;
    }

    @Override
    public String buildSentence() {
//        String sentence = "There was a problem assembling the sentence!";

        StringBuilder builder = new StringBuilder();
        builder.append(subjectClient.getWord().getString()).append(" ");
        builder.append(verbClient.getWord().getString()).append(" ");
        builder.append(articleClient.getWord().getString()).append(" ");
        builder.append(adjectiveClient.getWord().getString()).append(" ");
        builder.append(nounClient.getWord().getString());
        return builder.toString();

    }
}
