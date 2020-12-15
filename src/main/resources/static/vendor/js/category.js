window.addEventListener("load", function(){
    var modaladdCategory = document.getElementById("modal__layout-addCategory");
    var btnaddCategory = document.getElementById("btn-upload--category");
    var btnclose2 = document.getElementById("btn-close-cate");
    btnaddCategory.addEventListener('click', function() {
    modaladdCategory.style.display = 'flex';
    })
    btnclose2.addEventListener('click', closeModalCate);
   function closeModalCate() {
    modaladdCategory.style.display ='none' ;
   }

})