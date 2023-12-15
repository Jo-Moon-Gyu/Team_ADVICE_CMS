<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <title>사용자 계정 관리</title>
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
            <a href="QnA">조언 서비스 관리</a>
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

      <header>
        <section>
            <div class="contants_position">
                <h1>사용자 계정 관리</h1>
                <h3>가입한 계정들을 관리하는 페이지 입니다.</h3>
            </div>
        </section>
      </header>

      <section class="container_2">
        <div class="contants_list">
            <span>사용자 계정 관리</span>
            <div class="search_list">
              <input type="text" class="search" name="search" id="searchInput">
              <button class="btn-info" id="searchButton">검색</button>
            </div>
        </div>
      </section>
      
      
      <sction class="select">
        <div class="select_list">
          <select name="selection" id="selection" onchange="submitForm()">
            <option value="전체">전체</option>
            <option value="사용자">사용자</option>
            <option value="의대생">의대생</option>
          </select>
        </div>
      </sction>


      <section class="table_list">
        <div id="custom-responsive-table">
          <table class="table table-striped" id="contentTable">
            <thead class="bg-light">
              <tr>
               <th>No</th>
                <th>이메일</th>
                <th>이름</th>
                <th>가입일</th>
                <th>승인일</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${user}" varStatus="status">
            
                     <tr>
                        <td>${status.count}</td>
                        <td><a href="UserInfo?user_email=${user.userEmail}">${user.userEmail}</a></td>
                        <td>${user.userName}</td>
                        <td>${fn:substring(user.joinedAt, 0, 10)}</td>
                        <td>${fn:substring(user.approvedAt, 0, 10)}</td>
                     </tr>

                  </c:forEach>
            
            </tbody>
          </table>
        </div>
       <div class="pagination-container" id="paginationContainer">
            <div id="prev" class="prev-button material-symbols-outlined">chevron_left</div>
            <div class="number-button-wrapper"><span class="number-button">1</span></div>
            <div class="number-button-wrapper"><span class="number-button">2</span></div>
            <div class="number-button-wrapper"><span class="number-button">3</span></div>
            <div class="number-button-wrapper"><span class="number-button">4</span></div>
            <div class="number-button-wrapper"><span class="number-button">5</span></div>
            <div id="next" class="next-button material-symbols-outlined">chevron_right</div>
          </div>

      </section>        
      
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
       <script src="../js/main.js"></script>
    <script src="../js/event.js"></script>
    </body>
</html>