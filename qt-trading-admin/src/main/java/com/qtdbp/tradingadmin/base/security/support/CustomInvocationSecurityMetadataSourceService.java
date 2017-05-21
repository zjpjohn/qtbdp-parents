package com.qtdbp.tradingadmin.base.security.support;

import com.qtdbp.tradingadmin.base.security.SysConstant;
import com.qtdbp.tradingadmin.base.security.service.SysResourceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 类功能说明：通过数据库来管理资源，通过数据获取到资源权限列表
 * 
 * <p>Copyright: Copyright © 2012-2013 zrhis.com Inc.</p>
 * @author caidchen
 * @version v1.0
 *
 */
@Component
public class CustomInvocationSecurityMetadataSourceService implements
		FilterInvocationSecurityMetadataSource,InitializingBean {

	protected final Log logger = LogFactory.getLog(getClass());
	
	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections.emptyList();
	
	//private MatcherType requestMatcher = MatcherType.ant; 

	//权限集合
	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	@Autowired
	private SysResourceService resourceService ;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        
        Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;

		if(requestMap != null && !requestMap.isEmpty()) {
			for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
				if (entry.getKey().matches(request)) {
					attrs = entry.getValue();
					break;
				}
			}
		}

        logger.info("URL资源："+request.getRequestURI()+ " -> " + attrs);

        return attrs;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	/**
	 * 加载系统权限资源
	 * @return
	 */
	private Map<String,String> loadResuorce(){

		Map<String,String> map = new LinkedHashMap<String,String>();
		
		List<Map<String,String>> list = this.resourceService.findAllResourceMapping(null);
		Iterator<Map<String,String>> it = list.iterator();
		while(it.hasNext()){
			Map<String,String> rs = it.next();
			String resourcePath = rs.get(SysConstant.RESOURCE_KEY);
			String authorityMark = rs.get(SysConstant.AUTH_KEY);
			
			if(map.containsKey(resourcePath)){
				String mark = map.get(resourcePath);
				map.put(resourcePath, mark+","+authorityMark);
			}else{
				map.put(resourcePath, authorityMark);
			}
		}
		return map;
	}
	
	protected Map<RequestMatcher, Collection<ConfigAttribute>> bindRequestMap(){
		Map<RequestMatcher, Collection<ConfigAttribute>> map = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		
		Map<String,String> resMap = this.loadResuorce();
		for(Map.Entry<String,String> entry:resMap.entrySet()){
			String key = entry.getKey();
			Collection<ConfigAttribute> atts = SecurityConfig.createListFromCommaDelimitedString(entry.getValue());
			if(atts != null) map.put(new AntPathRequestMatcher(key), atts);
		}
		
		return map;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.requestMap = this.bindRequestMap();
		logger.info("资源权限列表"+this.requestMap);
	}
	
	/*public void refreshResuorceMap(){
		this.requestMap = this.bindRequestMap();
	}*/

	/*public void setRequestMatcher(MatcherType requestMatcher) {
		this.requestMatcher = requestMatcher;
	}*/

}
