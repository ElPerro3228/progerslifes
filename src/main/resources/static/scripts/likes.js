var elements = document.getElementsByClassName("likeButton");

for (var i = 0; i < elements.length; i++) {
    elements[i].addEventListener('click', function(e) {
        let postId = e.target.getAttribute("data-post-id");
        if (!e.target.classList.contains("delete")) {
            fetch(`http://localhost:8080/like`,{
                method: 'post',
                headers: new Headers({
                    'Autorization':'Basic',
                    'Accept':'application/json',
                    'Content-Type':'application/json'
                }),
                body: JSON.stringify({
                    postId: postId
                })
            }).then((response) => {
                if (response.ok) {
                    e.target.classList.toggle('delete');
                }
            });
        } else {
            fetch(`http://localhost:8080/like`,{
                method:'delete',
                headers: new Headers({
                    'Autorization':'Basic',
                    'Accept':'application/json',
                    'Content-Type':'application/json'
                }),
                body: JSON.stringify({
                    postId: postId
                })
            }).then((response) => {
                if (response.ok) {
                    e.target.classList.toggle('delete');
                }
            });
        }
    });
}