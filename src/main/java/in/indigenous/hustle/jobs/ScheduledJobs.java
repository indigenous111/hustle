package in.indigenous.hustle.jobs;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import in.indigenous.hustle.data.loader.DataLoader;
import in.indigenous.hustle.data.loader.DwellerDataLoader;
import in.indigenous.hustle.entities.UploadStatus;
import in.indigenous.hustle.mapper.UploadStatusMapper;
import in.indigenous.hustle.repository.UploadStatusRepository;
import in.indigenous.utils.io.FileReader;

@Component
public class ScheduledJobs {

	private static final Logger LOGGER = LogManager.getLogger(ScheduledJobs.class);

	@Autowired
	private UploadStatusMapper mapper;

	@Autowired
	private UploadStatusRepository uploadStatusRepository;

	@Autowired
	private FileReader xlsxFileReader;

	@Value("${file.upload.dir}")
	private String uploadDir;

	@Value("${file.failed.dir}")
	private String failedDir;

	@Autowired
	private DwellerDataLoader dwellerDataLoader;

	private Map<String, List<DataLoader>> dataLoaderMap;

	@PostConstruct
	public void init() {
		dataLoaderMap = new HashMap<String, List<DataLoader>>();
		List<DataLoader> dwellerLoaders = new ArrayList<>();
		dwellerLoaders.add(dwellerDataLoader);
		dataLoaderMap.put("dwellers", dwellerLoaders);
	}

	@Scheduled(fixedDelay = 30000)
	public void checkNewUploadStatus() {
		List<UploadStatus> statuses = uploadStatusRepository.findAll();
		List<UploadStatus> concerned = statuses.stream().filter(status -> {
			return in.indigenous.hustle.constants.UploadStatus.NEW.name().equals(status.getStatus());
		}).collect(Collectors.toList());
		if (CollectionUtils.isNotEmpty(concerned)) {
			for (UploadStatus status : concerned) {
				String module = status.getModule();
				String uploaddir = uploadDir + File.separator + module;
				String fileName = status.getFileName();
				try {
					Map<Object, List<Object>> data = xlsxFileReader.read(uploaddir, fileName);
					dataLoaderMap.get(module).forEach(loader -> {
						try {
							loader.load(data, status);
						} catch (Exception e) {
							// TODO Log
							e.printStackTrace();
						}
					});
				} catch (Throwable t) {
					t.printStackTrace();
					status.setStatus(in.indigenous.hustle.constants.UploadStatus.FAILED.name());
					status.setReason(t.getMessage());
					status.setProcessed(LocalDateTime.now());
					uploadStatusRepository.save(status);
					File uploadFile = new File(uploaddir + File.separator + fileName);
					String faileddir = failedDir + File.separator + module;
					File renameDir = new File(faileddir);
					if (!renameDir.exists()) {
						renameDir.mkdirs();
					}
					File failedfile = new File(renameDir + File.separator + fileName);
					try {
						FileUtils.copyFile(uploadFile, failedfile);
						uploadFile.delete();
					} catch (IOException e) {
						// TODO - Log
						e.printStackTrace();
					}

				}
			}
		}
	}
}
