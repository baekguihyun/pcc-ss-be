package org.promisepeople.ss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan("org.promisepeople.ss.fthchck.domain")
@SpringBootApplication
public class FthCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(FthCheckApplication.class, args);
	}

}
