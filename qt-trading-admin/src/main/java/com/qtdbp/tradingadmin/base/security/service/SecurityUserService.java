/**
 * 
 */
package com.qtdbp.tradingadmin.base.security.service;

import com.qtdbp.tradingadmin.base.security.entity.SysAuthorities;
import com.qtdbp.tradingadmin.base.security.entity.SysUsers;
import com.qtdbp.tradingadmin.base.security.repository.SysUsersRepository;
import com.qtdbp.tradingadmin.base.security.utils.PageUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 类功能说明：系统用户Service
 * 
 * @author caidchen
 * @version v1.0
 *
 */
@Service
public class SecurityUserService {

	protected Log logger = LogFactory.getLog(getClass());

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private SysUsersRepository usersRepository ;

	/**
	 * 加载用户权限
	 * @param userName
	 * @return
	 */
	public Collection<GrantedAuthority> loadUserAuthorities(String userName) {

		List<SysAuthorities> list = this.getSysAuthoritiesByUsername(userName);

		List<GrantedAuthority> auths = null ;
		if(list != null && !list.isEmpty()) {
			auths = new ArrayList<>();
			for (SysAuthorities authority : list) {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthorityMark());
				auths.add(grantedAuthority);
			}
		}

		return auths;
	}

	/**
	 * 先根据用户名获取到SysAuthorities集合
	 * @param username
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<SysAuthorities> getSysAuthoritiesByUsername(String username) {

		String sql = "SELECT * FROM SYS_AUTHORITIES WHERE AUTHORITY_ID IN( "+
				"SELECT DISTINCT AUTHORITY_ID FROM SYS_ROLES_AUTHORITIES  S1 "+
				"JOIN SYS_USERS_ROLES S2 ON S1.ROLE_ID = S2.ROLE_ID "+
				"JOIN SYS_USERS S3 ON S3.USER_ID = S2.USER_ID AND S3.USERNAME=?1)";

		Query query = this.entityManager.createNativeQuery(sql, SysAuthorities.class);
		query.setParameter(1, username);

		return query.getResultList();
	}

	/**
	 * 更新用户
	 * @param users
	 * @return
	 */
	public int update(SysUsers users) {

		String sql = "UPDATE sys_users SET last_login=?, login_ip=? where user_id=?";

		Query query = this.entityManager.createNativeQuery(sql, SysUsers.class);
		query.setParameter(1, users.getLastLogin());
		query.setParameter(2, users.getLoginIp());
		query.setParameter(3, users.getUserId());

		return query.executeUpdate() ;
	}

	/**
	 * 分页查询用户
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<SysUsers> findUsersForPage(int pageNumber, int pageSize){

		PageRequest request = PageUtils.buildPageRequest(pageNumber,pageSize,null);

		Page<SysUsers> sourceCodes= this.usersRepository.findAll(request);

		return sourceCodes ;
	}
}
