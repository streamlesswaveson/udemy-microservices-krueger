package udemy.lab6sentenceserver.service;

import udemy.lab6sentenceserver.model.Word;

public interface WordService {
    Word getSubject();
    Word getVerb();
    Word getArticle();
    Word getAdjective();
    Word getNoun();
}
