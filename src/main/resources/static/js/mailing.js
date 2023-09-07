fetch('https://script.google.com/macros/s/AKfycbzxN8GN14UcnQ2VQm4ZMUWygsPzxFh813cYjqmm3eCHSYQp2oLnSsYvI0bMg-QwRxE/exec', {
    method: 'POST',
    body: JSON.stringify(formDataObject),
    headers: {
        'Content-Type': 'application/json'
    }
})
    .then(response => response.json())
    .then(data => {
        if (data.result === 'success') {
            alert('양식이 성공적으로 제출되었습니다!');
            // 성공적으로 제출된 후 추가 동작을 수행할 수 있습니다.
        } else {
            alert('양식 제출 중 오류가 발생했습니다. 나중에 다시 시도해주세요.');
        }
    })
    .catch(error => {
        console.error('오류:', error);
        alert('오류가 발생했습니다. 나중에 다시 시도해주세요.');
    });

//결과를 json타입으로 보여주는 문제 포함 코드
