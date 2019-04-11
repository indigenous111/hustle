package in.indigenous.hustle.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "upload_status")
public class UploadStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String module;

	@Column(name = "file_name")
	private String fileName;

	@Column
	private LocalDateTime uploaded;

	@Column
	private LocalDateTime processed;

	@Column
	private String status;

	@Column
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
