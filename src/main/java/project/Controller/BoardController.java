package project.Controller;
import java.util.ArrayList;
import java.util.Date;

import project.domain.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    String title_string = "";
    static String title_string_static = "";

    String writer_string = "";

    static String writer_string_static = "";

    String content_string = "";

    static String content_string_static = "";
    //step2.배열객체 사용//--------------------------------------------------------------------------------------------------------------------
    static ArrayList<String> title_array =new ArrayList<String>();
    static ArrayList<String> writer_array =new ArrayList<String>();
    static ArrayList<String> content_array =new ArrayList<String>();

    //setp3.시용자 생성 객체 사용//--------------------------------------------------------------------------------------------------------------------
    static ArrayList<Board> board_array = new ArrayList<Board>();
    static int count = 0;

//------------------------------------------------------------------------------------------------------------------------------------------------


    //@getmapping 또는 @postMapping은 @RequestMapping의 자식 클래스이다.
    //@RequestMapping의 기능을 모두 쓸 수 있다.
    //자식클래스 어노테이션이 아닌 부모클래스 어노테이션을 쓰는 이유는 기능의 한정을 통해서
    //서버의 리소스 감소 및 보안을 위해서 이다.

    //Create
    @GetMapping("insertBoard")
    public String insertBoard(){
        return "insertBoard";
    }

    //[클라이언트]html form태그의 method속성의 값인 post를 인식하여 아래의 @PostMapping에 연결
// //------------------------------------------------------------------------------------------------------------
//    @PostMapping("insertBoard")
//    public String insertBoard(@RequestParam("title")String title_t,
//                              @RequestParam("writer")String writer_t,
//                              @RequestParam("content")String content_t,
//                              Model model){
////
////        title_string =title_t;
////        title_string_static = title_t;           ---1개만 할때
//        title_array.add(title_t);
//        model.addAttribute("title",title_t);
//
//
////        writer_string = writer_t;
////        writer_string_static = writer_t;
//        model.addAttribute("writer", writer_t);
//        writer_array.add(writer_t);
//
////        content_string = content_t;
////        content_string_static = content_t;
//        model.addAttribute("content", content_t);
//        content_array.add(content_t);
//        //redirect : 페이지 전환 이동
//        return "redirect:getBoardList";
//        }


// -----방법3.객체이용 ------------------------------------------------------------------------------------------------------------
    @RequestMapping("insertBoard")
    public String insertBoard(@RequestParam("title")String title,
                              @RequestParam("writer")String writer,
                              @RequestParam("content")String content){
        count++;
        Board board = new Board();

        board.setSeq((long)count);
        board.setTitle(title);
        board.setWriter(writer);
        board.setContent(content);
        board.setCreateDate(new Date());
        board.setCnt(0L);
        board_array.add(board);
        return "redirect:getBoardList";

    }
//--------------------------------------------------------------------------------------------------------------------

    //@어노테이션은 메서드 혹은 클래스에 속성,정의를 해서 스프링이나 자바에서 찾기 쉽도록 해주는 선언부
    //ex) @Override 은 부모 메서드를 재정의 하여 사용한다고 자바나 스프링에게 속성 명시
    //@Requestparam : [클라이언트]에서 string문자열을 [서버]에 전달하는 매개변수 선언
    //@RequestParam("title")String title에서("title")은 [클라이언트]의 name이라는 속성으로써 ket값을 매개변수를 전달.

    //Read
    @RequestMapping("getBoard")
    public String getBoard(
            @RequestParam("seq")String seq,
            @RequestParam("userRole")String userRole,
            @RequestParam("userId")String userId,
            @RequestParam("title")String title,
            @RequestParam("writer")String writer,
            @RequestParam("content")String content,
            @RequestParam("createDate")String createDate,
            @RequestParam("cnt")String cnt,
            Model model){
        model.addAttribute("seq",seq);
        model.addAttribute("title",title);
        model.addAttribute("writer",writer);
        model.addAttribute("content",content);
        model.addAttribute("createDate",createDate);
        model.addAttribute("cnt",cnt);
        model.addAttribute("userId",userId);
        model.addAttribute("userRole",userRole);
        return "getBoard";
    }

    //@RequestMapping은 [서버]에서 디스패처서블릿을 통해 html의 action태그의 주소와 동일한
    //문자열을 찾는 매핑기능(연결)이 실행되고 하단의 메서드가 실행 (목적지를 알 수 있음)
    //return STring인 이유는 viewResolver가 html파일을 찾기 위한 문자열을 리턴
//--------------------------------------------------------------------------------------------------------------------
     //    <방법1>
    //    @RequestMapping("/getBoardList")
