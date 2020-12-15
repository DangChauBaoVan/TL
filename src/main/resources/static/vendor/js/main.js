window.addEventListener("load", function(){
   var modalid=  document.getElementById("modal__layout");
   var btnupload = document.getElementById("btn-upload");
   var btnupload2 = document.getElementById("upload-2");
  var btndelete = document.getElementById('btn-delete');

 
   $('#checkAllItem').click(function() {

      if (this.checked) {
        $(':checkbox').prop('checked', true);
      } else {
        $(':checkbox').prop('checked', false);
      }
  });
  if($('#checkAllItem').prop('checked', true)){
    
      btndelete.attributes.disabled = false;
  }
  else {
    btndelete.attributes.disabled = true;
  }

   btnupload.addEventListener('click',function(){
    modalid.style.display='flex' ;
   });
  
  
   var btnclose = document.getElementById("btn-close");
  
 
   btnclose.addEventListener('click', closeModal);
   function closeModal() {
    modalid.style.display ='none' ;
   }
  
   btnupload2.addEventListener('click',function(){
    modalid.style.display='flex' ;
   });

 });