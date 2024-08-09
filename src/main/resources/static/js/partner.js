document.addEventListener('DOMContentLoaded', function() {
    fetch('/partner_sidebar.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('sidebar-placeholder').innerHTML = data;
        });
});