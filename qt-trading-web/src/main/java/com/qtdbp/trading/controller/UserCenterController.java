package com.qtdbp.trading.controller;

import com.qtdbp.trading.constants.SysRoleContants;
import com.qtdbp.trading.exception.GlobalException;
import com.qtdbp.trading.service.FdfsFileService;
import com.qtdbp.trading.service.security.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 个人中心Controller
 *
 * Created by dell on 2017/4/17.
 */
@Secured({SysRoleContants.ROLE_USER})
@Controller
public class UserCenterController extends BaseController {

    @Autowired
    private FdfsFileService fileService ;

    /**
     * 数据商城首页
     */
    private static final String PAGE_USER_CENTER = "usercenter/index" ;

    /**
     * 个人中心首页
     * @return
     */
    @RequestMapping(value = "/usercenter",method = RequestMethod.GET)
    public ModelAndView index() {

        ModelAndView result = new ModelAndView(PAGE_USER_CENTER);

        return result ;
    }

    /**
     * 下载文件
     * @param orderNo
     * @return
     * @throws GlobalException
     */
    @RequestMapping(value = "/download/{no}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> fileDownload(@PathVariable("no") String orderNo) throws GlobalException {

        SysUser user = getPrincipal() ;

        if(user == null) throw new GlobalException("授权过期，请重新登陆") ;

        ResponseEntity<byte[]> file ;
        try {
            file = fileService.downloadFile(orderNo, user.getId()) ;
        } catch (Exception e) {
            throw new GlobalException("下载出错："+e.getMessage()) ;
        }

        return file ;
    }
}
