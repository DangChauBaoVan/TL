window.addEventListener("load", function(){
  function myScroll() {
    var navbar = document.getElementById("navbar");
    var sticky = navbar.offsetTop;
    if (window.pageYOffset >= sticky) {
    navbar.classList.add("sticky")
    } else {
    navbar.classList.remove("sticky");
    }
  }
  window.onscroll = function() {
    myScroll()
  };
   var modalid=  document.getElementById("modal__layout");
   var btnupload = document.getElementById("btn-upload");
   var btnupload2 = document.getElementById("upload-2");
  var btndelete = document.getElementById('btn-delete');
  var checkall = document.getElementById('checkAllItem');
   $('#checkAllItem').click(function() {
      if (this.checked) {
        console.log('checked');
        btndelete.disabled = false;
        $(':checkbox').prop('checked', true);
      } else {
        btndelete.disabled = true;
        $(':checkbox').prop('checked', false);
      }
  });
 
  //  btnupload.addEventListener('click',function(){
  //   modalid.style.display='flex' ;
  //   document.body.classList.add("open-modal");
  //  });
  
   
  // var btnclose = document.getElementById("btn-close");
  // btnclose.addEventListener('click', closeModal);
  //  function closeModal() {
  //   modalid.style.display ='none' ;
  //   document.body.classList.remove("open-modal");

  //  }

  //  btnupload2.addEventListener('click',function(){
  //   modalid.style.display='flex' ;
  //  });
   

 });