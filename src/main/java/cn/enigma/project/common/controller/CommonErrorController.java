package cn.enigma.project.common.controller;

import cn.enigma.project.common.controller.response.BaseVO;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author luzh
 * Create: 2018/12/14 3:36 PM
 * Modified By:
 * Description:
 */
@ApiIgnore
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CommonErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Resource
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping
    @ResponseBody
    public BaseVO doHandleError(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = errorAttributes(request);
        BaseVO vo = new BaseVO<>();
        vo.setCode(map.get("code").toString());
        vo.setMsg(map.get("path") + " " + map.get("message"));
        response.setStatus(Integer.parseInt(map.get("code").toString()));
        return vo;
    }

//    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
//    public String doHandleErrorView(HttpServletRequest request, ModelMap modelMap) {
//        Map<String, Object> map = errorAttributes(request);
//        modelMap.put("status", map.get("code"));
//        modelMap.put("error", map.get("path") + " " + map.get("message"));
//        modelMap.put("path", map.get("path"));
//        modelMap.put("timestamp", map.get("timestamp"));
//        return "error";
//    }

    private Map<String, Object> errorAttributes(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        WebRequest webRequest = new ServletWebRequest(request);
        Map<String,Object> errorAttributesData = errorAttributes.getErrorAttributes(webRequest,true);
        map.put("path", errorAttributesData.get("path"));
        map.put("code", errorAttributesData.get("status"));
        map.put("message", errorAttributesData.get("message"));
        map.put("timestamp", errorAttributesData.get("timestamp"));
        return map;
    }
}
