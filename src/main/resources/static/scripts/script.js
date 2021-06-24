document.addEventListener('DOMContentLoaded', function() {
    localStorage.setItem('github', `${document.getElementById('github').textContent}`);
    var elems = document.querySelectorAll('.tooltipped');
    var instances = M.Tooltip.init(elems);
});
$(document).ready(function() {
    $('input#input_text, textarea#textarea2').characterCounter();
});
// $( "#comments" ).click(function() {
//     $( "#comments" ).parent(".post").children(".comments").hide("slow");
// });
document.querySelectorAll('#comments').forEach(item =>{
    item.addEventListener('click',(e)=>{
        let parent = e.target.closest(".post");
        let parentComments = parent.children[3];
        // let parentComments = $(parent).find(".comment");
        parentComments.classList.toggle("close");
    })
});
document.querySelectorAll('#deleteArrow').forEach(item =>{
    item.addEventListener('click',(e)=>{
        $item = $(item);
        $deletePost = $item.find("deletePost");
        $deletePost.toggle(function () {
            $deletePost.css({display: "block"});
        }, function () {
            $deletePost.css({display: "none"});
        });
    })
});
$(document).ready(function(){
    $('.materialboxed').materialbox();
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


$("input[class='deletePost']").click(function(){
    var $this = $(this);
    var $post = $this.closest(".post");
    var postId = $post.data('postId');

    $.ajax({
        type : "DELETE",
        contentType : "application/json",
        url : "/post",
        data : JSON.stringify(postId),
        dataType : 'json',
        success : function(data) {
            $post.remove();
        },
        error: function (request, status, error) {
        }
    });
});
