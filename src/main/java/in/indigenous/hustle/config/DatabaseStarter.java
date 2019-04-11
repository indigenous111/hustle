package in.indigenous.hustle.config;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseStarter {

	private static final Logger LOGGER = LogManager.getLogger(DatabaseStarter.class);

	@Autowired
	@Value("${application.db.path}")
	private String dbPath;

	public void start() {
		LOGGER.info("Starting DB ...");
		Runtime rt = Runtime.getRuntime();
		String dbCmd = dbPath + File.separator + "mysqld.exe";
		try {
			Process pr = rt.exec(dbCmd);
			int retVal = pr.waitFor();
			LOGGER.info("DB process exited with status: " + retVal);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
