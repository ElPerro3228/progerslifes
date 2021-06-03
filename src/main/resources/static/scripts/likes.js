var elements = document.getElementsByClassName("likeButton");

for (var i = 0; i < elements.length; i++) {
    elements[i].addEventListener('click', function(e) {
        let $this = $(this);
        let postId = $this.data('postId');
        if (!$this[0].classList.contains("delete")) {
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
            })
                .then((response) => {
                    if (response.ok) {
                        $this[0].classList.toggle('delete');
                        return response.json();
                    }
            })
                .then((responseJson) => {
                    $this[0].children[1].textContent = `${responseJson.postLikes}`;
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
            })
                .then((response) => {
                    if (response.ok) {
                        $this[0].classList.toggle('delete');
                        return response.json();
                    }
            })
                .then((responseJson) => {
                    $this[0].children[1].textContent = `${responseJson.postLikes}`;
                });
        }
    });
}