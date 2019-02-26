package udemy.lab6sentenceserver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    String word;

    public String getString() {
        return getWord();
    }
}
