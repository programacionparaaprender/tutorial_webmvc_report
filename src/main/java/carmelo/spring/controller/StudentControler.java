package carmelo.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.luv2code.jsf.jdbc.Student;
import com.luv2code.jsf.jdbc.StudentDbUtil;
import com.programacion.app.model.Usuario;

import net.sf.json.JSONSerializer;

@Controller
public class StudentControler {
    List<Usuario> users;
    
    private List<Student> students;
    private StudentDbUtil studentDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    
    public StudentControler() throws Exception {
        students = new ArrayList<>();
        studentDbUtil = StudentDbUtil.getInstance();
    }
    
    @PostConstruct
    public void init() {
        logger.info("Loading students");
        students.clear();
        try {
            // get all students from database
            students = studentDbUtil.getStudents();
        } catch (Exception exc) {
            logger.log(Level.SEVERE, "Error loading students", exc);
        }
    }
    
    @RequestMapping("/students/")
    public String listStudent(Model model) {
    	init();
        model.addAttribute("students", students);
        return "list-students";
    }
    
    @RequestMapping(value = "/students/new", method = RequestMethod.GET)
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    @RequestMapping(value = "/students/edit/{id}", method = RequestMethod.GET)
    public String showEditForm(@PathVariable("id") int id, Model model) {
        try {
            Student student = studentDbUtil.getStudent(id);
            model.addAttribute("student", student);
        } catch (Exception exc) {
            logger.log(Level.SEVERE, "Error loading student", exc);
            model.addAttribute("errorMessage", "Error loading student");
            return "redirect:/students/";
        }
        return "student-form";
    }

    @RequestMapping(value = "/students/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student student, 
                            BindingResult result, 
                            Model model) {
        
        // Validaciones b�sicas
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
            result.rejectValue("firstName", "error.student", "El nombre es requerido");
        }
        
        if (student.getLastName() == null || student.getLastName().trim().isEmpty()) {
            result.rejectValue("lastName", "error.student", "El apellido es requerido");
        }
        
        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            result.rejectValue("email", "error.student", "El email es requerido");
        }
        
        if (result.hasErrors()) {
            return "student-form";
        }
        
        try {
            if (student.getId() == 0) {
                // Crear nuevo estudiante
                studentDbUtil.addStudent(student);
                model.addAttribute("message", "Estudiante creado exitosamente");
            } else {
                // Actualizar estudiante existente
                studentDbUtil.updateStudent(student);
                model.addAttribute("message", "Estudiante actualizado exitosamente");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar estudiante", e);
            model.addAttribute("errorMessage", "Error al guardar estudiante");
            return "student-form";
        }
        
        return "redirect:/";
    }

    @RequestMapping(value = "/students/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        try {
            studentDbUtil.deleteStudent(id);
            //model.addAttribute("message", "Estudiante eliminado correctamente");
        } catch (Exception exc) {
            logger.log(Level.SEVERE, "Error deleting student", exc);
            //model.addAttribute("errorMessage", "Error al eliminar estudiante");
        }
        return "redirect:/students/";
    }
    
    @RequestMapping(value = "listUsers.htm")
    public @ResponseBody String listaTodosUsuarios() {
        Student student = students.get(0);
        return JSONSerializer.toJSON(student).toString();
    }
}