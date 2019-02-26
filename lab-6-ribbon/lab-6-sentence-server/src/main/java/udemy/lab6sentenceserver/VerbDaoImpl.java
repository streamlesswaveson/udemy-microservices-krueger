package udemy.lab6sentenceserver;

import org.springframework.stereotype.Component;

@Component("verbService")
public class VerbDaoImpl extends WordDaoImpl {

	@Override
	public String getPartOfSpeech() {
		return VERB;
	}

	
}
