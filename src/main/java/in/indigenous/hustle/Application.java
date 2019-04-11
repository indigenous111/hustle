package in.indigenous.hustle;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import in.indigenous.hustle.config.DatabaseStarter;
import in.indigenous.hustle.data.loader.DataLoader;
import in.indigenous.hustle.data.loader.DwellerDataLoader;
import in.indigenous.utils.io.FileReader;
import in.indigenous.utils.io.excel.XLSXFileReader;
import liquibase.integration.spring.SpringLiquibase;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages= {"in.indigenous.hustle.repository"})
@ComponentScan(basePackages = { "in.indigenous.hustle" })
@SpringBootApplication
@EnableScheduling
public class Application {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		context.getBean(DatabaseStarter.class).start();
	}

	@Bean
	public Map<String, List<String>> getCompMap() {
		Map<String, List<String>> compMap = new HashMap<>();
		List<String> pages = new ArrayList<>();
		pages.add("home");
		compMap.put("pages", pages);
		return compMap;
	}

	@Bean
	public CommonsMultipartResolver getMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSizePerFile(1000000);
		return resolver;
	}

	//@Bean
	public SpringLiquibase liquibase() throws Exception {
		SpringLiquibase liquibase = new SpringLiquibase();
		liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
		liquibase.setDataSource(dataSource());
		return liquibase;
	}

	@Bean
	public DataSource dataSource() throws Exception {
		BasicDataSourceFactory factory = new BasicDataSourceFactory();
		Properties props = new Properties();
		props.load(new InputStreamReader(new FileInputStream(new File("./src/main/resources/liquibase.properties"))));
		return factory.createDataSource(props);
	}
	
	@Bean
	public Map<String, String> filePathMapping() {
		Map<String, String> filePathMapping = new HashMap<>();
		filePathMapping.put("dwellers", "src/main/resources/downloads/dwellers");
		return filePathMapping;
	}
	
	@Bean
	public FileReader xlsxFileReader() {
		return new XLSXFileReader();
	}
	
}
