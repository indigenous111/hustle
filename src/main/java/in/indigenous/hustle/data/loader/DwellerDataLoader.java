package in.indigenous.hustle.data.loader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import in.indigenous.hustle.entities.Dweller;
import in.indigenous.hustle.entities.UploadStatus;
import in.indigenous.hustle.repository.DwellerRepository;
import in.indigenous.hustle.repository.UploadStatusRepository;

@Component
public class DwellerDataLoader implements DataLoader {

	@Autowired
	private DwellerRepository dwellerRepository;

	@Autowired
	private UploadStatusRepository uploadStatusRepository;

	@Value("${file.upload.dir}")
	private String uploadDir;

	@Value("${file.processed.dir}")
	private String processedDir;

	@Override
	public void load(Map<Object, List<Object>> data, UploadStatus status) throws Exception {
		List<Object> names = data.get("Name");
		List<Dweller> dwellers = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(names)) {
			names.forEach(name -> {
				Dweller dweller = dwellerRepository.findByName(String.valueOf(name));
				if (dweller == null) {
					dweller = new Dweller();
				}
				dweller.setName(String.valueOf(name));
				dwellers.add(dweller);
			});
		}
		if (CollectionUtils.isNotEmpty(dwellers)) {
			dwellerRepository.saveAll(dwellers);
			status.setStatus(in.indigenous.hustle.constants.UploadStatus.PROCESSED.name());
			status.setProcessed(LocalDateTime.now());
			uploadStatusRepository.save(status);
			String uploadedFile = uploadDir + File.separator + status.getModule() + File.separator
					+ status.getFileName();
			String processdir = processedDir + File.separator + status.getModule();
			File renameDir = new File(processdir);
			if (!renameDir.exists()) {
				renameDir.mkdirs();
			}
			String renameFile = processdir + File.separator + status.getFileName();
			File original = new File(uploadedFile);
			File newFile = new File(renameFile);
			try {
				FileUtils.copyFile(original, newFile);
				original.delete();
			} catch (IOException e) {
				// TODO - Log
				throw new Exception(e);
			}
		}
	}
}
