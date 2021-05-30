let server = document.getElementById('server'),
    sql = document.getElementById('sql'),
    solr = document.getElementById('solr');
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