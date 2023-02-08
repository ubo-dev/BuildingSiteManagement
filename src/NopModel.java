import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class NopModel implements ModelInterface {

	@Override
	public ResultSet select(Map<String, Object> whereParameters) throws Exception {
		return null;
	}

	@Override
	public int insert(String fieldNames, List<Object> rows) throws Exception {
		return 0;
	}

	@Override
	public int update(Map<String, Object> updateParameters, Map<String, Object> whereParameters) throws Exception {
		return 0;
	}

	@Override
	public int delete(Map<String, Object> whereParameters) throws Exception {
		return 0;
	}

	@Override
	public String toString() {
		return "No Operation Model";
	}
}
