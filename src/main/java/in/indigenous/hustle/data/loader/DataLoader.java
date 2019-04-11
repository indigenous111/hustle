package in.indigenous.hustle.data.loader;

import java.util.List;
import java.util.Map;

import in.indigenous.hustle.entities.UploadStatus;

public interface DataLoader {

	void load(Map<Object, List<Object>> data, UploadStatus status) throws Exception;
}
