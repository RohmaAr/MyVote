package Notifs;

import java.io.PrintWriter;

public class JspAlert {
 public JspAlert(PrintWriter o, String m) {
	o.println("<!DOCTYPE html>"+
			"<html>"+
			"<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
			+ "<style>\r\n"
			+ ".alert {\r\n"
			+ "  padding: 20px;\r\n"
			+ "  background-color: #f44336;\r\n"
			+ "  color: white;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ ".closebtn {\r\n"
			+ "  margin-left: 15px;\r\n"
			+ "  color: white;\r\n"
			+ "  font-weight: bold;\r\n"
			+ "  float: right;\r\n"
			+ "  font-size: 22px;\r\n"
			+ "  line-height: 20px;\r\n"
			+ "  cursor: pointer;\r\n"
			+ "  transition: 0.3s;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ ".closebtn:hover {\r\n"
			+ "  color: black;\r\n"
			+ "}\r\n"
			+ "</style>\r\n"
			+ "</head>\r\n"
			+ "<body>\r\n"
			+ "\r\n"
			+"<i class=\"fas fa-exclamation-triangle\"></i><br>\r\n"
			+ "<h2>Alert Message</h2>\r\n"
			+ "\r\n"
			+ "<p>Click on the \"x\" symbol to close the alert message.</p>\r\n"
			+ "<div class=\"alert\">\r\n"
			+ "  <span class=\"closebtn\" onclick=\"this.parentElement.style.display='none';\">&times;</span> \r\n"
			+ "  <strong>Oops!</strong>"+ m+"\r\n"
			+ "</div>\r\n"
			+ "\r\n"
			+ "</body>\r\n"
			+ "</html>");
    }
}
