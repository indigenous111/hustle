package in.indigenous.hustle.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.indigenous.hustle.entities.UploadStatus;
import in.indigenous.hustle.repository.UploadStatusRepository;

@Controller
public class FileUploadController {

	@Value("${file.upload.dir}")
	private String uploadDir;
	
	@Autowired
	private UploadStatusRepository uploadStatusRepository;

	@RequestMapping(value = "/{path}/upload", method = RequestMethod.POST)
	public String upload(@PathVariable final String path, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				File dir = new File(uploadDir + File.separator + path);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				String fileName = getFileName(path) + ".xlsx";
				File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				UploadStatus status = new UploadStatus();
				status.setFileName(fileName);
				status.setModule(path);
				status.setStatus(in.indigenous.hustle.constants.UploadStatus.NEW.name());
				status.setUploaded(LocalDateTime.now());
				uploadStatusRepository.save(status);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "home";
	}

	private String getFileName(final String name) {
		StringJoiner builder = new StringJoiner("_");
		builder.add(name);
		Date date = new Date();
		builder.add(String.valueOf(date.getTime()));
		return builder.toString();
	}
}
