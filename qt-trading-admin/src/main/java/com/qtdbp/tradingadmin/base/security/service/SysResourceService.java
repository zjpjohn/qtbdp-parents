/**
 * 
 */
package com.qtdbp.tradingadmin.base.security.service;

import com.qtdbp.tradingadmin.base.security.SysConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

/**
 * 类功能说明：系统资源Service
 * 
 * @author caidchen
 * @version v1.0
 *
 */
@Service
public class SysResourceService {

	protected Log logger = LogFactory.getLog(getClass());
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * 获取所有资源
	 * @param type 资源类型 URL、METHOD
	 * @return
	 */
	public List<Map<String,String>> findAllResourceMapping(String type){

		StringBuilder sql = new StringBuilder() ;
		sql.append("SELECT S3.RESOURCE_PATH,S2.AUTHORITY_MARK FROM SYS_AUTHORITIES_RESOURCES S1 "+
				"JOIN SYS_AUTHORITIES S2 ON S1.AUTHORITY_ID = S2.AUTHORITY_ID "+
				"JOIN SYS_RESOURCES S3 ON S1.RESOURCE_ID = S3.RESOURCE_ID") ;
		if(type != null) sql.append(" AND S3.RESOURCE_TYPE='"+type+"'") ;
		sql.append(" ORDER BY S3.PRIORITY DESC") ;

		List<Map<String,String>> list = new ArrayList<Map<String,String>>();

		Query query = this.entityManager.createNativeQuery(sql.toString());
		List<Object[]> result = query.getResultList();
		Iterator<Object[]> it = result.iterator();

		while(it.hasNext()){
			Object[] o = it.next();
			Map<String,String> map = new HashMap<String,String>();
			map.put(SysConstant.RESOURCE_KEY, (String)o[0]);
			map.put(SysConstant.AUTH_KEY, (String)o[1]);
			list.add(map);
		}

		return list;
	}

	/**
	 * 获取URL资源
	 * @return
	 */
	public List<Map<String,String>> getURLResourceMapping(){
		String sql = "SELECT S3.RESOURCE_PATH,S2.AUTHORITY_MARK FROM SYS_AUTHORITIES_RESOURCES S1 "+
				"JOIN SYS_AUTHORITIES S2 ON S1.AUTHORITY_ID = S2.AUTHORITY_ID "+
				"JOIN SYS_RESOURCES S3 ON S1.RESOURCE_ID = S3.RESOURCE_ID AND S3.RESOURCE_TYPE='URL' ORDER BY S3.PRIORITY DESC";
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		Query query = this.entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		Iterator<Object[]> it = result.iterator();
		
		while(it.hasNext()){
			Object[] o = it.next();
			Map<String,String> map = new HashMap<String,String>();
			map.put("resourcePath", (String)o[0]);
			map.put("authorityMark", (String)o[1]);
			list.add(map);
		}
		
		return list;
	}

	/**
	 * 获取方法资源
	 * @return
	 */
	public List<Map<String,String>> getMethodResourceMapping(){
		String sql = "SELECT S3.RESOURCE_PATH,S2.AUTHORITY_MARK FROM SYS_AUTHORITIES_RESOURCES S1 "+
				"JOIN SYS_AUTHORITIES S2 ON S1.AUTHORITY_ID = S2.AUTHORITY_ID "+
				"JOIN SYS_RESOURCES S3 ON S1.RESOURCE_ID = S3.RESOURCE_ID AND S3.RESOURCE_TYPE='METHOD' ORDER BY S3.PRIORITY DESC";
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		Query query = this.entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		Iterator<Object[]> it = result.iterator();
		
		while(it.hasNext()){
			Object[] o = it.next();
			Map<String,String> map = new HashMap<String,String>();
			map.put("resourcePath", (String)o[0]);
			map.put("authorityMark", (String)o[1]);
			list.add(map);
		}
		
		return list;
	}
	
}
