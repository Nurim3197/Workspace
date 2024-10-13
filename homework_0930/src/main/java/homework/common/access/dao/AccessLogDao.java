package homework.common.access.dao;

import homework.common.access.vo.AccessLogVO;

public interface AccessLogDao {
	
	public String NAMESPACE = "homework.common.access.dao.AccessLogDao";
	
	public int insertNewAccessLog(AccessLogVO accessLogVO);
	
	public int selectLoginFailCount(String ip);
	
}
