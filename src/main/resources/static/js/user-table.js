$("#mytable").DataTable();
callContent("manageUser");
var selectedUser = [];

//chọn user
$("#mytable tbody").on("click", "tr", function() {
  if ($(this).hasClass("selected")) {
    $(this).removeClass("selected");
    selectedUser = [];
  } else {
    $("#mytable tr.selected").removeClass("selected");
    $(this).addClass("selected");
    selectedUser = [];
    $(this)
      .find("td")
      .each((index, element) => {
        selectedUser.push($(element).text());
      });
  }
  console.log(selectedUser);
});
//hàm gọi content các trang nhét ajax vào từng case
function callContent(element) {
  $(".generalClass").css("display", "none");
  //gọi thành công thì mở modal lên
  $("#" + element).css("display", "block");
}

function editUser() {
  // if (selectedUser.length > 0) {
  //    var nameUser = selectedUser[1];
  //   alert(nameUser);
  //   $("#idUserEdit").attr('value',selectedUser[0]);
  //   $("#nameUserEdit").attr('value',selectedUser[1]);
  //   $("#phoneUserEdit").attr('value',selectedUser[2]);
  //   $("#emailUserEdit").attr('value',selectedUser[3]);
  //   $("#addressUserEdit").attr('value',selectedUser[4]);
    $("#editUser").modal("show");
    //gọi ajax đổ vào modal updateAdvertise
  // } else {
  //   alert("Please choose user to edit!!!");
  // }
}

function deleteUser() {
  // if (selectedUser.length > 0) {
  //   var nameUser = selectedUser[0];
  //   alert(nameUser);

    $("#deleteUser").modal("show");
    $("#yesdeleteUser").click(function() {
      alert("OK");
      //gọi ajax để xóa
    });
  // } else {
  //   alert("Please choose user to delete!!!");
  // }
}
$("#checkAllItem").click(function(){
  $(".itemimage").prop("checked", $(this).prop("checked"));
})