let server = document.getElementById('server'),
    sql = document.getElementById('sql'),
    solr = document.getElementById('solr'),
    solrUp = document.getElementById('solrUp');
solrUp.addEventListener('click',()=>{
    const url = 'http://localhost:8080/data/import?command=full-import';
    const response = fetch(url, {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'accept': 'application/json',
            'Autorization':'Basic'
        }
    });
})
document.addEventListener('DOMContentLoaded', function(e) {
    fetch(`http://localhost:8080/actuator/health`,{
        method:'get',
        headers: new Headers({
            'Autorization':'Basic'
        })
    })
        .then((response) => response.json())
        .then((responseJson) => {
            console.log(responseJson);
            if (responseJson.status == "UP"){
                server.src='/img/succsesss.png';
            } else{
                server.src='/img/error.png';
            }
            if (responseJson.components.solr.status =="UP"){
                solr.src='/img/succsesss.png';
            } else{
                solr.src='/img/error.png';
            }
            if (responseJson.components.db.status=="UP"){
                sql.src='/img/succsesss.png';
            } else{
                sql.src='/img/error.png';
            }
        });

});