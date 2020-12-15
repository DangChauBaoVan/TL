window.addEventListener("load", function(){
   var modalid=document.getElementById("modal__layout");
   
   var btnupload = document.getElementById("btn-upload");
   var btnupload2 = document.getElementById("upload-2");
   
   $('#checkAllItem').click(function(event) {

   if (this.checked) {
     $(':checkbox').prop('checked', true);
   } else {
     $(':checkbox').prop('checked', false);
   }
 });
   btnupload.addEventListener('click',function(){
    modalid.style.display='flex' ;
   });
   btnupload2.addEventListener('click',function(){
    modalid.style.display='flex' ;
   });
  
   var btnclose = document.getElementById("btn-close");
   function closeModal() {
    modalid.style.display ='none' ;
   }
   btnclose.addEventListener('click', closeModal);


 });