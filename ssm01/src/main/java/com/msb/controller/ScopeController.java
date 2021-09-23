package com.msb.controller;
import com.msb.pojo.User;
import com.msb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ScopeController {
    @Autowired
    private UserService userService;
    /*定义一个处理单元，向三个域放入数据*/
    @RequestMapping("setData")
    public String setData(HttpServletRequest req, HttpSession session){
        List<User> users = userService.findAllUser();
        //向3个域中放入数据
        req.setAttribute("message","reqMessage");
        req.setAttribute("users",users);

        session.setAttribute("message","sessionMessage");
        session.setAttribute("users",users);

        ServletContext application = req.getServletContext();
        application.setAttribute("message","applicationMessage");
        application.setAttribute("users",users);

        //跳转至showDataPage
        return "/showDataPage.jsp";
    }

    @RequestMapping("setData2")
    public String setData2(Model model){
        List<User> users = userService.findAllUser();
        //向域中放入数据
         model.addAttribute("message","reqMessage");
         model.addAttribute("users",users);
        //跳转至showDataPage
        //return "/showDataPage.jsp";
        return "redirect:/showDataPage.jsp";
    }

    @RequestMapping("setData3")
    public ModelAndView setData3(){
    
        ModelAndView mv = new ModelAndView();
        Map<String, Object> model = mv.getModel();
        //向request域中放入数据
        List<User> users = userService.findAllUser();
        model.put("message","reqMessage");
        model.put("users",users);

        //设置视图

        //mv.setViewName("forward:/showDataPage.jsp");
        mv.setViewName("redirect:/showDataPage.jsp");
        return mv;
    }

}
