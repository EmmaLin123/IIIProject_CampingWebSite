package cprkdao;

import java.util.List;

public interface DataDAO {
	List<Data> listData();
	void updateData(Data data);
	
	void insertData(Data data);
	
	void deleteCoupon(Data data);
	
	void selectCoupontype(Data data);
	
	void selectCouponnum(Data data);
	
	void selectMemberid(Data data);
}
