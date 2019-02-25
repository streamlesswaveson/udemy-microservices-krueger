package udemy.lab4wordserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class Lab4WordServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab4WordServerApplication.class, args);
    }


    @RestController
    public static class WordController {
        private final String words;

        public WordController(@Value("${words}") String words) {
            this.words = words;
        }

        @GetMapping("/")
        public @ResponseBody String getWord() {
            String[] wordArray = words.split(",");
            int i = (int)Math.round(Math.random() * (wordArray.length - 1));
            return wordArray[i];
        }
    }

    @RestController
    public static class SentenceController {
        private final DiscoveryClient discoveryClient;

        public SentenceController(DiscoveryClient discoveryClient) {
            this.discoveryClient = discoveryClient;
        }

        @GetMapping("/sentence")
        public @ResponseBody
        String getSentence() {
            return
                    getWord("LAB-4-SUBJECT") + " "
                            + getWord("LAB-4-VERB") + " "
                            + getWord("LAB-4-ARTICLE") + " "
                            + getWord("LAB-4-ADJECTIVE") + " "
                            + getWord("LAB-4-NOUN") + "."
                    ;
        }

        public String getWord(String service) {
            List<ServiceInstance> list = discoveryClient.getInstances(service);
            if (list != null && list.size() > 0) {
                URI uri = list.get(0).getUri();
                if (uri != null) {
                    return (new RestTemplate()).getForObject(uri, String.class);
                }
            }
            return null;
        }
    }

}
