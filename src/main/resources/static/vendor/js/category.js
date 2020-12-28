window.addEventListener("load", function(){
    window.onscroll = function() {
        myScroll()
    };
    
    var navbar = document.getElementById("navbar");
    var sticky = navbar.offsetTop;
    
    function myScroll() {
      if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky")
      } else {
        navbar.classList.remove("sticky");
      }
    }
    var modaladdCategory = document.getElementById("modal__layout-addCategory");
    var btnaddCategory = document.getElementById("btn-upload--category");
    var btnclose2 = document.getElementById("btn-close-cate");
    btnaddCategory.addEventListener('click', function() {
    modaladdCategory.style.display = 'flex';
    document.body.classList.add("open-modal");


    })
    btnclose2.addEventListener('click', closeModalCate);
   function closeModalCate() {
    modaladdCategory.style.display ='none' ;
    document.body.classList.remove("open-modal");

   }

})