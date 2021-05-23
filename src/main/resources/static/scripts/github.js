let gitHub = localStorage.github;
let gitUsername = document.getElementById('gitUsername'),
    gitImg = document.getElementById('gitAvatar'),
    gitDesc = document.getElementById('gitDesc'),
    gitLocation = document.getElementById('gitLocation'),
    gitFollowers = document.getElementById('gitFollowers'),
    gitFollowing = document.getElementById('gitFollowing');

document.addEventListener('DOMContentLoaded', function(e) {
    fetch(`https://api.github.com/users/${gitHub}`)
        .then((response) => response.json())
        .then((responseJson) => {
            gitUsername.textContent=responseJson.login;
            gitImg.src=responseJson.avatar_url;
            gitDesc.textContent=responseJson.bio;
            gitFollowers.textContent=`Followwers ${responseJson.followers}`;
            gitFollowing.textContent=`Following ${responseJson.following}`;
            gitLocation.textContent=`Location ${responseJson.location}`;
        });

});

document.addEventListener('DOMContentLoaded', function(e) {
    fetch(`https://api.github.com/users/${gitHub}/repos`)
        .then((response) => response.json())
        .then((responseJson) => {
            document.getElementById('searchResults').innerHTML="";
            responseJson.forEach(item =>{
                const {
                    full_name:full_name,
                    description:description,
                    language:language,
                    svn_url:svn_url
                } = item;
                const card = document.createElement('li');
                card.innerHTML = `
                            <a href="${item.svn_url}" class="repo" target="_blank">
                                <span class="name">${item.full_name}</span>
                                <span class="desc">${item.description}</span>
                                <span class="lang">${item.language}</span>
                            </a>
                        `;
                document.getElementById('gitRepos').append(card);
            });
        });
});