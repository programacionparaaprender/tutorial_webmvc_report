package carmelo.spring.controller;

import carmelo.spring.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luv2code.jsf.jdbc.Student;
import com.luv2code.jsf.jdbc.StudentDbUtil;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private List<Student> students;
    private StudentDbUtil studentDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    
    public CustomerController() throws Exception {
        students = new ArrayList<>();
        studentDbUtil = StudentDbUtil.getInstance();
    }
    
    @RequestMapping("/customer")
    public ModelAndView verInicio() {
        return new ModelAndView("customer");
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public String verReporte(@PathVariable("id") int id, Model model,
            @RequestParam(
                    name = "format",
                    defaultValue = "pdf",
                    required = false) String format) {
    	try {
            Student student = studentDbUtil.getStudent(id);
            model.addAttribute("student", student);
            
            model.addAttribute("format", format);
            model.addAttribute("datasource", customerService.findAll());
            model.addAttribute("param1String", student.getFirstName());
            model.addAttribute("param2String", student.getLastName());
            
        } catch (Exception exc) {
            logger.log(Level.SEVERE, "Error loading student", exc);
            model.addAttribute("errorMessage", "Error loading student");
            return "redirect:/students/";
        }
        

        return "user_report";
    }
    
    @RequestMapping("/report2")
    public String verReporte2(Model model,
            @RequestParam(
                    name = "format",
                    defaultValue = "pdf",
                    required = false) String format) {

        model.addAttribute("format", format);
        model.addAttribute("datasource", customerService.findAll());
        model.addAttribute("AUTOR", "Tutor de programacion");

        return "customer_report";
    }
    
}
