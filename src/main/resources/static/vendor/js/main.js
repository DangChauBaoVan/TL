window.addEventListener("load", function(){
   var modalid=  document.getElementById("modal__layout");
   var modaladdCategory = document.getElementById("modal__layout-addCategory");
   
   var btnupload = document.getElementById("btn-upload");
   var btnupload2 = document.getElementById("upload-2");
   var btnaddCategory = document.getElementById("btn-upload--category");
   $('#checkAllItem').click(function(event) {

   if (this.checked) {
     $(':checkbox').prop('checked', true);
   } else {
     $(':checkbox').prop('checked', false);
   }
 });
 btnaddCategory.addEventListener('click', function() {
  modaladdCategory.style.display = 'flex';
 })
   btnupload.addEventListener('click',function(){
    modalid.style.display='flex' ;
   });
  
  
   var btnclose = document.getElementById("btn-close");
  
   var btnclose2 = document.getElementById("btn-close-cate");
   btnclose.addEventListener('click', closeModal);
   function closeModal() {
    modalid.style.display ='none' ;
   }
   btnclose2.addEventListener('click', closeModalCate);
   function closeModalCate() {
    modaladdCategory.style.display ='none' ;
   }
   btnupload2.addEventListener('click',function(){
    modalid.style.display='flex' ;
   });

 });