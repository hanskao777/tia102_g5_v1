document.addEventListener('DOMContentLoaded', function() {
  const sidebarPlaceholder = document.getElementById('sidebar-placeholder');
  if (sidebarPlaceholder) {
    fetch('/b')  
      .then(response => response.text())
      .then(data => {
        sidebarPlaceholder.innerHTML = data;
      })
      .catch(error => console.error('Error loading sidebar:', error));
  }
});