//    public String getBoardList(Model model) {
//        //list타입으로 Board객체를 넣는 boardList변수명 선언
//        //=대입연산자로 heap메모리에 ArrayList타입으로 할당
//        //List는 Arraylist의 부모클래스
//        List<Board> boardList = new ArrayList<Board>();
//        for (int i = 1; i <= 10; i++) {
//            //Board클래스로 board인스턴스 생성
//            Board board = new Board();
//            //롬북으로 자동생성된 setter 메서드로 데이터 입력
//            board.setSeq(new Long(i));
//            board.setTitle("게시판 프로그램 테스트");
//            board.setWriter("도우너");
//            board.setContent("게시판 프로그램 테스트입니다...");
//            //내장 클래스인 java.util.Date 객체로 시간 데이터 출력
//            board.setCreateDate(new Date());
//            board.setCnt(0L);
//            //boardList배열에 board객체 넣기 (for문 10번 도니까 board 객체 10개 넣기)
//            boardList.add(board);
//        }
////--------------------------------------------------------------------------------------------------------------------
////       <방법2>
//        @RequestMapping("/getBoardList")
//        public String getBoardList(Model model) {
//            //list타입으로 Board객체를 넣는 boardList변수명 선언
//            //=대입연산자로 heap메모리에 ArrayList타입으로 할당
//            //List는 Arraylist의 부모클래스
//            List<Board> boardList = new ArrayList<Board>();
//            if(title_array.size() > 0) {
//                for (int i = 0; i < title_array.size(); i++) {
//                    //Board클래스로 board인스턴스 생성
//                    Board board = new Board();
//                    //롬북으로 자동생성된 setter 메서드로 데이터 입력
//                    board.setSeq(new Long(i) + 1);
//                    board.setTitle(title_array.get(i));
//                    board.setWriter(writer_array.get(i));
//                    board.setContent(content_array.get(i));
//                    //내장 클래스인 java.util.Date 객체로 시간 데이터 출력
//                    board.setCreateDate(new Date());
//                    board.setCnt(0L);
//                    //boardList배열에 board객체 넣기 (for문 10번 도니까 board 객체 10개 넣기)
//                    boardList.add(board);
//                }
//
//            }
////        boardList.get(0).getTitle()
//        //model 객체에 boardList(arraylist)를 boardlist key값으로 넣음
//        //attributeName = key
//        //attributeValue = value
//        //model에는 참조타입만 넣을 수 있다.(addAttribute 메서드 안에 매개변수 타입으로 확인 가능)
//        model.addAttribute("boardList", boardList);
//
//        //디스패처서블릿이 뷰 리졸버를 찾아서 연결해 줍니다.
//       //return getBoardList라는 문자욜로 templates에dlTsms 이름에 html파일을 찾는다
//        //return getBoardList라는 문자열로 templates에 있는 같은 이름에 html을 찾는다.
//        //ViewResolver
//        return "getBoardList";
//    }
    //--------------------------------------------------------------------------------------------------------------------

    //Read

//------------------------------------------------------------------------------------------------------------------------
@RequestMapping("getBoardList")
public String getBoardList(Model model){
    model.addAttribute("board_array",board_array);
    return "getBoardList";
}
//삭제--------------------------------------------------------------------------------------------------------------------
    //Delete
    @GetMapping("deleteBoard")
    public String deletBoard(@RequestParam("seq")String seq) {
        //seq매개변수 (getBoard.html에서 받아옴)로 board_array 배열에서
        //.getSeq로 같은 index를 찾아 board_array에 있는 board객체 삭제 >> 원하는 게시글 삭제
//  //---------------------------------------------------------- --------------------------------------------------------------
//        Board board = new Board();
//        Long L = Long.parseLong(seq);
//        board.setSeq(L);
        for (int i = 0; i < board_array.size(); i++) {
//            //board_array.get(i).getSeq() : board_array의 i번째 객체를 찾아서 getSeq()메소드를 통해 seq필드값 가져오기
//            //equals()메서드를 통해서 매개변수 seq값과 비교(참조타입이므로)
//            //seq 타입은 Long입니다. 소수점있는 데이터(숫자)이므로 매개변수 seq(String)과 같은 타입이 아니므로 비교불가
//            //board_array.get(i).getSeq()의 값 Long을 String으러 변환 = Long.toString()
                if(Long.toString(board_array.get(i).getSeq()).equals(seq)){
                    board_array.remove(i);
                }
//            if (board.getSeq().equals(board_array.get(i).getSeq())) {
//                board_array.remove(i);
//            }

        }
//------------------------------------------------------------------------------------------------------------------------

        return "redirect:getBoardList";
    }
    //수정------------------------------------------------------------------------------------------------------------------------
    //PostMapping[클라이언트] 에서 [서버]로 맵핑
    //Update
    @PostMapping("/updateBoard")
    public String updateBoard(
            //HTML에서 name속성을 가진 값을 매개변수 String seq에 할당 = @RequestParam("seq")
            @RequestParam("seq")String seq,
            @RequestParam("title")String title,
            @RequestParam("content")String content){

        //board_array배열을 순회하야 board객페의 seq필드값을 매개변수 seq와 비교하여 true값 찾기
        for(int i=0; i<board_array.size(); i++){
            if(Long.toString(board_array.get(i).getSeq()).equals(seq)){
                //setTitle과 같은 setter로 데이터 변경
                board_array.get(i).setTitle(title);
                board_array.get(i).setContent(content);
            }
        }
        return "redirect:getBoardList";
    }
}

