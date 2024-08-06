const { useState, useRef, useEffect } = React;

const ArticleEditor = () => {
    const [title, setTitle] = useState('');
    const [errors, setErrors] = useState({});
    const [images, setImages] = useState(null);
    const summernoteRef = useRef(null);
    const fileInputRef = useRef(null);

    useEffect(() => {
        // Initialize Summernote
        $(summernoteRef.current).summernote({
            height: 300, // Set editor height
            toolbar: [
                ['style', ['bold', 'italic', 'underline']],
                ['color', ['color']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['view', ['fullscreen', 'codeview']]
            ]
        });

        // Cleanup function to destroy Summernote instance
        return () => {
            $(summernoteRef.current).summernote('destroy');
        };
    }, []);

    const handleColorChange = (e) => {
        const color = e.target.value;
        $(summernoteRef.current).summernote('editor.insertText', `<span style="color: ${color};">`);
    };

    const onImageUpload = (e) => {
        const files = Array.from(e.target.files);
        if (files.length > 5) {
            setErrors({ images: '最多只能上傳5張圖片' });
            return;
        }
        const oversizedFiles = files.filter(file => file.size > 8 * 1024 * 1024);
        if (oversizedFiles.length > 0) {
            setErrors({ images: '圖片大小不能超過8MB' });
            return;
        }
        setImages(files);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const content = $(summernoteRef.current).summernote('code');
        const formData = new FormData();
        formData.append('articleTitle', title.trim());
        formData.append('articleContent', content);

        if (images) {
            images.forEach((image, index) => {
                formData.append(`articlePic${index}`, image);
            });
        }

        // Perform the fetch request or any other submission logic here
        // Example:
        // fetch('/article/insert', {
        //     method: 'POST',
        //     body: formData
        // })
        // .then(response => response.json())
        // .then(data => console.log(data))
        // .catch(error => console.error('Error:', error));
    };

    return (
        <div className='container'>
            <div className='form-container'>
                <form onSubmit={handleSubmit} encType='multipart/form-data'>
                    <div>
                        <label htmlFor='board'>文章版塊:</label>
                        <select id='board' name='boardID'>
                            {/* Add options dynamically */}
                        </select>
                        {/* Handle errors for board */}
                    </div>
                    
                    <div>
                        <label htmlFor='articleCategory'>文章類型:</label>
                        <select id='articleCategory' name='articleCategory'>
                            {/* Add options dynamically */}
                        </select>
                        {/* Handle errors for category */}
                    </div>
                    
                    <div>
                        <label htmlFor='articleTitle'>文章標題:</label>
                        <input
                            type='text'
                            id='articleTitle'
                            name='articleTitle'
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                        />
                        {/* Handle errors for title */}
                    </div>

                    <div className='editor-container'>
                        <textarea ref={summernoteRef} name='articleContent'></textarea>
                    </div>
                    
                    <input
                        type='file'
                        ref={fileInputRef}
                        accept='image/*'
                        onChange={onImageUpload}
                        multiple
                    />
                    {/* Handle errors for images */}

                    <button type='submit'>送出文章</button>
                </form>
            </div>
        </div>
    );
};

ReactDOM.render(
    <ArticleEditor />,
    document.getElementById('root')
);
