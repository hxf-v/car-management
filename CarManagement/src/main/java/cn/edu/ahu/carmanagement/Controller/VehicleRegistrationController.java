package cn.edu.ahu.carmanagement.Controller;

import cn.edu.ahu.carmanagement.Domain.CarRegister;
import cn.edu.ahu.carmanagement.Json.CarRegisterJson;
import cn.edu.ahu.carmanagement.Repo.AhuStudentRepo;
import cn.edu.ahu.carmanagement.Repo.AhuTeacherRepo;
import cn.edu.ahu.carmanagement.Repo.CarRegisterRepo;
import cn.edu.ahu.carmanagement.Utils.RequestParamCheck;
import cn.edu.ahu.carmanagement.Utils.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: FZC
 * @Despriction: 校园车辆管理系统-车辆注册管理
 * @Date: Created in 16:48 2019/4/22
 */
@Controller
@RequestMapping(value = "/register")
public class VehicleRegistrationController {

    private final CarRegisterRepo carRegisterRepo;
    private final AhuTeacherRepo ahuTeacherRepo;
    private final AhuStudentRepo ahuStudentRepo;

    /**
     * 依赖注入
     * @param carRegisterRepo 已注册车辆接口
     * @param ahuTeacherRepo
     * @param ahuStudentRepo
     */
    public VehicleRegistrationController(
            CarRegisterRepo carRegisterRepo,
            AhuTeacherRepo ahuTeacherRepo,
            AhuStudentRepo ahuStudentRepo) {
        this.carRegisterRepo = carRegisterRepo;
        this.ahuTeacherRepo = ahuTeacherRepo;
        this.ahuStudentRepo = ahuStudentRepo;
    }

    /**
     * 校园车辆管理系统-车辆注册管理-获取所有在册车辆信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Response get(Integer pageNumber,Integer pageSize){
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return new Response().success(carRegisterRepo.findByState(pageable,"启用"));
    }

    /**
     * 校园车辆管理系统-车辆注册管理-分部门获取所有在册车辆信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/department", method = RequestMethod.GET)
    public Response getDepartment(@RequestBody CarRegisterJson carRegisterJson){
        String department = carRegisterJson.getDepartment();
        Integer pageNumber = carRegisterJson.getPageNumber();
        Integer pageSize = carRegisterJson.getPageSize();
        //数据校验
        RequestParamCheck.checkNull(pageNumber, "pageNumber");
        RequestParamCheck.checkNull(pageSize, "pageSize");
        //pageable
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return new Response().success(carRegisterRepo.findByDepartmentAndState(pageable,department,"启用"));
    }

    /**
     * 校园车辆管理系统-车辆注册管理-教师学生身份验证,获取个人信息
     * @param carRegisterJson
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add/verification", method = RequestMethod.POST)
    public Response registerVerification(@RequestBody CarRegisterJson carRegisterJson){

        String sf = carRegisterJson.getSf();        //身份（教师或学生）
        String xgh = carRegisterJson.getXgh();      //学工号
        String idNum = carRegisterJson.getIdNum();  //身份证号码

        if (sf.equals("教师")){
            if (ahuTeacherRepo.findByIdAndSfzh(xgh,idNum) != null){
                return new Response().success(ahuTeacherRepo.findByIdAndSfzh(xgh,idNum));
            }else{
                return new Response().failure("工号或身份证号不正确，请查证后再试");
            }
        }else if (sf.equals("学生")){
            if (ahuStudentRepo.findByIdAndSfzh(xgh,idNum) != null){
                return new Response().success(ahuStudentRepo.findByIdAndSfzh(xgh,idNum));
            }else{
                return new Response().failure("工号或身份证号不正确，请查证后再试");
            }
        }else {
            return new Response().failure("参数不正确，请重试");
        }
    }

    /**
     * 校园车辆管理系统-车辆注册管理-添加车辆
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response add(@RequestBody CarRegisterJson carRegisterJson){
        //获取车辆号牌
        String plateNum = carRegisterJson.getPlateNum();
        if (carRegisterRepo.findByPlateNumAndState(plateNum,"启用") == null){
            return new Response().success(carRegisterRepo.save(carRegisterJson.convetAdd()));
        }else {
            return new Response().failure("车辆已存在，请查证后再添加");
        }
    }

    /**
     * 校园车辆管理系统-车辆注册管理-编辑车辆
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Response edit(@RequestBody CarRegisterJson carRegisterJson){
        //获取车辆号牌
        String plateNum = carRegisterJson.getPlateNum();
        if (carRegisterRepo.findByPlateNumAndState(plateNum,"启用") != null){
            CarRegister carRegister = carRegisterRepo.findByPlateNumAndState(plateNum,"启用");
            carRegisterRepo.save(carRegisterJson.convetEdit(carRegister));
            return new Response().success("修改成功");
        }else {
            return new Response().failure("车辆不存在，请查证");
        }
    }

    /**
     * 校园车辆管理系统-车辆注册管理-删除车辆
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response delete(@RequestBody CarRegisterJson carRegisterJson){
        //获取车辆号牌
        String plateNum = carRegisterJson.getPlateNum();
        if (carRegisterRepo.findByPlateNumAndState(plateNum,"启用") != null){
            CarRegister carRegister = carRegisterRepo.findByPlateNumAndState(plateNum,"启用");
            carRegisterRepo.save(carRegisterJson.convetDelete(carRegister));
            return new Response().success("删除成功");
        }else {
            return new Response().failure("车辆不存在，请查证");
        }
    }
}