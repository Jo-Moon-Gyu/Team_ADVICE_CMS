$(function() {
   $('.reset').click(function() {
      $('.reset_pw').show();
   });

   $('.close').click(function() {
      $('.reset_pw').hide();
   })
});

$(function() {
   $('.btnUpdate').click(function() {
      $('.update_account').show();
   });

   $('.close').click(function() {
      $('.update_account').hide();
   })
});


// 페이징 구현 js 입니다.
const COUNT_PER_PAGE = 6; // 한 페이지 당 최대 6개의 요소를 보여줄 것

const getTotalPageCount = () => {
  return Math.ceil(data.length / COUNT_PER_PAGE);
};

const numberButtonWrapper = document.querySelector('.number-button-wrapper');

const setPageButtons = () => {
  numberButtonWrapper.innerHTML = ''; // 페이지 번호 wrapper 내부를 비워줌

  for (let i = 1; i <= getTotalPageCount(); i++) {
    numberButtonWrapper.innerHTML += `<span class="number-button"> ${i} </span>`;
  }
};

// 페이지당 표시할 게시물 수
const perPage = 6;

// 게시물 행 요소들을 가져옵니다.
const rows = document.querySelectorAll("tbody tr");

// 페이지 버튼 컨테이너와 이전/다음 버튼 요소들을 가져옵니다.
const pageContainer = document.querySelector(".pagination-container");
const prevButton = document.getElementById("prev");
const nextButton = document.getElementById("next");

let currentPage = 1; // 현재 페이지를 추적하는 변수

// 초기 페이지를 표시합니다.
showPage(currentPage);

// 이전 버튼 클릭 시 이전 페이지로 이동합니다.
prevButton.addEventListener("click", () => {
  if (currentPage > 1) {
    currentPage--;
    showPage(currentPage);
  }
});

// 다음 버튼 클릭 시 다음 페이지로 이동합니다.
nextButton.addEventListener("click", () => {
  const totalPages = Math.ceil(rows.length / perPage);
  if (currentPage < totalPages) {
    currentPage++;
    showPage(currentPage);
  }
});

// 페이지를 변경하여 해당 페이지에 해당하는 게시물만 표시하는 함수
function showPage(page) {
  const startIndex = (page - 1) * perPage;
  const endIndex = startIndex + perPage;

  // 모든 게시물 행을 숨깁니다.
  rows.forEach((row) => {
    row.style.display = "none";
  });

  // 현재 페이지에 해당하는 게시물이 있는 경우에만 보이도록 합니다.
  for (let i = startIndex; i < endIndex && i < rows.length; i++) {
    const currentRow = rows[i];
    currentRow.style.display = "table-row";
  }

  // 동적으로 페이지 버튼을 생성합니다.
  createPageButtons();
}

// 동적으로 페이지 버튼을 생성하는 함수
function createPageButtons() {
  const totalPages = Math.ceil(rows.length / perPage);

  // 기존 페이지 버튼을 모두 제거합니다.
  while (pageContainer.firstChild) {
    pageContainer.removeChild(pageContainer.firstChild);
  }

  // 이전 버튼을 추가합니다.
  pageContainer.appendChild(prevButton);

  // 페이지 버튼을 동적으로 생성합니다.
  let startPage = 1;
  let endPage = 5;

  if (currentPage > 5) {
    startPage = Math.floor((currentPage - 1) / 5) * 5 + 1;
    endPage = startPage + 4;
  }

  endPage = Math.min(endPage, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    const numberButton = document.createElement("div");
    numberButton.classList.add("number-button");
    numberButton.textContent = i;
    numberButton.style.margin = "8px";
    numberButton.style.cursor = "pointer";

    numberButton.addEventListener("click", () => {
      document.querySelectorAll(".number-button").forEach((button) => {
        button.classList.remove("clicked");
      });

      // Add the 'clicked' class to the clicked button
      numberButton.classList.add("clicked");

      currentPage = i;
      showPage(currentPage);
    });

    pageContainer.appendChild(numberButton);
  }

  // 다음 버튼을 추가합니다.
  pageContainer.appendChild(nextButton);
}

// 초기 로드 시 페이지 버튼을 생성합니다.
createPageButtons();

// 검색 버튼 클릭 시 또는 Enter 키 입력 시 검색을 수행하는 함수
function performSearch() {
  const searchTerm = searchInput.value.toLowerCase();
  const tableRows = document.querySelectorAll("#contentTable tbody tr");

  // 초기화
  searchResults = [];

  tableRows.forEach((row) => {
    const rowData = row.innerText.toLowerCase();
    if (rowData.includes(searchTerm)) {
      searchResults.push(row);
    }
  });

  // 검색 후 첫 페이지로 이동
  currentPage = 1;
  showSearchResults();
}

// 검색 결과를 보여주는 함수
function showSearchResults() {
  const startIndex = (currentPage - 1) * perPage;
  const endIndex = startIndex + perPage;

  // 모든 게시물 행을 숨깁니다.
  rows.forEach((row) => {
    row.style.display = "none";
  });

  // 현재 페이지에 해당하는 검색 결과 게시물이 있는 경우에만 보이도록 합니다.
  for (let i = startIndex; i < endIndex && i < searchResults.length; i++) {
    const currentRow = searchResults[i];
    currentRow.style.display = "table-row";
  }

  // 동적으로 검색 결과 페이지 버튼을 생성합니다.
  createSearchResultButtons();
}

