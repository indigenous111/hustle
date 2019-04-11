package in.indigenous.hustle.data.preparer;

import java.util.List;
import java.util.Map;

public interface DataPreparer<P> {

	List<P> prepareData(Map<Object, List<Object>> map);
}
