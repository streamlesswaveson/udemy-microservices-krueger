package udemy.lab6sentenceserver;

import org.springframework.stereotype.Component;

@Component("nounService")
public class NounDaoImpl extends WordDaoImpl {

	@Override
	public String getPartOfSpeech() {
		return NOUN;
	}

	
}