// 동적으로 검색 결과 페이지 버튼을 생성하는 함수
function createSearchResultButtons() {
  const totalPages = Math.ceil(searchResults.length / perPage);

  // 기존 페이지 버튼을 모두 제거합니다.
  while (pageContainer.firstChild) {
    pageContainer.removeChild(pageContainer.firstChild);
  }

  // 이전 버튼을 추가합니다.
  pageContainer.appendChild(prevButton);

  // 검색 결과 페이지 버튼을 동적으로 생성합니다.
  let startPage = 1;
  let endPage = 5;

  if (currentPage > 5) {
    startPage = Math.floor((currentPage - 1) / 5) * 5 + 1;
    endPage = startPage + 4;
  }

  endPage = Math.min(endPage, totalPages);

  for (let i = startPage; i <= endPage; i++) {
    const numberButton = document.createElement("div");
    numberButton.classList.add("number-button");
    numberButton.textContent = i;
    numberButton.style.margin = "8px";
    numberButton.style.cursor = "pointer";

    numberButton.addEventListener("click", () => {
      document.querySelectorAll(".number-button").forEach((button) => {
        button.classList.remove("clicked");
      });

      // Add the 'clicked' class to the clicked button
      numberButton.classList.add("clicked");

      currentPage = i;
      showSearchResults();
    });

    pageContainer.appendChild(numberButton);
  }

  // 다음 버튼을 추가합니다.
  pageContainer.appendChild(nextButton);
}

// 검색 버튼 클릭 이벤트 리스너 등록
const searchButton = document.getElementById("searchButton");
searchButton.addEventListener("click", performSearch);

// Enter 키 입력 이벤트 리스너 등록
searchInput.addEventListener("keyup", function (event) {
  if (event.key === "Enter") {
    performSearch();
  }
});

// 색 유지 되게 만드는 코드입니다.
document.addEventListener("DOMContentLoaded", function() {
   var links = document.querySelectorAll("ul > li > a");
   var activePage = sessionStorage.getItem("activePage");

   // 페이지 로드 시 저장된 활성 페이지 상태를 확인하여 설정합니다.
   if (activePage) {
      links.forEach(function(link) {
         if (link.getAttribute("href") === activePage) {
            link.classList.add("active");
         }
      });
   }

   // 각 링크에 클릭 이벤트를 추가합니다.
   links.forEach(function(link) {
      link.addEventListener("click", function(event) {
         // 클릭된 링크에 active 클래스를 추가합니다.
         links.forEach(function(otherLink) {
            otherLink.classList.remove("active");
         });
         link.classList.add("active");

         // 클릭한 페이지의 상태를 세션 스토리지에 저장합니다.
         sessionStorage.setItem("activePage", link.getAttribute("href"));
      });
   });
});

/* 게시글이 없을때 나오는 부분 입니다. */
document.addEventListener("DOMContentLoaded", function() {
   // 여기에 게시물 데이터를 동적으로 추가하는 로직을 구현
   // 예를 들어, 게시물이 없으면 아래와 같이 메시지와 페이징 버튼들을 숨김
   const noPostsMessage = document.getElementById("noPostsMessage");
   const paginationContainer = document.getElementById("paginationContainer");
   const tableHead = document.getElementById("tableHead");
   //const buttonContainer = document.getElementById("buttonContainer");

   if (document.querySelectorAll("#contentTable tbody tr").length === 0) {
      noPostsMessage.style.display = "block";
      paginationContainer.style.visibility = "hidden";
      tableHead.style.display = "none";
      //buttonContainer.style.display = "none";
   } else {
      noPostsMessage.style.display = "none";
      paginationContainer.style.visibility = "visible";
      tableHead.style.display = "table-header-group";
      // buttonContainer.style.display = "block";
   }
});


function MainPage() {

   location.href = "Main";
}


function change() {

   //var id = document.getElementById("id");
   //var pw = document.getElementById("id");

   var email = document.getElementById("email").value;
   var pw = document.getElementById("pw").value;
   var conPw = document.getElementById("con_pw").value;
   var admin_pw = document.getElementById("pw");
   var admin_pw2 = document.getElementById("con_pw");
   var check = document.getElementById("check");
   if (pw != conPw) {


      check.innerHTML = "비밀번호가 일치하지 않습니다"
      check.style.color = 'red';

   } else {
      document.changePw.submit();
   }




}

function submitForm() {
   const selection = document.getElementById("selection").value;
   console.log(selection);
   if (selection === "전체") {
        window.location.href = window.location.href;
        return; // 이후 코드 실행을 막기 위해 반환
    }
   
   
   
   $.ajax({
      type: 'POST',
      url: 'User',

      data: {
         "userRole": selection
      },
      success: updateTable,
   });
   // AJAX 요청을 생성합니다.


}

function updateTable(res) {
   console.log(res)

   // 1. 테이블 내용 초기화 
   const tbody = $('tbody');

   // .html() : 해당 태그 안에 있는 코드 리턴
   // .html("text") : 해당 태그 안에 있는 코드 덮어쓰기 
   tbody.html("");

   // 2. 받아온 json 데이터를 이용해서, 테이블 내용 구성
   for (var i = 0; i < res.length; i++) {
      let user = res[i];
 let joinedAtShort = user.joinedAt.substring(0, 10);
let approvedAtShort = user.approvedAt ? user.approvedAt.substring(0, 10) : '';

      // ${변수명} : 백틱 내에서, 해당 자리에 변수값을 집어넣기 위해 사용 
      let tr = `
      
         <tr>
             <td>${i + 1}</td>
                        <td><a href="UserInfo?user_email=${user.userEmail}">${user.userEmail}</a></td>
                        <td>${user.userName}</td>
                        <td>${joinedAtShort}</td>
                        <td>${approvedAtShort}</td>
         </tr>
         
      `;


      // .append("text") : 해당 태그 안에 코드 추가 

      tbody.append(tr);
   }

}