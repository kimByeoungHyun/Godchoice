# [거기어때] 행사 공유 서비스

![KakaoTalk_20221208_185649538](https://user-images.githubusercontent.com/90454621/207082891-4dc7cee1-1542-4169-8f39-f95cb59c6a45.png)
<br/>

## 다른 지역의 행사가 궁금할 때?  [거기어때로 가기](https://godchoice.shop/login)
<hr/>

<a href="https://www.notion.so/euninote/55e04add1515418884f84b5377641c23" target="_blank">**Project 거기어때 Notion SA Home**</a>

[**Front End Github**](https://github.com/minhyeonhong/godchoice_FE)

[**Back End Github**](https://github.com/godchoice-project/godchoice_BE)

<br/>

<div style="text-align:center">

## 🌵 프로젝트 영상🌵

![ezgif com-gif-maker](https://user-images.githubusercontent.com/113873156/207862140-1e193d10-3eae-454c-b467-35134c757f45.gif)

## 💗 프로젝트 소개 💗
전국에 있는 다양한 행사를 소개하고 같이 갈 사람을 모집하는 서비스

<br/>

## 💞 프로젝트 기간 💞
### 2022.11.04 ~ 2022.12.16
<br/>
<br/>
</div>

## 🚩 주요 서비스
<br/>

![image](https://user-images.githubusercontent.com/90454621/207088933-8d19917d-2a1d-443d-9db2-54b5ed1636ea.png)

<br/>

### **✔ 지역 맞춤 설정**
<br/>
<div style="margin-left:20px">

**마이페이지**에서 지역 설정을 하면 **메인페이지**에서 선택한 지역의 행사들을 확인할 수 있습니다!

또한 홈 화면 지역 태그로 선택 지역의 행사들을 모아볼 수 있습니다.
</div>

### **✔ 다양한 행사 정보 확인과 소통**
<br/>
<div style="margin-left:20px">

**행사글, 모집글, 질문글**로 지역행사를 알리거나 함께 갈 사람을 모집할 수 있습니다. 

또한 궁금한 내용을 질문할 수 있고, 댓글과 대댓글로도 소통할 수 있습니다.
</div>

### **✔ 나의 관심 행사들만 모아보기**
<br/>
<div style="margin-left:20px">
마이페이지에서 내가 쓴 글과 댓글 단 글, 스크랩한 행사들을 확인할 수 있습니다.
</div>
<br/>
<br/>

<div style="text-align:center">

## 🪐 기술 정보 🪐
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white">
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=white">
<img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white">
<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=Amazon S3&logoColor=white">
<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=Amazon EC2&logoColor=white">
<img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white">
<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=Amazon RDS&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white">
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white">
<img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white">
<img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white">
<br/>
<hr/>

## 📚 프로젝트 아키텍처
<br/>

![캡처](https://user-images.githubusercontent.com/108788078/208015576-91e98450-a811-484b-b8a6-88c714dfda2d.PNG)
<br/>
<br/>

## 📚 ERD
<br/>
        
![캡처3](https://user-images.githubusercontent.com/108788078/208020199-b262bcb8-6594-409b-99d4-c0e52d7cae4f.PNG)
<br/>
<br/>

## 🥑 기술적 의사결정 

<br/>
<div markdown="1">

- **MySQL**
<div style="margin-left:40px">
연관관계를 통한 이점을 얻기위해 관계형 데이터 베이스를 사용하였습니다. 또 oracle에 비해 메모리 사용량이 적고, 용량 차지가 적고, 비용적인 부담도 적기 때문에 사용하게 되었습니다.
</div>
<br/>

- **swagger**
<div style="margin-left:40px">
개발 도중 API가 계속 바뀌게 되었는데 바뀔 때마다 원래 작성해둔 API를 고칠 필요 없이 프론트 엔드가 확인이 쉽고 문서화가 쉽기 때문에 사용했습니다.
</div>
<br/>

- **Querydsl**
<div style="margin-left:40px">
대부분의 기능들은 JPArepository를 사용해 구현이 가능했지만 검색 기능을 구현할 때  JPArepository만으로는 한계를 느껴 사용했습니다.
</div>
<br/>

- **jasypt**
<div style="margin-left:40px">
깃허브에 업로드 할때 민감한 정보인 비밀번호와 소셜로그인을 구현할때 필요한 클라이언트 비밀번호와 같은 프로퍼티들을 암호화하기 위해 사용했습니다.
</div>
<br/>

- **Sentry**
<div style="margin-left:40px">
배포를 진행하게 되면 오류가 생겼을 때 로그를 바로 볼 수 없는데 그 문제를 해결하기 위해 Sentry를 사용했습니다.
</div>
<br/>

## 🥩 주요 기능 🥩
<br/>

        ✔ 게시글 CRUD 
        ✔ 이미지 다중 업로드 / 이미지 삭제 기능
        ✔ 무한 스크롤
        ✔ 카카오, 네이버, 구글 소셜로그인
        ✔ 실시간 알림 기능
        ✔ 댓글, 대댓글 CD
<br/>
<br/>

## 🥥 트러블 슈팅 🥥
<hr/><br/>

## 🌝 Back-End
<br/>

### 1. 검색 관련 트러블 슈팅
<details>
<summary> 해결과정 </summary>

### <div style="color:gray">✔ 문제 발생 </div>
<br/>
<div style="padding-left:20px">
유저의 선택에 따라 조회할 조건이 바뀌게 되는데 조건이 바뀔때마다 다른 메서드를 사용해야 했습니다. 그 과정에서 생각하지 못한 경우가 생긴다면 오류가 생겨버리게 됩니다.
</div>
<br/>

### <div style="color:gray">✔ 해결 시도</div>
<br/>
<div style="padding-left:20px">
1. 처음으로 시도한 방법은 유저의 선택지를 많이 줄여 경우를 줄이는 방법
 
  - 이 경우 개발자 입장에서는 편하지만 유저를 생각하지 않는 사이트가 됨
2. 두번째로 시도한 방법은 모든 경우를 다 계산해 메서드를 작성하는 방법
 
  - 이 방법은 생각하지 못한 경우도 많이 생기고, 잘못된 메서드를 사용해 오류가 뜨는일이 빈번함.
</div>
<br/>

### <div style="color:gray">✔ 해결</div>
<br/>
<div style="padding-left:20px">
Querydsl을 사용해 다양한 경우에 한가지 쿼리로 동작할 수 있도록 구현했습니다.
 
        List<EventPost> eventPostList = queryFactory
                        .selectFrom(eventPost)
                        .where(listTag(tag),
                                eventPost.eventStatus.eq(progress)
                                        .and(searchKeyword(search)))
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .orderBy(listSort(sort), eventPost.eventPostId.desc())
                        .fetch();
</div>
</details>
<br/>

### 2. 대댓글 관련 트러블 슈팅
<details>
<summary> 해결과정 </summary><br/>

### <div style="color:gray">✔ 문제 발생</div>
<br/>
<div style="padding-left:20px">
댓글에 대댓글 작성을 구현하고자 했습니다.
 
이때, 댓글과 대댓글 테이블을 따로 관리하지 않고 같은 테이블에서 관리하고자했습니다.
</div>
<br/>

### <div style="color:gray">✔ 해결 시도</div>
<br/>
<div style="padding-left:20px">
1. 첫번째로 시도한 방법은 테이블을 따로 만들어서 구현했습니다.
 
 * 댓글과 대댓글의 구현방법을 이해하기 위한 작업이였습니다.
 * 하지만 저희 팀이 원했던 방법이 아니기 때문에 실패했습니다

</div>
<br/>

### <div style="color:gray">✔ 해결</div>
<br/>
<div style="padding-left:20px">
댓글 테이블에 부모댓글과 자식댓글의 연관관계를 만들고 댓글일때는 부모댓글이 없는 상태로 저장하고 대댓글에는 부모댓글을 저장해 연관관계를 형성했습니다.
 
      // 상위 댓글
     @ManyToOne
     @JoinColumn(name = "parent_id")
     @JsonIgnore
     private EventPostComment parent;

     // 하위 댓글
     @OneToMany(mappedBy = "parent", orphanRemoval = true)
     @JsonIgnore
     private List<EventPostComment> children = new ArrayList<>();
     </div>
<br/>
 
### <div style="color:gray">✔ 개선 또는 발전</div>
<br/>
<div style="padding-left:20px">
* 현재 서비스에서는 대댓글 기능만 구현되어있지만 이런 방법을 이용한다면 무한 대댓글 구현이 가능할 것으로 예상됩니다
 
 - 현재 예상하는 방법은 지금은 부모댓글에 댓글만 저장할 수 있지만 무한 대댓글을 구현할때는 부모댓글에 대댓글의 id를 넣어 무한 대댓글 구현할수 있을 것으로 예상

</div>
<br/>
</details>
<br/>


## ⭐️ 팀원분들
<hr/>

| Position                 | Name   | Github                                                     | MBTI |
| ------------------------ | ------ | -------------------------------------------------------- | ---- |
| 🔰 FE·React | 민현홍 | 🔗 [GitHub::Min Hyeonhong](https://github.com/minhyeonhong)       | ISFP |
| FE·React   | 박지윤 | 🔗 [GitHub::connie](https://github.com/verocony)     | ENFP |
| FE·React   | 이지나 | 🔗 [GitHub::C e l i n a](https://github.com/LEEJEENA)     | ISTJ |
| 🔰 BE·Spring             | 공은희 | 🔗 [GitHub::euni](https://github.com/euni1004) | INTP |
| BE·Spring                | 김병현 | 🔗 [GitHub::S a n d b a c k e n d](https://github.com/kimByeungHyun)   | ISFP |
| BE·Spring                | 정동훈 | 🔗 [GitHub::dhun0103](https://github.com/dhun0103)   | INTJ |
| UX/UI                    | 양은희 |                                                          | INTP |
