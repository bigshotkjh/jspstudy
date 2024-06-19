package pkg04_file;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1  // 업로드할 때 메모리에 저장되는 임시 파일의 크기
               , maxFileSize = 1024 * 1024 * 10       // 첨부 파일 하나의 크기
               , maxRequestSize = 1024 * 1024 * 100)  // 전체 첨부 파일의 크기

public class Upload1 extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // 일반 파라미터는 동일하게 처리
    request.setCharacterEncoding("UTF-8");
    System.out.println(request.getParameter("name"));
    
    // 저장할 경로
    String uploadPath = request.getServletContext().getRealPath("upload_dir");
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists())
      uploadDir.mkdirs();
    
    // 첨부 파일의 정보
    Part part = request.getPart("profile");

    // 첨부 파일의 원래 이름
    String originalFilename = null;
    if(part.getHeader("Content-Disposition").contains("filename")) {
      if(part.getSize() > 0) {
        originalFilename = part.getSubmittedFileName();
      }
    }
    
    // 첨부 파일의 저장 이름
    String filesystemName = null;
    if(originalFilename != null) {
      filesystemName = System.currentTimeMillis() + "_" + originalFilename;
    }
    
    // 첨부 파일 저장하기
    if(filesystemName != null) {
      part.write(uploadPath + "/" + filesystemName);
    }
    
    // 응답
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<div><a href=\"/servlet/file/NewFile1.html\">첨부화면으로가기</a></div>");
    File[] files = uploadDir.listFiles();
    for(File file : files) {
      String filename = file.getName();
      out.println("<div><a href=\"/servlet/download?filename=" + filename + "\">" + filename + "</a></div>");
    }
    out.flush();
    out.close();
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}