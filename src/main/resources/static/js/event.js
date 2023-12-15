function toggleCheckbox(index) {
  // 여기에 체크박스 처리 코드 추가
}

function btnToggle(index) {
  const contentCell = document.querySelector(`#contentTable tbody tr:nth-child(${index + 1})`);
  const btnGroup = document.querySelector(`.btnGroup`);

  if (btnGroup.querySelector('.btn_disabled')) {
    // 비활성화 버튼을 클릭한 경우
    contentCell.classList.add("completed");
    btnGroup.innerHTML = '<input type="button" class="btn_activate" value="활성화" onclick="btnToggle(0)">';

  } else {
    // 활성화 버튼을 클릭한 경우
    contentCell.classList.remove("completed");
    btnGroup.innerHTML = '<input type="button" class="btn_disabled" value="비활성화" onclick="btnToggle(0)">';
  }
}

// 취소선을 추가하는 함수
function addStrikeThrough(index) {
  const contentCell = document.querySelector(`#contentTable tbody tr:nth-child(${index + 1})`);
  contentCell.classList.add("completed");
}

// 취소선을 제거하는 함수
function removeStrikeThrough(index) {
  const contentCell = document.querySelector(`#contentTable tbody tr:nth-child(${index + 1})`);
  contentCell.classList.remove("completed");
}
