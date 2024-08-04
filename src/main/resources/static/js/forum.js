document.addEventListener('DOMContentLoaded', function() {
    // 初始化所有的 tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl)
    })

    // 載入 header
    fetch('header.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('header').innerHTML = data;
        })
        .catch(error => console.error('Error loading header:', error));

    // 載入 footer
    fetch('footer.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('footer').innerHTML = data;
        })
        .catch(error => console.error('Error loading footer:', error));

    // 各版類別點擊切換
    const listItems = document.querySelectorAll('.list-group-item');
    
    listItems.forEach(item => {
        item.addEventListener('click', function (event) {
            event.preventDefault();
            updateBreadcrumb(this.dataset.category);
            // 這裡可以添加其他操作，例如加載相應類別的內容
            console.log('Selected category:', this.dataset.category);
        });
    });
    
    function updateBreadcrumb(category) {
        const breadcrumb = document.querySelector('.breadcrumb-item.active');
        if (breadcrumb) {
            breadcrumb.textContent = category;
        }
    }

    // 文章類別點擊切換
    const btnGroup = document.querySelector('.btn-group');
    const buttons = btnGroup.querySelectorAll('.btn');

    buttons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();
            buttons.forEach(btn => btn.classList.remove('active'));
            this.classList.add('active');
            console.log('Selected category:', this.textContent);
        });
    });
});