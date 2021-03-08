package dog.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dog.svc.DogRegistService;
import vo.ActionForward;
import vo.Dog;

public class DogRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		DogRegistService dogRegistService = new DogRegistService();
		String realFolder = ""; //파일업로드 될 경로
		
		String saveFolder = "/images";
		String encType = "UTF-8";
		int maxSize = 5*1024*1024; 
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder,maxSize,encType,new DefaultFileRenamePolicy()	);
		
		int id = 0;
		String kind = multi.getParameter("kind");
		int price = Integer.parseInt(multi.getParameter("price"));
		String image = multi.getFilesystemName("image");
		String country = multi.getParameter("nation");
		int height = Integer.parseInt(multi.getParameter("height"));
		int weight = Integer.parseInt(multi.getParameter("weight"));
		String content = multi.getParameter("content");
		int readcount = 0;
		
		Dog dog = new Dog(id,kind,price,image,country,height,weight,content,readcount);
		boolean isRegistSuccess = dogRegistService.registDog(dog);
		
		if(isRegistSuccess) {
			forward = new ActionForward("dogList.dog", true);
		} else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
