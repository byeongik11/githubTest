package board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.svc.BoardWriteProService;
import vo.ActionForward;
import vo.BoardBean;
// 글 등록
public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		BoardBean boardBean = null;
		String realFolder = "";
		String saveFolder = "/boardUpload";				//파일 저장폴더
		int fileSize = 5*1024*1024;
		ServletContext context = request.getServletContext(); 
		realFolder = context.getRealPath(saveFolder);	// 파일 저장풀경로
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8",new DefaultFileRenamePolicy());
		// qna_board_write의 폼태그의 enctype요소가 multipart이므로 값을 전달받을 때는 MultipartRequest의 getParameter 메서드를 이용한다. 
		boardBean = new BoardBean();
		boardBean.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
		boardBean.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
		boardBean.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
		boardBean.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
		boardBean.setBOARD_FILE(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		BoardWriteProService boardWriteProService = new BoardWriteProService();
		boolean isWriteSuccess = boardWriteProService.registArticle(boardBean);
		
		if(!isWriteSuccess) {	// false 이면
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('등록실패')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.bo");
		}
		
		return forward;
		
	}

}
