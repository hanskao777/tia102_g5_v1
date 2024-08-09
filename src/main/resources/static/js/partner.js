document.addEventListener('DOMContentLoaded', function() {
    fetch('/sidebar.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('sidebar-placeholder').innerHTML = data;
        });
});