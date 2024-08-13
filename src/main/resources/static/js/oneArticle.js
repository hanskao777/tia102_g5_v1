

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


/*按讚和收藏功能*/
$(document).ready(function() {

	/*測試-載入會員選項到當前會員選擇下拉選單*/
	loadMembersForCurrentUser();

	/*測試-當選擇當前會員時，更新按鈕狀態*/
	$('#currentMemberSelect').change(function() {
		updateAllButtonsState();
	});


	/*通用的文章互動函數*/
	/*原有的JS 因測試會員先隱藏
	function handleArticleInteraction(buttonClass, toggleUrl, countUrl) {
		$(buttonClass).click(function() {
			var $btn = $(this);
			var articleId = $btn.data('articleid');
			var memberId = $btn.data('memberid');
		    
			$.ajax({
				url: toggleUrl,
				method: 'POST',
				data: {
					memberID: memberID,
					articleID: articleID
				},
				success: function(response) {
					updateButtonUI($btn, response);
					updateInteractionCount($btn, countUrl, articleID);
				},
				error: function(xhr, status, error) {
					console.error("Error: " + error);
					alert("操作失敗，請稍後再試。");
				}
			});
		});
	}*/

	function handleArticleInteraction(buttonClass, toggleUrl, countUrl, statusUrl) {
		$(buttonClass).each(function() {
			var $btn = $(this);
			var articleID = $btn.data('articleid');
			updateButtonState($btn, articleID, statusUrl);
		});

		$(buttonClass).click(function() {
			var $btn = $(this);
			var articleID = $btn.data('articleid');
			var memberID = $('#currentMemberSelect').val();

			if (!memberID) {
				alert('請先選擇當前會員');
				return;
			}

			$.ajax({
				url: toggleUrl,
				method: 'POST',
				data: {
					memberID: memberID,
					articleID: articleID
				},
				success: function(response) {
					updateButtonUI($btn, response);
					updateInteractionCount($btn, countUrl, articleID);
				},
				error: function(xhr, status, error) {
					console.error("Error: " + error);
					alert("操作失敗，請稍後再試。");
				}
			});
		});
	}

	/*更新按鈕狀態*/
	function updateButtonUI($btn, isActive) {
		var $icon = $btn.find('i');
		if (isActive) {
			$icon.removeClass('far').addClass('fas').css('color', '#ff0000');
		} else {
			$icon.removeClass('fas').addClass('far').css('color', '');
		}
	}

	// 測試會員：更新按鈕狀態
	function updateButtonState($btn, articleID, statusUrl) {
		var memberID = $('#currentMemberSelect').val();
		if (!memberID) return;

		$.get(statusUrl + articleID + '/' + memberID, function(isActive) {
			updateButtonUI($btn, isActive);
		});
	}



	// 測試會員：更新所有按鈕狀態
	function updateAllButtonsState() {
		$('.heart-btn').each(function() {
			updateButtonState($(this), $(this).data('articleid'), '/heart/status/');
		});
		$('.bookmark-btn').each(function() {
			updateButtonState($(this), $(this).data('articleid'), '/articleCollection/status/');
		});
	}

	// 測試會員：載入會員選項到當前會員選擇下拉選單
	function loadMembersForCurrentUser() {
		$.ajax({
			url: '/messages/members',
			type: 'GET',
			success: function(response) {
				var select = $('#currentMemberSelect');
				select.empty();
				select.append($('<option>', {
					value: '',
					text: '請選擇當前會員'
				}));

				var generalMemberListData = response.generalMemberListData;

				if (Array.isArray(generalMemberListData) && generalMemberListData.length > 0) {
					generalMemberListData.forEach(function(generalmember) {
						select.append($('<option>', {
							value: generalmember.memberID,
							text: generalmember.memberNickName
						}));
					});
				} else {
					console.log("No members received or invalid data format");
				}
			},
			error: function(xhr, status, error) {
				console.error('載入會員列表失敗:', error);
			}
		});
	}

	/*更新統計*/
	function updateInteractionCount($btn, countUrl, articleID) {
		$.get(countUrl + articleID, function(count) {
			$btn.find('.count').text(count);
		});
	}

	/*初始化按讚功能*/
	handleArticleInteraction('.heart-btn', '/heart/toggle', '/heart/count/', '/heart/status/');

	/*初始化收藏功能*/
	handleArticleInteraction('.bookmark-btn', '/articleCollection/toggle', '/articleCollection/count/', '/articleCollection/status/');
});

// 文章檢舉按鈕點擊事件
$(document).on('click', '.report-btn', function() {
	const articleID = $(this).data('articleid');
	const memberID = $(this).data('memberid');
	// 在這裡添加檢舉文章的邏輯
	console.log(`檢舉文章: articleID=${articleID}, memberID=${memberID}`);
});




/*留言功能*/
$(document).ready(function() {
	loadComments();
	loadMembers();

	$('#comment-form').submit(function(e) {
		e.preventDefault();
		submitComment();
	});
});

