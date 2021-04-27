document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.tooltipped');
    var instances = M.Tooltip.init(elems, options);
});
$(document).ready(function() {
    $('input#input_text, textarea#textarea2').characterCounter();
});