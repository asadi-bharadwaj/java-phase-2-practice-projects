import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/productid")
public class productid extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="jdbc:mysql://localhost:3306/product";
		String uname="root";
		String pass="Bharatbj@1";
		
		response.setContentType("text/html");
		
		String pId = request.getParameter("productid");
	
		PrintWriter out = response.getWriter();
		
		String query="select * from product.products where product_ID=?";
		out.print("<h1>Product Details...</h1>");
		out.print("<table border='1'><tr><th>Product Id</th><th>Product Name</th><th>price</th></tr>");
		
		try {
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection dbCon = DriverManager.getConnection(url, uname, pass);
	      PreparedStatement st=  dbCon.prepareStatement(query);
	      
	      st.setString(1, pId);
	      
	      ResultSet rs =st.executeQuery();
	      
	      while(rs.next()) {
	    	  
	    	  out.print("<tr><td>");
	    	  out.println(rs.getInt(1));
	    	  out.print("</td>");
	    	  out.print("<td>");
	    	  out.print(rs.getString(2));
	    	  out.print("</td>");
	    	  out.print("<td>");
	    	  out.print(rs.getInt(3));
	    	  out.print("</td>");
	    	  out.print("</tr>");
	    
	    	  
			}
	      
		}
		catch(Exception e){
			
			System.out.println("Some Issue : "+ e.getMessage());
			
			
		}
		
		out.print("</table>");
		
	}

}