package com.alwan.dansTest.jobsApps;

import com.alwan.dansTest.jobsApps.exception.JobsAppsExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JobsAppsExceptionHandler.class})
public class ApplicationStartup {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStartup.class, args);
	}

}
