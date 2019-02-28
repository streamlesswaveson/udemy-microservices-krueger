package udemy.lab6sentenceserver.dao;

import org.springframework.stereotype.Component;

@Component("articleService")
public class ArticleDaoImpl extends WordDaoImpl {

	@Override
	public String getPartOfSpeech() {
		return ARTICLE;
	}

	
}
