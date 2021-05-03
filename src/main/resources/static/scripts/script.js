document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.tooltipped');
    var instances = M.Tooltip.init(elems);
});
$(document).ready(function() {
    $('input#input_text, textarea#textarea2').characterCounter();
});
document.getElementById('search').addEventListener('input',()=>{
    fetch(`http://localhost:8080/suggest?query=${document.getElementById('search').value}`,{
        method:'post',
        headers: new Headers({
            'Autorization':'Basic'
        })
    })
        .then((response) => response.json())
        .then((responseJson) => {
            console.log(responseJson, 'res JSON');
            if (responseJson.status == "success") {
                console.log(this.state);
                alert("okeYYYY!!");
            }
        });
})