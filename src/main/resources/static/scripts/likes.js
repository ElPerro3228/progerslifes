var elements = document.getElementsByClassName("likeButton");

for (var i = 0; i < elements.length; i++) {
    elements[i].addEventListener('click', function(e) {
        let $this = $(this);
        let $postId = $this.data('postId');
        let response = fetch(`http://localhost:8080/like`,{
            method:'post',
            headers: new Headers({
                'Autorization':'Basic',
                'Accept':'application/json',
                'Content-Type':'application/json'
            }),
            body: JSON.stringify({
                postId: $postId
            })
        });

        if (response.ok) {
            $this.className += ' pink darken-1';
            $this.className.replace(' likeButton', 'deleteLike');
        }
    }, false);
}


var elements = document.getElementsByClassName("deleteLike");

for (var i = 0; i < elements.length; i++) {
    elements[i].addEventListener('click', function(e) {
        let $this = $(this);
        let $postId = $this.data('postId');
        let response = fetch(`http://localhost:8080/like`,{
            method:'delete',
            headers: new Headers({
                'Autorization':'Basic',
                'Accept':'application/json',
                'Content-Type':'application/json'
            }),
            body: JSON.stringify({
                postId: $postId
            })
        });

        if (response.ok) {
            $this.className.replace(' pink darken-1', '');
            $this.className.replace(' deleteLike', 'likeButton');
        }
    }, false);
}