package comment;

import java.util.ArrayList;
import java.util.List;



public interface CommDataDao {

	List<CommData> list = new ArrayList<>();

	void insertData(CommData data);
	
}
