<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta property="og:type" content="website" />
    <meta property="og:site_name" content="ADVISER" />
    <meta property="og:title" content="상처 치료법을 모르는 당신, ADVISER를 활용하여 치료법을 알아보세요!" />
    <meta property="og:description" content="" />
    <meta property="og:image" content="" />
    <meta property="og:url" content="" />

    <meta property="twitter:card" content="summary" />
    <meta property="twitter:site" content="ADVISER" />
    <meta property="twitter:title" content="상처 치료법을 모르는 당신, ADVISER를 활용하여 치료법을 알아보세요!" />
    <meta property="twitter:description" content="" />
    <meta property="twitter:image" content="" />
    <meta property="twitter:url" content="" />

	<link rel="shortcut icon" href="./img/favicon.ico"/>
    <link rel="icon" href="./img/favicon-16x16.png" type="image/x-icon"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.2/reset.min.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/main.css">
    <link rel="stylesheet" href="./css/container.css">
    <link rel="stylesheet" href="./css/contants.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js" integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js" integrity="sha512-16esztaSRplJROstbIIdwX3N97V1+pZvV33ABoG1H2OyTttBxEGkTsoIVsiP1iaTtM8b3+hu2kB6pQ4Clr5yug==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <title>메인 페이지</title>
</head>
<body>
    <aside class="side-bar">
      <section class="team_project">
        <section class="team_img">
          <div class="team">
            <div class="team_name">
              <img class="team_banner" src="./img/ADVISER_web.jpg" alt="ADVISER" onclick="MainPage()">
            </div>
          </div>
        </section>
      </section>
      <section class="team_project">
        <section class="team_img">
          <div class="team">
            <div class="team_name">
              <img src="" alt="">
            </div>
          </div>
        </section>
      </section>
        <section class="side-bar__icon-box">
          <section class="side-bar__icon-1">
            <div class="square_position">
              <div class="sp">
                <img class="square" src="./img/person.png" alt="">
              </div>
            </div>
          </section>
          <div class="span_id">
            <span>${admin.admin_name }</span>
           
          </div>
          <div class="btn_two">
            <a href="#" class="btn btnUpdate">정보수정</a>
            <a href="logout" class="btn" onclick="signOut()">로그아웃</a>
          </div>
          <div class="hr_reset">
              <hr>
          </div>
        </section>
        <ul>
          <li>
            <a href="QnA"><i></i>조언 서비스 관리</a>
          </li>
          <li>
            <a href="Post">푸쉬 메시지 관리</a>
          </li>
          <li>
            <a href="User">사용자 계정 관리</a>
          </li>
        </ul>
    </aside>
	<form action = "changePw"  name="changePw" method = "post">
    <div class="update_account">
      <div class="account_update">
          <span class="material-symbols-outlined close">close</span>
          <h2>정보수정</h2>
          <div class="login_update">
              <p>아이디</p>
              <span>${admin.admin_name}</span>
              <input name ="admin_email" type = "hidden" value="${admin.admin_email}" id="email">
          </div>
          <div class="change_pw">
            <p>새 비밀번호</p>
            <input type="text" name="admin_pw" class="changePw" id="pw">
          </div>
          <div class="change_pw2">
            <p>비밀번호 확인</p>
            <input type="text" name="admin_pw2" class="changePw2" id ="con_pw">
          </div>
          <div id ="check"></div>

          <div class="change_update">
              
              <button type="button" class="change_submit" onclick="change()" id="confirm">확인</button>
          </div>
      </div>
      
  </div>
  </form>
  
  <div style="margin-left: 300px; margin-top: 50px; display: block; position: fixed;  height: 90%; width: 80%;">
      <div style="display:flex; position: absolute; height: 100%; width: 100%; border: 0px solid #000;">
        <div style="text-align: center; align-items: center; display: flex; margin-left: auto; margin-right: auto; font-weight: bold; font-size: 30px;">ADVISER메시지 관리 시스템(CMS)</div>
      </div>
    </div>

      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
      <script src="../js/main.js"></script>
</body>
</html>