/*載入會員*/
function loadMembers() {

	$.ajax({
		url: '/messages/members',
		type: 'GET',
		success: function(response) {
			var select = $('#memberSelect');
			select.empty();
			select.append($('<option>', {
				value: '',
				text: '請選擇會員'
			}));

			var generalMemberListData = response.generalMemberListData;

			if (Array.isArray(generalMemberListData) && generalMemberListData.length > 0) {
				generalMemberListData.forEach(function(generalmember) {
					select.append($('<option>', {
						value: generalmember.memberID,
						text: generalmember.memberNickName
					}));
				});
			} else {
				console.log("No members received or invalid data format");
			}
		},
		error: function(xhr, status, error) {
			console.error('載入會員列表失敗:', error);
			console.log("Response status:", xhr.status);
			console.log("Response text:", xhr.responseText);
		}
	});
}

/*載入留言*/
function loadComments() {
	var articleID = $('#articleID').val();
	$.ajax({
		url: '/messages/list/' + articleID,
		type: 'GET',
		success: function(response) {
			displayComments(response);
			updateCommentCount(response.length);/*更新留言數量*/
		},
		error: function(xhr, status, error) {
			console.error('載入留言失敗:', error);
		}
	});
}

/*送出留言*/
function submitComment() {
	var articleID = $('#articleID').val();  /*使用當前頁面的文章ID*/
	var memberID = $('#memberSelect').val();
	var content = $('#commentContent').val();

	if (!articleID) {
		alert('無法獲取文章 ID');
		return;
	}

	if (!memberID) {
		alert('請選擇會員');
		return;
	}

	var message = {
		article: {
			articleID: parseInt(articleID)
		},
		generalMember: {
			memberID: parseInt(memberID)
		},
		messageContent: content
	};


	$.ajax({
		url: '/messages/insert',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(message),
		success: function(response) {
			$('#commentContent').val('');
			$('#memberSelect').val('');
			loadComments();
		},
		error: function(xhr, status, error) {
			console.error('提交留言失敗:', error);
			console.log('Error details:', xhr.responseText);
			if (xhr.responseJSON && Array.isArray(xhr.responseJSON)) {
				alert('提交失敗: ' + xhr.responseJSON.map(e => e.defaultMessage).join(', '));
			} else if (xhr.responseText) {
				alert('提交失敗: ' + xhr.responseText);
			} else {
				alert('提交失敗，請稍後再試');
			}
		}
	});
}

/*顯示留言*/
function displayComments(comments) {
	var commentsList = $('#comments-list');
	commentsList.empty();

	comments.forEach(function(comment) {
		var commentDate = new Date(comment.messageCreateTime);
		var formattedDate = commentDate.getFullYear() + '-' +
			('0' + (commentDate.getMonth() + 1)).slice(-2) + '-' +
			('0' + commentDate.getDate()).slice(-2) + ' ' +
			('0' + commentDate.getHours()).slice(-2) + ':' +
			('0' + commentDate.getMinutes()).slice(-2);

		var formattedContent = comment.messageContent.replace(/\n/g, '<br>');

		/*會員頭像,會員有圖片顯示該會員圖片,沒有則顯示預設圖標*/
		var memberPictureUrl = '/messages/picture/' + comment.generalMember.memberID;
		var memberPictureHtml = '<img src="' + memberPictureUrl + '" alt="會員圖片" class="member-picture" onerror="this.onerror=null; this.parentNode.innerHTML=\'<i class=\\\'fas fa-user-circle comment-avatar-icon\\\'></i>\';">';

		var commentHtml = '<div class="card mb-3">' +
			'<div class="card-body">' +
			'<div class="d-flex justify-content-between align-items-center mb-2">' +
			'<div class="d-flex align-items-center">' +
			'<div class="avatar-container">' + memberPictureHtml + '</div>' +
			'<h5 class="card-title mb-0 ml-2">' + comment.generalMember.memberNickName + '</h5>' +
			'</div>' +
			'<small class="text-muted">' + formattedDate + '</small>' +
			'</div>' +
			'<p class="card-text">' + formattedContent + '</p>' +
			'<div class="d-flex justify-content-end">' +
			'<button class="btn btn-sm btn-outline-danger report-comment-btn" data-message-id="' + comment.messageID + '">' +
			'<i class="fa-solid fa-shield-halved"></i> 檢舉' +
			'</button>'
			'</div>' +
			'</div>' +
			'</div>';
		commentsList.append(commentHtml);
	});
	updateCommentCount(comments.length);/*呼叫函數更新留言數量*/
}

/*更新留言數量*/
function updateCommentCount(count) {
	$('#comment-count').text(count);
}

