package carmelo.spring.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.programacion.app.model.Usuario;
@Controller
@RequestMapping("/")
public class HomeController {
    
	List<Usuario> users;
	
	public HomeController() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
	    Connection conexion = null; 
	    users = new java.util.LinkedList<Usuario>();
	    try {
	        Class.forName("org.postgresql.Driver");
	        String connectionUrl = "jdbc:postgresql://localhost:5434/spring_mvc?user=sa&password=123";
	        conexion = DriverManager.getConnection(connectionUrl);
	    } catch (SQLException e) {
	        System.out.println("SQL Exception: "+ e.toString());
	    } catch (ClassNotFoundException cE) {
	        System.out.println("Class Not Found Exception: "+ cE.toString());
	    }
	    
	    String sql = "SELECT id, nombre, email, password FROM users LIMIT 1000;";
	    
	    try (PreparedStatement cmd = conexion.prepareStatement(sql)) {
	        ResultSet rs = cmd.executeQuery();
	        
	        while(rs.next()) {
	            String name = rs.getString(2);
	            Long id = rs.getLong(1);
	            String email = rs.getString(3);
	            String password = rs.getString(4);
	            Usuario temp = new Usuario(id, name, email, password);
	            users.add(temp);
	        }
	        conexion.close();
	    }
	    catch(Exception ex) {
	        ex.printStackTrace(); // Mejor manejo de errores
	    }
	}
	
	/*
	 * @Autowired private UsuarioDao usuarioDao;
	 */
    
	@RequestMapping("/")
    public String index(Model model) {
    	return "redirect:/students/";
    }
	
    @RequestMapping("/ed")
    public String index2(Model model) {
		
		int totalUsuarios = users.size();//usuarioDao.contarUsuarios();
		model.addAttribute("totalUsuarios", totalUsuarios);
		 
        // Agregar datos al modelo
        model.addAttribute("titulo", "Bienvenido a Spring MVC 3.0.5");
        model.addAttribute("mensaje", "Esto es un mensaje desde el controlador");
        model.addAttribute("fecha", new java.util.Date());
        
        // Lista de ejemplo
        java.util.List<String> tecnologias = new java.util.ArrayList<String>();
        tecnologias.add("Spring MVC 3.0.5");
        tecnologias.add("Java 1.7");
        tecnologias.add("JSP 2.1");
        model.addAttribute("tecnologias", tecnologias);
        
        return "index"; // Nombre de la vista JSP (sin extensi�n)
    }
}