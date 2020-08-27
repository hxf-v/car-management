package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Dao.TemporaryRouteRegisterDao;
import cn.edu.ahu.carmanagement.Dao.UserInfoDao;
import cn.edu.ahu.carmanagement.Dao.WholeRouteRecordDao;
import cn.edu.ahu.carmanagement.Domain.UserInfo;
import cn.edu.ahu.carmanagement.Json.UserRequestJson;
import cn.edu.ahu.carmanagement.Repo.*;
import cn.edu.ahu.carmanagement.Utils.RequestParamCheck;
import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: FZC
 * @Despriction: 管理员系统管理Controller
 * @Date: Created in 16:48 2019/4/22
 */
@Controller
@RequestMapping(value = "/admin/system")
public class AdminSystemManagementController {

    private final UserInfoRepo userInfoRepo;
    private final SchoolCarRegisterRepo schoolCarRegisterRepo;
    private final TemporaryRouteRegisterRepo temporaryRouteRegisterRepo;
    private final TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo;
    private final TemporaryRouteRegisterDao temporaryRouteRegisterDao;
    private final WholeRouteRecordRepo wholeRouteRecordRepo;
    private final WholeRouteRecordDao wholeRouteRecordDao;
    private final UserInfoDao userInfoDao;

    /**
     * 依赖注入
     * @param userInfoRepo
     * @param schoolCarRegisterRepo
     * @param temporaryRouteRegisterRepo
     * @param temporaryRouteUnRegisterRepo
     * @param temporaryRouteRegisterDao
     * @param wholeRouteRecordRepo
     * @param wholeRouteRecordDao
     */
    public AdminSystemManagementController(UserInfoRepo userInfoRepo,
                                           SchoolCarRegisterRepo schoolCarRegisterRepo,
                                           TemporaryRouteRegisterRepo temporaryRouteRegisterRepo,
                                           TemporaryRouteUnRegisterRepo temporaryRouteUnRegisterRepo,
                                           TemporaryRouteRegisterDao temporaryRouteRegisterDao,
                                           WholeRouteRecordRepo wholeRouteRecordRepo,
                                           WholeRouteRecordDao wholeRouteRecordDao,
                                           UserInfoDao userInfoDao) {
        this.userInfoRepo = userInfoRepo;
        this.schoolCarRegisterRepo = schoolCarRegisterRepo;
        this.temporaryRouteRegisterRepo = temporaryRouteRegisterRepo;
        this.temporaryRouteUnRegisterRepo = temporaryRouteUnRegisterRepo;
        this.temporaryRouteRegisterDao = temporaryRouteRegisterDao;
        this.wholeRouteRecordRepo = wholeRouteRecordRepo;
        this.wholeRouteRecordDao = wholeRouteRecordDao;
        this.userInfoDao=userInfoDao;
    }

    /**
     * 校园车辆管理系统-管理员系统管理-页面展示获取所有用户表单
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Response user() {
        return new Response().success(userInfoDao.findAll());
    }

    /**
     * 校园车辆管理系统-管理员系统管理-根据账号查询用户
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/find", method = RequestMethod.POST)
    public Response userFind(@RequestBody UserRequestJson userRequestJson){

        String account = userRequestJson.getAccount();
        //数据校验
        RequestParamCheck.checkNull(account, "account");
        return new Response().success(userInfoDao.findLikeAccount(account));
    }

    /**
     * 校园车辆管理系统-管理员系统管理-添加用户
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public Response userAdd(@RequestBody UserRequestJson userRequestJson){

        String account = userRequestJson.getAccount();

        //数据校验
        RequestParamCheck.checkNull(account, "account");

        if (userInfoRepo.findByAccount(account) == null){
            userInfoRepo.save(userRequestJson.convertUser());
            return new Response().success();
        }else{
            return new Response().failure("账户已存在，请直接登录");
        }
    }

    /**
     * 校园车辆管理系统-管理员系统管理-编辑用户
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public Response userEdit(@RequestBody UserRequestJson userRequestJson){

        String account = userRequestJson.getAccount();
        //数据校验
        RequestParamCheck.checkNull(account, "account");

        if (userInfoRepo.findByAccount(account) != null){
            UserInfo userInfo = userInfoRepo.findByAccount(account);
            userInfoRepo.save(userRequestJson.convertEdit(userInfo));
            return new Response().success("编辑用户成功");
        }else{
            return new Response().failure("账户不存在，请查证");
        }
    }

    /**
     * 校园车辆管理系统-管理员系统管理-删除用户
     * @param userRequestJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public Response userDelete(@RequestBody UserRequestJson userRequestJson){

        String account = userRequestJson.getAccount();
        //数据校验
        RequestParamCheck.checkNull(account, "account");

        if (userInfoRepo.findByAccount(account) != null){
            UserInfo userInfo = userInfoRepo.findByAccount(account);
            userInfoRepo.save(userRequestJson.convertDelete(userInfo));
            return new Response().success("删除用户成功");
        }else{
            return new Response().failure("账户不存在，请查证");
        }
    }
}
