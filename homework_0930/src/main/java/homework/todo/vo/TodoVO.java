package homework.todo.vo;

public class TodoVO {
	/**
	 * todo 리스트의 pk값
	 */
	private int tdId;
	
	/**
	 * todo 리스트 내용
	 */
	private String tdCntnt;
	
	/**
	 * 기한
	 */
	private String tdDdDt;
	
	/**
	 * 추가 날짜
	 */
	private String tdCrtDt;
	
	/**
	 * 완료 여부(Y완료, N진행중)	
	 */
	private String tdCmplt;

	public int getTdId() {
		return tdId;
	}

	public void setTdId(int tdId) {
		this.tdId = tdId;
	}

	public String getTdCntnt() {
		return tdCntnt;
	}

	public void setTdCntnt(String tdCntnt) {
		this.tdCntnt = tdCntnt;
	}

	public String getTdDdDt() {
		return tdDdDt;
	}

	public void setTdDdDt(String tdDdDt) {
		this.tdDdDt = tdDdDt;
	}

	public String getTdCrtDt() {
		return tdCrtDt;
	}

	public void setTdCrtDt(String tdCrtDt) {
		this.tdCrtDt = tdCrtDt;
	}

	public String getTdCmplt() {
		return tdCmplt;
	}

	public void setTdCmplt(String tdCmplt) {
		this.tdCmplt = tdCmplt;
	}
	
	
}