// 留言檢舉按鈕點擊事件
$(document).on('click', '.report-comment-btn', function() {
    const messageID = $(this).data('message-id');
    
    // 獲取當前選擇的會員ID（檢舉人）
    const reporterID = $('#currentMemberSelect').val();
    
    if (!reporterID) {
        alert('請先選擇當前會員');
        return;
    }

    // 彈出輸入框讓用戶填寫檢舉原因
    const prosecuteReason = prompt('請輸入檢舉原因（2-50字，只能包含中文、英文、數字和底線）：');
    
    if (prosecuteReason === null) {
        return; // 用戶取消了輸入
    }

    // 檢查prosecuteReason是否符合規則
    if (!/^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$/.test(prosecuteReason)) {
        alert('檢舉原因格式不正確，請重新輸入。');
        return;
    }

    if (confirm('您確定要檢舉這則留言嗎？')) {
        const data = JSON.stringify({
            message: { messageID: parseInt(messageID) },
            generalMember: { memberID: parseInt(reporterID) },
            prosecuteReason: prosecuteReason
        });
        
		console.log('Sending data:', data);

		        $.ajax({
		            url: '/prosecutes',
		            method: 'POST',
		            contentType: 'application/json',
		            data: data,
		            success: function(response) {
		                alert('留言檢舉已提交，感謝您的回報。');
		                // 更新UI，例如禁用檢舉按鈕
		                $('.report-comment-btn[data-message-id="' + messageID + '"]').prop('disabled', true).text('已檢舉');
		            },
		            error: function(xhr, status, error) {
		                console.error('留言檢舉提交失敗:', error);
		                console.error('Status:', status);
		                console.error('Response:', xhr.responseText);
		                alert('檢舉失敗，請稍後再試。');
		            }
		        });
    }
});

// 文章檢舉按鈕點擊事件
$(document).on('click', '.report-btn', function() {
    const articleID = $(this).data('articleid');
    
    // 獲取當前選擇的會員ID（檢舉人）
    const reporterID = $('#currentMemberSelect').val();
    
    if (!reporterID) {
        alert('請先選擇當前會員');
        return;
    }

    // 彈出輸入框讓用戶填寫檢舉原因
    const prosecuteReason = prompt('請輸入檢舉原因（2-50字，只能包含中文、英文、數字和底線）：');
    
    if (prosecuteReason === null) {
        return; // 用戶取消了輸入
    }

    // 檢查prosecuteReason是否符合規則
    if (!/^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,50}$/.test(prosecuteReason)) {
        alert('檢舉原因格式不正確，請重新輸入。');
        return;
    }

    if (confirm('您確定要檢舉這篇文章嗎？')) {
        const data = JSON.stringify({
            article: { articleID: parseInt(articleID) },
            generalMember: { memberID: parseInt(reporterID) },
            prosecuteReason: prosecuteReason
        });
        
        console.log('Sending data:', data);

        $.ajax({
            url: '/prosecutes',
            method: 'POST',
            contentType: 'application/json',
            data: data,
            success: function(response) {
                alert('文章檢舉已提交，感謝您的回報。');
                // 更新UI，例如禁用檢舉按鈕
                $('.report-btn[data-articleid="' + articleID + '"]').prop('disabled', true).text('已檢舉');
            },
			error: function(xhr, status, error) {
			         console.error('文章檢舉提交失敗:', error);
			         console.error('Status:', status);
			         console.error('Response:', xhr.responseText);
			         
			         let errorMessage = '檢舉失敗，請稍後再試。';
			         try {
			             const response = JSON.parse(xhr.responseText);
			             if (response.message) {
			                 errorMessage = response.message;
			                 if (errorMessage === "此文章已被檢舉") {
			                     // 更新UI，例如禁用檢舉按鈕
			                     $('.report-btn[data-articleid="' + articleID + '"]').prop('disabled', true).text('已檢舉');
			                 }
			             }
			         } catch (e) {
                        console.error('解析錯誤訊息失敗', e);
                    
                }
                alert(errorMessage);
            }
        });
    }
});

//檢查文章是否已被檢舉
function checkArticleReportStatus(articleID) {
    $.ajax({
        url: `/prosecutes/article/${articleID}`,
        method: 'GET',
        success: function(isReported) {
            if (isReported) {
                // 如果文章已被檢舉，可以禁用檢舉按鈕或顯示已檢舉的狀態
                $('.report-btn[data-articleid="' + articleID + '"]').prop('disabled', true).text('已檢舉');
            }
        },
        error: function(xhr, status, error) {
            console.error('檢查文章檢舉狀態失敗:', error);
        }
    });
}

//檢查留言是否已被檢舉
function checkMessageReportStatus(messageID) {
    $.ajax({
        url: `/prosecutes/message/${messageID}`,
        method: 'GET',
        success: function(isReported) {
            if (isReported) {
                // 如果留言已被檢舉，可以禁用檢舉按鈕或顯示已檢舉的狀態
                $('.report-comment-btn[data-message-id="' + messageID + '"]').prop('disabled', true).text('已檢舉');
            }
        },
        error: function(xhr, status, error) {
            console.error('檢查留言檢舉狀態失敗:', error);
        }
    });
}