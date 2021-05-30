document.addEventListener('DOMContentLoaded', function() {
    localStorage.setItem('github', `${document.getElementById('github').textContent}`);
    var elems = document.querySelectorAll('.tooltipped');
    var instances = M.Tooltip.init(elems);
});
$(document).ready(function() {
    $('input#input_text, textarea#textarea2').characterCounter();
});
$(document).ready(function(){
    $('select').formSelect();
});
document.getElementById('search').addEventListener('input', function(e) {

        fetch(`http://localhost:8080/suggest?query=${document.getElementById('search').value}`,{
            method:'post',
            headers: new Headers({
                'Autorization':'Basic'
            })
        })
            .then((response) => response.json())
            .then((responseJson) => {
                document.getElementById('searchResults').innerHTML="";
                responseJson.forEach(item =>{
                    const {
                        username:username,
                        picturePath:picturePath,
                        id
                    } = item;
                    const card = document.createElement('li');
                    card.innerHTML = `
                            <a href="/profile/${username}" class="follower">
                                <img src="${picturePath}" alt="fff" class="user-avatar">
                                <span class="username">${username}</span>
                            </a>
                        `;
                    document.getElementById('searchResults').append(card);
                });
            });

});
