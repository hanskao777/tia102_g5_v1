document.addEventListener('DOMContentLoaded', function() {
    fetch('/partnerSidebar')
        .then(response => response.text())
        .then(data => {
            document.getElementById('sidebar-placeholder').innerHTML = data;
        });
});