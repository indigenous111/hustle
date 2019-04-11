package in.indigenous.hustle.models;

import java.time.LocalDateTime;

public class UploadStatus {

	private Long id;

	private String module;

	private String fileName;

	private String stauts;

	private LocalDateTime uploaded;

	private LocalDateTime processed;

	private String reason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStauts() {
		return stauts;
	}

	public void setStauts(String stauts) {
		this.stauts = stauts;
	}

	public LocalDateTime getUploaded() {
		return uploaded;
	}

	public void setUploaded(LocalDateTime uploaded) {
		this.uploaded = uploaded;
	}

	public LocalDateTime getProcessed() {
		return processed;
	}

	public void setProcessed(LocalDateTime processed) {
		this.processed = processed;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
