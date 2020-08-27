package cn.edu.ahu.carmanagement.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: FZC
 * @Despriction:
 * @Date:Created in 22:15 2020/3/22
 */
@RestController
public class TestController {
    //Logger建议用slf4j
    Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        log.debug("debug ****************************");
        log.info("info ****************************");
        log.warn("warn ****************************");
        log.error("error ****************************");
        return "success";
    }
}
