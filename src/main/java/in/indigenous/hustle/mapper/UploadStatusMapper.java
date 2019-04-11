package in.indigenous.hustle.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.indigenous.hustle.models.UploadStatus;

@Component
public class UploadStatusMapper {

	public UploadStatus toModel(in.indigenous.hustle.entities.UploadStatus entity) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(entity, UploadStatus.class);
	}

	public in.indigenous.hustle.entities.UploadStatus toEntity(UploadStatus model) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(model, in.indigenous.hustle.entities.UploadStatus.class);
	}
}
