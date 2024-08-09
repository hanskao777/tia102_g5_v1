function initializeSidebar() {
    const submenuToggles = document.querySelectorAll('[data-toggle="submenu"]');
    submenuToggles.forEach(toggle => {
        toggle.addEventListener('click', function(e) {
            e.preventDefault();
            const targetId = this.getAttribute('data-target');
            const targetSubmenu = document.getElementById(targetId);
            targetSubmenu.classList.toggle('active');
        });
    });

    const navLinks = document.querySelectorAll('.nav-link[data-section]');
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const targetSection = this.getAttribute('data-section');
            showSection(targetSection);
        });
    });
}

function showSection(sectionId) {
    const sections = document.querySelectorAll('.section');
    sections.forEach(section => {
        section.style.display = 'none';
    });
    const targetSection = document.getElementById(sectionId);
    if (targetSection) {
        targetSection.style.display = 'block';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const sidebarPlaceholder = document.getElementById('sidebar-placeholder');
    if (sidebarPlaceholder && sidebarPlaceholder.children.length > 0) {
        initializeSidebar();
    } else {
        const observer = new MutationObserver(function(mutations) {
            if (sidebarPlaceholder.children.length > 0) {
                initializeSidebar();
                observer.disconnect();
            }
        });
        observer.observe(sidebarPlaceholder, {childList: true});
    }
});