package udemy.lab5sentenceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class Lab5SentenceRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab5SentenceRibbonApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RestController
	public static class SentenceController {
		private final DiscoveryClient discoveryClient;
		private final RestTemplate restTemplate;

		public SentenceController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
			this.discoveryClient = discoveryClient;
			this.restTemplate = restTemplate;
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
			return restTemplate.getForObject("http://" + service, String.class);
		}

		public String _getWord(String service) {
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
