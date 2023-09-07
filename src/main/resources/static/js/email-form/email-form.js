// document.addEventListener("DOMContentLoaded", function() {
//     const emailForm = document.getElementById("emailForm");
//
//     emailForm.addEventListener("submit", function(event) {
//         event.preventDefault(); // 기본 제출 동작을 막음
//
//         // 폼 데이터 가져오기
//         const formData = new FormData(emailForm);
//
//         // 이메일을 서버로 전송
//         fetch("/send-email", {
//             method: "POST",
//             body: formData,
//         })
//             .then(response => response.json())
//             .then(data => {
//                 if (data.success) {
//                     alert("이메일이 성공적으로 발송되었습니다.");
//                 } else {
//                     alert("이메일 발송에 실패하였습니다.");
//                 }
//             })
//             .catch(error => {
//                 console.error("이메일 발송 중 오류 발생:", error);
//                 alert("이메일 발송 중 오류가 발생하였습니다.");
//             });
//     });
// });

//----------------------------------------
//
// document.getElementById('contactForm').addEventListener('submit', function(e) {
//     e.preventDefault();
//     var formData = new FormData(e.target);
//     var formDataObject = {};
//     formData.forEach(function (value, key) {
//         formDataObject[key] = value;
//     });
//
//     fetch('https://script.google.com/macros/s/AKfycbzxN8GN14UcnQ2VQm4ZMUWygsPzxFh813cYjqmm3eCHSYQp2oLnSsYvI0bMg-QwRxE/exec', {
//         method: 'POST',
//         body: JSON.stringify(formDataObject),
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     })
//         .then(response => response.json())
//         .then(data => {
//             if (data.result === 'success') {
//                 console.log("Sent successfully")
//                 alert('이메일이 성공적으로 발송되었습니다!');
//             } else {
//                 console.log("Error occurred")
//                 alert('이메일 발송 중 오류가 발생했습니다. 나중에 다시 시도해주세요.');
//             }
//         })
//         .catch(error => {
//             console.error('Error:', error);
//             alert('오류가 발생했습니다. 나중에 다시 시도해주세요.');
//         });
// });

//---------------------------------------------

// (function() {
//     // get all data in form and return object
//     function getFormData(form) {
//         var elements = form.elements;
//         var honeypot;
//
//         var fields = Object.keys(elements).filter(function(k) {
//             if (elements[k].name === "honeypot") {
//                 honeypot = elements[k].value;
//                 return false;
//             }
//             return true;
//         }).map(function(k) {
//             if(elements[k].name !== undefined) {
//                 return elements[k].name;
//                 // special case for Edge's html collection
//             }else if(elements[k].length > 0){
//                 return elements[k].item(0).name;
//             }
//         }).filter(function(item, pos, self) {
//             return self.indexOf(item) == pos && item;
//         });
//
//         var formData = {};
//         fields.forEach(function(name){
//             var element = elements[name];
//
//             // singular form elements just have one value
//             formData[name] = element.value;
//
//             // when our element has multiple items, get their values
//             if (element.length) {
//                 var data = [];
//                 for (var i = 0; i < element.length; i++) {
//                     var item = element.item(i);
//                     if (item.checked || item.selected) {
//                         data.push(item.value);
//                     }
//                 }
//                 formData[name] = data.join(', ');
//             }
//         });
//
//         // add form-specific values into the data
//         formData.formDataNameOrder = JSON.stringify(fields);
//         formData.formGoogleSheetName = form.dataset.sheet || "responses"; // default sheet name
//         formData.formGoogleSendEmail
//             = form.dataset.email || ""; // no email by default
//
//         return {data: formData, honeypot: honeypot};
//     }
//
//     function handleFormSubmit(event) {  // handles form submit without any jquery
//         event.preventDefault();           // we are submitting via xhr below
//         var form = event.target;
//         var formData = getFormData(form);
//         var data = formData.data;
//
//         // If a honeypot field is filled, assume it was done so by a spam bot.
//         if (formData.honeypot) {
//             return false;
//         }
//
//         // disableAllButtons(form);
//         var url = form.action;
//         var xhr = new XMLHttpRequest();
//         xhr.open('POST', url);
//         // xhr.withCredentials = true;
//         xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//         xhr.onreadystatechange = function() {
//             if (xhr.readyState === 4 && xhr.status === 200) {
//                 form.reset();
//                 var formElements = form.querySelector(".form-elements")
//                 if (formElements) {
//                     formElements.style.display = "none"; // hide form
//                 }
//                 var thankYouMessage = form.querySelector(".thankyou_message");
//                 if (thankYouMessage) {
//                     thankYouMessage.style.display = "block";
//                 }
//             }
//         };
//         // url encode form data for sending as post data
//         var encoded = Object.keys(data).map(function(k) {
//             return encodeURIComponent(k) + "=" + encodeURIComponent(data[k]);
//         }).join('&');
//         xhr.send(encoded);
//     }
//
//     function loaded() {
//         // bind to the submit event of our form
//         var forms = document.querySelectorAll("form.gform");
//         for (var i = 0; i < forms.length; i++) {
//             forms[i].addEventListener("submit", handleFormSubmit, false);
//         }
//     };
//     document.addEventListener("DOMContentLoaded", loaded, false);
//
//     function disableAllButtons(form) {
//         var buttons = form.querySelectorAll("button");
//         for (var i = 0; i < buttons.length; i++) {
//             buttons[i].disabled = true;
//         }
//     }
// })();


//-------------------


    function submitForm(event) {
    event.preventDefault(); // 기본 제출 동작 중단

    // 서버로 데이터 전송하는 코드 (여기서는 예시로 alert 사용)
    alert("메시지가 전송되었습니다.");

    // 입력란 비우기
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("subject").value = "";
    document.getElementById("message").value = "";
}
function showMessage() {
    var textContainer = document.querySelector('.text-container');
    var messageBox = document.querySelector('.thankyou_message');

    textContainer.classList.add('active'); // .active 클래스 추가
    messageBox.classList.add('visible');
}