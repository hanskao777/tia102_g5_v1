document.addEventListener('DOMContentLoaded', function() {
    // 初始化所有的 tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });


    // 載入 header
    fetch('/header')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('header').innerHTML = data;
        })
        .catch(error => {
            console.error('Error loading header:', error);
            document.getElementById('header').innerHTML = '<p>Error loading header</p>';
        });

    // 載入 footer
    fetch('/footer')
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            document.getElementById('footer').innerHTML = data;
        })
        .catch(error => {
            console.error('Error loading footer:', error);
            document.getElementById('footer').innerHTML = '<p>Error loading footer</p>';
        });

		// 討論版分類點擊切換
		const listItems = document.querySelectorAll('.list-group-item');
		
		const urlParams = new URLSearchParams(window.location.search);
		const selectedBoardID = urlParams.get('boardID');
		
		if (selectedBoardID) {
		    const selectedBoard = document.querySelector(`.list-group-item[data-board-id="${selectedBoardID}"]`);
		    if (selectedBoard) {
		        updateBreadcrumb(selectedBoard.getAttribute('data-board-name'));
		    }
		} else {
		    updateBreadcrumb('Home');
		}

		listItems.forEach(item => {
			if (item.getAttribute('data-board-id') === selectedBoardID) {
			           item.classList.add('active');
			           updateBreadcrumb(item.getAttribute('data-board-name'));
			       }
				   
		    item.addEventListener('click', function (event) {
		        event.preventDefault();
				
		        // 移除所有項目的 active 類				
		        listItems.forEach(i => i.classList.remove('active'));
				
		        // 為當前點擊的項目添加 active 類
		        this.classList.add('active');
		        
		        // 更新麵包屑
		        updateBreadcrumb(this.getAttribute('data-board-name'));
				
				// 獲取 boardID
				const boardID = this.getAttribute('data-board-id');
					
				// 構建新的 URL
				const currentUrl = new URL(window.location.href);
				if (boardID) {
				  currentUrl.searchParams.set('boardID', boardID);
				} else {
				  currentUrl.searchParams.delete('boardID');
				}
				
				 // 重新載入頁面
				 window.location.href = currentUrl.toString();
		        
		    });
		});
		

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
		// 更新麵包屑導航
		function updateBreadcrumb(boardName) {
		    const currentBoard = document.getElementById('currentBoard');
			if (currentBoard) {
			       currentBoard.textContent = boardName || 'Home';
			   }
		}
		
		// 點擊發文按鈕跳轉發文頁面
		function goToAddArticle() {
		  window.location.href = 'post';
		}
		
		// 文章類別頁籤切換
		document.addEventListener('DOMContentLoaded', function() {
		    const categoryButtons = document.querySelectorAll('.btn-group .btn');
		    const articles = document.querySelectorAll('table tbody tr');

		    categoryButtons.forEach(button => {
		        button.addEventListener('click', function() {
		            // 移除所有按鈕的 active 類
		            categoryButtons.forEach(btn => btn.classList.remove('active'));
		            // 為當前點擊的按鈕添加 active 類
		            this.classList.add('active');

		            const category = this.getAttribute('data-category');
		            filterArticles(category);
		        });
		    });

		    function filterArticles(category) {
		        articles.forEach(article => {
		            if (category === '全部' || article.getAttribute('data-category') === category) {
		                article.style.display = '';
		            } else {
		                article.style.display = 'none';
		            }
		        });
		    }

		    // 初始化時顯示所有文章
		    filterArticles('全部');
		});
		
		
		