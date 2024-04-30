# food-sharing  
🏆 2023 한이음 공모전 입선 🏆
* [🎬Youtube 링크🎬](https://youtu.be/o3GsTX4JVY4?si=fFSOrZmdRMNxyKwv)
* 23.06.12~23.09.16
* 개발 인원 : HW - 2명, SW - 3명
* 나의 역할
  * 게시판, 마이페이지 개발
  * DB 설계, 연동
  * 서버 구축, 연결
  
* 프로젝트 정보

![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/930cc90d-9882-49d4-b4ac-544aaf83e3f5)


* 메뉴구성도

![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/944e10f4-bef1-43e5-90b1-5accde582b1a) 

* 프로젝트 구성도

![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/1dc377af-13f2-4c16-a4b9-7593db6bd4f2)


* 기능 목록

![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/03be361f-ab35-4cb3-9819-9c9f039b4a72)

* SW 주요 기능

  
  * 로그인(시작화면)
    
    앱을 시작했을 때 나오는 초기화면입니다.
  
    푸드쉐어링 앱에 사용자를 등록하여 이용 가능하게 하는 회원가입과 로그인 기능입니다.
    
    비밀번호를 잊어버렸을 때 비밀번호를 다시 찾을 수 있는 비밀번호 찾기 기능도 있습니다.
    
    ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/27e9976a-ab19-4517-b595-5c5d9efda778)

  * 회원가입

    사용자에게 필요한 정보(아이디, 비밀번호, 이름, 닉네임, 전화번호, 주소)를 입력받아 DB에 저장합니다.
 
    아이디와 비밀번호는 중복확인을 통해 데이터가 겹치지 않도록 합니다. 모든 정보가 입력되면 회원등록이 가능합니다.
 
    ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/382a3895-d79d-4202-8fee-8bb29a674f88)


  *  홈화면

     로그인 성공 시 보이는 홈화면입니다. 상단에는 공지사항이 있으며 인기게시물과 추후에는 평점 관련 글을 게시할 예정입니다.
 
     하단에는 공유냉장고 설명과 홍보, 탄소배출 관련 내용이 기재되어있습니다.
 
     ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/5ab7fd80-f1a4-4f0e-bfb6-a11abeea5ac7)

   * 공유냉장고 화면
 
     사용자들이 등록한 공유 음식들을 확인하고 이를 이용하는 공간입니다.

     음식의 수량 및 유통기한 등을 확인 가능합니다. 가장 나눔을 많이 한 사람은 이달의 나눔왕으로 뽑혀 올라갑니다.
  
     ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/0ceaa430-970e-4e4e-9122-beca56c35507)


   * 찜목록 화면
 
     내가 찜해놓은 음식들의 목록을 볼 수 있습니다.

     음식의 상세페이지에서 하트를 누르면 자동적으로 찜목록에 추가됩니다. 편집을 통해 음식을 목록에서 삭제할 수 있습니다.

     ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/7ced6708-dc3d-4b24-a93a-307b6fdad493)


  
   * 음식 등록 화면

     공유하고자 하는 음식의 이름과 수량, 유통기한을 입력하여 등록할 수 있습니다.

     사진찍기를 통해 현재 음식 상태를 보여줄 사진 또한 등록 가능합니다.

     ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/414036e3-c8cb-4b7c-8fe1-db602854bee3)


   * 개인음식 보관 화면

     나의 냉장고라는 이름으로 개인음식을 보관할 수 있는 보관함입니다.

     버튼을 통해 열고 닫을 수 있습니다.

     ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/22c6302e-93b9-4f89-a8a6-a89fe8a02d35)


  * 개인보관함 비밀번호 화면

    개인 보관함을 열기위한 비밀번호를 입력하는 화면입니다. 4자리를 입력하지않고 OK를 누르면 4자리를 누르라는 메시지가 나옵니다.

    비밀번호를 입력 후 AC를 누르면 모두 지워지고, C를 누르면 숫자 하나씩 지워집니다.

    비밀번호 4자리를 일치하게 입력하고 OK를 누르면 보관함이 열렸다는 메시지와 함께 보관함이 열립니다.

    ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/efaaa651-883f-41cd-9e73-df3d58a08e7e)

  * 게시판 화면

    앱의 사용자들이 글을 작성할 수 있으며 작성된 글을 볼 수 있는 화면입니다.

    인기 게시글은 상단에 띄워지며 게시글에는 댓글과 좋아요를 달 수 있습니다.

    ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/fd05e445-c33a-465a-b775-4e30e37b04b0)

  * 마이페이지 화면

    개인프로필과 닉네임을 볼 수 있으며 다양한 기능을 할 수 있습니다.

    자신의 정보를 수정할 수 있는 정보수정 기능, 문의 및 신고를 통해 관리자와 소통을 할 수 있으며 이를 통해 서비스를 개선할 수 있습니다.

    마지막으로 로그아웃을 통해 다른 사용자 계정으로 이용가능합니다.

    ![image](https://github.com/MinseoK1m/food-sharing/assets/138808284/748fbfab-8611-46ca-83d8-b1d190f88a00)

--- 
# 마무리하며

### 프로젝트의 시작부터 완성, 그리고 좋은 결과까지 얻을 수 있었던 나의 소중한 첫 공모전 프로젝트

3학년이 되면서 뭐라도 해야할 것 같은 마음에 동기, 선배들과 함께 도전한 한이음 프로젝트에서 
프로젝트가 진행되는 과정을 차근차근 잘 배울 수 있었다.





