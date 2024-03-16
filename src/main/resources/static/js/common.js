function handleLinkClick(event) {
    event.preventDefault();
    alert('준비중인 서비스입니다.');
}

var mypage = document.getElementById('mypage');
mypage.addEventListener('click', handleLinkClick);

var bookClub = document.getElementById('book-club');
bookClub.addEventListener('click', handleLinkClick);