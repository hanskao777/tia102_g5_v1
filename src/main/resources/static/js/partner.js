document.addEventListener('DOMContentLoaded', function() {
    fetch('/b')
        .then(response => response.text())
        .then(data => {
            document.getElementById('sidebar-placeholder').innerHTML = data;
        });
});