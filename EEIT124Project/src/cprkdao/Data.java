package cprkdao;

public class Data {
	private int Memberid;
	private String Expirationdate;
	private String Coupontype;
	private String Couponnum;
	
	
	public int getMemberid() {
		return Memberid;
	}
	public void setMemberid(int memberid) {
		Memberid = memberid;
	}
	public String getExpirationdate() {
		return Expirationdate;
	}
	public void setExpirationdate(String expirationdate) {
		Expirationdate = expirationdate;
	}
	public String getCoupontype() {
		return Coupontype;
	}
	public void setCoupontype(String coupontype) {
		Coupontype = coupontype;
	}
	public String getCouponnum() {
		return Couponnum;
	}
	public void setCouponnum(String couponnum) {
		Couponnum = couponnum;
	}
}
